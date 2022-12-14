package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Monsters {
    private List<String> myMonsterType;
    private int myM_AmaxCount;
    private int myM_BmaxCount;
    private int myM_CmaxCount;
    private List myMList;
    private Random rand = new Random();

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
    }

    List<String> getmList(){
        return myMList;
    }

    void setQuestion(final String theMonType, final String theCategory,
                     final QuestionDatabase theQuestion) {
        switch (theMonType) {
            case "bandit" -> theQuestion.setQuestionList(theCategory, 1);
            case "guard" -> theQuestion.setQuestionList(theCategory, 2);
            case "gatekeeper" -> theQuestion.setQuestionList(theCategory, 3);
        }
        //return theQuestion.getQuestionList();
    }

    public Question getQuestion(final ArrayList<Question> theQuestionList) {
        int random = rand.nextInt(theQuestionList.size());
        return theQuestionList.get(random);
    }

    boolean hintPassChance(final String theMonType) {
        boolean hintPassChance = false;
        switch (theMonType) {
            case "bandit" ->
                    hintPassChance = rand.nextInt(100) <= 100; //10% chance
            case "guard" ->
                    hintPassChance = rand.nextInt(100) <= 20;
            case "gatekeeper" ->
                    hintPassChance = rand.nextInt(100) <= 100;
        }
        return hintPassChance;
    }
}