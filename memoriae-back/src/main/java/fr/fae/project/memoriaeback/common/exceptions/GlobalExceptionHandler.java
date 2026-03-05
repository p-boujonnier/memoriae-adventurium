package fr.fae.project.memoriaeback.common.exceptions;

import fr.fae.project.memoriaeback.account.user.application.common.ServiceResponse;
import fr.fae.project.memoriaeback.common.exceptions.dtos.FieldValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse<List<FieldValidationError>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<FieldValidationError> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new FieldValidationError(
                        e.getField(),
                        Objects.requireNonNullElse(e.getDefaultMessage(), "Invalid field")
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ServiceResponse<>("2200", "Validation error", errors));
    }
}
