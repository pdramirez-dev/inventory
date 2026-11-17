package org.example.inventory.inventory.adapter.in.web;

import org.example.inventory.inventory.domain.exception.DuplicateSkuException;
import org.example.inventory.inventory.domain.exception.InvalidInventoryItemException;
import org.example.inventory.inventory.domain.exception.InventoryItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InventoryItemNotFoundException.class)
    ProblemDetail handleNotFound(InventoryItemNotFoundException exception) {
        return problem(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(DuplicateSkuException.class)
    ProblemDetail handleDuplicateSku(DuplicateSkuException exception) {
        return problem(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(InvalidInventoryItemException.class)
    ProblemDetail handleInvalidItem(InvalidInventoryItemException exception) {
        return problem(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ProblemDetail problem(HttpStatus status, String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(status.getReasonPhrase());
        return problem;
    }
}
