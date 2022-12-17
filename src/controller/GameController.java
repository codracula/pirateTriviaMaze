package controller;

import model.GameModel;
import view.GameView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameController {

    /**
     * initializing audio file
     */
    private File myMusic = new File("./audio.wav");
    /**
     * initializing clip
     */
    private static Clip myAudio;

    /**
     * accepts user input
     */
    private final Scanner myScan = new Scanner(System.in);
    /**
     * initializes the GameModel
     */
    private final GameModel myModel;
    /**
     * initializes GameView
     */
    private final GameView myView;
    /**
     * initialize characterClass
     */
    private String myCharacterClass;

    /**
     * constructor for GameController and sets model and view
     * @param theModel the GameModel
     * @param theView the GameView
     */
    public GameController(GameModel theModel, GameView theView) {
        myModel = theModel;
        myView = theView;
    }

    /**
     * takes in user input for menu choices
     */
    public void menu() throws IOException, ClassNotFoundException {
        int choice = myScan.nextInt();
        switch (choice) {
            case 1:
                myView.showSelection();
                setPlayerClass();
                //System.out.println(myModel.maze2String());
                break;
            case 2:
                System.out.println("Load game");
                //UserFunctions.loadLastGame();
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

    /**
     * takes user input for option menu choices
     */
    private void optionMenu() throws IOException, ClassNotFoundException {

        int choice = myScan.nextInt();
        switch (choice) {
            case 1:
                myView.showOptionMenu();
                stopMusic();
                optionMenu();
                break;
            case 2:
                myView.showOptionMenu();
                playMusic();
                optionMenu();
                break;
            case 3:
                myView.showTitleScreen();
                menu();
                break;
            default:
                System.out.println("Not a valid option.");
        }
    }

    /**
     * sets the characterClass from view to controller
     */
    private void setPlayerClass() {
        myCharacterClass = myView.showCharacterClasses();
    }

    /**
     * moves the player around the maze and allows for the player to stop and play music.
     * @return moveable, if player is able to move in that direction
     */
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
        } else if (action == 'K' || action == 'k') {
            stopMusic();
        } else if (action == 'L' || action == 'l') {
            playMusic();
        } else {
            System.out.println("Invalid input, please try again");
            moveable = false;
            while (!moveable) {
                moveable = movePlayer();
            }
        }
        return moveable;
    }

    /**
     * initiates the intro sequence before the maze
     */
    public void setUp() throws IOException, ClassNotFoundException {
        playMusicNow();
        myView.showTitleScreen();
        menu();
        myModel.setQuestion();
    }

    /**
     * plays the game while player is alive
     */
    public void playGame() throws IOException, ClassNotFoundException {
        setUp();
        while (myModel.myPlayer.getKeyCount() != 4) {
            traverse();
        }
        canExit();
        while (!myModel.doBossFight()) {
            traverse();
        }
        myView.showGameOver(myModel.getMyPlayer());
    }

    /**
     *shows player moving throughout the game
     * as well as any encounters in the room
     */
    private void traverse() {
        myView.showInventory(myModel.getMyPlayer());
        myView.showMaze(myModel.myMaze);
        movePlayer();
        myView.showRoom(myModel.myMaze.getRoom(myModel.getPlayerRow(), myModel.getPlayerCol()));
        myModel.roomActivity();
        myModel.setCurrentP();
    }

    /**
     * if player has 4 keys, generates exit
     */
    private void canExit() {
        myView.canExit();
        myModel.getExit();
    }
    /**
     * Stops music if music is playing.
     */
    public void stopMusic() {
        try {
            if (myAudio.isActive()) {
                System.out.print("Stopping music...\n");
                myAudio.stop();
            } else {
                System.out.println("There is no music playing.");
            }
        } catch (NullPointerException e) {
            System.out.println("There is no music playing currently");

        }

    }
    /**
     * Play music on start.
     */
    public void playMusicNow() {
        try {
            if (myMusic.exists()) {
                System.out.print("Playing music... \n");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(myMusic);
                myAudio = AudioSystem.getClip();
                myAudio.open(audioInput);
                FloatControl gainControl =
                        (FloatControl) myAudio.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-23.0f);
                myAudio.start();
                myAudio.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("An error has occurred.");
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Plays music if music is not active.
     */
    public void playMusic() {
        if (myAudio.isActive()) {
            System.out.println("Music is already playing.");

        }else{
            playMusicNow();
        }
    }

}

