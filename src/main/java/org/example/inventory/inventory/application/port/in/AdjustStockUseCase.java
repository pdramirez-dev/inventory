package org.example.inventory.inventory.application.port.in;

import org.example.inventory.inventory.application.command.AdjustStockCommand;

public interface AdjustStockUseCase {

    void handle(AdjustStockCommand command);
}
