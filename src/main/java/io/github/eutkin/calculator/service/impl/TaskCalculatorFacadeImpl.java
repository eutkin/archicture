package io.github.eutkin.calculator.service.impl;

import io.github.eutkin.calculator.service.TaskCalculatorFacade;
import io.github.eutkin.calculator.service.exception.CalculateException;
import io.github.eutkin.calculator.service.exception.ResultHandleException;
import io.github.eutkin.calculator.service.exception.TaskHandleException;
import io.github.eutkin.calculator.service.model.Result;
import io.github.eutkin.calculator.service.model.Task;
import io.github.eutkin.calculator.service.spi.ResultHandler;
import io.github.eutkin.calculator.service.spi.TaskCalculator;
import io.github.eutkin.calculator.service.spi.TaskPreHandler;
import io.github.eutkin.calculator.service.view.Feedback;
import io.github.eutkin.calculator.service.view.ResultInfo;

import java.util.function.Function;

public class TaskCalculatorFacadeImpl implements TaskCalculatorFacade {

    private final TaskPreHandler taskPreHandler;

    private final TaskCalculator taskCalculator;

    private final ResultHandler resultHandler;

    public TaskCalculatorFacadeImpl(
            TaskPreHandler taskPreHandler,
            TaskCalculator taskCalculator,
            ResultHandler resultHandler
    ) {
        this.taskPreHandler = taskPreHandler;
        this.taskCalculator = taskCalculator;
        this.resultHandler = resultHandler;
    }


    public Feedback calculate(Task task) {
        try {

            Task handledTask = taskPreHandler.handle(task);
            Result result = taskCalculator.calculate(handledTask);
            ResultInfo resultInfo = resultHandler.handle(result);

            return Feedback.fromResult(resultInfo);
        } catch (CalculateException | TaskHandleException | ResultHandleException e) {
            return Feedback.fromThrowable(e);
        }
    }
}
