package com.task.service;

import com.task.core.Task;
import com.task.api.request.TaskDTO;
import com.task.db.TaskRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository;

    @Inject
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.findById(id).ifPresent(task -> taskRepository.delete(task.getId()));
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        //task.setDueDate(taskDTO.getDueDate());

        Long taskId = taskRepository.insert(task);
        return taskRepository.findById(taskId).orElseThrow( () -> new RuntimeException("Task could not be created"));
    }
}
