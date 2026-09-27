package com.ryo.arora.application.handler;

public interface GenericService<T, R> {

    void verify(T request);

    R execute(T request);

    void errorHandler(Exception exception);

}
