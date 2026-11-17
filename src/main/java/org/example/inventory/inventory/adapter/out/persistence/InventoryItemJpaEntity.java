package org.example.inventory.inventory.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "inventory_items")
class InventoryItemJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private int quantity;

    protected InventoryItemJpaEntity() {
    }

    InventoryItemJpaEntity(UUID id, String sku, String name, int quantity) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
    }

    UUID getId() {
        return id;
    }

    String getSku() {
        return sku;
    }

    String getName() {
        return name;
    }

    int getQuantity() {
        return quantity;
    }
}
