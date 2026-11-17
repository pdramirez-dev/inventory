# Inventory architecture

The application follows Domain-Driven Design, hexagonal architecture, and CQRS.

## Dependency direction

```text
Web adapter -> input ports -> application services -> domain
                                      |
                                      v
                                output ports
                                      ^
                                      |
                            persistence adapter
```

The domain has no Spring, HTTP, database, or serialization dependencies.

## Package responsibilities

```text
inventory/
  domain/
    model/          Aggregates and business invariants
    exception/      Domain failures
  application/
    command/        Write intentions
    query/          Read intentions and read models
    port/in/        Use cases exposed by the application
    port/out/       Infrastructure contracts required by the application
    service/        Command and query handlers
  adapter/
    in/web/         HTTP controllers and transport DTOs
    out/persistence JPA entities, Spring Data, and port implementations
```

## Rules

1. Controllers translate HTTP data and invoke one input port. They contain no business rules.
2. Commands may change state. Queries never change state.
3. Domain aggregates protect their invariants and do not expose JPA entities.
4. Application services coordinate use cases and transaction boundaries.
5. Infrastructure implements output ports; it never becomes a dependency of the domain.
6. PostgreSQL backs both CQRS sides for now. Separate read storage only when scale or reporting needs justify it.
7. Each new capability should be delivered as a complete vertical slice with domain and application tests.

## Current vertical slice

- `POST /api/inventory/items`: creates an inventory item.
- `PATCH /api/inventory/items/{id}/stock`: adjusts stock while preventing negative quantities.
- `GET /api/inventory/items/{id}`: returns the query-side view.
