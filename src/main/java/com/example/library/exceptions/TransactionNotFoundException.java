package com.example.library.exceptions;

import java.util.logging.Logger;

public class TransactionNotFoundException extends RuntimeException{
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public TransactionNotFoundException(int id){
        super("Transaction with ID not found: "+id);
        LOGGER.severe("Transaction with ID not found: "+id);
    }
}
