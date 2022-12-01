package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RoomJunitTest {


    @BeforeEach
    public void initEach(){
        //Room room = new Room("monsterA");
    }

    @Test
        /* default */ void testConstructor() {
        Room room = new Room();

        assertEquals(null, room.getOccupant(), "occupant is null");
        assertTrue(room.isEmpty(), "room is not empty");
        assertFalse(room.myIsVisited, "room has been visited");

    }

    @Test
        /* default */ void testConstructor2() {
        Room room = new Room("monsterA");

        assertEquals("monsterA", room.getOccupant(), "not equals");
    }

    @Test
        /* default */ void testSetKey() {
        Room room = new Room();
        room.setKey(true);
        assertTrue(room.myHasKey, "myHasKey is false");
        room.setKey(false);
        assertFalse(room.myHasKey, "myHasKey is true");

    }

    @Test
        /* default */ void testHasKey() {
        Room room = new Room();
        room.setKey(true);
        assertTrue(room.hasKey(), "myHasKey is false");
        room.setKey(false);
        assertFalse(room.hasKey(), "myHasKey is true");
    }

    @Test
        /* default */ void testSetEmpty() {
        Room room = new Room();
        room.setOccupant("bandit");
        room.setEmpty();
        assertEquals(null, room.myOccupant, "has occupant");
        assertTrue(room.myIsEmpty, "room isn't empty");
    }

    @Test
        /* default */ void testSetOccupant() {
        Room room = new Room();
        room.setOccupant("bandit");
        assertEquals("bandit", room.myOccupant, "myOccupant not match");
        assertFalse(room.myIsEmpty, "room isn't empty");
    }

    @Test
        /* default */ void testEmpty() {
        Room room = new Room();
        room.setEmpty();
        assertTrue(room.myIsEmpty, "room isn't empty");
        room.setOccupant("bandit");
        assertFalse(room.myIsEmpty, "room is empty");
    }

    @Test
        /* default */ void testSetVisited() {
        Room room = new Room();
        room.setVisited();
        assertTrue(room.myIsVisited, "room isn't visited");
    }

    @Test
        /* default */ void testGetVisited() {
        Room room = new Room();
        assertFalse(room.myIsVisited, "room is visited");
        room.setVisited();
        assertTrue(room.getVisited(), "room isn't visited");
    }

    @Test
        /* default */ void testGetOccupant() {
        Room room = new Room("monster");
        assertEquals("monster", room.getOccupant(), "myOccupant not match");
    }

    @Test
        /* default */ void testToString() {
        Room room = new Room("monster");
        assertEquals("monster", room.toString(), "toString not match");
    }

}
