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

    public ArrayList<String> setChoices() {
        String[] elements = myChoices.split(",");
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
        System.out.println("Category: " + myCategory);
        System.out.println("Difficulty: " + myDifficulty);
        System.out.println("Question: " + myQuestion);
        System.out.println("Choices: ");
        ArrayList<String> choices = setChoices();
        System.out.println("A) " + choices.get(0));
        System.out.println("B) " + choices.get(1));
        System.out.println("C) " + choices.get(2));
        System.out.println("D) " + choices.get(3));
        System.out.println("Answer: " + myAnswer);
        System.out.println();
        return null;
    }


}

