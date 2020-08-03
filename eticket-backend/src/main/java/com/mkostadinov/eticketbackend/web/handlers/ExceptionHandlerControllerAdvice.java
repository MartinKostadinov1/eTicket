package com.mkostadinov.eticketbackend.web.handlers;

import com.mkostadinov.eticketbackend.exception.authorization.InvalidAccessTokenException;
import com.mkostadinov.eticketbackend.exception.authorization.UnauthorizedUserException;
import com.mkostadinov.eticketbackend.exception.user.UserAlreadyExistException;
import com.mkostadinov.eticketbackend.exception.user.UserNotFoundException;
import com.mkostadinov.eticketbackend.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({UnauthorizedUserException.class})
    public ModelAndView userIsNotAuthorized() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/403");

        return modelAndView;
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({UserAlreadyExistException.class, IllegalArgumentException.class})
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
