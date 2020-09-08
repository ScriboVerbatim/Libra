package com.example.library.exceptions;

import java.util.logging.Logger;

public class BookUnitBorrowedException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public BookUnitBorrowedException(int id) {
        super("Book unit with ID already borrowed: "+ id);
        LOGGER.severe("Book unit with ID already borrowed: "+ id);

    }
}