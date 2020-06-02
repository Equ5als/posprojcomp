package se.kth.iv1350.posproj.view;

import java.util.ArrayList;
import se.kth.iv1350.posproj.model.RegisterObserver;

/**
 * The observer that will watch the Register class.
 */
public class TotalRevenueView implements RegisterObserver {
    private ArrayList<Double> salePayments;

    /**
     * Create the RegisterObserver.
     */
    public TotalRevenueView() {
        this.salePayments = new ArrayList<Double>();
    }

    /**
     * Register the new balance after a sale.
     * @param balance the new balance after a sale.
     */
    @Override
    public void newSalePayment(double balance) {
        salePayments.add(balance);
        printBalanceStatus();
    }

    private void printBalanceStatus() {
        int numberOfSales = salePayments.size();
        double revenue = salePayments.get(salePayments.size() - 1);
        System.out.println("*********\n*Revenue*\n*********");
        System.out.println("Balance: " + revenue + " kr."+ "\n"+ "Number of sale(s) done: " + numberOfSales + ".\n");
    }
}
