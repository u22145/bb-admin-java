package com.playbaby.bb.admin.common.constant;

import com.playbaby.bb.admin.common.entity.vo.ErrorTypeVO;
import org.springframework.http.HttpStatus;

/**
 * 常見ErrorType
 */
public class CommonErrorTypes {

    /**
     * SUCCESS
     */
    public static final ErrorTypeVO SUCCESS = new ErrorTypeVO(HttpStatus.OK, 0, "");

    /**
     * default error codes
     */
    public static final ErrorTypeVO INTERNAL_SERVER_ERROR = new ErrorTypeVO(HttpStatus.INTERNAL_SERVER_ERROR, 10000, "Internal server error");
    public static final ErrorTypeVO INVALID_PARAMETER = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10001, "Invalid parameter {}, value {}");
    public static final ErrorTypeVO UNAUTHORIZED = new ErrorTypeVO(HttpStatus.UNAUTHORIZED, 10002, "Unauthorized");
    public static final ErrorTypeVO FORBIDDEN = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10003, "Forbidden");
    public static final ErrorTypeVO RESOURCE_NOT_FOUND = new ErrorTypeVO(HttpStatus.NOT_FOUND, 10004, "Resource not found");
    public static final ErrorTypeVO REQUEST_URI_NOT_FOUND = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10005, "The request uri {} is not found");
    public static final ErrorTypeVO REQUEST_METHOD_NOT_SUPPORTED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10006, "Request method '{}' not supported");
    public static final ErrorTypeVO MISSING_REQUEST_BODY = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10007, "Required request body is missing");
    public static final ErrorTypeVO INTERNAL_INTERFACE_NOT_FOUND = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10008, "internal interface is not found");
    public static final ErrorTypeVO LERINTERNAL_SERVER_ERROR = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10009, "lerinternal server error");

    // common error codes
    public static final ErrorTypeVO WRITE_JSON_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10100, "Write json failed");
    public static final ErrorTypeVO READ_JSON_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10101, "Read json failed");

    public static final ErrorTypeVO ENCODE_BASE64_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10102, "Encode base64 string failed");
    public static final ErrorTypeVO DECODE_BASE64_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10103, "Decode base64 string failed");

    public static final ErrorTypeVO INVOKE_MICROSERVICE_FAILED = new ErrorTypeVO(HttpStatus.INTERNAL_SERVER_ERROR, 10104, "Invoke microservice failed. The api url:{}, request parameters:{}, and the error response:{}");

    public static final ErrorTypeVO GET_DOMAIN_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10105, "Get domain failed");
    public static final ErrorTypeVO ENCRYPT_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10106, "Encrypt failed");
    public static final ErrorTypeVO DECRYPT_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10107, "Decrypt failed");
    public static final ErrorTypeVO COPY_BEAN_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10108, "Copy bean failed");
    public static final ErrorTypeVO CONVERT_ENUM_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10109, "Failed to convert value {} to enum {}");
    public static final ErrorTypeVO CRYPT_FAILED = new ErrorTypeVO(HttpStatus.BAD_REQUEST, 10110, "Crypt failed");

    /**
     * Auth error code
     */
    public static final ErrorTypeVO LOGIN_FAILED = new ErrorTypeVO(HttpStatus.INTERNAL_SERVER_ERROR, 10201, "login failed");
    /**
     * please use CommonErrorTypes.INVALID_TOKEN
     */
    @Deprecated
    public static final ErrorTypeVO AUTH_VERIFICATION_FAILED = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10202, "auth verification failed");
    public static final ErrorTypeVO INVALID_TOKEN = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10203, "invalid token");
    /**
     * please use CommonErrorTypes.INVALID_TOKEN
     */
    @Deprecated
    public static final ErrorTypeVO TOKEN_EXPIRED = new ErrorTypeVO(HttpStatus.FORBIDDEN, 10204, "token expired");

    public static final ErrorTypeVO NO_USER = new ErrorTypeVO(HttpStatus.OK, 10205, "no user");

    public static final ErrorTypeVO NO_PLATFORM = new ErrorTypeVO(HttpStatus.OK, 10206, "no platform");

}
