package com.ryo.arora.shared.enums;

import lombok.Getter;

@Getter
public enum BusinessCode {
    CREATE_USER("createUserHandler"),
    LOGIN_USER("loginHandler"),
    LOGOUT_USER("logoutHandler");

    private final String value;
    BusinessCode(String value){
        this.value = value;
    }
}
