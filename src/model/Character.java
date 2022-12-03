package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Character {
    private static int myLives;
    private final String myHeroType;
    private final Question myQuestion;

    public Character(int theLives, String theHeroType, Question question) {
        myHeroType = theHeroType;
        myLives = theLives;
        this.myQuestion = question;
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

//    public void hasLifeline(){
//        if(Inventory.getMyHintpassCount() == 0) {
//            System.out.println("You do not have any hint passes available.");
//        } else {
//            System.out.println("You have a hintpass!");
//            System.out.println("Splitting your choices 50/50...");
//            System.out.println("\n");
//            useHintpass(qu);
//            Inventory.myHintpassCount--;
//        }
//    }

    public ArrayList<String> useHintpass(Question question) {
        String Myanswer = question.getMyAnswer();
        ArrayList<String> answers = question.setChoices();
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(Myanswer);
        answers.remove(Myanswer);
        tempArray.add(answers.get(0));
        Collections.shuffle(tempArray);
        Inventory.myHintpassCount--;
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
            Inventory.myHintpassCount = myHintpassCount;
            return myHintpassCount;
        }

        public static int setMyKeyCount(final int myKeyCount) {
            Inventory.myKeyCount = myKeyCount;
            return myKeyCount;
        }

        public String toString() {
            return ("Key count: " + myKeyCount + ", " + "Hint pass count: " + myHintpassCount);
        }
    }
}
