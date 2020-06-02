package se.kth.iv1350.posproj.model;

import se.kth.iv1350.posproj.dto.ItemDTO;

/**
 * Represents the product bought in the sale and the quantity of the item.
 */
public class ItemBought {
    private final ItemDTO item;
    private int itemQuantity;

    /**
     * Create an object with the proper quantity.
     * @param product that is being added to the purchase.
     */
    public ItemBought(ItemDTO product, int itemQuantity) {
        this.item = product;
        this.itemQuantity = itemQuantity;
    }

    /**
     * Get the product added.
     * @return ItemDTO which have all the information of the product.
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * Get how many of the same item was added to the sale.
     * @return number with the quantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Update the quantity of the item added to be increased by one.
     */
    public void updateItemQuantity() {
        this.itemQuantity++;
    }

    /**
     * Make it possible to compare two ItemBought classes together by comparing their attribute.
     * @param object type that can be anything but in our case it would be <code>ItemBought</code>.
     * @return boolean whether the objects are the same or not.
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass()) {
            ItemBought compareItem = (ItemBought) obj;
            if(this.item.getItemDescription().equals(compareItem.getItem().getItemDescription()) &&
                    this.item.getItemPrice() == compareItem.getItem().getItemPrice() &&
                    this.item. getVatRate() == compareItem.getItem().getVatRate() &&
                    this.itemQuantity == compareItem.getItemQuantity()){
                return true;
            }
        }
        return false;
    }
}
