package org.example.inventory.inventory.application.service;

import org.example.inventory.inventory.application.port.in.GetInventoryItemUseCase;
import org.example.inventory.inventory.application.port.out.InventoryItemQueryRepository;
import org.example.inventory.inventory.application.query.GetInventoryItemQuery;
import org.example.inventory.inventory.application.query.InventoryItemView;
import org.example.inventory.inventory.domain.exception.InventoryItemNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetInventoryItemService implements GetInventoryItemUseCase {

    private final InventoryItemQueryRepository repository;

    public GetInventoryItemService(InventoryItemQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryItemView handle(GetInventoryItemQuery query) {
        return repository.findViewById(query.itemId())
                .orElseThrow(() -> new InventoryItemNotFoundException(query.itemId()));
    }
}
