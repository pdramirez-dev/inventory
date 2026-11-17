package org.example.inventory.inventory.domain.exception;

import java.util.UUID;

public class InventoryItemNotFoundException extends RuntimeException {

    public InventoryItemNotFoundException(UUID id) {
        super("Inventory item not found: " + id);
    }
}
