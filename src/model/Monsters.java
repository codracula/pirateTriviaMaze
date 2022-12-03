package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Monsters {
    private List<String> myMonsterType;
    private int myM_AmaxCount;
    private int myM_BmaxCount;
    private int myM_CmaxCount;
    private List myMList;

    public Monsters(final int theM_A, final int theM_B, final int theM_C){
        myM_AmaxCount = theM_A;
        myM_BmaxCount = theM_B;
        myM_CmaxCount = theM_C;
        populateMonster();
        generateList();

    }

    private void populateMonster(){
        myMonsterType = new ArrayList();
        myMonsterType.add("bandit");
        myMonsterType.add("guard");
        myMonsterType.add("gateKeeper");
    }

    private void generateList(){
        myMList = new ArrayList<String>(myMonsterType.size());
        for (int i = myM_AmaxCount; i > 0; i--) {
            myMList.add(myMonsterType.get(0));
        }
        for (int i = myM_BmaxCount; i > 0; i--) {
            myMList.add(myMonsterType.get(1));
        }
        for (int i = myM_CmaxCount; i > 0; i--) {
            myMList.add(myMonsterType.get(2));
        }
        Collections.shuffle(myMList);
//        System.out.println(myMList);

    }

    protected List<String> getmList(){
        return myMList;
    }

    protected String getMonsterType(final int theType) {
        return (String) myMonsterType.get(theType);
    }

    //set parameter monType to String for now in order to tell which monType
    //probably change in future
    //need to get category information from title screen as well, hardcoded for now
    protected ArrayList<Question> setQuestion(final String theMonType, final String theCategory,
                                              final QuestionDatabase theQuestion, final int theIndex) {

        switch (theMonType) {
            case "bandit" -> theQuestion.setQuestionList(theCategory, 1);
            case "guard" -> theQuestion.setQuestionList(theCategory, 2);
            case "gatekeeper" -> theQuestion.setQuestionList(theCategory, 3);
        }
        theQuestion.getQuestionList().get(theIndex).toString();

        //asks question and takes in user input
        //probably should move somewhere else later
        ArrayList<String> choices = theQuestion.getChoices(theIndex);
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (choices.get(userAnswer - 1).equals(theQuestion.getAnswer(theIndex))) {
            System.out.println("Yay, you're correct!");
            //correct++;
        } else {
            System.out.println("Boo, you're wrong!");
            System.out.println("The answer is " + theQuestion.getAnswer(theIndex));
            //wrong++;
        }

        //don't remove this tho
        theQuestion.removeQuestion(theIndex);
        return theQuestion.getQuestionList();
    }

}
