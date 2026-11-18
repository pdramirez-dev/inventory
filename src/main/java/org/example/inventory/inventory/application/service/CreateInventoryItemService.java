package org.example.inventory.inventory.application.service;

import org.example.inventory.inventory.application.command.CreateInventoryItemCommand;
import org.example.inventory.inventory.application.mediator.RequestHandler;
import org.example.inventory.inventory.application.port.out.InventoryItemCommandRepository;
import org.example.inventory.inventory.domain.exception.DuplicateSkuException;
import org.example.inventory.inventory.domain.model.InventoryItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateInventoryItemService implements RequestHandler<UUID, CreateInventoryItemCommand> {

    private final InventoryItemCommandRepository repository;

    public CreateInventoryItemService(InventoryItemCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<CreateInventoryItemCommand> requestType() {
        return CreateInventoryItemCommand.class;
    }

    @Override
    @Transactional
    public UUID handle(CreateInventoryItemCommand command) {
        InventoryItem item = InventoryItem.create(
                UUID.randomUUID(),
                command.sku(),
                command.name(),
                command.initialQuantity()
        );

        if (repository.existsBySku(item.sku())) {
            throw new DuplicateSkuException(item.sku());
        }

        return repository.save(item).id();
    }
}
