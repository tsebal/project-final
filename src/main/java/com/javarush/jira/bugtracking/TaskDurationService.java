package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.repository.ActivityRepository;
import com.javarush.jira.common.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskDurationService {
    private static final String STATE_IN_PROGRESS = "in progress";
    private static final String STATE_READY = "ready";
    private static final String STATE_COMPLETED = "completed";

    private ActivityRepository activityRepository;

    public TaskDurationService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Long getDevelopTimeInSec(Long taskId) {
        Optional<Long> readyStateDuration = activityRepository.getTaskDurationSeconds(taskId, STATE_IN_PROGRESS, STATE_READY);

        return readyStateDuration.orElseThrow(() -> new NotFoundException("TaskID#" + taskId + " is not found or not ready"));
    }

    public Long getTestTimeInSec(Long taskId) {
        Optional<Long> completedStateDuration = activityRepository.getTaskDurationSeconds(taskId, STATE_READY, STATE_COMPLETED);

        return completedStateDuration.orElseThrow(() -> new NotFoundException("TaskID#" + taskId + " is not found or not ready"));
    }
}
