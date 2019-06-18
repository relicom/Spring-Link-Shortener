package com.example.shortlink.exception;

public class VisitorParameterException extends Exception {
    public VisitorParameterException() {
        super();
    }

    public VisitorParameterException(String message) {
        super(message);
    }

    public VisitorParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorParameterException(Throwable cause) {
        super(cause);
    }

    protected VisitorParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
