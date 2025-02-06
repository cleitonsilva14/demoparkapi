package io.dev.demoparkapi.web.exception;

// org.springframework.web.bind.MethodArgumentNotValidException
// org.springframework.dao.DataIntegrityViolationException

import io.dev.demoparkapi.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Exception que a classe vai capturar
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){

        log.error("API Error - ", ex);

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", result));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(RuntimeException ex,
                                                                         HttpServletRequest request){


        log.error("API Error - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));


    }



}
