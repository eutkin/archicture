package io.github.eutkin.calculator.service.spi;

import io.github.eutkin.calculator.service.exception.CalculateException;
import io.github.eutkin.calculator.service.model.Result;
import io.github.eutkin.calculator.service.model.Task;
import org.springframework.lang.NonNull;

public interface TaskCalculator {

    /**
     * Расчитывает задание
     * @param task задание
     * @return результат задания
     * @throws CalculateException ошибка при расчете задания
     */
    @NonNull
    Result calculate(@NonNull Task task) throws CalculateException;
}