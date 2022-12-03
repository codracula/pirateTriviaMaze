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

    public Monsters(int theM_A, int theM_B, int theM_C){
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
        for (int i = myM_AmaxCount; i > 0; i--){
            myMList.add(myMonsterType.get(0));
        }
        for (int i = myM_BmaxCount; i > 0; i--){
            myMList.add(myMonsterType.get(1));
        }
        for (int i = myM_CmaxCount; i > 0; i--){
            myMList.add(myMonsterType.get(2));
        }
        Collections.shuffle(myMList);
//        System.out.println(myMList);

    }

    protected List<String> getmList(){
        return myMList;
    }

    protected String getMonsterType(int theType) {
        return (String) myMonsterType.get(theType);
    }

    //set parameter monType to String for now in order to tell which monType
    //probably change in future
    //need to get category information from title screen as well, hardcoded for now
    protected ArrayList<Question> setQuestion(String monType, String category, QuestionDatabase question, int index) {

        switch (monType) {
            case "bandit" -> question.setQuestionList(category, 1);
            case "guard" -> question.setQuestionList(category, 2);
            case "gatekeeper" -> question.setQuestionList(category, 3);
        }
        question.getQuestionList().get(index).toString();

        //asks question and takes in user input
        //probably should move somewhere else later
        ArrayList<String> choices = question.getChoices(index);
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (choices.get(userAnswer - 1).equals(question.getAnswer(index))) {
            System.out.println("Yay, you're correct!");
            //correct++;
        } else {
            System.out.println("Boo, you're wrong!");
            System.out.println("The answer is " + question.getAnswer(index));
            //wrong++;
        }

        //don't remove this tho
        question.removeQuestion(index);
        return question.getQuestionList();
    }

}
