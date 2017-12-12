package com.keyrus.virtualStore.exception;

import org.apache.log4j.Logger;

/**
 * The class handles the run time exceptions presented in the system.
 * @author Jhonatan Orozco
 * @version 1
 */
public class VirtualStoreException extends Exception {

    private final static Logger logger = Logger.getLogger(VirtualStoreException.class);

    public VirtualStoreException(String arg0) {
        super(arg0);
        logger.error("VirtualStoreException: "+arg0);
    }
}
