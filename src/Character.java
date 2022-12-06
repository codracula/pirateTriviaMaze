package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Character {

    private int myLives;

    public Character(final int theLives) {
        setLives(myLives);
    }

    private int setLives(final int theLives) {
        if (theLives > 0) {
            myLives = theLives;
            return myLives;
        } else {
            return 0;
        }
    }

    public int getLives() {
        return myLives;
    }

    private void decreaseLives() {
        myLives--;
    }

    private void increaseLives() {
        myLives++;
    }

    private boolean isAlive() {
        return myLives != 0;
    }

    public int getKeyCount() {
        return Model.Character.Inventory.getMyKeyCount();
    }

    public int getHintpassCount() {
        return Model.Character.Inventory.getMyHintpassCount();
    }

    private ArrayList<String> useHintpass(Question question) {
        String Myanswer = question.getMyAnswer();
        ArrayList<String> answers = question.setChoices();
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(Myanswer);
        answers.remove(Myanswer);
        tempArray.add(answers.get(0));
        Collections.shuffle(tempArray);
        Model.Character.Inventory.myHintpassCount--;
        return tempArray;
    }

    static class Inventory {

        private static int myHintpassCount;
        private static int myKeyCount;

        public Inventory(final int theHintpassCount, final int theKeyCount) {
            myHintpassCount = theHintpassCount;
            myKeyCount = theKeyCount;
        }

        static int getMyKeyCount() {
            return myKeyCount;
        }

        static int getMyHintpassCount() {
            return myHintpassCount;
        }

        static int setMyHintpassCount(final int theHintpassCount) {
            if (theHintpassCount >= 0) {
                myHintpassCount = theHintpassCount;
                return myHintpassCount;
            } else {
                return 0;
            }
        }

        static int setMyKeyCount(final int theKeyCount) {
            if (theKeyCount > 0) {
                myKeyCount = theKeyCount;
                return myKeyCount;
            } else {
                return 0;
            }
        }

    }
}
