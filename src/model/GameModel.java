package model;

import java.util.ArrayList;
import java.util.Random;
import view.*;

/**
 *  Model class for MVC
 * @author Jeep Naarkom
 */
public final class GameModel {

    /**
     *  initialize maze class.
     */
    public Maze myMaze;
    /**
     *  initialize character class.
     */
    public Character myPlayer;
    /**
     *  initialize question database.
     */
    private QuestionDatabase myQuestions;
    /**
     *  initialize static random.
     */
    private static Random myRandom;
    /**
     *  variable for total key count to populate the maze.
     */
    private final int myMaxKeyCount;
    /**
     *  bandits list of questions.
     */
    private ArrayList<Question> banditQ;
    /**
     *  guard's list of questions.
     */
    private ArrayList<Question> guardQ;
    /**
     *  gatekeeper's list of questions
     */
    private ArrayList<Question> gatekeeperQ;
    /**
     *  game view.
     */
    private final GameView myView;

    /**
     *  constructor to set values for instance variables.
     * @param theView   view model.
     */
    public GameModel(GameView theView) {
        myView = theView;
        myMaxKeyCount = 4;
        final int mazeRow = 4;
        final int mazeCol = 7;
        final int monsterA_count = 6;
        final int monsterB_count = 2;
        final int monsterC_count = 0;
        final int keyCount = 4;

        myMaze = new Maze(mazeRow, mazeCol, monsterA_count, monsterB_count, monsterC_count, keyCount);
        myPlayer = new Character();
    }

    /**
     *  get Maze
     * @return  Maze
     */
    //-----------maze-----------------
    public Maze getMyMaze() {
        return myMaze;
    }

    /**
     *  generate exit.
     */
    public void getExit() {
        myMaze.genExit();
    }

    /**
     *  get player row position
     * @return row position
     */
    public int getPlayerRow() {
        return myMaze.getRowPos();
    }

    /**
     *  get player col position.
     * @return  col position
     */
    public int getPlayerCol() {
        return myMaze.getColPos();
    }

    /**
     *  getMyPlayer.
     * @return  myPlayer.
     */
    //-----------player info-----------------
    public Character getMyPlayer() {
        return myPlayer;
    }

    /**
     *  decrease live.
     */
    public void decMyLive() {
        if (myPlayer.getLives() == 1) {
            myPlayer.decreaseLives();
            myView.showGameOver(myPlayer);
        } else {
            myPlayer.decreaseLives();
        }
    }

    /**
     *  set current position.
     */
    //-----------player control/direction-----------------
    public void setCurrentP() {
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }

    /**
     *  move left.
     */
    public void moveLeft() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() - 1);
    }

    /**
     *  move right.
     */
    public void moveRight() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() + 1);
    }

    /**
     *  move up.
     */
    public void moveUp() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos()-1, myMaze.getColPos());
    }

    /**
     *  move down.
     */
    public void moveDown() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos()+1, myMaze.getColPos());
    }

    /**
     *  method to activate room activity based on the room location of row/col.
     */
    public void roomActivity() {
        if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "K") {
            Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "bandit") {
            //activate bandit question set
            monEncounter(banditQ, "bandit");
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "guard") {
            //activate guard question set
            monEncounter(guardQ, "guard");
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "E") {
            for (int i = 0; i < 3; i++) {
                monEncounter(gatekeeperQ, "gatekeeper");
            }
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
            myPlayer.setMyLives(myPlayer.getLives() + 1);
            for (int i = 0; i < 4; i++) {
                monEncounter(gatekeeperQ, "boss");
            }
        }
    }

    /**
     *  encounter monster.
     * @param theMonQ   monster question list.
     * @param theMonType    monster type.
     */
    private void monEncounter(final ArrayList<Question> theMonQ, final String theMonType) {
        int index = getQuestionIndex(theMonQ);
        Question currentQ = getQuestion(theMonQ);
        int correct = myView.showQuestion(currentQ, myPlayer);
        if (correct == 2) {
            correct = myView.showHintpassUse(currentQ, myPlayer);
            if (correct == 0) {
                decMyLive();
                correct = myView.showHintpassUse(currentQ, myPlayer);
            }
        }
        while (correct == 0) {
            decMyLive();
            correct = myView.showQuestion(currentQ, myPlayer);
        }
        theMonQ.remove(index);
        hintPassChance(theMonType);
    }

    /**
     *  hint pass chance.
     * @param theMonType    monster type.
     */
    private void hintPassChance(final String theMonType) {
        if (myMaze.getMon().hintPassChance(theMonType)) {
            myView.showMonEnd(theMonType);
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
        }
    }

    /**
     *  set question.
     */
    public void setQuestion() {
        myQuestions = new QuestionDatabase();
        myMaze.getMon().setQuestion("bandit", myView.getCategory(), myQuestions);
        banditQ = myQuestions.getQuestionList();
        myMaze.getMon().setQuestion("guard", myView.getCategory(), myQuestions);
        guardQ = myQuestions.getQuestionList();
        myMaze.getMon().setQuestion("gatekeeper", myView.getCategory(), myQuestions);
        gatekeeperQ = myQuestions.getQuestionList();
        System.out.println(banditQ);
        System.out.println(guardQ);
        System.out.println(gatekeeperQ);
    }

    /**
     *  get question
     * @param theMonsterQ   monster question list.
     * @return  question.
     */
    private Question getQuestion(final ArrayList<Question> theMonsterQ) {
        return myMaze.getMon().getQuestion(theMonsterQ);
    }

    /**
     *  get question index.
     * @param monsterQ  monster question list.
     * @return  question index.
     */
    int getQuestionIndex(final ArrayList<Question> monsterQ) {
        myRandom = new Random();
        return myRandom.nextInt(monsterQ.size());
    }

    /**
     *  boss fight.
     * @return  boolean.
     */
    public boolean doBossFight() {
        boolean fight = false;
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
            fight = true;
        }
        return fight;
    }
}