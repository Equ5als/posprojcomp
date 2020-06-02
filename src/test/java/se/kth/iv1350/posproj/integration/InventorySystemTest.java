package se.kth.iv1350.posproj.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import se.kth.iv1350.posproj.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class InventorySystemTest {
    private InventorySystem inventorySystem;

    @BeforeEach
    void setUp(){
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    void tearDown() {
        inventorySystem = null;
    }

    @Test
    void retrieveItem() {
        ItemDTO desiredItem = new ItemDTO("Bread", 25, 0.12);
        int desiredItemId = 8;
        try {
            ItemDTO actualItem = inventorySystem.getItem(desiredItemId);
            assertEquals(desiredItem, actualItem, "Inventory product was not the same as the desired one.");
        } catch (Exception exception) {System.out.println(exception.getMessage());}
    }

    @Test
    void retrieveNonExistentItem() {
        assertThrows(NonExistentItemException.class, () -> inventorySystem.getItem(50),
                "The item should not be found in the database.");
    }

    @Test
    void connectionTest() {
        assertThrows(DatabaseConnectionException.class, () -> inventorySystem.getItem(100),
                "Connection with the database should fail but it did not.");
    }

}
