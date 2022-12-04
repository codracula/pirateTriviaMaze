package Controller;

import model.Character;
import model.GameModel;

import model.UserFunctions;

import java.util.Scanner;

public class Controller {
    private final Scanner nScan = new Scanner(System.in);
    private GameModel theModel;
    public Controller(GameModel theModel) {
        this.theModel = theModel;
        promptTitle();
        menu();

    }
    public void promptTitle() {
        System.out.println("Welcome to Trivia Maze!");
        System.out.println("=================================");
        System.out.println("Please select an option by entering an integer.");
        System.out.println("1) Start game");
        System.out.println("2) Load game");
        System.out.println("3) Options");
        System.out.println("4) Quit \n");
    }
    public void menu() {

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                startGame();
                System.out.println(theModel.maze2String());
                break;
            case 2:
                System.out.println("Load game");
                UserFunctions.loadLastGame();
                break;
            case 3:
                System.out.println("Options Menu");
                System.out.println("=================================");
                System.out.println("1) Stop music");
                System.out.println("2) Play music \n");
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
    public void optionMenu() {

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
    public void startGame() {
        System.out.print("Let's get started!" + "\n" + "What is your name: ");
        theModel.setMyPlayerName(nScan.nextLine());
        System.out.println("Alright " + theModel.getMyPlayerName() + ", choose a class:");
        playerClassSelect();
    }
    public void playerClassSelect() {
        boolean myFlag;
        myFlag = false;

        while(!myFlag) {
        System.out.println("Merchant");
        System.out.println("Sailor");
        System.out.println("Headhunter \n");
        System.out.println("Please type in the class you want to play as. \n");

        theModel.setMyPlayerClass(nScan.nextLine());

            switch (theModel.getMyPlayerClass().toLowerCase()) {

                case "merchant":
                    System.out.println("You've chosen a merchant class, be prepared for arithmetic quest");
                    myFlag = true;
                    break;
                case "sailor":
                    System.out.println("You're now a sailor..YARRRRR'");
                    myFlag = true;
                    break;
                case "headhunter":
                    System.out.println("You are now a headhunter");
                    myFlag = true;
                    break;
            }

            System.out.println("Let's begin the adventure!");

        }
    }
    public void movePlayer(){
        char myDirection;

        System.out.println("Choose which direction you would like to move");
        System.out.println("D) Down");
        System.out.println("R) Right");
        System.out.println("U) Up");
        System.out.println("L) Left");

        Scanner scan = new Scanner(System.in);
        myDirection = scan.next().charAt(0);

        if(myDirection == 'D'){
            System.out.println("Move down (D)");
            theModel.moveDown();
            System.out.println("Row: "+theModel.getPlayerRow());
            System.out.println("Col: "+theModel.getPlayerCol());


        }
        if(myDirection == 'R'){
            System.out.println("Move Right (R)");
            theModel.moveRight();
            System.out.println("Row: "+theModel.getPlayerRow());
            System.out.println("Col: "+theModel.getPlayerCol());

        }
        if(myDirection == 'U'){
            System.out.println("Move Up (U)");
            theModel.moveUp();
            System.out.println("Row: "+theModel.getPlayerRow());
            System.out.println("Col: "+theModel.getPlayerCol());


        }
        if(myDirection == 'L'){
            System.out.println("Move Left (L)");
            theModel.moveLeft();
            System.out.println("Row: "+theModel.getPlayerRow());
            System.out.println("Col: "+theModel.getPlayerCol());
            System.out.println(theModel.maze2String());
        }
    }

}