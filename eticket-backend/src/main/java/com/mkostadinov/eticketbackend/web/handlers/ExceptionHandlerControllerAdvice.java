package com.mkostadinov.eticketbackend.web.handlers;

import com.mkostadinov.eticketbackend.exception.authorization.InvalidAccessTokenException;
import com.mkostadinov.eticketbackend.exception.authorization.UnauthorizedUserException;
import com.mkostadinov.eticketbackend.exception.payment.PaymentFailedException;
import com.mkostadinov.eticketbackend.exception.payment.PaymentUnableToProceedException;
import com.mkostadinov.eticketbackend.exception.user.UserAlreadyExistException;
import com.mkostadinov.eticketbackend.exception.user.UserNotFoundException;
import com.mkostadinov.eticketbackend.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({UnauthorizedUserException.class, NoHandlerFoundException.class})
    public ModelAndView userIsNotAuthorized() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        return modelAndView;
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({UserAlreadyExistException.class, IllegalArgumentException.class, PaymentUnableToProceedException.class, PaymentFailedException.class})
    public ResponseEntity<ErrorResponse> badRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({InvalidBearerTokenException.class})
    public ResponseEntity<ErrorResponse> invalidBearerTokenException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid bearer token!"));
    }

    @ExceptionHandler({InvalidAccessTokenException.class})
    public ResponseEntity<ErrorResponse> invalidAccessTokenException(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }


}
