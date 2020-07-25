package com.mkostadinov.eticketbackend.web.handlers;

import com.mkostadinov.eticketbackend.exception.authorization.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({UnauthorizedUserException.class})
    public ModelAndView userIsNotAuthorized() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/403");

        return modelAndView;
    }
}
