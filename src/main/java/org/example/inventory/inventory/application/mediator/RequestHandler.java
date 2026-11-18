package org.example.inventory.inventory.application.mediator;

/**
 * Handles exactly one request type.
 */
public interface RequestHandler<R, T extends Request<R>> {

    Class<T> requestType();

    R handle(T request);
}
