package com.tasks.PF1_Assignment.Controller;

import static org.junit.jupiter.api.Assertions.*;import com.tasks.PF1_Assignment.Controller.TaskController;
import com.tasks.PF1_Assignment.Model.Task;
import com.tasks.PF1_Assignment.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private TaskController taskController;

    private List<Task> tasks;
    private Task task;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        tasks = Arrays.asList(
                new Task(1L, "Task 1", "Description 1", false, new Date(), null),
                new Task(2L, "Task 2", "Description 2", true, new Date(), new Date())
        );

        task = new Task(3L, "Task 3", "Description 3", false, new Date(), null);
    }

    @Test
    public void shouldGetAllTasks() {
        when(taskService.getAllTasks()).thenReturn(tasks);

        List<Task> response = taskController.getAllTasks();

        assertEquals(tasks, response);
    }
    @Test
    public void shouldGetTaskById() {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(tasks.get(0)));

        ResponseEntity<Task> response = taskController.getTaskById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks.get(0), response.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenGettingTaskByIdWithNonExistingId() {
        when(taskService.getTaskById(3L)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskController.getTaskById(3L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void shouldCreateTask() {
        when(taskService.createTask(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void shouldUpdateTask() {
        when(taskService.updateTask(1L, task)).thenReturn(Optional.of(task));

        ResponseEntity<Task> response = taskController.updateTask(1L, task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingTaskWithNonExistingId() {
        when(taskService.updateTask(3L, task)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskController.updateTask(3L, task);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
