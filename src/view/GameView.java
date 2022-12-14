package view;

import model.Character;
import model.Maze;
import model.Question;
import model.Room;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GameView {

    private final Scanner console = new Scanner(System.in);
    private String characterClass;
    private String playerName;
    private int qCorrect;
    private int qWrong;

    public void showTitleScreen() {
        showIntro();
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
            category = "Riddle";
        } else {
            category = "";
        }
        return category;
    }

    public char showMoves(final Maze theMaze) {
        System.out.println("Which action would you like to take?");
        if (theMaze.getRowPos() != 3) { //made getRowPos public
            System.out.println("S) Down");
        }
        if (theMaze.getColPos() != 6) {
            System.out.println("D) Right");
        }
        if (theMaze.getRowPos() != 0) {
            System.out.println("W) Up");
        }
        if (theMaze.getColPos() != 0) {
            System.out.println("A) Left");
        }
        char action = console.next().charAt(0);
        return action;
    }

    private void showCharacterInfo(final Character theCharacter) {
        System.out.println("Name: " + playerName);
        System.out.println("Class: " + characterClass);
        System.out.println("Lives: " + theCharacter.getLives());
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
        System.out.println();
        if (Objects.equals(theRoom.getOccupant(), "bandit")) {
            System.out.println("Before you can go into the room, a bandit jumps out at you");
            System.out.println("\"If you want to come this way, you better answer my question first\"");
            showBandit();
        } else if (Objects.equals(theRoom.getOccupant(), "guard")) {
            System.out.println("Before you can go into the room, a guard steps in front of you");
            showGuard();
            System.out.println("\"You cannot pass without answering this question\"");
        } else if (Objects.equals(theRoom.getOccupant(), "K")) {
            System.out.println("You found a key! Better keep it safe");
        } else if (Objects.equals(theRoom.getOccupant(), "E")) {
            System.out.println("You found the way out!");
            System.out.println("Oh no! Before you can go through, the gatekeeper stands in your way");
            System.out.println("Get ready for a slew of questions, hope you got some hint passes saved up");
            showGatekeeper();
        }  else if (Objects.equals(theRoom.getOccupant(), "P")) {
            System.out.println("It's just you in the room");
            System.out.println("You take a moment to collect yourself, then you're ready to move on");
        } else {
            System.out.println("There's nothing in here, keep exploring!");
        }
        System.out.println();
    }

    public void showMonEnd(final String theMonType) {
        if (theMonType.equals("bandit") || theMonType.equals("guard")) {
            System.out.println("The monster drops a hint pass while running away");
        } else if (theMonType.equals("gatekeeper")) {
            System.out.println("The gatekeeper looks at you approvingly");
            System.out.println("\"You're pretty smart\"");
            System.out.println("\"So smart, it's like you deserve an A\"");
            System.out.println("\"Hate to break it to you, but I'm not the final boss\"");
            System.out.println("\"Since I'm such a nice guy, I'll help you a little, just don't let my boss know\"");
            System.out.println("");
            System.out.println("You gained a hint pass and a life!");
            System.out.println();
        } else if (theMonType.equals("boss")) {
            System.out.println("You walk past the gatekeeper and enter the portal");
            showPortal();
            System.out.println("You see a figure looming");
            System.out.println("It's the actual boss");
            showBoss();
            System.out.println();
        }
    }

    public void showInventory(final Character thePlayer) {
        System.out.println();
        System.out.println("Number of keys: " + thePlayer.getKeyCount());
        System.out.println("Number of hint passes: " + thePlayer.getHintpassCount());
        System.out.println("Lives: " + thePlayer.getLives());
    }

    public void canExit() {
        System.out.println("You found all four keys!");
        System.out.println("The exit has appeared. Find the way out!");
    }

    public int showQuestion(final Question theQuestion, final Character thePlayer) {
        System.out.println(theQuestion.getMyQuestion());
        ArrayList<String> choices = theQuestion.setChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
        System.out.println("5: Use a hint pass, you have " + thePlayer.getHintpassCount() + " remaining");
        int correct = 0;
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (userAnswer == 5) {
            correct = 2;
        } else {
            correct = showCorrect(theQuestion, choices, userAnswer, thePlayer);
        }
        return correct;
    }

    //0 = wrong answer, try again
    //1 = correct answer
    //2 = used hint pass
    //3 = no hint passes to use
    public int showHintpassUse(final Question theQuestion, final Character thePlayer) {
        int correct = 0;
        if (thePlayer.getHintpassCount() == 0) {
            System.out.println();
            System.out.println("You don't have any hint passes!");
            System.out.println("Defeat some bandits and guards, maybe they'll drop some");
            System.out.println();
            correct = 3;
        } else {
            System.out.println("You used a hint pass");
            System.out.println("Half of the answers magically go away leaving you with a 50/50 chance");
            ArrayList<String> hintPassChoices = thePlayer.useHintpass(theQuestion);
            for (int i = 0; i < hintPassChoices.size(); i++) {
                System.out.println((i + 1) + ": " + hintPassChoices.get(i));
            }
            System.out.println("Enter your answer as a number");
            int userAnswer = console.nextInt();
            correct = showCorrect(theQuestion, hintPassChoices, userAnswer, thePlayer);
        }
        return correct;
    }

    private int showCorrect(final Question theQuestion, final ArrayList<String> theChoices, final int theUserAnswer, final Character thePlayer) {
        int correct = 0;
        if (theChoices.get(theUserAnswer - 1).equals(theQuestion.getMyAnswer())) {
            System.out.println("Yay, you're correct!");
            correct = 1;
            qCorrect++;
        } else {
            System.out.println("Boo, you're wrong! -1 life, you have " + thePlayer.getLives() + " left");
            System.out.println("Try again");
            qWrong++;
        }
        System.out.println();
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
        if (thePlayer.getLives() == 0) {
            System.out.println("⠀  ⠀   (\\__/)");
            System.out.println("       (•ㅅ•)      Game over.");
            System.out.println("    ＿ノヽ ノ＼＿      Better luck");
            System.out.println("`/　`/ ⌒Ｙ⌒ Ｙ  ヽ     next time.");
            System.out.println("( 　(三ヽ人　 /　  |");
            System.out.println("|　ﾉ⌒＼ ￣￣ヽ   ノ");
            System.out.println("ヽ＿＿＿＞､＿_／");
            System.out.println("    ｜( 王 ﾉ〈   (\\__/)");
            System.out.println("     /ﾐ`ー―彡\\  (•ㅅ•)");
            System.out.println("    / ╰    ╯  \\ /    \\>");
        } else {
            System.out.println("Against all odds you have bested the maze and the boss.");
            System.out.println("Congratulations!"+ "\n");
        }
        showSummary(thePlayer);
        System.out.println("=================================\n");
        System.out.println("Would you like to begin again?\nEnter Y for yes or any other char to exit the game.");
        return console.next().charAt(0);
    }

    private void showTitlePic() {
        System.out.println("888888888888888888888888888888888888888888888888888888888888");
        System.out.println("888888888888888888888888888888888888888888888888888888888888");
        System.out.println("8888888888888888888888888P" + "\"  \"" + "9888888888888888888888888888");
        System.out.println("8888888888888888P" + "88888P          988888" + "9888888888888888888");
        System.out.println("8888888888888888  " + "9888            888P" + "  888888888888888888");
        System.out.println("888888888888888888bo " + "9  d8o  o8b  P" + " od88888888888888888888");
        System.out.println("888888888888888888888bob 98  " +  "8P dod88888888888888888888888");
        System.out.println("888888888888888888888888    db    88888888888888888888888888");
        System.out.println("88888888888888888888888888      8888888888888888888888888888");
        System.out.println("88888888888888888888888P" + "9bo  odP" + "98888888888888888888888888");
        System.out.println("88888888888888888888P" + "od88888888bo " + "98888888888888888888888");
        System.out.println("888888888888888888   d88888888888888b   88888888888888888888");
        System.out.println("8888888888888888888oo8888888888888888oo888888888888888888888");
        System.out.println("8888888888888888888888888888888888888888888888888Ojo 9888888");
    }

    private void showBandit() {
        System.out.println("    ####");
        System.out.println("  ########");
        System.out.println("  ##I  I##");
        System.out.println("  ##\\ U/##");
        System.out.println("   #|  |#");
        System.out.println(" .-'\\__/'-. ");
        System.out.println("|          |");
        System.out.println("| ||    || |");
        System.out.println("| ||    || |");
        System.out.println("| ||    || |");
        System.out.println("|_||____||_|");
        System.out.println("| ||_[]_|| |");
        System.out.println("MM |    | MM");
        System.out.println("   | || |");
        System.out.println("   | || |");
        System.out.println("   | || |");
        System.out.println("   |_||_|");
        System.out.println("  _| || |");
        System.out.println("(___||___)");
    }

    private void showGuard() {
        System.out.println("        .#####.");
        System.out.println("       |_____|");
        System.out.println("     (\\#/ \\#/)");
        System.out.println("       |  U  |");
        System.out.println("       \\  _  /");
        System.out.println("        \\___/");
        System.out.println("    .---'   `---.");
        System.out.println("   /  #########  \\");
        System.out.println("  /  |####|####|  \\");
        System.out.println(" /  /\\ ####### /\\  \\");
        System.out.println("(  \\  \\  ###  /  /  )");
        System.out.println("\\  \\  \\_###_/  /  /");
        System.out.println(" \\  \\ |\\   /| /  /");
        System.out.println("    'uuu| \\_/ |uuu'");
        System.out.println("       |  |  |");
        System.out.println("       |  |  |");
        System.out.println("       |  |  |");
        System.out.println("       |  |  |");
        System.out.println("       |  |  |");
        System.out.println("       )  |  (");
        System.out.println("     .oooO Oooo.");
    }

    private void showGatekeeper() {
        System.out.println("       _____ ");
        System.out.println("      / ~~~ \\ ");
        System.out.println("      \\ `¿´ / ");
        System.out.println("    _.-\\ - /-.");
        System.out.println(" /¯¯¯¯/¯|¯|¯\\¯¯¯¯\\ ");
        System.out.println("(  ´)|´  ¯  `|(´  ) ");
        System.out.println("|   ||       ||   |");
        System.out.println(" \\   )|     |(   /");
        System.out.println("__\\==||_____||==/");
        System.out.println("--/  |+-----++:0+");
        System.out.println("--UUUU+--+--+0000");
        System.out.println("      |  |  |");
        System.out.println("      |  |  |");
        System.out.println("      |  |  |");
        System.out.println("      |__|__| ");
        System.out.println("      (__|__)");
    }

    private void showBoss() {
        System.out.println("                 _____   ");
        System.out.println("              .-\" .-. \"-.");
        System.out.println("            _/ '=(0.0)=' \\_ ");
        System.out.println("          /`   .='|m|'=.   `\\ ");
        System.out.println("          \\________________ / ");
        System.out.println("      .--.__///`'-,__~\\\\\\\\~` ");
        System.out.println("     / /6|__\\// a (__)-\\\\\\\\");
        System.out.println("     \\ \\/--`((   ._\\   ,)))  ");
        System.out.println("     /  \\\\  ))\\  -==-  (O)( ");
        System.out.println("    /    )\\((((\\   .  /))))) ");
        System.out.println("   /  _.' /  __(`~~~~`)__   ");
        System.out.println("  //\"\\\\,-'-\"`   `~~~~\\\\~~`\"-. ");
        System.out.println(" //  /`\"              `      `\\   ");
    }

    private void showPortal() {
        System.out.println("             .,-:;//;:=,");
        System.out.println("         . :H@@@MM@M#H/.,+%;,");
        System.out.println("      ,/X+ +M@@M@MM%=,-%HMMM@X/,");
        System.out.println("     -+@MM; $M@@MH+-,;XMMMM@MMMM@+-");
        System.out.println("    ;@M@@M- XM@X;. -+XXXXXHHH@M@M#@/.");
        System.out.println("  ,%MM@@MH ,@%=            .---=-=:=,.");
        System.out.println("  -@#@@@MX .,              -%HX$$%%%+;");
        System.out.println(" =-./@M@M$                  .;@MMMM@MM:");
        System.out.println(" X@/ -$MM/                    .+MM@@@M$");
        System.out.println(",@M@H: :@:                    . -X#@@@@-");
        System.out.println(",@@@MMX, .                    /H- ;@M@M=");
        System.out.println(".H@@@@M@+,                    %MM+..%#$.");
        System.out.println(" /MMMM@MMH/.                  XM@MH; -;");
        System.out.println("  /%+%$XHH@$=              , .H@@@@MX,");
        System.out.println("   .=--------.           -%H.,@@@@@MX,");
        System.out.println("   .%MM@@@HHHXX$$$%+- .:$MMX -M@@MM%.");
        System.out.println("     =XMMM@MM@MM#H;,-+HMM@M+ /MMMX=");
        System.out.println("       =%@M@M#@$-.=$@MM@@@M; %M%=");
        System.out.println("         ,:+$+-,/H#MMMMMMM@- -,");
        System.out.println("               =++%%%%+/:-.");
    }

    private void showIntro() {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣧⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣄⠀⠀⢀⣿⣤⡄⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠀⠈⠉⢻⡆⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⢀⡠⣤⣴⡶⠿⢿⠀⠀⠀⢸⢻⡀⠀⠀⠀⠀⠀⣀⣠⣤⡄⠀");
        System.out.println("⠀⠀⠀⠰⠛⠋⠉⠀⠀⠀⢸⡀⠀⠀⢸⣀⣧⣤⠴⠒⠚⠛⢻⡍⠁⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣼⠷⠖⠚⠉⠉⢹⡀⠀⠀⠀⠀⠘⣇⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠰⢞⡟⠉⣁⣠⠖⠉⠀⠀⠀⠀⢷⠀⠀⠀⠀⡀⠘⡆⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⢀⡞⣠⣾⠟⠁⠀⣀⣀⣀⣀⣀⣈⣧⣰⣺⠿⣿⣿⣿⣆⣀");
        System.out.println("⠀⠀⠀⠀⠀⢸⣽⣫⣥⣴⣶⣾⡟⠛⣉⣿⣇⣀⣽⣦⣤⠴⠚⠛⠛⢻⠁");
        System.out.println("⠀⠀⠀⠀⠀⣾⣿⣻⠟⢻⣁⣸⣧⣴⣿⣿⣿⠿⣿⣿⡄⠀⠀⠀⠀⠈⢷⡀⠀");
        System.out.println("⠀⠀⠀⢀⣀⣿⡮⠶⠶⠛⣛⡿⣿⠟⠋⠀⣿⣧⣿⠹⣵⡄⠀⢀⣤⣽⣾⣷⡀⠀");
        System.out.println("⠀⠀⣀⡈⢩⡏⢀⡠⠾⠛⠁⠀⠀⠀⠀⢠⡏⠘⣿⠀⢳⡽⡿⠿⠿⢿⣿⣷⣿⣶⠾⠇⠀");
        System.out.println("⠀⠀⠀⢹⡟⠰⠋⢀⣀⣤⣤⣶⡶⠿⠟⠛⡇⠀⡏⣇⠀⠀⠻⡶⠛⠛⠋⠁⠀⣌⠀");
        System.out.println("⠀⠀⠀⠘⣧⣶⣾⣿⣻⣿⣿⣯⣤⣤⣴⣾⡇⠀⡇⠉⠀⠀⠀⠹⣄⠀⠀⠀⠀⡇⠀");
        System.out.println("⣀⠀⠀⢠⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣾⡇⠀⡇⠀⠀⠀⠀⠀⠙⣆⠀⠀⢸⠇");
        System.out.println("⠹⡟⠶⣟⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢸⠀⠀⠀⠀⠀⠀⠘⣷⡀⢸");
        System.out.println("⠀⣷⠀⠈⠹⡟⠛⠛⣿⠛⢛⡟⠛⣿⢿⣿⡄⠀⠸⡄⠀⠀⠀⠀⠀⠀⠈⢟⣮⡀");
        System.out.println("⠀⠘⡆⠀⠀⢹⠀⠀⡏⠀⣼⠇⠀⡇⠀⢸⡇⠀⠀⣧⠀⢸⠀⠀⠀⠀⠀⠈⢿⡳⣄⠀");
        System.out.println("⠀⠀⢳⡀⠀⠈⣇⢸⠀⠀⣿⠀⠀⡇⠀⣼⢧⢸⡆⠘⣆⡞⣤⠶⠶⣶⣤⣤⣤⣹⡌⠳⣄");
        System.out.println("⠀⠀⠘⣇⠀⠀⢹⣸⠀⠀⡟⠀⠀⣧⣼⣿⢸⣹⣷⠈⠉⠉⢻⣷⣶⠶⠾⠽⣿⣿⣿⣦⠀⠙⢦⣀⣀⣤⠆");
        System.out.println("⠀⠀⠀⠸⡄⢠⣿⣏⡇⠀⡇⠀⣸⣿⣿⣿⣌⣿⣇⣀⠀⠀⢸⣿⣿⣷⣄⠀⠀⠀⠀⠉⠉⣹⣶⠟⠉⠀⠀");
        System.out.println("⠀⠀⠀⠀⢳⣸⣽⢻⣧⠀⣷⢀⣷⢿⣿⣿⡇⠀⣆⣿⣿⣿⡞⠛⠿⠿⠿⣷⣄⣀⣠⣴⠿⠋⠀⠀");
        System.out.println("⣠⣀⣀⣀⡼⢯⣿⢺⣿⡀⢹⠘⠛⢸⣿⣿⡇⣼⡟⢿⣻⣿⣿⣆⠀⠀⢀⡠⣿⣿⠟⠁⠀⠀");
        System.out.println("⠙⣯⡻⢷⣤⣼⣧⠈⣿⣧⠘⡆⠀⣾⣿⣿⣷⣿⣤⣤⡽⣿⣿⣿⣧⡴⣯⡾⠋⠀");
        System.out.println("⠀⠸⠖⠋⠉⠉⣩⡧⡽⣿⢷⣇⣀⣿⣿⡾⠿⣿⣿⠠⢶⣾⣿⣿⣷⣾⣿⠀");
        System.out.println("⠀⠀⠀⢀⣤⣿⣏⣍⣥⣽⣏⣽⣥⣬⣿⠷⠖⠉⢀⣠⡴⠿⠛⣹⣿⣿⡿⠀⠀⠀");
        System.out.println("⠀⠀⠀⠸⣷⣝⣿⣷⣶⣾⣿⣿⣿⠉⢀⣠⠤⠚⠋⠀⠀⢶⠀⢹⣿⣿⠁⡠⣤⠀⠀");
        System.out.println("⠀⠀⠀⠀⢹⣿⣙⣿⣿⣿⣿⣿⣿⣿⣟⡇⠀⠀⢀⣠⣄⣼⣤⢾⣻⠿⢛⣻⣿⣴⣤⣠⡄⠀");
        System.out.println("⠀⣠⣤⣔⡛⢿⣟⣻⣉⣁⣬⡟⠻⢿⣿⣷⠶⢶⡏⠈⢻⢀⣄⣼⢁⣠⣽⡋⣙⣴⣧⣿⣧⡄⠀");
        System.out.println("⠈⠉⠉⠙⠛⠛⠛⠋⠀⠹⠛⠷⠴⢿⣾⣾⣾⣿⣿⢿⡿⣿⣿⣿⠿⠿⣿⡿⠿⠛⠋⠉⠁⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠘⠿⠛⠙⠉⠠⠛⠻⠿⠛⠛⠁⠀⠀⠀⠀⠀⠀");
        System.out.println();
        System.out.println("========================================================");
        System.out.println("");
        System.out.println("Welcome to Pirates of Euclideans");
        System.out.println("Press enter to continue");
        console.nextLine();
    }

}
