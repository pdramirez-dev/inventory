package org.example.inventory.inventory.adapter.in.web;

import org.example.inventory.inventory.application.command.AdjustStockCommand;
import org.example.inventory.inventory.application.command.CreateInventoryItemCommand;
import org.example.inventory.inventory.application.port.in.AdjustStockUseCase;
import org.example.inventory.inventory.application.port.in.CreateInventoryItemUseCase;
import org.example.inventory.inventory.application.port.in.GetInventoryItemUseCase;
import org.example.inventory.inventory.application.query.GetInventoryItemQuery;
import org.example.inventory.inventory.application.query.InventoryItemView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/items")
public class InventoryItemController {

    private final CreateInventoryItemUseCase createInventoryItem;
    private final AdjustStockUseCase adjustStock;
    private final GetInventoryItemUseCase getInventoryItem;

    public InventoryItemController(
            CreateInventoryItemUseCase createInventoryItem,
            AdjustStockUseCase adjustStock,
            GetInventoryItemUseCase getInventoryItem
    ) {
        this.createInventoryItem = createInventoryItem;
        this.adjustStock = adjustStock;
        this.getInventoryItem = getInventoryItem;
    }

    @PostMapping
    public ResponseEntity<CreateInventoryItemResponse> create(@RequestBody CreateInventoryItemRequest request) {
        UUID id = createInventoryItem.handle(new CreateInventoryItemCommand(
                request.sku(),
                request.name(),
                request.initialQuantity()
        ));

        return ResponseEntity
                .created(URI.create("/api/inventory/items/" + id))
                .body(new CreateInventoryItemResponse(id));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> adjustStock(
            @PathVariable UUID id,
            @RequestBody AdjustStockRequest request
    ) {
        adjustStock.handle(new AdjustStockCommand(id, request.quantityChange()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public InventoryItemView getById(@PathVariable UUID id) {
        return getInventoryItem.handle(new GetInventoryItemQuery(id));
    }

    public record CreateInventoryItemRequest(String sku, String name, int initialQuantity) {
    }

    public record CreateInventoryItemResponse(UUID id) {
    }

    public record AdjustStockRequest(int quantityChange) {
    }
}
