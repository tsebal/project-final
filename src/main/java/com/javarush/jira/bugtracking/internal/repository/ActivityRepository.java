package com.javarush.jira.bugtracking.internal.repository;

import com.javarush.jira.bugtracking.internal.model.Activity;
import com.javarush.jira.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ActivityRepository extends BaseRepository<Activity> {
    @Query(value = "select extract(epoch from (tend.updated - tstart.updated)) \n" +
            "from activity tstart, activity tend \n" +
            "where tstart.task_id = tend.task_id \n" +
            "and tstart.task_id = ?1 \n" +
            "and tstart.status_code = ?2 \n" +
            "and tend.status_code = ?3",
            nativeQuery = true)
    Optional<Long> getTaskDurationSeconds(Long taskId, String startState, String endState);
}
