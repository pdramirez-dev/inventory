package org.example.inventory.inventory.application.command;

public record CreateInventoryItemCommand(String sku, String name, int initialQuantity) {
}
