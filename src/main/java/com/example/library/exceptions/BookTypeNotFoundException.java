package com.example.library.exceptions;

import java.util.logging.Logger;

public class BookTypeNotFoundException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public BookTypeNotFoundException(String isbn) {
        super("Book type with ISBN not found: "+isbn);
        LOGGER.severe("Book type with ISBN not found: "+isbn);
    }
}
