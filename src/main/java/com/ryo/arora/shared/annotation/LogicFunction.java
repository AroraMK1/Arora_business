package com.ryo.arora.shared.annotation;

import com.ryo.arora.shared.enums.BusinessCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicFunction {
    BusinessCode value();
}