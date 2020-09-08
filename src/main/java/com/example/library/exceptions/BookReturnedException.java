package com.example.library.exceptions;

import java.util.logging.Logger;

public class BookReturnedException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public BookReturnedException(int id) {
        super("Book unit with ID already returned: "+id);
        LOGGER.severe("Book unit with ID already returned: "+id);
    }
}
