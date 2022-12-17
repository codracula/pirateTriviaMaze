package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {
    Character chara = new Character();


    @Test
    void testUseHintpass() {
        Question theQuestion = new Question("Geography",1,"How many continents are there?","6, 7, 8, 5","7");
        ArrayList<String> test = chara.useHintpass(theQuestion);
        assertEquals(test.size(), 2);
    }

    @Test
    void testDecLive() {
        chara.setMyLives(3);
        chara.decreaseLives();
        assertEquals(2, chara.getLives());
    }

    @Test
    void testIsAliveWhenAlive() {
        chara.setMyLives(3);
        assertTrue(chara.isAlive());

    }
    @Test
    void testIsAliveWhenDead() {
        chara.setMyLives(0);
        assertFalse(chara.isAlive());
    }

    @Test
    void testGetHintpassCount() {
        chara.setHintpassCount(3);
        assertEquals(chara.getHintpassCount(), 3);
    }

    @Test
    void testGetKeyCount() {
        chara.setKeyCount(4);
        assertEquals(chara.getKeyCount(), 4);
    }

    @Test
    void testSetKeycount() {
        chara.setKeyCount(4);
        assertEquals(chara.getKeyCount(), 4);
        chara.setKeyCount(3);
        assertEquals(chara.getKeyCount(), 3);
        chara.setKeyCount(2);
        assertEquals(chara.getKeyCount(), 2);
    }
    @Test
    void testSetMyLives() {
        chara.setMyLives(4);
        assertEquals(chara.getLives(), 4);
        chara.setMyLives(3);
        assertEquals(chara.getLives(), 3);
        chara.setMyLives(2);
        assertEquals(chara.getLives(), 2);


    }
}
