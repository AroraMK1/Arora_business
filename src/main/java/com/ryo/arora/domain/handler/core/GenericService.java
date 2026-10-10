package com.ryo.arora.domain.handler.core;

public interface GenericService<T, R> {

    void verify(T request);

    R execute(T request);

    void errorHandler(Exception exception);

}
