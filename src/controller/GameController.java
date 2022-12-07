package controller;
import java.util.Scanner;
import model.Character;
import model.GameModel;
import model.Maze;
import model.UserFunctions;


public class GameController {
    private final Scanner nScan = new Scanner(System.in);
    private GameModel myModel;
    private Maze myMaze;
    public GameController(GameModel theModel) {
        //myMaxKeyCount = 4;
        final int mazeRow = 4;
        final int mazeCol = 7;
        final int monsterA_count = 6;
        final int monsterB_count = 2;
        final int monsterC_count = 0;
        final int keyCount = 4;

        //TODO by Steve
        //use getplayer name and getplayer class method to update myPlayerName, myPlayerClass
        //before initialize Character class.

        myMaze = new Maze(mazeRow, mazeCol, monsterA_count, monsterB_count, monsterC_count, keyCount);
        //added by Juno
       // myPlayer = new Character(myLive, myPlayerClass);
        myModel = theModel;
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
                System.out.println(myModel.maze2String());
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
        myModel.setMyPlayerName(nScan.nextLine());
        System.out.println("Alright " + myModel.getMyPlayerName() + ", choose a class:");
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

        myModel.setMyPlayerClass(nScan.nextLine());

            switch (myModel.getMyPlayerClass().toLowerCase()) {

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
            moveDown();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());


        }
        if(myDirection == 'R'){
            System.out.println("Move Right (R)");
            moveRight();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());

        }
        if(myDirection == 'U'){
            System.out.println("Move Up (U)");
            moveUp();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());


        }
        if(myDirection == 'L'){
            System.out.println("Move Left (L)");
            moveLeft();
            System.out.println("Row: "+myModel.getPlayerRow());
            System.out.println("Col: "+myModel.getPlayerCol());
            System.out.println(myModel.maze2String());
        }
    }

    public void moveLeft() {


        if (myMaze.getColPos() == 0){
            throw new IllegalArgumentException("Out of bound");
        }
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        myMaze.roomSetEmpty(tempRow, tempCol);
        //move 1 column to left ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() - 1);
        myModel.roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");

        System.out.println("ROWS2: " + myMaze.getMyCurrentRow());
        System.out.println("COLS2: " + myMaze.getMyCurrentCol());

    }
    public void moveRight() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        //move 1 column to right ----position 1 is col space
        myMaze.setPCurrent(myMaze.getRowPos(), myMaze.getColPos() + 1);
        myModel.roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }
    public void moveUp() {

        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        //move 1 row up  ------position 0 is row space
        myMaze.setPCurrent(myMaze.getRowPos() - 1, myMaze.getColPos());
        myModel.roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(tempRow, tempCol, "P");
    }
    public void moveDown() {
        final int tempRow = myMaze.getRowPos();
        final int tempCol = myMaze.getColPos();
        //move 1 row down  ------position 0 is row space
        myMaze.setPCurrent(myMaze.getRowPos() + 1, myMaze.getColPos());
        myModel.roomActivity(myMaze.getRowPos(), myMaze.getColPos());
        myMaze.roomSetEmpty(tempRow, tempCol);
        myMaze.setOccupant(myMaze.getRowPos(), myMaze.getColPos(), "P");
    }

    public void setMyMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }
}