package com.playbaby.bb.admin.common.entity.vo;

import com.playbaby.bb.admin.common.constant.CommonErrorTypes;
import com.playbaby.bb.admin.common.exception.APIException;
import lombok.Data;

import java.io.Serializable;

/**
 * RESTFUL return type
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS_CODE = CommonErrorTypes.SUCCESS.getCode();
    public static final String SUCCESS_MSG = CommonErrorTypes.SUCCESS.getMessage();
    public static final int FAILED_CODE = 1;
    public static final String FAILED_MSG = "failed";
    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    public static final int ENCRYPT_ERR = 500;
    public static final int DECRYPT_ERR = 501;
    public static final int DECRYPT__METHOD_ERR = 502;
    public static final Result<?> OK = new Result<>("OK", SUCCESS_MSG, SUCCESS_CODE);

    private String msg = SUCCESS_MSG;
    private int code = SUCCESS_CODE;
    private int status = 1;
    private T data;

    @SuppressWarnings("unused")
    public Result() {
        super();
    }

    @SuppressWarnings("unused")
    public Result(T data, ErrorTypeVO errorType){
        this(data, errorType.getMessage(), errorType.getCode());
    }

    @SuppressWarnings("unused")
    public Result(ErrorTypeVO errorType){
        super();
        this.msg = errorType.getMessage();
        this.code = errorType.getCode();
    }

    private Result(T data, String msg, int code) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    @SuppressWarnings("unused")
    private Result(String msg, int code) {
        super();
        this.code = code;
        this.msg = msg;
    }

    @SuppressWarnings("unused")
    private Result(T data) {
        super();
        this.data = data;
    }

    public static Result<?> getInstance(APIException e) {
        return new Result<>(e.getMessage(), e.getCode());
    }

    public static <T> Result<T> of(T t) { return new Result<>(t); }
}
