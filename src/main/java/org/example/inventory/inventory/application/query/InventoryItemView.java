package org.example.inventory.inventory.application.query;

import java.util.UUID;

public record InventoryItemView(UUID id, String sku, String name, int quantity) {
}
