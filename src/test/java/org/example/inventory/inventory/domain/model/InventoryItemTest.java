package org.example.inventory.inventory.domain.model;

import org.example.inventory.inventory.domain.exception.InvalidInventoryItemException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InventoryItemTest {

    @Test
    void adjustsStockWhileProtectingTheAggregateInvariant() {
        InventoryItem item = InventoryItem.create(UUID.randomUUID(), "sku-001", "Keyboard", 10);

        item.adjustStock(-4);

        assertEquals(6, item.quantity());
        assertThrows(InvalidInventoryItemException.class, () -> item.adjustStock(-7));
    }

    @Test
    void normalizesTheSku() {
        InventoryItem item = InventoryItem.create(UUID.randomUUID(), " sku-001 ", "Keyboard", 0);

        assertEquals("SKU-001", item.sku());
    }
}
