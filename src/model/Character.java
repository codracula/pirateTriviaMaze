package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Character {
    private static int myLives;
    private final String myHeroType;

    public Character(String theHeroType) {
        myHeroType = theHeroType;
        myLives = 4;
    }

    public String myHeroType() {
        return myHeroType;
    }

    public String HeroType() {
        return myHeroType;
    }

    public void decreaseLives() {
        myLives--;
    }

    public ArrayList<String> useHintpass(Question question) {
        String Myanswer = question.getMyAnswer();
        ArrayList<String> answers = question.setChoices();
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(Myanswer);
        answers.remove(Myanswer);
        tempArray.add(answers.get(0));
        Collections.shuffle(tempArray);
        model.Character.Inventory.myHintpassCount--;
        return tempArray;
    }

    public static boolean isAlive() {
        return myLives != 0;
    }

    public String toString() {
        //return ("Name: " + TitleScreen.getName() + ", " + "Lives: " +
        //myLives + ", " + " Hero type: " + myHeroType);
        return "name";
    }

    public int getLives() {
        return myLives;
    }

    public int getHintpassCount() {
        return model.Character.Inventory.getMyHintpassCount();
    }

    public int getKeyCount() {
        return model.Character.Inventory.getMyKeyCount();
    }

    static class Inventory {
        private static int myHintpassCount; //how many passes does the player start with
        private static int myKeyCount; //how many keys does the player start with

        public Inventory(final int theHintpassCount, final int theKeyCount){
            myHintpassCount = theHintpassCount;
            myKeyCount = theKeyCount;
        }

        public static int getMyKeyCount() {
            return myKeyCount;
        }
        public static int getMyHintpassCount() {
            return myHintpassCount;
        }

        public static int setMyHintpassCount(final int theHintpassCount) {
            model.Character.Inventory.myHintpassCount = myHintpassCount;
            return myHintpassCount;
        }

        public static int setMyKeyCount(final int myKeyCount) {
            model.Character.Inventory.myKeyCount = myKeyCount;
            return myKeyCount;
        }

        public String toString() {
            return ("Key count: " + myKeyCount + ", " + "Hint pass count: " + myHintpassCount);
        }
    }
}