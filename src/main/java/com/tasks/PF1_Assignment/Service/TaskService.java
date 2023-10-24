package com.tasks.PF1_Assignment.Service;
import com.tasks.PF1_Assignment.Exception.TaskAlreadyExistsException;
import com.tasks.PF1_Assignment.Exception.TaskNotFoundException;
import com.tasks.PF1_Assignment.Model.Task;
import com.tasks.PF1_Assignment.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task;
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }
    }

    public Task createTask(Task task) {
        // Check if the task already exists
        if (taskRepository.existsById(task.getId())) {
            throw new TaskAlreadyExistsException("Task with ID " + task.getId() + " already exists");
        }
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            task.setCompletionDate(updatedTask.getCompletionDate());
            Task updateTask = taskRepository.save(task);
            return Optional.of(updateTask);
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }
    }
}
