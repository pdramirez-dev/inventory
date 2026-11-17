package org.example.inventory.inventory.domain.exception;

public class InvalidInventoryItemException extends RuntimeException {

    public InvalidInventoryItemException(String message) {
        super(message);
    }
}
