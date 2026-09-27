package com.ryo.arora.shared.enums;

import lombok.Getter;

@Getter
public enum BusinessCode {
    CREATE_USER("userServiceHandler");

    private final String value;
    BusinessCode(String value){
        this.value = value;
    }
}
