package se.kth.iv1350.posproj.controller;

import se.kth.iv1350.posproj.exception.ActionFailedException;
import se.kth.iv1350.posproj.exception.SystemFailureException;
import se.kth.iv1350.posproj.logger.Logger;
import se.kth.iv1350.posproj.model.*;
import se.kth.iv1350.posproj.integration.*;
import se.kth.iv1350.posproj.dto.*;

import java.util.ArrayList;

/**
 * Responsible to call the appropriate classes and methods to carry out the functionality of the program.
 */
public class Controller {
    private Sale currentSale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private RegisterMoneyHandler registerMoneyHandler;
    private Printer printer;
    private ItemRegistry itemRegistry;
    private SaleMoneyHandler saleMoneyHandler;

    /**
     * Setting up the systems needed to start the software.
     * @param inventorySystem give the ability to get a hold on the items in the store's inventory.
     * @param accountingSystem give the ability to record the process of sale.
     * @param printer to print out the evidence of the sale ({@link ReceiptDTO}).
     * @param registerMoneyHandler Simulate the register device.
     */
    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer, RegisterMoneyHandler registerMoneyHandler) {
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.registerMoneyHandler = registerMoneyHandler;
        this.printer = printer;
    }

    /**
     * Marks a start for a new sale, beside preparing the components that manages the sale.
     */
    public void startNewSale() {
        this.itemRegistry = new ItemRegistry();
        this.saleMoneyHandler = new SaleMoneyHandler();
        this.currentSale = new Sale();
    }

    /**
     * add the <code>product</code> to the sale. if the <code>product</code> is not existed,
     * an invalid message will be displayed otherwise the information about the added item will be displayed.
     * @param itemId is needed to be able to retrieve the desired <code>product</code> information from the database.
     * @return ItemDTO which carries the product information.
     * @throws ActionFailedException will notify the view about an action failure.
     * @throws SystemFailureException will notify the view if a system in not functioning properly.
     */
    public ItemDTO registerItem(int itemId) throws ActionFailedException, SystemFailureException {
        try {
            ItemDTO product = this.itemRegistry.addItem(itemId, inventorySystem);
            currentSale.updateSaleStatus(itemRegistry, saleMoneyHandler);
            return product;
        } catch (NonExistentItemException exception) {
            throw new ActionFailedException();
        } catch (DatabaseConnectionException exception) {
            throw new SystemFailureException();
        }
    }

    /**
     * Marks the end of the current sale and wraps up the payment.
     * @param payment is amount paid by the customer.
     */
    public void enterPayment(double payment) {
        currentSale.endSale(payment, this.registerMoneyHandler, this.inventorySystem, this.accountingSystem, this.printer);
    }

    /**
     * Get the running total cost of the current sale.
     * @return amount of the current cost.
     */
    public double getTotalPriceWithVat() {
        double totalPrice = this.saleMoneyHandler.getTotalPriceWithVat(this.itemRegistry);
        return totalPrice;
    }

    /**
     * Adds the observer to the right class.
     * @param newObserver the observer class based on the RegisterObserver.
     */
    public void addRegisterObserver(RegisterObserver newObserver) {
        this.registerMoneyHandler.addNewObserver(newObserver);
    }
}