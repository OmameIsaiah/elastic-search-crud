package com.elastic.search.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {
    private String message;
    private String code;
    private Object data;
    private Object meta;

    public ApiResponse() {
    }

    public ApiResponse(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ApiResponse(String message, String code, Object data, Object meta) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.meta = meta;
    }
}
