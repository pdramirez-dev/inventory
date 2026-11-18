package org.example.inventory.inventory.application.mediator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class DefaultMediator implements Mediator {

    private final Map<Class<?>, RequestHandler<?, ?>> handlers;

    public DefaultMediator(List<RequestHandler<?, ?>> requestHandlers) {
        Map<Class<?>, RequestHandler<?, ?>> registeredHandlers = new HashMap<>();

        for (RequestHandler<?, ?> handler : requestHandlers) {
            RequestHandler<?, ?> previous = registeredHandlers.putIfAbsent(handler.requestType(), handler);
            if (previous != null) {
                throw new IllegalStateException(
                        "Multiple handlers registered for request: " + handler.requestType().getName()
                );
            }
        }

        this.handlers = Map.copyOf(registeredHandlers);
    }

    @Override
    public <R> R send(Request<R> request) {
        Objects.requireNonNull(request, "The request is required");

        RequestHandler<?, ?> handler = handlers.get(request.getClass());
        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler registered for request: " + request.getClass().getName()
            );
        }

        return invoke(handler, request);
    }

    @SuppressWarnings("unchecked")
    private <R, T extends Request<R>> R invoke(RequestHandler<?, ?> handler, T request) {
        return ((RequestHandler<R, T>) handler).handle(request);
    }
}
