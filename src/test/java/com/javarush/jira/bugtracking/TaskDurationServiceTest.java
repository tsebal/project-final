package com.javarush.jira.bugtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TaskDurationServiceTest {
    public static final Long EXPECTED_DEVELOP_TIME = 321731L;
    public static final Long EXPECTED_TEST_TIME = 262225L;

    @Autowired
    private TaskDurationService service;

    @Test
    void methodShouldCheckTaskDevelopDuration() {
        Long actualDevelopTime = service.getDevelopTimeInSec(3L);
        Assertions.assertEquals(EXPECTED_DEVELOP_TIME, actualDevelopTime);
    }

    @Test
    void methodShouldCheckTaskTestDuration() {
        Long actualTestTime = service.getTestTimeInSec(3L);
        Assertions.assertEquals(EXPECTED_TEST_TIME, actualTestTime);
    }
}
