package io.github.eutkin.calculator.service.spi;


import io.github.eutkin.calculator.service.exception.ResultHandleException;
import io.github.eutkin.calculator.model.Result;
import io.github.eutkin.calculator.service.view.ResultInfo;
import org.springframework.lang.NonNull;

/**
 * Модуль обработки результата задания
 */
public interface ResultHandler {

    /**
     * Обрабатывает результат расчета задания
     * @param result результат расчета
     * @return сообщение об успешной обработки результата
     * @throws ResultHandleException ошибка при обработке результата
     */
    ResultInfo handle(@NonNull Result result) throws ResultHandleException;

}
