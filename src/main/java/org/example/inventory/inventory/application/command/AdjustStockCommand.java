package org.example.inventory.inventory.application.command;

import org.example.inventory.inventory.application.mediator.Request;

import java.util.UUID;

public record AdjustStockCommand(UUID itemId, int quantityChange) implements Request<Void> {
}
