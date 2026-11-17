package org.example.inventory.inventory.application.port.out;

import org.example.inventory.inventory.application.query.InventoryItemView;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemQueryRepository {

    Optional<InventoryItemView> findViewById(UUID id);
}
