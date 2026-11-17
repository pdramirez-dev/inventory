package org.example.inventory.inventory.adapter.out.persistence;

import org.example.inventory.inventory.application.port.out.InventoryItemCommandRepository;
import org.example.inventory.inventory.application.port.out.InventoryItemQueryRepository;
import org.example.inventory.inventory.application.query.InventoryItemView;
import org.example.inventory.inventory.domain.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class InventoryItemPersistenceAdapter implements InventoryItemCommandRepository, InventoryItemQueryRepository {

    private final SpringDataInventoryItemRepository repository;

    public InventoryItemPersistenceAdapter(SpringDataInventoryItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryItem save(InventoryItem item) {
        InventoryItemJpaEntity saved = repository.save(toEntity(item));
        return toDomain(saved);
    }

    @Override
    public Optional<InventoryItem> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySkuIgnoreCase(sku);
    }

    @Override
    public Optional<InventoryItemView> findViewById(UUID id) {
        return repository.findById(id).map(this::toView);
    }

    private InventoryItemJpaEntity toEntity(InventoryItem item) {
        return new InventoryItemJpaEntity(item.id(), item.sku(), item.name(), item.quantity());
    }

    private InventoryItem toDomain(InventoryItemJpaEntity entity) {
        return InventoryItem.reconstitute(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getQuantity()
        );
    }

    private InventoryItemView toView(InventoryItemJpaEntity entity) {
        return new InventoryItemView(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getQuantity()
        );
    }
}
