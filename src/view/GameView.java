package view;

import model.*;
import model.Character;
import model.QuestionDatabase;
import controller.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class GameView {

    private final Scanner console = new Scanner(System.in);
    private String characterClass;
    private String playerName;
    private int qCorrect;
    private int qWrong;

    public void showTitleScreen() {
        System.out.println("Welcome to Trivia Maze!");
        System.out.println("=================================");
        System.out.println("Please select an option by entering an integer.");
        System.out.println("1) Start game");
        System.out.println("2) Load game");
        System.out.println("3) Options");
        System.out.println("4) Quit \n");
    }

    public void showOptionMenu() {
        System.out.println("Options Menu");
        System.out.println("=================================");
        System.out.println("1) Stop music");
        System.out.println("2) Play music \n");
    }

    public void showStart() {
        System.out.print("Let's get started!" + "\n" + "What is your name: ");
        playerName = console.nextLine();
        System.out.println();
        System.out.println("Alright " + playerName + ", choose a class:");
    }

    public String showCharacterClasses() {
        boolean myFlag;
        myFlag = false;

        while(!myFlag) {
            System.out.println("Merchant: Savvy with math to calculate business");
            System.out.println("Sailor: Well versed in the sea and land");
            System.out.println("Headhunter: Clever and witty \n");
            System.out.println("Please type in the class you want to play as. \n");

            //myModel.setMyPlayerClass(nScan.nextLine());
            characterClass = console.nextLine();
            switch (characterClass.toLowerCase()) {
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
        return characterClass;
    }

    private void showInstructions() {
        System.out.println("Traverse through two mazes to get to the boss!");
        System.out.println("Find the 4 keys scattered throughout the maze in order to progress");
        System.out.println("Be careful of bandits, guards and gatekeepers");
        System.out.println("They'll try to thwart your progress by asking questions");
    }

    public String getCategory() {
        String category = "";
        if (Objects.equals(characterClass, "Merchant")) {
            category = "Arithmetic";
        } else if (Objects.equals(characterClass, "Sailor")) {
            category = "Geography";
        } else if (Objects.equals(characterClass, "Headhunter")) {
            category = "Riddles";
        } else {
            category = "";
        }
        return category;
    }

    public char showMoves(final Maze theMaze) {
        System.out.println("Which action would you like to take?");
        if (theMaze.getRowPos() != 3) { //made getRowPos public
            System.out.println("D) Down");
        }
        if (theMaze.getColPos() != 6) {
            System.out.println("R) Right");
        }
        if (theMaze.getRowPos() != 0) {
            System.out.println("U) Up");
        }
        if (theMaze.getColPos() != 0) {
            System.out.println("L) Left");
        }
        System.out.println("I) Inventory");
        System.out.println("S) Save game");
        char action = console.next().charAt(0);
        return action;
    }

    private void showCharacterInfo(final Character theCharacter) {
//        System.out.println("Name: " + TitleScreen.getName());
//        System.out.println("Hero Type: " + theCharacter.getHeroType());
//        System.out.println("Lives: " + theCharacter.getLives());
    }

    public void showMaze(final Maze theMaze) {
        final StringBuilder sb = new StringBuilder();
        final String breakLine = "___________________________________________\n";
        final String breakRoom = "|     |     |     |     |     |     |     |\n";
        for (Room[] rooms : theMaze.getMaze()) {

            sb.append(breakLine);
            sb.append(breakRoom);
            sb.append("|  ");
            for (Room room : rooms) {

                if (room.getOccupant() == null) {
                    sb.append(" ");
                } else if (room.getOccupant().equals("bandit")) {
                    sb.append("B");
                } else if (room.getOccupant().equals("guard")) {
                    sb.append("G");
                } else if (room.getOccupant().equals("gateKeeper")) {
                    sb.append("R");
                } else {
                    sb.append(room.getOccupant().toString());
                }
                sb.append("  |  ");
            }
            sb.append("\n");
            sb.append(breakRoom);
        }
        sb.append(breakLine);
        sb.append("P = player, E = exit, B = bandit, G = guard, R = gatekeeper, K = key\n");
        System.out.println(sb.toString());
    }

    public void showRoom(final Room theRoom) {
        System.out.print("You enter the room and encounter a ");
        if (Objects.equals(theRoom.getOccupant(), "bandit")) {
            System.out.println("bandit! Get ready for a question");
        } else if (Objects.equals(theRoom.getOccupant(), "guard")) {
            System.out.println("guard! Get ready for a question");
        } else if (Objects.equals(theRoom.getOccupant(), "gatekeeper")) {
            System.out.println("gatekeeper! Get ready for a difficult question");
        } else if (Objects.equals(theRoom.getOccupant(), "K")) {
            System.out.println("key! Better keep it safe");
        } else if (Objects.equals(theRoom.getOccupant(), "E")) {
            System.out.println("way out! You found the exit!");
            //System.out.println("Looks like there's another maze in the town over");
            System.out.println("Oh no! Looks like the big boss is here");
        }  else if (Objects.equals(theRoom.getOccupant(), "P")) {
            System.out.println("moment of peace while you sort through your belongings");
            System.out.println("You're ready to move on now");
        } else {
            System.out.println("empty room, keep exploring!");
        }
    }

    public void showMonEnd() {
        System.out.println("The monster drops a hint pass while running away");
    }

    public void showInventory(final Character thePlayer) {
        System.out.println("Opening Inventory");
        System.out.println("Number of keys: " + thePlayer.getKeyCount());
        System.out.println("Number of hint passes: " + thePlayer.getHintpassCount());
        System.out.println("Lives: " + thePlayer.getLives());
    }

    public void canExit() {
        System.out.println("You found all four keys!");
        System.out.println("The exit has appeared. Find the way out!");
    }

    public int showQuestion(final Question theQuestion) {
        System.out.println(theQuestion.getMyQuestion());
        ArrayList<String> choices = theQuestion.setChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
        System.out.println("5: Use a hint pass");
        int correct = 0;
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (userAnswer == 5) {
            correct = 2;
        } else if (choices.get(userAnswer - 1).equals(theQuestion.getMyAnswer())) {
            System.out.println("Yay, you're correct!");
            correct = 1;
            qCorrect++;
        } else {
            System.out.println("Boo, you're wrong! -1 life");
            System.out.println("Try again");
            qWrong++;
            }
        return correct;
    }

    public int showHintpassUse(final Question theQuestion, final Character thePlayer) {
        int correct = 0;
        System.out.println("You used a hint pass");
        System.out.println("Half of the answers magically go away leaving you with a 50/50 chance");
        ArrayList<String> hintPassChoices = thePlayer.useHintpass(theQuestion);
        for (int i = 0; i < hintPassChoices.size(); i++) {
            System.out.println((i + 1) + ": " + hintPassChoices.get(i));
        }
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (hintPassChoices.get(userAnswer - 1).equals(theQuestion.getMyAnswer())) {
            System.out.println("Yay, you're correct!");
            correct = 1;
            qCorrect++;
        } else {
            System.out.println("Boo, you're wrong! -1 life");
            System.out.println("Try again");
            qWrong++;
        }
        return correct;
    }

    private void showSummary(final Character thePlayer) {
        System.out.println("Name: " + playerName);
        System.out.println("Class: " + characterClass);
        System.out.println("Keys collected: " + thePlayer.getKeyCount());
        System.out.println("Correct answers: " + qCorrect);
        System.out.println("Incorrect answers: " + qWrong);
    }

    public char showGameOver(final Character thePlayer) {
        System.out.println("Unfortunately your adventure ends here...");
        System.out.println("Better luck next time!"+ "\n");
        System.out.println("          Your summary");
        System.out.println("=================================");
        showSummary(thePlayer);
        System.out.println("=================================\n");
        System.out.println("Would you like to begin again?\nEnter Y for yes or any other char to exit the game.");
        return console.next().charAt(0);

    }

    public char winnerWinnerChickenDinner(final Character thePlayer) {
        System.out.println("Against all odds you have bested the maze and the boss.");
        System.out.println("Congratulations!"+ "\n");
        System.out.println("          Your summary");
        System.out.println("=================================");
        showSummary(thePlayer);
        System.out.println("=================================\n");
        System.out.println("Would you like to begin again?\nEnter Y for yes or any other char to exit the game.");
        return console.next().charAt(0);
    }

}
