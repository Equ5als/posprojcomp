package se.kth.iv1350.posproj.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posproj.dto.ItemDTO;
import se.kth.iv1350.posproj.integration.InventorySystem;

public class ItemRegistryTest {
    private ItemRegistry itemRegistry;
    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp(){
        itemRegistry = new ItemRegistry();
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    public void tearDown(){
        itemRegistry = null;
        inventorySystem = null;
    }

    @Test
    void addItemToList() {
        ArrayList<ItemBought> desiredItemList = new ArrayList<ItemBought>();
        desiredItemList.add(new ItemBought(new ItemDTO("Noodles", 8, 0.06),1));
        desiredItemList.add(new ItemBought(new ItemDTO("Eggs", 39, 0.25),1));
        desiredItemList.add(new ItemBought(new ItemDTO("Butter", 40, 0.25),1));
        try {
            itemRegistry.addItem(10, inventorySystem);
            itemRegistry.addItem(3, inventorySystem);
            itemRegistry.addItem(9, inventorySystem);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        ArrayList<ItemBought> actualItemList = itemRegistry.getItemList();
        assertEquals(desiredItemList, actualItemList, "The Lists did not match.");
    }

    @Test
    void checkItemCount() {
        try {
            itemRegistry.addItem(10, inventorySystem);
            itemRegistry.addItem(10, inventorySystem);
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(7, inventorySystem);
        } catch (Exception exception) {System.out.println(exception.getMessage());}
        ArrayList<ItemBought> expectedItemCount = new ArrayList<ItemBought>();
        expectedItemCount.add(new ItemBought(new ItemDTO("Noodles", 8, 0.06),2));
        expectedItemCount.add(new ItemBought(new ItemDTO("Bacon", 30, 0.12),3));
        expectedItemCount.add(new ItemBought(new ItemDTO("Chips", 27, 0.12),1));
        ArrayList<ItemBought> actualItemCount = itemRegistry.getItemList();
        assertEquals(expectedItemCount, actualItemCount, "The item count does not match.");
    }

    @Test
    void showItemList() {
        String[] expectedPrintOutList = new String[] {
                "Bacon X 3\t30.0",
                "Milk X 2\t10.0",
                "Eggs X 1\t39.0"
        };
        try {
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(1, inventorySystem);
            itemRegistry.addItem(3, inventorySystem);
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(5, inventorySystem);
            itemRegistry.addItem(1, inventorySystem);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        String[] actualPrintOutList = itemRegistry.itemRegistryToString();
        boolean result = false;
        for(int i = 0; i < 3; i++) {
            if(!expectedPrintOutList[i].equals(actualPrintOutList[i])) {
                result = false;
                break;
            }
            result = true;
        }
        assertTrue(result, "The two lists print out were different.");
    }

    @Test
    void catchItemNotFound() {
        boolean check = false;
        try{
            itemRegistry.addItem(15, inventorySystem);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            check = true;
        }
        assertTrue(check, "The NonExistentItemException was not caught by the ItemRegistry");
    }

    @Test
    void catchConnectionFailure() {
        boolean check = false;
        try {
            itemRegistry.addItem(100, inventorySystem);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            check = true;
        }
        assertTrue(check, "The DatabaseConnectionException was not caught by the ItemRegistry");
    }
}

