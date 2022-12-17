package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;


public class InventoryTest {

    Character.Inventory inv = new Character.Inventory(3, 4);




    @Test
    void testGetMyKeyCount() {
        assertEquals(inv.getMyKeyCount(), 4);
    }

    @Test
    void testGetMyHintpassCount() {
        assertEquals(inv.getMyHintpassCount(), 3);
    }

    @Test
    void testSetMyHintpassCount() {
        inv.setMyHintpassCount(4);
        assertEquals(inv.getMyHintpassCount(), 4);
        inv.setMyHintpassCount(6);
        assertEquals(inv.getMyHintpassCount(), 6);
    }

    @Test
    void testSetMyKeyCount() {
        inv.setMyKeyCount(2);
        assertEquals(inv.getMyKeyCount(), 2);
        inv.setMyKeyCount(3);
        assertEquals(inv.getMyKeyCount(), 3);

    }

    @Test
    void testToString() {
        int myKeyCount = 4;
        int myHintpassCount = 3;
        assertEquals(inv.toString(), "Key count: " + myKeyCount + ", " + "Hint pass count: " + myHintpassCount);
    }
}
