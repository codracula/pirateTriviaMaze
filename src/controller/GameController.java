package controller;

import java.io.IOException;
import java.util.Scanner;

import model.*;
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
                myModel.myMaze.loadLastGame();
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
                //myUF.stopMusic();
                break;
            case 2:
                //myUF.playMusic();
                break;
            default:
                System.out.println("Not a valid option.");
        }
    }

    private void setPlayerClass() {
        characterClass = myView.showCharacterClasses();
    }

    public void movePlayer() throws IOException {
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
        } else if (action == 'S') {
            myModel.myMaze.saveGame();
        }
    }

    public void setUp() {
        myView.showTitleScreen();
        menu();
        myModel.setQuestion();
    }

    public void playGame() throws IOException {
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

    private void traverse() throws IOException {
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

    private void loseRestart() throws IOException {
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

