package org.example.inventory.inventory.application.port.in;

import org.example.inventory.inventory.application.command.CreateInventoryItemCommand;

import java.util.UUID;

public interface CreateInventoryItemUseCase {

    UUID handle(CreateInventoryItemCommand command);
}
