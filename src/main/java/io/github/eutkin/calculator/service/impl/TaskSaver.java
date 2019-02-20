package io.github.eutkin.calculator.service.impl;

import io.github.eutkin.calculator.repository.TaskRepository;
import io.github.eutkin.calculator.service.exception.TaskHandleException;
import io.github.eutkin.calculator.model.Task;
import io.github.eutkin.calculator.service.spi.TaskPreHandler;

import java.util.Objects;

public class TaskSaver implements TaskPreHandler {

    private final TaskRepository taskRepository;

    public TaskSaver(TaskRepository taskRepository) {
        this.taskRepository = Objects.requireNonNull(taskRepository);
    }

    @Override
    public Task handle(Task task) throws TaskHandleException {
        return taskRepository.save(task);
    }
}
