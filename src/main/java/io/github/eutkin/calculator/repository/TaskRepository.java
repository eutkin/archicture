package io.github.eutkin.calculator.repository;

import io.github.eutkin.calculator.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
