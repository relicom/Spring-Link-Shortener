package com.example.shortlink.exception;

public class LinkParameterException extends Exception {
    public LinkParameterException() {
        super();
    }

    public LinkParameterException(String message) {
        super(message);
    }

    public LinkParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkParameterException(Throwable cause) {
        super(cause);
    }

    protected LinkParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
