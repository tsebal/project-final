package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.repository.ActivityRepository;
import com.javarush.jira.common.error.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TaskDurationServiceTest {
    private static final Long TASK_ID = 3L;
    private static final Long INVALID_TASK_ID = 5L;
    private static final String STATE_IN_PROGRESS = "in progress";
    private static final String STATE_READY = "ready";
    private static final String STATE_COMPLETED = "completed";
    private static final Long EXPECTED_DEVELOP_TIME = 321731L;
    private static final Long EXPECTED_TEST_TIME = 262225L;

    @MockBean
    private TaskDurationService mockedTaskDurationService;

    @BeforeEach
    void init(@Mock ActivityRepository mockedActivityRepository) {
        this.mockedTaskDurationService = new TaskDurationService(mockedActivityRepository);

        lenient().when(mockedActivityRepository.getTaskDurationSeconds(TASK_ID, STATE_IN_PROGRESS, STATE_READY))
                .thenReturn(Optional.of(EXPECTED_DEVELOP_TIME));
        lenient().when(mockedActivityRepository.getTaskDurationSeconds(TASK_ID, STATE_READY, STATE_COMPLETED))
                .thenReturn(Optional.of(EXPECTED_TEST_TIME));
    }

    @Test
    void methodShouldCheckTaskDevelopDuration() {
        Long actualDevelopTime = mockedTaskDurationService.getDevelopTimeInSec(TASK_ID);

        Assertions.assertEquals(EXPECTED_DEVELOP_TIME, actualDevelopTime);
    }

    @Test
    void methodShouldCheckTaskTestDuration() {
        Long actualTestTime = mockedTaskDurationService.getTestTimeInSec(TASK_ID);

        Assertions.assertEquals(EXPECTED_TEST_TIME, actualTestTime);
    }

    @Test
    void methodGetDevelopTimeInSecShouldThrowNotFoundExceptionIfTaskIdIsAbsent() {
        Throwable thrownException = assertThrows(NotFoundException.class,
                () -> mockedTaskDurationService.getDevelopTimeInSec(INVALID_TASK_ID));

        assertNotNull(thrownException.getMessage());
    }

    @Test
    void methodGetTestTimeInSecShouldThrowNotFoundExceptionIfTaskIdIsAbsent() {
        Throwable thrownException = assertThrows(NotFoundException.class,
                () -> mockedTaskDurationService.getTestTimeInSec(INVALID_TASK_ID));

        assertNotNull(thrownException.getMessage());
    }
}
