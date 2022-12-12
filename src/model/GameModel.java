package model;

//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import view.*;

public final class GameModel {
    /**
     *  player starting live count.
     */
    private int myLive;
    /**
     *  player name.
     */
    private String myPlayerName;
    /**
     *  player class.
     */
    private String myPlayerClass;
    /**
     *  initialize maze class.
     */
    public Maze myMaze;
    /**
     *  initialize character class.
     */
    public Character myPlayer;///////////////////////////////////////////////made public, is ok???
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
//    private Monster myMon;

    private ArrayList<Question> banditQ;
    private ArrayList<Question> guardQ;
    private ArrayList<Question> gatekeeperQ;
    private GameView myView;

    public int prevCol;
    public int prevRow;

    /**
     *  constructor to set values for instance variables.
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

        //TODO by Steve
        //use getplayer name and getplayer class method to update myPlayerName, myPlayerClass
        //before initialize Character class.

        myMaze = new Maze(mazeRow, mazeCol, monsterA_count, monsterB_count, monsterC_count, keyCount);
        //added by Juno
        myPlayer = new Character(myPlayerClass);
        //initialize inventory
        gameStart();
    }

    /** Start the game set condition to each room.
     *
     */
    private void gameStart() {

        //if collect all 4 keys spawn the exit
        if (Character.Inventory.getMyHintpassCount() == myMaxKeyCount) {
            myMaze.genExit();
        }

        //if enter the exit then...
        //spawn another town with a gateKeeper, reset key collected, reset room explored.
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
            Character.Inventory.setMyKeyCount(0);
            myMaze = new Maze(4, 7, 6, 2, 1, 4);
        }

        //once collected all keys, spawn the exit
        if (Character.Inventory.getMyHintpassCount() == 4) {
            myMaze.genExit();
        }
        //collect another set of keys and enter the exit
        //activate boss fight
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {

            //TODO by Juno
            bossFight();
            //TODO by Steve
            //if win display victorious screen and
            //reset stats (hintpass, live, key, explored room)
        }
    }

    //-----------maze-----------------
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

    public int getMyLive() {
        return myPlayer.getLives();
    }

    void gameOver() {
        //TODO for Steve
        //do something when game over
        //display game over message, then press any keys to continue then restart the game menu
        //reset stats (hintpass, live, key, explored room)
    }

    //-----------player info-----------------
    public void setMyPlayerName(final String theName) {

        myPlayerName = Objects.requireNonNull(theName);
    }

    public void decMyLive() {
        myPlayer.decreaseLives();
    }

    public String getMyPlayerName() {
        return myPlayerName;
    }

    public void setMyPlayerClass(final String theClass) {
        myPlayerClass = Objects.requireNonNull(theClass);
    }

    public String getMyPlayerClass() {
        return myPlayerClass;
    }

    //-----------player control/direction-----------------
    public void setCurrentP() {
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }
    public void moveLeft() {

        if (myMaze.getColPos() == 0){
            throw new IllegalArgumentException("Out of bound");
        }
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() - 1);
        //myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }
    public void moveRight() {
        if (myMaze.getColPos() == myMaze.getMazeCol()-1){
            throw new IllegalArgumentException("Out of bound");
        }
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() + 1);
        //myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }
    public void moveUp() {

        if (myMaze.getRowPos() == 0){
            throw new IllegalArgumentException("Out of bound");
        }
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos()-1, myMaze.getColPos());
        //myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }
    public void moveDown() {
        if (myMaze.getRowPos() == myMaze.getMazeRow()-1){
            throw new IllegalArgumentException("Out of bound");
        }
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos()+1, myMaze.getColPos());
        //myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }

    /** method to activate room acitvivity based on the room location of row/col
     *
     //     * @param theRow the row position
     //     * @param theCol the col position
     //     */
    public void roomActivity() {
        boolean correct;
        int index;
        if (myMaze.roomHasKey(getPlayerRow(), getPlayerCol())) {
            Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()).equals("bandit")) {
            //activate bandit question set
            index = getQuestionIndex(banditQ);
            getQuestion(banditQ);
            correct = myView.showQuestion(myQuestions, index);
            if (!correct) {
                decMyLive();
                myView.showQuestion(myQuestions, index);
            }
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()).equals("guard")) {
            //activate guard question set
            index = getQuestionIndex(guardQ);
            getQuestion(guardQ);
            correct = myView.showQuestion(myQuestions, index);
            if (!correct) {
                decMyLive();
                myView.showQuestion(myQuestions, index);
            }
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()).equals("gateKeeper")) {
            //activate gateKeeper question set
            index = getQuestionIndex(gatekeeperQ);
            getQuestion(gatekeeperQ);
            correct = myView.showQuestion(myQuestions, index);
            if (!correct) {
                decMyLive();
                myView.showQuestion(myQuestions, index);
            }
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()).equals("hintPass")) {
            //add hintpass to inventory
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
            myMaze.setOccupant(getPlayerRow(), getPlayerCol(), null);
        }
    }
    //-----------display-----------------
//    public String maze2String() {
//        return myMaze.m2String();
//    }

    public void setQuestion() {
        myQuestions = new QuestionDatabase();
        banditQ = myMaze.getMon().setQuestion("bandit", myView.getCategory(), myQuestions);
        //banditQ = myQuestions.getQuestionList();
        guardQ = myMaze.getMon().setQuestion("guard", myView.getCategory(), myQuestions);
        //guardQ = myQuestions.getQuestionList();
        gatekeeperQ = myMaze.getMon().setQuestion("gatekeeper", myView.getCategory(), myQuestions);
        //gatekeeperQ = myQuestions.getQuestionList();
    }

    private Question getQuestion(final ArrayList<Question> monsterQ) {
        return myMaze.getMon().getQuestion(monsterQ);
    }

    int getQuestionIndex(final ArrayList<Question> monsterQ) {
        myRandom = new Random();
        return myRandom.nextInt(monsterQ.size());
    }

//    boolean isCorrect(final String theUserAnswer) {
//        final String answer = myQuestions.getAnswer(getQuestionIndex());
//        return theUserAnswer.equals(answer);
//    }

    private void bossFight() {
//        String placeholder="";
//        for (int i = 0; i < 3; i++) {
//            myMaze.getMon().getQuestion(gatekeeperQ); //asking difficult questions
//            if (!isCorrect(placeholder)) {
//                //decLive();
//            }
//        }
    }
}