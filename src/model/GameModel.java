package model;

//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import controller.GameController;
import view.*;

public final class GameModel {
//    /**
//     *  player name.
//     */
//    private String myPlayerName;
//    /**
//     *  player class.
//     */
//    private String myPlayerClass;
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
    private final GameView myView;

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

        myMaze = new Maze(mazeRow, mazeCol, monsterA_count, monsterB_count, monsterC_count, keyCount);
        myPlayer = new Character();
        //gameStart();
    }

    /** Start the game set condition to each room.
     *
     */
//    private void gameStart() {
//
//        //if collect all 4 keys spawn the exit
//        if (Character.Inventory.getMyHintpassCount() == myMaxKeyCount) {
//            myMaze.genExit();
//        }
//
//        //if enter the exit then...
//        //spawn another town with a gateKeeper, reset key collected, reset room explored.
//        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
//            Character.Inventory.setMyKeyCount(0);
//            myMaze = new Maze(4, 7, 6, 2, 1, 4);
//        }
//
//        //once collected all keys, spawn the exit
//        if (Character.Inventory.getMyHintpassCount() == 4) {
//            myMaze.genExit();
//        }
//        //collect another set of keys and enter the exit
//        //activate boss fight
//        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
//
//            //TODO by Juno
//            bossFight();
//            //TODO by Steve
//            //if win display victorious screen and
//            //reset stats (hintpass, live, key, explored room)
//        }
//    }

    //-----------maze-----------------
    public Maze getMyMaze() {
        return myMaze;
    }

    public void getExit() {
        myMaze.genExit();
    }

    public int getExitCol() {
        return myMaze.getExitCol();
    }

    public int getExitRow() {
        return myMaze.getExitRow();
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

    public int getMyLive() {
        return myPlayer.getLives();
    }

    //-----------player info-----------------
    public Character getMyPlayer() {
        return myPlayer;
    }

    public void decMyLive() {
        if (myPlayer.getLives() == 1) {
            myView.showGameOver(myPlayer);
        } else {
            myPlayer.decreaseLives();
        }
    }

    //-----------player control/direction-----------------
    public void setCurrentP() {
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }

    public void moveLeft() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() - 1);
    }

    public void moveRight() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        System.out.println("Col " + myMaze.getColPos());
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() + 1);
    }

    public void moveUp() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos()-1, myMaze.getColPos());
    }

    public void moveDown() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setPCurrent(myMaze.getRowPos()+1, myMaze.getColPos());
    }

    /** method to activate room acitvivity based on the room location of row/col
     *
//     * @param theRow the row position
//     * @param theCol the col position
//     */
    public void roomActivity() {
        if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "K") {
            Character.Inventory.setMyKeyCount(Character.Inventory.getMyKeyCount() + 1);
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "bandit") {
            //activate bandit question set
            monEncounter(banditQ);
            hintPassChance("bandit");
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "guard") {
            //activate guard question set
            monEncounter(guardQ);
            hintPassChance("guard");
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "gatekeeper") {
            //activate gateKeeper question set
            monEncounter(gatekeeperQ);
            hintPassChance("gatekeeper");
        } else if (myMaze.getOccupant(getPlayerRow(), getPlayerCol()) == "E") {
            bossFight();
        }
    }

    private void monEncounter(final ArrayList<Question> theMonQ) {
        int index = getQuestionIndex(theMonQ);
        Question currentQ = getQuestion(theMonQ);
        int correct = myView.showQuestion(currentQ);
        if (correct == 2) {
            correct = myView.showHintpassUse(currentQ, myPlayer);
            if (correct == 0) {
                decMyLive();
                correct = myView.showHintpassUse(currentQ, myPlayer);
            }
        }
        while (correct == 0) {
            decMyLive();
            correct = myView.showQuestion(currentQ);
        }
        theMonQ.remove(index);
    }

    private void hintPassChance(final String theMonType) {
        if (myMaze.getMon().hintPassChance(theMonType)) {
            myView.showMonEnd();
            Character.Inventory.setMyHintpassCount(Character.Inventory.getMyHintpassCount() + 1);
        }
    }

    public void setQuestion() {
        myQuestions = new QuestionDatabase();
        myMaze.getMon().setQuestion("bandit", myView.getCategory(), myQuestions);
        banditQ = myQuestions.getQuestionList();
        myMaze.getMon().setQuestion("guard", myView.getCategory(), myQuestions);
        guardQ = myQuestions.getQuestionList();
        myMaze.getMon().setQuestion("gatekeeper", myView.getCategory(), myQuestions);
        gatekeeperQ = myQuestions.getQuestionList();
    }

    private Question getQuestion(final ArrayList<Question> theMonsterQ) {
        return myMaze.getMon().getQuestion(theMonsterQ);
    }

    int getQuestionIndex(final ArrayList<Question> monsterQ) {
        myRandom = new Random();
        return myRandom.nextInt(monsterQ.size());
    }

    private void bossFight() {
        for (int i = 0; i < 3; i++) {
            monEncounter(gatekeeperQ);
        }
    }

    public boolean doBossFight() {
        boolean fight = false;
        if (myMaze.getColPos() == myMaze.getExitCol() && myMaze.getRowPos() == myMaze.getExitRow()) {
            fight = true;
        }
        return fight;
    }

    public void loadLastGame() {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("f.txt"));
            myMaze.loadMaze(in);
            in.close();
        }catch(Exception e){System.out.println(e);}
    }

    public void saveGame() throws IOException {
        //try {
        FileOutputStream fout = new FileOutputStream("f.txt");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        //myMaze = new Maze(4, 7, 6, 2, 1, 4);
        myMaze.saveMaze(out);
        //myPlayer.savePlayer(out);
        System.out.println("game saved");
        //} catch(Exception ex){System.out.println("error saving");}
    }
}