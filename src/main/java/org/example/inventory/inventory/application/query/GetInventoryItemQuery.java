package org.example.inventory.inventory.application.query;

import org.example.inventory.inventory.application.mediator.Request;

import java.util.UUID;

public record GetInventoryItemQuery(UUID itemId) implements Request<InventoryItemView> {
}
