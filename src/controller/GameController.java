package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import model.Character;
import view.GameView;

public class GameController {
    private final Scanner nScan = new Scanner(System.in);
    private final GameModel myModel;
    private final GameView myView;
    private String characterClass;

    public GameController(GameModel theModel, GameView theView) {
        myModel = theModel;
        myView = theView;
    }

    public void menu() {
        int choice = nScan.nextInt();
        switch (choice) {
            case 1:
                myView.showStart();
                setPlayerClass();
                //System.out.println(myModel.maze2String());
                break;
            case 2:
                System.out.println("Load game");
                UserFunctions.loadLastGame();
                break;
            case 3:
                myView.showOptionMenu();
                optionMenu();
                break;
            case 4:
                System.out.println("Quit");
                System.exit(1);
                break;
            default:
                System.out.println("Not a valid option.");
        }
    }

    private void optionMenu() {

        int choice = nScan.nextInt();
        switch (choice) {
            case 1:
                UserFunctions.stopMusic();
                break;
            case 2:
                UserFunctions.playMusic();
                break;
            default:
                System.out.println("Not a valid option.");
        }
    }

    private void setPlayerClass() {
        characterClass = myView.showCharacterClasses();
    }

    public boolean movePlayer() {
        char action = myView.showMoves(myModel.getMyMaze());
        boolean moveable = true;
        if (action == 'S' || action == 's') {
            myModel.moveDown();
        } else if (action == 'D' || action == 'd') {
            myModel.moveRight();
        } else if (action == 'W' || action == 'w') {
            myModel.moveUp();
        } else if (action == 'A' || action == 'a') {
            myModel.moveLeft();
        } else if (action == '~') {
            myModel.myPlayer.setMyLives(100);
        } else {
            System.out.println("Invalid input, please try again");
            moveable = false;
            while (!moveable) {
                moveable = movePlayer();
            }
        }
        return moveable;
    }

    public void setUp() {
        myView.showTitleScreen();
        menu();
        myModel.setQuestion();
    }

    public void playGame() {
        setUp();
        while (myModel.myPlayer.getKeyCount() != 4) {
            traverse();
        }
        canExit();
        while (!myModel.doBossFight()) {
            traverse();
        }
        myView.showGameOver(myModel.getMyPlayer());
        restart();
    }

    private void traverse() {
        myView.showInventory(myModel.getMyPlayer());
        myView.showMaze(myModel.myMaze);
        movePlayer();
        myView.showRoom(myModel.myMaze.getRoom(myModel.getPlayerRow(), myModel.getPlayerCol()));
        myModel.roomActivity();
        myModel.setCurrentP();
    }

    private void canExit() {
        myView.canExit();
        myModel.getExit();
    }

    private void restart() {
        char choice = myView.showGameOver(myModel.getMyPlayer());
        if(choice == 'Y' || choice == 'y') {
            resetStats(); //play game again from title screen
        } else {
            System.exit(1);
        }
    }

    private void resetStats() {
        myModel.myPlayer.setHintpassCount(0);
        myModel.myPlayer.setKeyCount(0);
        myModel.myPlayer.setMyLives(3);
    }
}

