package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RoomTest {

    @Test
        /* default */ void testConstructor() {
        Room room = new Room();

        assertEquals(null, room.getOccupant(), "occupant is null");
        assertTrue(room.isEmpty(), "room is not empty");
        assertFalse(room.getVisited(), "room has been visited");
    }

    @Test
        /* default */ void testConstructor2() {
        Room room = new Room();
        room.setOccupant("M");
        assertEquals("M", room.getOccupant(), "not equals");
    }
//    @Test
//        /* default */ void testSetKey() {
//        Room room = new Room();
//        room.setKey(true);
//        assertTrue(room.hasKey(), "myHasKey is false");
//        room.setKey(false);
//        assertFalse(room.hasKey(), "myHasKey is true");
//
//    }
//
//    @Test
//        /* default */ void testHasKey() {
//        Room room = new Room();
//        room.setKey(true);
//        assertTrue(room.hasKey(), "myHasKey is false");
//        room.setKey(false);
//        assertFalse(room.hasKey(), "myHasKey is true");
//    }

    @Test
        /* default */ void testSetEmpty() {
        Room room = new Room();
        room.setOccupant("bandit");
        room.setEmpty();
        assertEquals(null, room.getOccupant(), "has occupant");
        assertTrue(room.isEmpty(), "room isn't empty");
    }

    @Test
        /* default */ void testSetOccupant() {
        Room room = new Room();
        room.setOccupant("bandit");
        assertEquals("bandit", room.getOccupant(), "myOccupant not match");
        assertFalse(room.isEmpty(), "room isn't empty");
    }

    @Test
        /* default */ void testEmpty() {
        Room room = new Room();
        room.setEmpty();
        assertTrue(room.isEmpty(), "room isn't empty");
        room.setOccupant("bandit");
        assertFalse(room.isEmpty(), "room is empty");
    }

    @Test
        /* default */ void testSetVisited() {
        Room room = new Room();
        room.setVisited();
        assertTrue(room.getVisited(), "room isn't visited");
    }

    @Test
        /* default */ void testGetVisited() {
        Room room = new Room();
        assertFalse(room.getVisited(), "room is visited");
        room.setVisited();
        assertTrue(room.getVisited(), "room isn't visited");
    }

    @Test
        /* default */ void testGetOccupant() {
        Room room = new Room();
        room.setOccupant("M");
        assertEquals("M", room.getOccupant(), "myOccupant not match");
    }

    @Test
        /* default */ void testToString() {
        Room room = new Room();
        room.setOccupant("OUT");
        assertEquals("OUT", room.toString(), "toString not match");
    }

}
