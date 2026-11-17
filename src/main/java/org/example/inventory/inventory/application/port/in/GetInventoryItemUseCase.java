package org.example.inventory.inventory.application.port.in;

import org.example.inventory.inventory.application.query.GetInventoryItemQuery;
import org.example.inventory.inventory.application.query.InventoryItemView;

public interface GetInventoryItemUseCase {

    InventoryItemView handle(GetInventoryItemQuery query);
}
