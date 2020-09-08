package com.example.library.exceptions;

import java.util.logging.Logger;

public class BookUnitNotFoundException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public BookUnitNotFoundException(int id) {
        super("Book unit with ID not found: "+ id);
        LOGGER.severe("Book unit with ID not found: "+ id);

    }
}
