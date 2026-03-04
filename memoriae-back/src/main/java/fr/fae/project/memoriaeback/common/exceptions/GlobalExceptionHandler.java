package fr.fae.project.memoriaeback.common.exceptions;

import fr.fae.project.memoriaeback.account.user.application.common.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex) {
        String rawMessage = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Objects.requireNonNullElse(
                        error.getDefaultMessage(),
                        "2204:Missing required field"))
                .findFirst()
                .orElse("2204:Missing required field");

        String[] parts = rawMessage.split(":", 2);
        String code = parts[0];
        String message = parts.length > 1 ? parts[1] : rawMessage;

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ServiceResponse<>(code, message, null));
    }
}
