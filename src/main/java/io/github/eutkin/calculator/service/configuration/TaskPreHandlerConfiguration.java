package io.github.eutkin.calculator.service.configuration;

import io.github.eutkin.calculator.repository.TaskRepository;
import io.github.eutkin.calculator.service.impl.TaskCounter;
import io.github.eutkin.calculator.service.impl.TaskSaver;
import io.github.eutkin.calculator.service.spi.TaskPreHandler;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Configuration
public class TaskPreHandlerConfiguration {

    @Bean
    @Order(2)
    public TaskPreHandler taskSaver(TaskRepository taskRepository) {
        return new TaskSaver(taskRepository);
    }

    @Bean
    @Order(1)
    public TaskCounter taskCounter(MeterRegistry meterRegistry) {
        return new TaskCounter(meterRegistry);
    }

    @Bean
    @Primary // это аннотация означает, что если есть несколько бинов, реализаций одного интерфейса,
    // то при использовании @Autowired будет внедрен бин, помечененный аннотацией @Primary.
    public TaskPreHandler compositeTaskPreHandler(
            @Autowired(required = false) List<TaskPreHandler> handlers // [taskCounter, taskSaver]
    ) {
        Stream<TaskPreHandler> handlerStream = handlers == null ?
                Stream.empty() : handlers.stream();
        BinaryOperator<TaskPreHandler> operator = (handler, nextHandler) ->
                task -> nextHandler.handle(handler.handle(task));
        // сложный кусок. Здесь мы все обработчики объединяем в
        // одну длинную последовательную цепочку.
        return handlerStream
                .reduce(task -> task, operator);
    }
}
