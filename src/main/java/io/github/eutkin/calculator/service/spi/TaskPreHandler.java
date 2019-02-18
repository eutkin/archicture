package io.github.eutkin.calculator.service.spi;

import io.github.eutkin.calculator.service.exception.TaskHandleException;
import io.github.eutkin.calculator.service.model.Task;
import org.springframework.lang.NonNull;

public interface TaskPreHandler {

    /**
     * Обработывает задание. Способ обработки зависит от реализации.
     *
     * {@link Task} - модель представления задания
     *
     * @param task задание.
     * @return контейнер для задания. Необходим, если вместо с задачей
     * надо передать дополнительную информацию
     * @throws TaskHandleException ошибка при обработке
     */
    @NonNull
    Task handle(@NonNull Task task) throws TaskHandleException;
}
