package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameModel {

    private int[] current;
    private int myHintPass;
    private int myLive;
    private String myPlayerName;
    private String myPlayerClass;
    private Maze myMaze;
//    private model.Monster mon;
//    private int mACount;
//    private int mBCount;
//    private int mCCount;
    public GameModel(){

        myMaze = new Maze(4,7, 6,2,1,4);
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



    //get question
    //notify correct answer
    //notify out of hintpass
}
