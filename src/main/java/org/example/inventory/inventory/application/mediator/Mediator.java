package org.example.inventory.inventory.application.mediator;

/**
 * Entry point for dispatching application commands and queries.
 */
public interface Mediator {

    <R> R send(Request<R> request);
}
