package pl.akai.bookcrossing.common.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.akai.bookcrossing.common.service.BookRentDuplicated;
import pl.akai.bookcrossing.common.service.IllegalUserException;
import pl.akai.bookcrossing.common.service.InvalidTokenException;

import javax.persistence.EntityNotFoundException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, "Malformed request");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handleIllegalUserException(InvalidTokenException ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(IllegalUserException.class)
    protected ResponseEntity<Object> handleIllegalUserException(IllegalUserException ex) {
        String message = "Access forbidden. Wrong user token";
        ErrorResponse apiError = new ErrorResponse(HttpStatus.FORBIDDEN, message);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String message = String.format("%s with given ID not exists", ex.getMessage());
        ErrorResponse apiError = new ErrorResponse(HttpStatus.NOT_FOUND, message);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(BookRentDuplicated.class)
    protected ResponseEntity<Object> handleBookRentDuplicated(BookRentDuplicated ex) {
        String message = String.format("Book request for book with ID %s already exist for given requester", ex.getMessage());
        ErrorResponse apiError = new ErrorResponse(HttpStatus.CONFLICT, message);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
