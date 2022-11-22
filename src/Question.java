import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

    final private String myCategory;
    final private int myDifficulty;
    final private String myQuestion;
    final private String myChoices;
    final private String myAnswer;

    public Question(final String theCategory, final int theDifficulty, final String theQuestion, final String theChoices, final String theAnswer){
        myCategory = theCategory;
        myDifficulty = theDifficulty;
        myQuestion = theQuestion;
        myChoices = theChoices;
        myAnswer = theAnswer;
    }

    public String getMyCategory() {
        return myCategory;
    }

    public int getMyDifficulty() {
        return myDifficulty;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    //converts String MyChoices to an Array List and returns it
    public ArrayList<String> setChoices() {
        //String mySplitChoices = myChoices.replaceAll("\\s", "");
        String[] elements = myChoices.split(",");
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }
        List<String> list = Arrays.asList(elements);
        ArrayList<String> choiceArray = new ArrayList<>(list);
        return choiceArray;
    }

    public String getMyChoices() {
        return myChoices;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public String toString() {
        //System.out.println("Category: " + myCategory);
        System.out.println("Difficulty: " + myDifficulty);
        System.out.println("Question: " + myQuestion);
        System.out.println("Choices: ");
        ArrayList<String> choices = setChoices();
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
        //System.out.println("Answer: " + myAnswer);
        System.out.println();
        return null;
    }


}