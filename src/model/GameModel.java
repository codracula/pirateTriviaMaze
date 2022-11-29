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
//    private model.Monster mon;
//    private int mACount;
//    private int mBCount;
//    private int mCCount;
    public GameModel(){

        myMaze = new Maze(4,7, 6,2,1,4);
        myPlayer = new Character(myLive, myPlayerClass, myQuestions.getQuestionSet(getQuestionIndex()));
        myHintPass = 0;
        myLive = 3;
        myPlayerName = "";
        myPlayerClass = "";

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

    //-----------player control/direction-----------------
    protected void moveLeft(){
        myMaze.setPCurrent(1, myMaze.getColPos() - 1);

    }
    protected void moveRight(){
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() + 1);
    }
    protected void moveUp(){
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(0 , myMaze.getRowPos() - 1);
    }
    protected void moveDown(){
        myMaze.setPCurrent(0, myMaze.getRowPos() + 1);
    }
    //-----------display-----------------
    public String maze2String(){
        return myMaze.toString();
    }

    //1 is placeholder in getMonsterType
    protected void getQuestion() {
//myMaze.getMon().setQuestion(myMaze.roomCheckOc(myCurrentPlace[1], myCurrentPlace[2]), myPlayerClass, myQuestions, getQuestionIndex());
        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());    }

    protected int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(myQuestions.getQuestionList().size());
    }

    protected boolean isCorrect(String userAnswer) {
        String answer = myQuestions.getAnswer(getQuestionIndex());
        return userAnswer.equals(answer);
    }
}
