package com.ryo.arora.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDataEntity<T> {
    @Builder.Default
    private int code = 1000;
    private String message;
    private T result;
}

