package org.example.inventory.inventory.application.command;

import org.example.inventory.inventory.application.mediator.Request;

import java.util.UUID;

public record CreateInventoryItemCommand(
        String sku,
        String name,
        int initialQuantity
) implements Request<UUID> {
}
