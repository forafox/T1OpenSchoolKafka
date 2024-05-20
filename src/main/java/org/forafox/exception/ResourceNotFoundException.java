package org.forafox.exception;

import org.forafox.annotation.Throw;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}