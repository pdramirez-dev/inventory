# Inventory architecture

The application follows Domain-Driven Design, hexagonal architecture, and CQRS.

## Dependency direction

```text
Web adapter -> mediator -> request handlers -> domain
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
    mediator/       Request dispatch contract and handler registry
    port/out/       Infrastructure contracts required by the application
    service/        Command and query handlers
  adapter/
    in/web/         HTTP controllers and transport DTOs
    out/persistence JPA entities, Spring Data, and port implementations
```

## Rules

1. Controllers translate HTTP data and send commands or queries through the mediator. They contain no business rules.
2. Each request has exactly one handler; the mediator rejects missing or duplicate registrations.
3. Commands may change state. Queries never change state.
4. Domain aggregates protect their invariants and do not expose JPA entities.
5. Application services are request handlers that coordinate use cases and transaction boundaries.
6. Infrastructure implements output ports; it never becomes a dependency of the domain.
7. PostgreSQL backs both CQRS sides for now. Separate read storage only when scale or reporting needs justify it.
8. Each new capability should be delivered as a complete vertical slice with domain and application tests.

## Current vertical slice

- `POST /api/inventory/items`: creates an inventory item.
- `PATCH /api/inventory/items/{id}/stock`: adjusts stock while preventing negative quantities.
- `GET /api/inventory/items/{id}`: returns the query-side view.
