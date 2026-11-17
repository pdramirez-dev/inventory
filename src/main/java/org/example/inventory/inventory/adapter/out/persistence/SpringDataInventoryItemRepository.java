package org.example.inventory.inventory.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface SpringDataInventoryItemRepository extends JpaRepository<InventoryItemJpaEntity, UUID> {

    boolean existsBySkuIgnoreCase(String sku);
}
