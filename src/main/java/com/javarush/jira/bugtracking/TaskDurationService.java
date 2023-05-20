package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.repository.ActivityRepository;
import com.javarush.jira.common.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskDurationService {
    public static final String STATE_IN_PROGRESS = "in progress";
    public static final String STATE_READY = "ready";
    public static final String STATE_COMPLETED = "completed";

    private ActivityRepository activityRepository;

    public TaskDurationService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Long getDevelopTimeInSec(Long taskId) {
        Optional<Long> readyStateDuration = activityRepository.getTaskDurationSeconds(taskId, STATE_IN_PROGRESS, STATE_READY);

        if (!readyStateDuration.isPresent()) {
            throw new NotFoundException("TaskID#" + taskId + " is not found or not ready");
        }

        return readyStateDuration.get();
    }

    public Long getTestTimeInSec(Long taskId) {
        Optional<Long> completedStateDuration = activityRepository.getTaskDurationSeconds(taskId, STATE_READY, STATE_COMPLETED);

        if (!completedStateDuration.isPresent()) {
            throw new NotFoundException("TaskID#" + taskId + " is not found or not ready");
        }

        return completedStateDuration.get();
    }
}
