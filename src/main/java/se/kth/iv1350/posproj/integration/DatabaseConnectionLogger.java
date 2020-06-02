package se.kth.iv1350.posproj.integration;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import se.kth.iv1350.posproj.logger.Logger;

/**
 * The logger responsible to save the log on the data base connection exception.
 */
public class DatabaseConnectionLogger implements Logger {
    private PrintWriter logWriter;
    private DatabaseConnectionException exception;

    /**
     * Initiate the logger and prepare the file to write the data in.
     */
    public DatabaseConnectionLogger() {
        this.exception = new DatabaseConnectionException();
        try {
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Write the log for the developer in the log file.
     */
    @Override
    public void developerLog() {
        exception.printStackTrace(logWriter);
    }
}
