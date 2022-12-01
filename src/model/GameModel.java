package model;

import Controller.Controller;

public class GameModel {

    //private int[] myCurrentPlace;
    private int myHintPass;
    private int myLives;
    public static String myPlayerName;
    public static String myPlayerClass;
    private Maze myMaze;
    static Character myPlayer;

    private QuestionDatabase myQuestions;

    private Monster myMon;

    public GameModel() {

        myMaze = new Maze(4, 7, 6, 2, 1, 4);
        myHintPass = 0;
        myLives = 3;
        //myCurrentPlace = new int[10];
        myPlayer = new Character(myLives, myPlayerClass, myPlayerName);//, myQuestions.getQuestionSet(getQuestionIndex()));
        myPlayerName = "";
        myPlayerClass = "";

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
    protected String roomCheck(int theRow, int theCol) {
        return myMaze.roomCheckOc(theRow, theCol);
    }

    //remove monster, set empty
    protected void roomSetEmpty(int theRow, int theCol) {
        myMaze.roomSetEmpty(theRow, theCol);
    }

    //reveal room, set visited to true
    protected void roomSetVisit(int theRow, int theCol) {
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
    protected void decLive() {
        this.myLives--;
    }

    protected void incHintpass() {
        this.myHintPass++;
    }

    protected void decHintpass() {
        this.myHintPass--;
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
    public String maze2String() {
        return myMaze.m2String();
    }

    //1 is placeholder in getMonsterType
//    protected void getQuestion() {
////myMaze.getMon().setQuestion(myMaze.roomCheckOc(myCurrentPlace[1], myCurrentPlace[2]), myPlayerClass, myQuestions, getQuestionIndex());
//        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());    }
//
//    protected int getQuestionIndex() {
//        Random random = new Random();
//        return random.nextInt(myQuestions.getQuestionList().size());
//    }
//
//    protected boolean isCorrect(String userAnswer) {
//        String answer = myQuestions.getAnswer(getQuestionIndex());
//        return userAnswer.equals(answer);
//}
}

