package controller;

import java.util.Scanner;

import model.Character;
import model.GameModel;
import model.UserFunctions;
import view.GameView;


public class GameController {
    private final Scanner nScan = new Scanner(System.in);
    private GameModel myModel;
    private GameView myView;
    private String characterClass;

    public GameController(GameModel theModel, GameView theView) {
        //public GameController(GameModel theModel) {
        myModel = theModel;
        myView = theView;
        //myView.showTitleScreen();
        //promptTitle();
        //menu();

    }
//    private void promptTitle() {
//        System.out.println("Welcome to Trivia Maze!");
//        System.out.println("=================================");
//        System.out.println("Please select an option by entering an integer.");
//        System.out.println("1) Start game");
//        System.out.println("2) Load game");
//        System.out.println("3) Options");
//        System.out.println("4) Quit \n");
//    }

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

    public void movePlayer(){
        char myDirection = myView.showMoves();
        if(myDirection == 'D'){
            myModel.moveDown();
        }
        if(myDirection == 'R'){
            myModel.moveRight();
        }
        if(myDirection == 'U') {
            myModel.moveUp();
        }
        if(myDirection == 'L'){
            myModel.moveLeft();
        }
        if(myDirection == 'I') {
            myView.showInventory(myModel.myPlayer);
        }
    }

    private void decLives() {
        myModel.decMyLive();
    }

}