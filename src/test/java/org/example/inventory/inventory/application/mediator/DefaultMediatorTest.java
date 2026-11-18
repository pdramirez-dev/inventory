package org.example.inventory.inventory.application.mediator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultMediatorTest {

    @Test
    void dispatchesARequestToItsHandler() {
        DefaultMediator mediator = new DefaultMediator(List.of(new GreetingHandler()));

        String response = mediator.send(new GreetingRequest("Inventory"));

        assertEquals("Hello, Inventory", response);
    }

    @Test
    void rejectsRequestsWithoutAHandler() {
        DefaultMediator mediator = new DefaultMediator(List.of());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> mediator.send(new GreetingRequest("Inventory"))
        );

        assertEquals(
                "No handler registered for request: " + GreetingRequest.class.getName(),
                exception.getMessage()
        );
    }

    @Test
    void rejectsMultipleHandlersForTheSameRequest() {
        assertThrows(
                IllegalStateException.class,
                () -> new DefaultMediator(List.of(new GreetingHandler(), new GreetingHandler()))
        );
    }

    private record GreetingRequest(String name) implements Request<String> {
    }

    private static class GreetingHandler implements RequestHandler<String, GreetingRequest> {

        @Override
        public Class<GreetingRequest> requestType() {
            return GreetingRequest.class;
        }

        @Override
        public String handle(GreetingRequest request) {
            return "Hello, " + request.name();
        }
    }
}
