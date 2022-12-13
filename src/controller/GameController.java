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

    public void movePlayer() {
        char action = myView.showMoves(myModel.getMyMaze());
        if (action == 'D') {
            myModel.moveDown();
        } else if (action == 'R') {
            myModel.moveRight();
        } else if (action == 'U') {
            myModel.moveUp();
        } else if (action == 'L') {
            myModel.moveLeft();
        } else if (action == 'I') {
            myView.showInventory(myModel.myPlayer);
        }
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
        myView.winnerWinnerChickenDinner(myModel.getMyPlayer());

    }

    private void traverse() {
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

    private void loseRestart() {
        char choice = myView.showGameOver(myModel.getMyPlayer());
        if (choice == 'y' || choice == 'Y') {
            setUp();
            playGame();
        } else {
            System.exit(1);
        }
    }

    private void winRestart() {
        char choice = myView.winnerWinnerChickenDinner(myModel.getMyPlayer());
        if(choice == 'Y' || choice == 'y') {
            resetStats();
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

