package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Steven Le
 */
public final class Character implements Serializable {
    /**
     * Initialize myLives.
     */
    private static int myLives;

    /**
     * Constructor for Character.
     */
    public Character() {
        myLives = 1 + 2;
        setHintpassCount(0);
        setKeyCount(0);
    }

    /**
     * Method to decrease lives when player answers a question wrong.
     */
    public void decreaseLives() {
        myLives--;
    }
    /**
     * Method for the use of hintpass.
     * Splits possible choices 50-50 and returns it.
     * @return tempArray
     * @param question
     */
    public ArrayList<String> useHintpass(final Question question) {
        String answer = question.getMyAnswer();
        ArrayList<String> answers = question.setChoices();
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(answer);
        answers.remove(answer);
        tempArray.add(answers.get(0));
        Collections.shuffle(tempArray);
        Inventory.myHintpassCount--;
        return tempArray;
    }
    /**
     * Boolean method to check if player is still alive.
     * Returns true if player is alive, false if not.
     * @return myLives
     */

    public boolean isAlive() {
        return myLives != 0;
    }
    /**
     * Getter method for lives. Returns amount of lives.
     * @return myLives
     */
    public int getLives() {
        return myLives;
    }
    /**
     * Getter method for hintpass count.
     * Returns amount of hint passes kept inventory.
     * @return Inventory.getMyHintpassCount()
     */
    public int getHintpassCount() {
        return Inventory.getMyHintpassCount();
    }
    /**
     * Setter method for hintpass count.
     * @param theHintpassCount
     */
    public void setHintpassCount(final int theHintpassCount) {
        Inventory.setMyHintpassCount(theHintpassCount);
    }
    /**
     * Getter method for key count.
     * Returns the number of keys kept in inventory.
     * @return Inventory.getMyKeyCount()
     */
    public int getKeyCount() {
        return Inventory.getMyKeyCount();
    }
    /**
     * Setter method for key count.
     * @param theKeyCount
     */
    public void setKeyCount(final int theKeyCount) {
        Inventory.setMyKeyCount(theKeyCount);
    }
    /**
     * Setter method for lives.
     * @param theLive
     */
    public void setMyLives(final int theLive) {
        myLives = theLive;
    }

    static class Inventory {
        /**
         * Initializing hint pass count.
         */
        private static int myHintpassCount;
        /**
         * Initializing key count.
         */
        private static int myKeyCount;

        Inventory(final int theHintpassCount, final int theKeyCount) {
            myHintpassCount = setMyHintpassCount(theHintpassCount);
            myKeyCount = setMyKeyCount(theKeyCount);
        }

        public static int getMyKeyCount() {
            return myKeyCount;
        }
        public static int getMyHintpassCount() {
            return myHintpassCount;
        }

        public static int setMyHintpassCount(final int theHintpassCount) {
            if (getMyHintpassCount() >= 0) {
                myHintpassCount = theHintpassCount;
            } else {
                myHintpassCount = 0;
            }
            return myHintpassCount;
        }

        public static int setMyKeyCount(final int theKeyCount) {
            if (getMyKeyCount() >= 0) {
                myKeyCount = theKeyCount;
            } else {
                myKeyCount = 0;
            }
            return myKeyCount;
        }

        public String toString() {
            return ("Key count: " + myKeyCount + ", "
                    + "Hint pass count: " + myHintpassCount);
        }
    }
}
