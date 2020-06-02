package se.kth.iv1350.posproj.model;

/**
 * An observer that will get notified when the balance of the register is updated.
 */
public interface RegisterObserver {
    /**
     * Register the new balance from the new sale payments.
     * @param balance the new balance after a sale.
     */
    void newSalePayment(double balance);
}
