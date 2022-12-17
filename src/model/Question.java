package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Juno L.
 */
public class Question {

    final private String myCategory;
    final private int myDifficulty;
    final private String myQuestion;
    final private String myChoices;
    final private String myAnswer;

    /**
     *
     * @param theCategory
     * @param theDifficulty
     * @param theQuestion
     * @param theChoices
     * @param theAnswer
     */
    public Question(final String theCategory, final int theDifficulty, final String theQuestion, final String theChoices, final String theAnswer){
        myCategory = theCategory;
        myDifficulty = theDifficulty;
        myQuestion = theQuestion;
        myChoices = theChoices;
        myAnswer = theAnswer;
    }

    /**
     * @return myCategory of questions to be asked
     */
    public String getMyCategory() {
        return myCategory;
    }

    /**
     * @return myDifficulty of questions to be asked 1-3
     */
    public int getMyDifficulty() {
        return myDifficulty;
    }

    /**
     * @return the question to be asked
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * converts String of myChoices to an arrayList
     * @return arrayList of myChoice strings separated by commas
     */
    public ArrayList<String> setChoices() {
        String[] elements = myChoices.split(",");
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }
        List<String> list = Arrays.asList(elements);
        ArrayList<String> choiceArray = new ArrayList<>(list);
        return choiceArray;
    }

    /**
     * @return String of choices (4 multiple choice)
     */
    public String getMyChoices() {
        return myChoices;
    }

    /**
     * @return answer to current question
     */
    public String getMyAnswer() {
        return myAnswer;
    }
}