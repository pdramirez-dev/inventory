package org.example.inventory.inventory.application.service;

import org.example.inventory.inventory.application.command.AdjustStockCommand;
import org.example.inventory.inventory.application.port.in.AdjustStockUseCase;
import org.example.inventory.inventory.application.port.out.InventoryItemCommandRepository;
import org.example.inventory.inventory.domain.exception.InventoryItemNotFoundException;
import org.example.inventory.inventory.domain.model.InventoryItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdjustStockService implements AdjustStockUseCase {

    private final InventoryItemCommandRepository repository;

    public AdjustStockService(InventoryItemCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void handle(AdjustStockCommand command) {
        InventoryItem item = repository.findById(command.itemId())
                .orElseThrow(() -> new InventoryItemNotFoundException(command.itemId()));

        item.adjustStock(command.quantityChange());
        repository.save(item);
    }
}
