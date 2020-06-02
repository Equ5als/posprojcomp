package se.kth.iv1350.posproj.exception;

/**
 * Base class that the exceptions handled in the inventory system should look like.
 */
public interface InventorySystemException {
    /**
     * The message which will be displayed for the user.
     * @return <code>String</code> with the message.
     */
    public String developerMessage();
}
