package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *  monsters class to populate monsters
 * @author Jeep Naarkom
 * @author Juno Lee
 */
public class Monsters {
    /**
     *  monster type list.
     */
    private List<String> myMonsterType;
    /**
     *  monster A max count.
     */
    private int myM_AmaxCount;
    /**
     *  monster B max count.
     */
    private int myM_BmaxCount;
    /**
     *  monster c max count.
     */
    private int myM_CmaxCount;
    /**
     *  monster list.
     */
    private List myMList;
    /**
     *  static random.
     */
    private static Random myRand;

    /**
     *  constructor
     * @param theM_A    # of monster A to generate.
     * @param theM_B    # of monster B to generate.
     * @param theM_C    # of monster C to generate.
     */
    public Monsters(final int theM_A, final int theM_B, final int theM_C){
        myRand = new Random();
        myM_AmaxCount = theM_A;
        myM_BmaxCount = theM_B;
        myM_CmaxCount = theM_C;
        populateMonster();
        generateList();
    }

    /**
     *  populate monsters.
     */
    private void populateMonster(){
        myMonsterType = new ArrayList();
        myMonsterType.add("bandit");
        myMonsterType.add("guard");
        myMonsterType.add("gateKeeper");
    }

    /**
     *  generate all monsters in a list.
     */
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

    /**
     *  get monster list.
     * @return  monster list.
     */
    List<String> getmList(){
        return myMList;
    }

    /**
     *  set question
     * @param theMonType    monster type.
     * @param theCategory   question category.
     * @param theQuestion   question database.
     */
    void setQuestion(final String theMonType, final String theCategory,
                                    final QuestionDatabase theQuestion) {
        switch (theMonType) {
            case "bandit" -> theQuestion.setQuestionList(theCategory, 1);
            case "guard" -> theQuestion.setQuestionList(theCategory, 2);
            case "gatekeeper" -> theQuestion.setQuestionList(theCategory, 3);
        }
    }

    /**
     *  get question
     * @param theQuestionList   question list.
     * @return  question.
     */
    public Question getQuestion(final ArrayList<Question> theQuestionList) {
        int random = myRand.nextInt(theQuestionList.size());
        return theQuestionList.get(random);
    }

    /**
     *  hint pass chance
     * @param theMonType    theMonster type.
     * @return  boolean.
     */
    boolean hintPassChance(final String theMonType) {
        boolean hintPassChance = false;
        switch (theMonType) {
            case "bandit" ->
                    hintPassChance = myRand.nextInt(100) <= 100; //10% chance
            case "guard" ->
                    hintPassChance = myRand.nextInt(100) <= 20;
            case "gatekeeper" ->
                    hintPassChance = myRand.nextInt(100) <= 30;
        }
        return hintPassChance;
    }
}