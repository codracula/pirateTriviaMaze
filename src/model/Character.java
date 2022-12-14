package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Character {
    private static int myLives;
   // private final String myHeroType;

    public Character() {
     //   myHeroType = "ahhh";
        myLives = 3;
        setHintpassCount(0);
        setKeyCount(0);
    }

    public void decreaseLives() {
        myLives--;
    }

    public ArrayList<String> useHintpass(Question question) {
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

    public static boolean isAlive() {
        return myLives != 0;
    }

    public int getLives() {
        return myLives;
    }

    public int getHintpassCount() {
        return Inventory.getMyHintpassCount();
    }

    public void setHintpassCount(final int theHintpassCount) {
        Inventory.setMyHintpassCount(theHintpassCount);
    }

    public int getKeyCount() {
        return Inventory.getMyKeyCount();
    }

    public void setKeyCount(final int theKeyCount) {
        Inventory.setMyKeyCount(theKeyCount);
    }

    public void setMyLives(final int theLive) {
        myLives = theLive;
    }

    static class Inventory {
        private static int myHintpassCount; //how many passes does the player start with
        private static int myKeyCount; //how many keys does the player start with

        public Inventory(final int theHintpassCount, final int theKeyCount){
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
            return ("Key count: " + myKeyCount + ", " + "Hint pass count: " + myHintpassCount);
        }
    }
}