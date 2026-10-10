package com.ryo.arora.shared.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "This user is existed"),
    USER_NOT_EXISTED(1002, "This user is not existed"),
    UNAUTHENTICATED(1003, "This user is not authenticated"),
    INVALID_CREDENTIALS(1004, "Invalid email or password"),

    METHOD_ARGUMENT_INVALID(9998, "Invalid Argument"),
    SOMETHING_WENT_WRONG(9999, "Something went wrong");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

}
