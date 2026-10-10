package com.ryo.arora.domain.handler.core;

import com.ryo.arora.shared.response.ResponseDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceDispatcherImpl implements ServiceDispatcher {

    private final GenericService<Object, Object> genericService;

    @Override
    @SuppressWarnings("unchecked")
    public <R> ResponseDataEntity<R> execute(Object request) {
        return (ResponseDataEntity<R>) genericService.execute(request);
    }
}
