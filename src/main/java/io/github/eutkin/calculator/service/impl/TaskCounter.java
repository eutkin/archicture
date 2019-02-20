package io.github.eutkin.calculator.service.impl;

import io.github.eutkin.calculator.service.exception.TaskHandleException;
import io.github.eutkin.calculator.model.Task;
import io.github.eutkin.calculator.service.spi.TaskPreHandler;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class TaskCounter implements TaskPreHandler {

    private final Counter counter;

    public TaskCounter(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("counter.task");
    }

    @Override
    public Task handle(Task task) throws TaskHandleException {
        counter.increment();
        return task;
    }
}
