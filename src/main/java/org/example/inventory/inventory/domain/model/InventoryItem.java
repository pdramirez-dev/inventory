package org.example.inventory.inventory.domain.model;

import org.example.inventory.inventory.domain.exception.InvalidInventoryItemException;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public final class InventoryItem {

    private final UUID id;
    private final String sku;
    private String name;
    private int quantity;

    private InventoryItem(UUID id, String sku, String name, int quantity) {
        this.id = Objects.requireNonNull(id, "The inventory item id is required");
        this.sku = normalizeSku(sku);
        this.name = requireText(name, "The inventory item name is required");
        if (quantity < 0) {
            throw new InvalidInventoryItemException("The initial quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public static InventoryItem create(UUID id, String sku, String name, int initialQuantity) {
        return new InventoryItem(id, sku, name, initialQuantity);
    }

    public static InventoryItem reconstitute(UUID id, String sku, String name, int quantity) {
        return new InventoryItem(id, sku, name, quantity);
    }

    public void adjustStock(int quantityChange) {
        if (quantityChange == 0) {
            throw new InvalidInventoryItemException("The stock adjustment cannot be zero");
        }

        int resultingQuantity = quantity + quantityChange;
        if (resultingQuantity < 0) {
            throw new InvalidInventoryItemException("The stock adjustment would produce a negative quantity");
        }

        quantity = resultingQuantity;
    }

    private static String normalizeSku(String sku) {
        return requireText(sku, "The SKU is required").toUpperCase(Locale.ROOT);
    }

    private static String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidInventoryItemException(message);
        }
        return value.trim();
    }

    public UUID id() {
        return id;
    }

    public String sku() {
        return sku;
    }

    public String name() {
        return name;
    }

    public int quantity() {
        return quantity;
    }
}
