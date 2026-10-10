package com.ryo.arora.domain.handler.core;

import com.ryo.arora.shared.annotation.LogicFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenericServiceImpl
        implements GenericService<Object, Object> {

    private final ApplicationContext applicationContext;

    @Override
    public Object execute(Object request) {

        GenericService<Object, Object> service = resolveService(request);

        service.verify(request);

        return service.execute(request);
    }

    @SuppressWarnings("unchecked")
    private GenericService<Object, Object> resolveService(
            Object request
    ) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Request must not be null"
            );
        }

        LogicFunction logicFunction = request.getClass().getAnnotation(LogicFunction.class);

        if (logicFunction == null) {
            throw new IllegalArgumentException(
                    "Request "
                            + request.getClass().getSimpleName()
                            + " does not have @LogicFunction"
            );
        }

        String beanName = logicFunction.value().getValue();

        return applicationContext.getBean(
                beanName,
                GenericService.class
        );
    }

    @Override
    public void verify(Object request) {
        // Dispatcher does not contain business validation.
    }

    @Override
    public void errorHandler(Exception exception) {
        // Delegate to GlobalExceptionHandler
    }
}