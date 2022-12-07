package model;

//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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
    private Maze myMaze;
    /**
     *  initialize character class.
     */
    private Character myPlayer;
    /**
     *  initialize question database.
     */
    private QuestionDatabase myQuestions;
    /**
     *  initialize static random.
     */
    private static Random myRandom;
    private final int myMaxKeyCount;
//    private Monster myMon;

    /**
     *  constructor to set values for instance variables.
     */
    public GameModel() {
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
        myPlayer = new Character(myLive, myPlayerClass);// myQuestions.getQuestionSet(getQuestionIndex()));

        //initialize inventory
        gameStart();
    }

    /** Start the game by create a character.
     *
     */
    void gameStart() {

        //if collect all 4 keys spawn the exit
        if (Character.Inventory.getMyHintpassCount() == myMaxKeyCount) {
            myMaze.genExit();
        }

        //if enter the exit then...
        //spawn another town with a gateKeeper, reset key collected, reset room explored.
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
            Character.Inventory.setMyKeyCount(0);
            myMaze = new Maze(4,7, 6,2,1,4);
        }

        //once collected all keys, spawn the exit
        if (Character.Inventory.getMyHintpassCount() == 4) {
            myMaze.genExit();
        }
        //collect another set of keys and enter the exit
        //activate boss fight
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {

            //TODO by Juno
            //activate boss fight question
            //TODO by Steve
            //if win display victorious screen and
            //reset stats (hintpass, live, key, explored room)
        }
    }
    //-----------maze-----------------
    public int getPlayerRow() {
        return myMaze.getRowPos();
    }

    public int getPlayerCol() {
        return myMaze.getColPos();
    }
    //-----------room-----------------
    //check room occupant
    String roomCheck(final int theRow, final int theCol) {
        return myMaze.roomCheckOc(theRow, theCol);
    }
    //remove monster, set empty
    void roomSetEmpty(final int theRow, final int theCol) {
        myMaze.roomSetEmpty(theRow, theCol);
    }
    //reveal room, set visited to true
    void roomSetVisit(final int theRow, final int theCol) {
        myMaze.roomSetVisit(theRow, theCol);
    }
    //remove key from the room
    void roomRemoveKey(final int theRow, final int theCol) {
        myMaze.roomRemoveKey(theRow, theCol);
    }
    //check condition if room has been visited  --> to adjust fog of war
    boolean roomCheckVisited(final int theRow, final int theCol) {
        return myMaze.roomCheckVisit(theRow, theCol);
    }
    //-----------lives and hintpass-----------------
    void decLive() {
        if (myLive > 1) {
            myLive--;
        } else if (myLive == 1) {
            gameOver();
        }

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


    /** method to activate room acitvivity based on the room location of row/col
     *
     * @param theRow the row position
     * @param theCol the col position
     */
    public void roomActivity(final int theRow, final int theCol) {

        if (myMaze.roomHasKey(theRow, theCol)) {
            //Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(theRow, theCol).equals("bandit")) {
            //activate bandit question set
            //ArrayList<Question> banditQ =  myMaze.getMon().setQuestion("bandit", myPlayerClass, myQuestions, getQuestionIndex());
        } else if (myMaze.getOccupant(theRow, theCol).equals("guard")) {
            //activate guard question set
            //ArrayList<Question> guardQ = myMaze.getMon().setQuestion("guard", myPlayerClass, myQuestions, getQuestionIndex());
        } else if (myMaze.getOccupant(theRow, theCol).equals("gateKeeper")) {
            //activate gateKeeper question set
            //ArrayList<Question> gatekeeperQ = myMaze.getMon().setQuestion("gatekeeper", myPlayerClass, myQuestions, getQuestionIndex());
        } else if (myMaze.getOccupant(theRow, theCol).equals("hintPass")) {
            //add hintpass to inventory
            //Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
            //myMaze.setOccupant(theRow, theCol, null);
        }
    }
    //-----------display-----------------
    public String maze2String() {
        return myMaze.m2String();
    }

    //1 is placeholder in getMonsterType
    void getQuestion() {

        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());
    }

    int getQuestionIndex() {
        myRandom = new Random();
        return myRandom.nextInt(myQuestions.getQuestionList().size());
    }

    boolean isCorrect(final String theUserAnswer) {
        final String answer = myQuestions.getAnswer(getQuestionIndex());
        return theUserAnswer.equals(answer);
    }
}
