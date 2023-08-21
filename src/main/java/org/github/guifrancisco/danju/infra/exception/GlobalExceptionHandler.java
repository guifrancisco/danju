package org.github.guifrancisco.danju.infra.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.github.guifrancisco.danju.domain.dto.DataErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataErrorResponse> handleIllegalArgumentException(IllegalArgumentException e){
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());

        DataErrorResponse error = new DataErrorResponse("Invalid argument", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        DataErrorResponse error = new DataErrorResponse("Validation error", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DataErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        return ResponseEntity.status(404).body(new DataErrorResponse("Entity Not Found", details));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<DataErrorResponse> handleJWTCreationException(JWTCreationException e) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        DataErrorResponse error = new DataErrorResponse("JWT Creation Error", details);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<DataErrorResponse> handleJWTVerificationException(JWTVerificationException e) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        DataErrorResponse error = new DataErrorResponse("JWT Verification Error", details);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}