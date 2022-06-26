package org.rodneyparshall.rightrx.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.constant.ExceptionConstants;
import org.rodneyparshall.rightrx.domain.HttpResponse;
import org.rodneyparshall.rightrx.exception.domain.UserNotFoundException;
import org.rodneyparshall.rightrx.exception.domain.UsernameExistsException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;


//Exception Handling class extended by UserController to throw Exceptions

@RestControllerAdvice
@Slf4j
public class ExceptionHandling {

    //Method to create my custom Http responses using my custom HttpResponse class located in my Domain file.
    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),message.toUpperCase()), httpStatus);

    }

    //You can modify .yml file to Override Whitelabel page Error in browser with custom JSON
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, ExceptionConstants.PAGE_WAS_NOT_FOUND);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException(BadCredentialsException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, ExceptionConstants.INCORRECT_CREDENTIALS);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException(AccessDeniedException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.FORBIDDEN, ExceptionConstants.NOT_ENOUGH_PERMISSION);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<HttpResponse> userExistsException(UsernameExistsException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotAllowedException(HttpRequestMethodNotSupportedException exception){
        log.error(exception.getMessage());
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods().iterator().next());
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(ExceptionConstants.METHOD_IS_NOT_ALLOWED, supportedMethod));
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionConstants.INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> internalIOException(IOException exception){
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
