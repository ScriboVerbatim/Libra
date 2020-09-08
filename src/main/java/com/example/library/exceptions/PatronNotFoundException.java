package com.example.library.exceptions;

import java.util.logging.Logger;

public class PatronNotFoundException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public PatronNotFoundException(Integer id) {
        super("Patron with ID not found: "+id);
        LOGGER.severe("Patron with ID not found: "+id);
    }
}
