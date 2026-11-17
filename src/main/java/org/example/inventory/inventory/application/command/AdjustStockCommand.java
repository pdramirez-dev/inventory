package org.example.inventory.inventory.application.command;

import java.util.UUID;

public record AdjustStockCommand(UUID itemId, int quantityChange) {
}
