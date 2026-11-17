package org.example.inventory.inventory.application.port.out;

import org.example.inventory.inventory.domain.model.InventoryItem;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemCommandRepository {

    InventoryItem save(InventoryItem item);

    Optional<InventoryItem> findById(UUID id);

    boolean existsBySku(String sku);
}
