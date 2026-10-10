package com.ryo.arora.domain.handler.core;

import com.ryo.arora.shared.response.ResponseDataEntity;

public interface ServiceDispatcher {

    <R> ResponseDataEntity<R> execute(Object request);
}
