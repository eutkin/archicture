package io.github.eutkin.calculator.service;

import io.github.eutkin.calculator.service.view.Feedback;
import io.github.eutkin.calculator.service.model.Task;
import org.springframework.lang.NonNull;

/**
 * Калькулятор заданий в целом. Принимает задание на расчет и отдает обратную связь (сообщение)
 */
public interface TaskCalculatorFacade {

    @NonNull
    Feedback calculate(@NonNull Task task);
}
