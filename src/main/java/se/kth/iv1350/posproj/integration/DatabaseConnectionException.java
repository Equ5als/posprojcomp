package se.kth.iv1350.posproj.integration;

import se.kth.iv1350.posproj.exception.InventorySystemException;

/**
 * A simulation for the exception in which the data base can not be reached.
 */
public class DatabaseConnectionException extends java.lang.Exception implements InventorySystemException {
    /**
     * Constructor with the message that will be dedicated for the developer.
     */
    public DatabaseConnectionException() {
        super("Something went wrong when connecting to the database to retrieve information.");
    }

    /**
     * Get the message that will be shown for the developer.
     * @return <code>String</code> carrying the message.
     */
    @Override
    public String developerMessage() {
        return getMessage();
    }
}
