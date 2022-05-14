package uz.unicorn.rentme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;
import uz.unicorn.rentme.response.AppErrorDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle500(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDTO<>(
                        new AppErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle400(BadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle404(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {CustomSQLException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleSQL(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(new DataDTO<>(
                new AppErrorDTO(HttpStatus.CONFLICT, e.getMessage(), webRequest)));
    }

}
