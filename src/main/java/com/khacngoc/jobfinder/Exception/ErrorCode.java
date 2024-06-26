package com.khacngoc.jobfinder.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "Tài khoản không tồn tại", HttpStatus.BAD_REQUEST),
    PASSWORD_VALID(1003, "Mật khẩu phải chứa ít nhất 8 ký tự", HttpStatus.BAD_REQUEST),
    POST_NOT_EXISTED(1005, "Bài viết không tồn tại", HttpStatus.NOT_FOUND),
    CAREER_EXISTED(1007, "Ngành nghề đã tồn tại", HttpStatus.BAD_REQUEST),
    CAREER_NOT_EXISTED(1008, "Ngành nghề không tồn tại", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1006, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1004, "UnAuthenticated", HttpStatus.UNAUTHORIZED);

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
