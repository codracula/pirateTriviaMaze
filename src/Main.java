import controller.GameController;
import controller.UserFunctions;
import model.GameModel;
import model.Maze;
import view.GameView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        GameView myView = new GameView();
        GameModel myModel = new GameModel(myView);
        GameController myController = new GameController(myModel, myView);

//        System.out.println("myPlayerName: " + myModel.getMyPlayerName());
//        System.out.println("myPlayerClassName: " + myModel.getMyPlayerClass());
        System.out.println("spawnRow: " + myModel.getPlayerRow());
        System.out.println("spawnCol: " + myModel.getPlayerCol());
//
//        while(true) {
//
//            theController.movePlayer();
//            System.out.println(theModel.maze2String());
//
//        }

        myView.showTitleScreen();
        myController.menu();
        myModel.setQuestion();


        while (myModel.getMyLive() != 0) {
            myView.showMaze(myModel.myMaze);
            myController.movePlayer();
            myView.showRoom(myModel.myMaze.getRoom(myModel.getPlayerRow(), myModel.getPlayerCol()));
            myModel.roomActivity();
            myModel.setCurrentP();
            System.out.println("LIVES: " + myModel.getMyLive());


            //show maze
//        myView.showMaze(myModel.myMaze);
            //if move then move and show whats in room
//            myController.movePlayer();
//            myView.showRoom(myModel.myMaze.getRoom(myModel.getPlayerRow(), myModel.getPlayerCol()));
//            myModel.roomActivity();
//            myModel.setCurrentP();
            //if the room has a key, get key
            //if thats the 4th key, spawn exit
            //if the room has a monster, fightg the monster
            //if answer is wrong, keep asking until correct or dead
            // if answer is correct possibility of dropping hint pass
            //room becomes empty
            //if not move, then they're probably opening inventory so don't show maze and room contents

        }

    }}