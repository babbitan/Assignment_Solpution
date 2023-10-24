package com.tasks.PF1_Assignment.Repository;

import com.tasks.PF1_Assignment.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}