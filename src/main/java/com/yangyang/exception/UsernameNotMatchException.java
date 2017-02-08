package com.yangyang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配!")
public class UsernameNotMatchException extends RuntimeException {
    public UsernameNotMatchException() {
        super();
    }

    public UsernameNotMatchException(String message) {
        super(message);
    }

    public UsernameNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotMatchException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
