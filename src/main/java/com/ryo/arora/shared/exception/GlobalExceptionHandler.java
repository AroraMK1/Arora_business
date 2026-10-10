package com.ryo.arora.shared.exception;

import com.ryo.arora.shared.response.ResponseDataEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ResponseDataEntity<?>> handlingRuntimeException(RuntimeException exception){
        return ResponseEntity.badRequest().body(
                ResponseDataEntity.builder()
                        .message(ErrorCode.SOMETHING_WENT_WRONG.getMessage())
                        .code(ErrorCode.SOMETHING_WENT_WRONG.getCode())
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseDataEntity<?>> handlingMethodArgumentNotValidException
            (MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(
                ResponseDataEntity.builder()
                        .message(ErrorCode.METHOD_ARGUMENT_INVALID.getMessage())
                        .code(ErrorCode.METHOD_ARGUMENT_INVALID.getCode())
                        .result(errors)
                        .build()
        );
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseDataEntity<?>> handlingAppException(AppException exception){
        return ResponseEntity.badRequest().body(
                ResponseDataEntity.builder()
                        .message(exception.getErrorCode().getMessage())
                        .code(exception.getErrorCode().getCode())
                        .build()
        );
    }

}
