package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class GameModel {

    private int[] myCurrentPlace;
    private int myHintPass;
    private int myLive;
    private String myPlayerName;
    private String myPlayerClass;
    private Maze myMaze;
    private Character myPlayer;
    private QuestionDatabase myQuestions;
    private Monster myMon;

    public GameModel(){

        myMaze = new Maze(4,7, 6,2,0,4);
        //added by Juno
        myPlayer = new Character(myLive, myPlayerClass, myQuestions.getQuestionSet(getQuestionIndex()));
        //initialize inventory

        myHintPass = 0;
        myLive = 3;
        myPlayerName = "";
        myPlayerClass = "";
        gameStart();
    }

    protected void gameStart(){
        //if collect all 4 keys spawn the exit
        if (Character.Inventory.getMyHintpassCount() == 4) {
            myMaze.genExit();
        }

        //if enter the exit then...
        //spawn another town with a gateKeeper, reset key collected, reset room explored.
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()){
            Character.Inventory.setMyKeyCount(0);
            myMaze = new Maze(4,7, 6,2,1,4);
        }

        //once collected all keys, spawn the exit
        if (Character.Inventory.getMyHintpassCount() == 4) {
            myMaze.genExit();
        }
        //collect another set of keys and enter the exit

        //activate boss fight
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()){
            //activate boss fight

            //go back to selection screen whether lose or win, wipe maze,
            //reset stats (hintpass, live, key, explored room)

        }


    }
    //-----------maze-----------------
    protected int getPlayerRow(){
        return myMaze.getRowPos();
    }

    protected int getPlayerCol(){
        return myMaze.getColPos();
    }
    //-----------room-----------------
    //check room occupant
    protected String roomCheck(int theRow, int theCol){
        return myMaze.roomCheckOc(theRow, theCol);
    }
    //remove monster, set empty
    protected void roomSetEmpty(int theRow, int theCol){
        myMaze.roomSetEmpty(theRow, theCol);
    }
    //reveal room, set visited to true
    protected void roomSetVisit(int theRow, int theCol){
        myMaze.roomSetVisit(theRow, theCol);
    }
    //remove key from the room
    protected void roomRemoveKey(int theRow, int theCol) {
        myMaze.roomRemoveKey(theRow, theCol);
    }
    //check condition if room has been visited  --> to adjust fog of war
    protected boolean roomCheckVisited(int theRow, int theCol) {
        return myMaze.roomCheckVisit(theRow, theCol);
    }
    //-----------lives and hintpass-----------------
    protected void decLive(){
        this.myLive--;
    }
    protected void incHintpass(){
        this.myHintPass++;
    }
    protected void decHintpass(){
        this.myHintPass--;
    }
    //-----------player info-----------------
    protected void setMyPlayerName(String theName){
        this.myPlayerName = theName;
    }

    protected String getMyPlayerName(){
        return this.myPlayerName;
    }
    //-----------player control/direction-----------------
    protected void moveLeft(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() - 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myCurrentPlace[0], myCurrentPlace[1], "P");

    }
    protected void moveRight(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() + 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myCurrentPlace[0], myCurrentPlace[1], "P");
    }
    protected void moveUp(){

        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(0 , myMaze.getRowPos() - 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myCurrentPlace[0], myCurrentPlace[1], "P");
    }
    protected void moveDown(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 row down  ------position 0 is row space
        myMaze.setPCurrent(0, myMaze.getRowPos() + 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);

        myMaze.setOccupant(myCurrentPlace[0], myCurrentPlace[1], "P");
    }

    private void roomActivity(int theRow, int theCol){

        if (myMaze.roomHasKey(theRow, theCol)){
            Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(theRow, theCol) == "bandit"){
            //activate bandit question set

        } else if (myMaze.getOccupant(theRow, theCol) == "guard"){
            //activate guard question set

        } else if (myMaze.getOccupant(theRow, theCol) == "gateKeeper"){
            //activate gateKeeper question set

        } else if (myMaze.getOccupant(theRow, theCol) == "hintPass"){
            //add hintpass to inventory
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
            myMaze.setOccupant(theRow, theCol, null);
        }
    }
    //-----------display-----------------
    public String maze2String(){
        return myMaze.m2String();
    }

    //1 is placeholder in getMonsterType
    protected void getQuestion() {

        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());
    }

    protected int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(myQuestions.getQuestionList().size());
    }

    protected boolean isCorrect(String userAnswer) {
        String answer = myQuestions.getAnswer(getQuestionIndex());
        return userAnswer.equals(answer);
    }
}
