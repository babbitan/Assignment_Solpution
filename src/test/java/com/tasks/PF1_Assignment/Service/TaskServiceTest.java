package com.tasks.PF1_Assignment.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tasks.PF1_Assignment.Exception.TaskAlreadyExistsException;
import com.tasks.PF1_Assignment.Exception.TaskNotFoundException;
import com.tasks.PF1_Assignment.Model.Task;
import com.tasks.PF1_Assignment.Repository.TaskRepository;
import com.tasks.PF1_Assignment.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> taskList = List.of(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(taskList);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetTaskByIdExistingTask() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(taskId);

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    public void testGetTaskByIdNonExistingTask() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        when(taskRepository.existsById(task.getId())).thenReturn(false);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(task, result);
    }

    @Test
    public void testCreateTaskAlreadyExists() {
        Task task = new Task();
        when(taskRepository.existsById(task.getId())).thenReturn(true);

        assertThrows(TaskAlreadyExistsException.class, () -> taskService.createTask(task));
    }

    @Test
    public void testUpdateTaskExistingTask() {
        Long taskId = 1L;
        Task existingTask = new Task();
        Task updatedTask = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any())).thenReturn(updatedTask);

        Optional<Task> result = taskService.updateTask(taskId, updatedTask);

        assertTrue(result.isPresent());
        assertEquals(updatedTask, result.get());
    }

    @Test
    public void testUpdateTaskNonExistingTask() {
        Long taskId = 1L;
        Task updatedTask = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, updatedTask));
    }
}
