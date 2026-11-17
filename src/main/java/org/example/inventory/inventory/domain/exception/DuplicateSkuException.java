package org.example.inventory.inventory.domain.exception;

public class DuplicateSkuException extends RuntimeException {

    public DuplicateSkuException(String sku) {
        super("An inventory item already exists with SKU: " + sku);
    }
}
