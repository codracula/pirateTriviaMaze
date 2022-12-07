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
//    private void startGame() {
//        System.out.print("Let's get started!" + "\n" + "What is your name: ");
//        //myModel.setMyPlayerName(nScan.nextLine());
//        String playerName = nScan.nextLine();
//        System.out.println("Alright " + playerName + ", choose a class:");
//        setPlayerClass();
//    }

    private void setPlayerClass() {
        characterClass = myView.showCharacterClasses();
    }

//    private String getCategory() {
//        String category;
//        if (characterClass.equals("Merchant")) {
//            category = "Arithmetic";
//        } else if (characterClass.equals("Sailor")) {
//            category = "Geography";
//        } else if (characterClass.equals("Headhunter")) {
//            category = "Riddles";
//        } else {
//            category = "";
//        }
//        return category;
//    }


    public void movePlayer(){
        char myDirection = myView.showMoves();
        if(myDirection == 'D'){
            System.out.println("Move down (D)");
            myModel.moveDown();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());
        }
        if(myDirection == 'R'){
            System.out.println("Move Right (R)");
            myModel.moveRight();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());
        }
        if(myDirection == 'U') {
            System.out.println("Move Up (U)");
            myModel.moveUp();
            System.out.println("Row: " + myModel.getPlayerRow());
            System.out.println("Col: " + myModel.getPlayerCol());
        }
        if(myDirection == 'L'){
            System.out.println("Move Left (L)");
            myModel.moveLeft();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());
            //System.out.println(myModel.maze2String());
        }
    }

}