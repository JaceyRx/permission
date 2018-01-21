package com.mmall.exception;

/**
 * 自定义异常类
 *
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/20 15:23
 */
public class PermissionException extends RuntimeException{

    public PermissionException() {}

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    protected PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PermissionException(String message) {
        super(message);
    }

}
