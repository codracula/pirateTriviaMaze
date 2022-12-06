package view;

import Model.*;
import Model.Character;
import Model.QuestionDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    private final Scanner console = new Scanner(System.in);
    private String characterClass;

    private void showTitleScreen() {
        System.out.println("Welcome to Trivia Maze!");
        System.out.println("=================================");
        System.out.println("Please select an option by entering an integer.");
        System.out.println("1) Start game");
        System.out.println("2) Load game");
        System.out.println("3) Options");
        System.out.println("4) Quit \n");
    }

    private void showMenu() {
        int choice = console.nextInt();

        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                System.out.println("Load game");
                //loadGame();
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

    private void showCharacterClasses() {
        System.out.println("Choose your character class!");
        System.out.println("Who you choose will affect what adversaries you will face");
        System.out.println("So choose to your strengths!");
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("1) A merchant, skilled in mathematics?");
        System.out.println("2) A sailor, well versed on the land and sea?");
        System.out.println("3) A headhunter, clever and witty?");
        int characterChoice = console.nextInt();
    }

    private void showMoves() {
        System.out.println("Which direction would you like to move?");
        System.out.println("D) Down");
        System.out.println("R) Right");
        System.out.println("U) Up");
        System.out.println("L) Left");
        char direction = console.next().charAt(0);
    }

    private void showCharacterInfo(Character theCharacter) {
        System.out.println("Name: " + TitleScreen.getName());
        System.out.println("Hero Type: " + theCharacter.getHeroType());
        System.out.println("Lives: " + theCharacter.getLives());
    }

    private void showMaze(Maze theMaze) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theMaze.length; i++){
            sb.append ("___________________________________________\n");
            sb.append("|     |     |     |     |     |     |     |\n");
            sb.append("|  ");
            for (int j = 0 ; j < theMaze[i].length; j++) {

                if (theMaze[i][j].getOccupant() == null){
                    sb.append(" ");
                } else if (theMaze[i][j].getOccupant() == "bandit"){
                    sb.append("1");
                } else if (theMaze[i][j].getOccupant() == "guard"){
                    sb.append("2");
                } else if (theMaze[i][j].getOccupant() == "gateKeeper"){
                    sb.append("3");
                } else {
                    sb.append(theMaze[i][j].getOccupant().toString());
                }
                sb.append("  |  ");
            }
            sb.append("\n");
            sb.append("|     |     |     |     |     |     |     |\n");

        }
        sb.append ("___________________________________________\n");
        sb.append("P = player, E = exit, 1 = bandit, 2 = guard, 3 = gatekeeper, K = key\n");
        System.out.println(sb.toString());
    }

    private void showRoom(Room theRoom) {
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
            System.out.println("way out! You found the exit!");
        }
    }

    private void showInventory(Character thePlayer) {
        System.out.println("Hintpass count: " + thePlayer.getHintpassCount());
        System.out.println("Key count: " + thePlayer.getKeyCount());
    }

    private void showQuestion(QuestionDatabase theQuestion, int theIndex) {
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
}
