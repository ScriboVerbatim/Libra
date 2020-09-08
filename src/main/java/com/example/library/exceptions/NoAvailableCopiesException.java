package com.example.library.exceptions;

import java.util.logging.Logger;

public class NoAvailableCopiesException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public NoAvailableCopiesException(String isbn) {
        super("Book type with ISBN has no available copies: "+ isbn);
        LOGGER.severe("Book type with ISBN has no available copies: "+ isbn);

    }
}
