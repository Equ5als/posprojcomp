package se.kth.iv1350.posproj.model;

import java.util.ArrayList;

import se.kth.iv1350.posproj.exception.ActionFailedException;
import se.kth.iv1350.posproj.integration.*;
import se.kth.iv1350.posproj.dto.ItemDTO;
import se.kth.iv1350.posproj.logger.Logger;

/**
 * Responsible to hold the purchased products.
 */
public class ItemRegistry {
    private ArrayList<ItemBought> itemList;
    private Logger logger;

    /**
     * Create the item registry.
     */
    public ItemRegistry() {
        itemList = new ArrayList<ItemBought>();
        this.logger = null;
    }

    /**
     * Add the product entered with an identifier to the sale item list.
     * @param itemId unique number related to a certain product.
     * @param inventorySystem retrieve the information about the existing products in stock.
     * @return <code>product</code> which is the item added if found otherwise it throws an exception
     * @throws DatabaseConnectionException which indicates that the inventory system holding the items can not be reached.
     * @throws NonExistentItemException which indicates that the item could not be found in the stock.
     */
    public ItemDTO addItem(int itemId, InventorySystem inventorySystem) throws NonExistentItemException,
            DatabaseConnectionException {
        try {
            ItemDTO product = inventorySystem.getItem(itemId);
            if (itemList.size() > 0) {
                boolean itemAlreadyAdded = false;
                for (int i = 0; i < itemList.size(); i++) {
                    if (product.equals(this.itemList.get(i).getItem())) {
                        this.itemList.get(i).updateItemQuantity();
                        itemAlreadyAdded = true;
                        break;
                    }
                }
                if (!itemAlreadyAdded) {
                    this.itemList.add(new ItemBought(product, 1));
                }
            } else {
                this.itemList.add(new ItemBought(product,1));
            }
            return product;
        } catch (NonExistentItemException exception) {
            this.logger = new NonExistentItemLogger();
            this.logger.developerLog();
            throw new NonExistentItemException();
        }
        catch (DatabaseConnectionException exception) {
            this.logger = new DatabaseConnectionLogger();
            this.logger.developerLog();
            throw new DatabaseConnectionException();
        }
    }

    /**
     * Method to return an attribute {@link itemList}.
     * @return a list of items that is an attribute.
     */
    public ArrayList<ItemBought> getItemList() {
        return this.itemList;
    }

    /**
     * The show the items purchased in the sale.
     * @return string array with the products, their count and price.
     */
    public String[] itemRegistryToString() {
        String[] saleItems = new String[this.itemList.size()];
        for(int i = 0; i < saleItems.length; i++) {
            saleItems[i] = itemList.get(i).getItem().getItemDescription() + " X " + itemList.get(i).getItemQuantity()
                    + "\t" + itemList.get(i).getItem().getItemPrice();
        }
        return saleItems;
    }
}