package se.kth.iv1350.posproj.model;

import java.util.ArrayList;

/**
 * Simulate the register device.
 */
public class RegisterMoneyHandler {
    private double registerBalance;
    private ArrayList<RegisterObserver> observers;

    /**
     * Create the register money handler.
     */
    public RegisterMoneyHandler() {
        this.registerBalance = 0;
        this.observers = new ArrayList<RegisterObserver>();
    }

    /**
     * Update the amount of the money in the register.
     * @param newBalance new amount added into the register.
     */
    public void updateRegisterBalance(double newBalance) {
        this.registerBalance += newBalance;
        notifyObservers();
    }

    private void notifyObservers() {
        if(observers.size() > 0) {
            for (RegisterObserver observer : observers) {
                observer.newSalePayment(this.registerBalance);
            }
        }
    }

    /**
     * Set the observers for the class.
     * @param newObserver the observer of type RegisterObserver.
     */
    public void addNewObserver(RegisterObserver newObserver) {
        observers.add(newObserver);
    }
}