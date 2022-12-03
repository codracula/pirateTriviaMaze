package unitTest;


import java.util.Random;

public class GameModel {

//    private int[] myCurrentPlace;
//    private int myHintPass;
    private int myLive;
    private String myPlayerName;
    private String myPlayerClass;
    private Maze myMaze;
    private Character myPlayer;
    private QuestionDatabase myQuestions;
//    private Monster myMon;

    public GameModel(){

        //TODO by Steve
        //use getplayer name and getplayer class method to update myPlayerName, myPlayerClass
        //before initialize Character class.

//        myPlayerName = "";
//        myPlayerClass = "";
        myMaze = new Maze(4,7, 6,2,0,4);
        //added by Juno
        myPlayer = new Character(myLive, myPlayerClass, myQuestions.getQuestionSet(getQuestionIndex()));
        //initialize inventory
        gameStart();
    }

    void gameStart(){
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

            //TODO by Juno
            //activate boss fight question
            //TODO by Steve
            //if win display victorious screen and
            //reset stats (hintpass, live, key, explored room)
        }
    }
    //-----------maze-----------------
    int getPlayerRow(){
        return myMaze.getRowPos();
    }

    int getPlayerCol(){
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
            gameOver();
        }

    }
    void gameOver(){
        //TODO for Steve
        //do something when game over
        //display game over message, then press any keys to continue then restart the game menu
        //reset stats (hintpass, live, key, explored room)
    }

    //-----------player info-----------------
    void setMyPlayerName(String theName){
        if (theName != null){
            myPlayerName = theName;
        } else {

        }

    }

    String getMyPlayerName(){
        return myPlayerName;
    }

    void setMyPlayerClass(String theClass){
        myPlayerClass = theClass;
    }

    String getMyPlayerClass(){
        return myPlayerClass;
    }

    //-----------player control/direction-----------------
    void moveLeft(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() - 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myMaze.getColPos(), myMaze.getRowPos(), "P");

    }
    void moveRight(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() + 1);
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myMaze.getColPos(), myMaze.getRowPos(), "P");
    }
    void moveUp(){

        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(myMaze.getRowPos() - 1, myMaze.getColPos());
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myMaze.getColPos(), myMaze.getRowPos(), "P");
    }
    void moveDown(){
        int tempRow = myMaze.getRowPos();
        int tempCol = myMaze.getColPos();
        //move 1 row down  ------position 0 is row space
        myMaze.setPCurrent(myMaze.getRowPos() + 1, myMaze.getColPos());
        roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);

        myMaze.setOccupant(myMaze.getColPos(), myMaze.getRowPos(), "P");
    }

    void roomActivity(int theRow, int theCol){

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
//    void getQuestion() {
//
//        myMaze.getMon().setQuestion(myMaze.roomCheckOc(getPlayerRow(), getPlayerCol()), myPlayerClass, myQuestions, getQuestionIndex());
//    }

    int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(myQuestions.getQuestionList().size());
    }

    boolean isCorrect(String userAnswer) {
        String answer = myQuestions.getAnswer(getQuestionIndex());
        return userAnswer.equals(answer);
    }
}
