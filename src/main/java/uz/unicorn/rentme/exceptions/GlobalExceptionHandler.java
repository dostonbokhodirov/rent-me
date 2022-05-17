package uz.unicorn.rentme.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.unicorn.rentme.response.AppErrorDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleNotFound(NotFoundException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleBadRequest(BadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleBadCredentials(BadCredentialsException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleRuntime(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDTO<>(
                        new AppErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {CustomSQLException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleCustomSQL(CustomSQLException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.CONFLICT, e.getMessage(), webRequest)));
    }

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError errorObject : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) errorObject).getField();
            String message = errorObject.getDefaultMessage();
            errors.put(fieldName, message);
        }
        return new org.springframework.http.ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
