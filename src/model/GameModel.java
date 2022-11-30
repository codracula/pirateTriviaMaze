package model;

import Controller.Controller;

public class GameModel {

    private int[] myCurrentPlace;
    private int myHintPass;
    private int myLive;
    public static String myPlayerName;
    public static String myPlayerClass;
    private Maze myMaze;
    static Character myPlayer;

    private QuestionDatabase myQuestions;

    private Monster myMon;

    public GameModel() {

        myMaze = new Maze(4, 7, 6, 2, 1, 4);
        myHintPass = 0;
        myLive = 3;
        myPlayer = new Character(myLive, myPlayerClass, myPlayerName);//, myQuestions.getQuestionSet(getQuestionIndex()));
        myPlayerName = Controller.getMyPlayerName();
        myPlayerClass = Controller.getMyPlayerClass();

    }
//Used to test of controller passes player name and class name
    public static String getMyPlayerName() {
        return myPlayerName;
    }
    public static String getMyPlayerClass() {
        return myPlayerClass;
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
        this.myLive--;
    }

    protected void incHintpass() {
        this.myHintPass++;
    }

    protected void decHintpass() {
        this.myHintPass--;
    }

    //-----------player control/direction-----------------
     public void moveLeft() {
        myMaze.setPCurrent(1, myMaze.getColPos() - 1);

    }

    public void moveRight() {
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(1, myMaze.getColPos() + 1);
    }

    public void moveUp() {
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(0, myMaze.getRowPos() - 1);
    }

    public void moveDown() {
        myMaze.setPCurrent(0, myMaze.getRowPos() + 1);
    }

    //-----------display-----------------
    public String maze2String() {
        return "test" + myMaze.toString() ;
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

