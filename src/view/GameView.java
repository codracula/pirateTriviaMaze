package view;

import model.*;
import model.Character;
import model.QuestionDatabase;
import controller.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameView {

    private final Scanner console = new Scanner(System.in);
    private String characterClass;

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
        //myModel.setMyPlayerName(nScan.nextLine());
        String playerName = console.nextLine();
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

    public String getCategory() {
        String category = "";
        if (characterClass == "Merchant") {
            category = "Arithmetic";
        } else if (characterClass == "Sailor") {
            category = "Geography";
        } else if (characterClass == "Headhunter") {
            category = "Riddles";
        } else {
            category = "";
        }
        return category;
    }

    public char showMoves() {
        System.out.println("Which direction would you like to move?");
        System.out.println("D) Down");
        System.out.println("R) Right");
        System.out.println("U) Up");
        System.out.println("L) Left");
        char direction = console.next().charAt(0);
        return direction;
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
        System.out.print("You enter the room and encounter a " + theRoom.getOccupant());
        if (theRoom.getOccupant() == "1") {
            System.out.println("bandit! Get ready for a question");
        } else if (theRoom.getOccupant() == "2") {
            System.out.println("guard! Get ready for a question");
        } else if (theRoom.getOccupant() == "3") {
            System.out.println("gatekeeper! Get ready for a difficult question");
        } else if (theRoom.getOccupant() == "K") {
            System.out.println("a key! Better keep it safe");
        } else if (theRoom.getOccupant() == "E") {
            System.out.println("a way out! You found the exit!");
        } else {
            System.out.println("empty room, keep exploring!");
        }
    }

    private void showInventory(final Character thePlayer) {
        System.out.println("Hintpass count: " + thePlayer.getHintpassCount());
        System.out.println("Key count: " + thePlayer.getKeyCount());
    }

    private boolean canExit(final Character thePlayer) {
        if (thePlayer.getKeyCount() == 4) {
            return true;
        } else {
            return false;
        }
    }

    public void showQuestion(final QuestionDatabase theQuestion, final int theIndex) {
        theQuestion.getQuestionList().get(theIndex).toString();
        //asks question and takes in user input
        //probably should move somewhere else later
        ArrayList<String> choices = theQuestion.getChoices(theIndex);
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (choices.get(userAnswer - 1).equals(theQuestion.getAnswer(theIndex))) {
            System.out.println("Yay, you're correct!");
            //correct++;
        } else {
            System.out.println("Boo, you're wrong!");
            System.out.println("The answer is " + theQuestion.getAnswer(theIndex));
            //wrong++;
        }
    }

    private void showExitable() {
//        if (theKeyCount == 4) {
//            System.out.println("a way out! You found the exit!");
//        } else {
//            System.out.println("a way out! But you can't go through without all the keys");
//        }
    }

    private void showGameOver() {
        System.out.println("GAME OVER");
    }

    private void showBossFight() {
        System.out.println("You encounter the final boss!");
    }
}
