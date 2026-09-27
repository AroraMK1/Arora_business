package com.ryo.arora.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceDispatcherImpl implements ServiceDispatcher {

    private final GenericService<Object, Object> genericService;

    @Override
    public Object execute(Object request) {
        return genericService.execute(request);
    }
}
