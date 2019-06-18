package com.example.shortlink.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalServiceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView errorHandler(HttpServletRequest req, Throwable ex) {
        ModelAndView model = new ModelAndView("404");
        model.addObject("url", req.getRequestURI());
        model.addObject("ex", ex.getMessage());
        return model;
    }
}
