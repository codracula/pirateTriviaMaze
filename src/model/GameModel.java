package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.Scanner;

public class GameModel {

    //    private int[] myCurrentPlace;
//    private int myHintPass;
    private int myLive;
    private String myPlayerName;
    private String myPlayerClass;
    private Maze myMaze;
    public Character myPlayer;
    private QuestionDatabase myQuestions;
//    private Monster myMon;

    public GameModel(){

        myPlayerName = "";
        myPlayerClass = "";
        myMaze = new Maze(4,7, 6,2,0,4);
        //added by Juno
        myPlayer = new Character(myLive, myPlayerClass, myPlayerName);//, myQuestions.getQuestionSet(getQuestionIndex()));
        //initialize inventory
       // gameStart();
    }



    //    void gameStart(){
//        //if collect all 4 keys spawn the exit
//        if (Character.Inventory.getMyHintpassCount() == 4) {
//            myMaze.genExit();
//        }
//
//        //if enter the exit then...
//        //spawn another town with a gateKeeper, reset key collected, reset room explored.
//        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()){
//            Character.Inventory.setMyKeyCount(0);
//            myMaze = new Maze(4,7, 6,2,1,4);
//        }
//
//        //once collected all keys, spawn the exit
//        if (Character.Inventory.getMyHintpassCount() == 4) {
//            myMaze.genExit();
//        }
//        //collect another set of keys and enter the exit
//        //activate boss fight
//        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()){
//
//            //TODO by Juno
//            //activate boss fight question
//            //TODO by Steve
//            //if win display victorious screen and
//            //reset stats (hintpass, live, key, explored room)
//        }
//    }
    //-----------maze-----------------
    public int getPlayerRow(){
        return myMaze.getRowPos();
    }

    public int getPlayerCol(){
        return myMaze.getColPos();
    }
    //-----------room-----------------
    //check room occupant
    String roomCheck(int theRow, int theCol){
        return myMaze.roomCheckOc(theRow, theCol);
    }
    //remove monster, set empty
    void roomSetEmpty(int theRow, int theCol){
        myMaze.roomSetEmpty(theRow, theCol);
    }
    //reveal room, set visited to true
    void roomSetVisit(int theRow, int theCol){
        myMaze.roomSetVisit(theRow, theCol);
    }
    //remove key from the room
    void roomRemoveKey(int theRow, int theCol) {
        myMaze.roomRemoveKey(theRow, theCol);
    }
    //check condition if room has been visited  --> to adjust fog of war
    boolean roomCheckVisited(int theRow, int theCol) {
        return myMaze.roomCheckVisit(theRow, theCol);
    }
    //-----------lives and hintpass-----------------
    void decLive(){
        if (myLive > 1){
            myLive--;
        } else if (myLive == 1){
            //gameOver();
        }

    }
//    void gameOver(){
//        System.out.println("Game over. You have run out of lives. Better luck next time.");
//        Scanner scan = new Scanner(System.in);
//
//        scan.nextChar;
//
//        //do something when game over
//        //display game over message, then press any keys to continue then restart the game menu
//        //reset stats (hintpass, live, key, explored room)
//    }

    //-----------player info-----------------
    public void setMyPlayerName(String theName){
        if (theName != null){
            myPlayerName = theName;
        }
    }
    public String getMyPlayerName(){
        return myPlayerName;
    }

    public void setMyPlayerClass(String theClass){
        myPlayerClass = theClass;
    }

    public String getMyPlayerClass(){
        return myPlayerClass;
    }

    //-----------player control/direction-----------------
    public void moveLeft(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();;
        int tempCol2 = myMaze.getColPos() - 1;

        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() - 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(tempRow, tempCol2, "P");


    }
    public void moveRight(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        int tempCol2 = myMaze.getColPos() + 1;
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() + 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(tempRow, tempCol2, "P");


    }
    public void moveUp(){

        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        int tempRow2 = myMaze.getRowPos() - 1;
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(0 , myMaze.getRowPos() - 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(tempRow2, tempCol, "P");

    }
    public void moveDown(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        int tempRow2 = myMaze.getRowPos() + 1;
        //move 1 row down  ------position 0 is row space
        myMaze.setPCurrent(0, myMaze.getRowPos() + 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(tempRow2, tempCol, "P");

    }

    void roomActivity(int theRow, int theCol){

        if (myMaze.roomHasKey(theRow, theCol)){
            System.out.println("KEY");
            Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(theRow, theCol) == "bandit"){
            System.out.println("BANDIT");

        } else if (myMaze.getOccupant(theRow, theCol) == "guard"){
            //activate guard question set
            System.out.println("GUARD");

        } else if (myMaze.getOccupant(theRow, theCol) == "gateKeeper"){
            //activate gateKeeper question set
            System.out.println("GATE KEEPER");

        } else if (myMaze.getOccupant(theRow, theCol) == "hintPass"){
            //add hintpass to inventory
            System.out.println("HINTPASS");
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
            myMaze.setOccupant(theRow, theCol, null);
        }
    }
    //-----------display-----------------
    public String maze2String(){
        return myMaze.m2String();
    }

    //1 is placeholder in getMonsterType
    void getQuestion() {

        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());
    }

    int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(myQuestions.getQuestionList().size());
    }

    boolean isCorrect(String userAnswer) {
        String answer = myQuestions.getAnswer(getQuestionIndex());
        return userAnswer.equals(answer);
    }
}