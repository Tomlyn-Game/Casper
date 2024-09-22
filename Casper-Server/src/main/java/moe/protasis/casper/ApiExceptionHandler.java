package moe.protasis.casper;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.exception.APIException;
import moe.protasis.casper.response.StandardAPIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> OnException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(new StandardAPIResponse(1, "server error"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> OnBadRequestException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(new StandardAPIResponse(2, "invalid request"));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> OnServletException(ServletException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(400).body(new StandardAPIResponse(3, "invalid request"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> OnNoHandlerFoundException() {
        return ResponseEntity.status(404).body(new StandardAPIResponse(15, "not found"));
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> OnStandardException(APIException ex) {
        return ResponseEntity.status(ex.getCode()).body(new StandardAPIResponse(ex.getStatusCode(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> OnMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(400).body(new StandardAPIResponse(15, "invalid request"));
    }
}
