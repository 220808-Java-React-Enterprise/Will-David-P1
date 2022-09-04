package com.revature.P1.utils.custom_exceptions;

public class InvalidSqlException extends RuntimeException {
    public InvalidSqlException() {
    }

    public InvalidSqlException(String message) {
        super(message);
    }

    public InvalidSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSqlException(Throwable cause) {
        super(cause);
    }

    public InvalidSqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
