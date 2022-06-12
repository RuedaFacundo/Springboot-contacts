package com.folcademy.Api.Exceptions;

public class ErrorMessage {
    private String message;
    private String detail;
    private String code;
    private String path;

    public ErrorMessage(String message, String detail, String code, String path) {
        this.message = message;
        this.detail = detail;
        this.code = code;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public String getCode() {
        return code;
    }

    public String getPath() {
        return path;
    }
}
