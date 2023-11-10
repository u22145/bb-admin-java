package com.playbaby.bb.admin.common.exception;

import lombok.Getter;

@Getter
public class APIException extends Exception {
    private int code;

    private APIException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public static APIException getInstance(int code, String msg) {
        return new APIException(code, msg);
    }

    public String toString() {
        return "code=" + code + ", msg=" + getMessage();
    }
}
