package view;

import model.Character;
import model.Maze;
import model.Question;
import model.Room;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GameView {

    /**
     * allows for user input
     */
    private final Scanner myConsole = new Scanner(System.in);
    /**
     * initialize the character class that user chooses in the beginning
     */
    private String myCharacterClass;
    /**
     * initialize the name of the user
     */
    private String myPlayerName;
    /**
     * initialize the number of correct answers
     */
    private int myCorrectQ;
    /**
     * initialize the number of wrong answers
     */
    private int myWrongQ;

    /**
     * displays the intro as well as the menu
     */
    public void showTitleScreen() {
        showIntro();
        showStory();
        System.out.println("=================================");
        System.out.println("Please select an option by entering an integer.");
        System.out.println("1) Start game");
        System.out.println("2) Load game");
        System.out.println("3) Options");
        System.out.println("4) Quit \n");
    }

    /**
     * prints out the menu options
     */
    public void showOptionMenu() {
        System.out.println("Options Menu");
        System.out.println("=================================");
        System.out.println("1) Stop music");
        System.out.println("2) Play music");
        System.out.println("3) Back to main menu \n");
    }

    /**
     * gets the user's name and asks for class choice
     */
    public void showSelection() {
        System.out.print("Let's get started!" + "\n" + "What is your name: ");
        myPlayerName = myConsole.nextLine();
        System.out.println();
        System.out.println("Alright " + myPlayerName + ", choose a class:");
    }

    /**
     * shows class options and gets user's choice
     * @return the characterClass the user chooses
     */
    public String showCharacterClasses() {
        boolean myFlag;
        myFlag = false;

        while(!myFlag) {
            System.out.println("Merchant: Savvy with math to calculate business");
            System.out.println("Sailor: Well versed in the sea and land");
            System.out.println("Headhunter: Clever and witty \n");
            System.out.println("Please type in the class you want to play as. \n");

            myCharacterClass = myConsole.nextLine();
            switch (myCharacterClass.toLowerCase()) {
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
        return myCharacterClass;
    }

    /**
     * gets the category from the characterClass
     * @return a string of the category which is determined by characterClass
     */
    public String getCategory() {
        String category = "";
        if (Objects.equals(myCharacterClass, "Merchant")) {
            category = "Arithmetic";
        } else if (Objects.equals(myCharacterClass, "Sailor")) {
            category = "Geography";
        } else if (Objects.equals(myCharacterClass, "Headhunter")) {
            category = "Riddle";
        } else {
            category = "";
        }
        return category;
    }

    /**
     * shows options for movement if possible and gets user input
     * @param theMaze the maze being traversed
     * @return char action that is the user's choice of movement
     */
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

        System.out.println("K) Stop music");
        System.out.println("L) Play music");

        char action = myConsole.next().charAt(0);
        return action;
    }

    /**
     * displays character information
     * @param theCharacter the game representation of the user
     */
    private void showCharacterInfo(final Character theCharacter) {
        System.out.println("Name: " + myPlayerName);
        System.out.println("Class: " + myCharacterClass);
        System.out.println("Lives: " + theCharacter.getLives());
    }

    /**
     * displays the maze in an ascii fashion
     * @param theMaze the maze being traversed
     */
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

    /**
     * displays the contents of the room
     * @param theRoom the current room the user is in or intends to go in
     */
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

    /**
     * shows the result of a successful monster encounter
     * @param theMonType the type of monster that was encountered
     */
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
            System.out.println("You go past the gatekeeper and enter the portal");
            myConsole.nextLine();
            System.out.println("press enter to go through");
            showPortal();
            System.out.println();
            System.out.println("You've never gotten this far in the game before, you're not sure what to expect");
            System.out.println("You see a figure looming");
            System.out.println("It's the actual boss");
            showBoss();
        }
    }

    /**
     * displays the inventory of the user
     * @param thePlayer the game representation of the user
     */
    public void showInventory(final Character thePlayer) {
        System.out.println();
        System.out.println("Number of keys: " + thePlayer.getKeyCount());
        System.out.println("Number of hint passes: " + thePlayer.getHintpassCount());
        System.out.println("Lives: " + thePlayer.getLives());
    }

    /**
     * displays when the user can exit the maze
     */
    public void canExit() {
        System.out.println("You found all four keys!");
        System.out.println("The exit has appeared. Find the way out!");
    }

    /**
     * displays the question to be asked and gets the user's answer
     * @param theQuestion the question being asked
     * @param thePlayer the user
     * @return a numerical representation of the possible outcomes
     * correct = 0 user answered incorrectly
     * correct = 1 user answered correctly
     */
    public int showQuestion(final Question theQuestion, final Character thePlayer) {
        System.out.println(theQuestion.getMyQuestion());
        ArrayList<String> choices = theQuestion.setChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
        System.out.println("5: Use a hint pass, you have " + thePlayer.getHintpassCount() + " remaining");
        int correct = 0;
        System.out.println("Enter your answer as a number");
        int userAnswer = myConsole.nextInt();
        if (userAnswer == 5) {
            correct = 2;
        } else {
            correct = showCorrect(theQuestion, choices, userAnswer);
        }
        return correct;
    }

    //0 = wrong answer, try again
    //1 = correct answer
    //2 = used hint pass
    //3 = no hint passes to use
    /**
     * displays what happens if user uses a hintpass
     * @param theQuestion the question being asked
     * @param thePlayer the user
     * @return a numerical value representing a scenario
     */
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
            int userAnswer = myConsole.nextInt();
            correct = showCorrect(theQuestion, hintPassChoices, userAnswer);
        }
        return correct;
    }

    /**
     * displays if user's answer was correct or not
     * @param theQuestion the question being asked
     * @param theChoices the multiple choices
     * @param theUserAnswer user's answer as an in
     * @return a numerical value representing if the user was correct or not
     */
    private int showCorrect(final Question theQuestion, final ArrayList<String> theChoices, final int theUserAnswer) {
        int correct = 0;
        if (theChoices.get(theUserAnswer - 1).equals(theQuestion.getMyAnswer())) {
            System.out.println("Yay, you're correct!");
            correct = 1;
            myCorrectQ++;
        } else {
            System.out.println("Boo you're wrong! -1 life");
            System.out.println("Try again");
            myWrongQ++;
        }
        System.out.println();
        return correct;
    }

    /**
     * displays the summary of user's achievements in game
     * @param thePlayer
     */
    private void showSummary(final Character thePlayer) {
        System.out.println("Name: " + myPlayerName);
        System.out.println("Class: " + myCharacterClass);
        System.out.println("Keys collected: " + thePlayer.getKeyCount());
        System.out.println("Correct answers: " + myCorrectQ);
        System.out.println("Incorrect answers: " + myWrongQ);
    }

    /**
     * displays game over, both good and bad endings
     * @param thePlayer the user
     * @return char representing if the user wishes to continue playing
     */
    public void showGameOver(final Character thePlayer) {
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
            System.out.println();
            System.out.println("Guess you won't be going home any time soon");
        } else {
            System.out.println("The boss looks at you in a way you can' quite place");
            System.out.println("Before you can react, the portal surges behind you and engulfs you");
            System.out.println("When you manage to open your eyes, you find yourself back in your room");
            System.out.println();
            System.out.println("Against all odds you have bested the game and returned home to your soft bed");
            System.out.println("Congratulations!");
            System.out.println();
            System.out.println("   ||");
            System.out.println("   ||                   ||");
            System.out.println("||/||___                ||");
            System.out.println("|| /`   )____________||_/|");
            System.out.println("||/___ //_/_/_/_/_/_/||/ |");
            System.out.println("||(___)/_/_/_/_/_/_/_||  |");
            System.out.println("||     |_|_|_|_|_|_|_|| /|");
            System.out.println("||     | | | | | | | ||/||");
            System.out.println("||~~~~~~~~~~~~~~~~~~~||");
            System.out.println("||                   ||  ");
            System.out.println();
            System.out.println("You've returned back to your room, everything is as you left it");
            System.out.println("Your project code is still blinking on your computer");
            System.out.println("Going over to your desk, you turn off your computer and opt for your bed instead");
            System.out.println("After all, you worked pretty hard. You deserve a rest");
        }
        System.out.println("=================================\n");
        showSummary(thePlayer);
        System.exit(0);
    }

    /**
     * displays story
     */
    private void showStory() {
        System.out.println();
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("Before the game fully starts you shut it off");
        System.out.println("As much as you'd love to play, you have far too much work to do");
        System.out.println("You try to blink away the sleepiness as best you could");
        System.out.println("After all, you had to turn in your project soon and the deadline was coming up");
        System.out.println("But as you try to keep coding, your eyes grow heavier and heavier until they close");
        System.out.println("press enter to continue");
        myConsole.nextLine();
        showPortal();
        System.out.println("You wake up to the harsh abrasion of stone against your skin");
        System.out.println("Confused, you open your eyes, you don't recognize your surroundings right away");
        System.out.println("Rather than your normally cluttered room, it appears you're in a pirate's lair");
        System.out.println("Realizations slowly dawns on you as it's an environment you've seen before");
        System.out.println("It looks like you're inside your favorite game Pirates of the Euclideans");
        System.out.println("press enter to continue");
        myConsole.nextLine();
        System.out.println("As you look for a way out, you notice a message on the wall");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("For you who does too much work and no play");
        System.out.println("You will not be able to return to your world unless you beat this world");
        System.out.println("Seek out the four keys to reveal the exit");
        System.out.println("Traverse through the pirate's lair, but be wary of its dwellers");
        System.out.println("------------------------------------------------------------------------");
        System.out.println();
        showFlag();
    }

    /**
     * displays a cool pirate flag in ascii
     */
    private void showFlag() {
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

    /**
     * displays an ascii art bandit
     */
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

    /**
     * displays an ascii art guard
     */
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

    /**
     * displays an ascii art gatekeeper
     */
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

    /**
     * displays an ascii art of the boss
     */
    private void showBoss() {
        System.out.println();
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
        System.out.println();
        System.out.println("Hold on, I won't let you go just yet!");
        System.out.println("You've got a good noggin,");
    }

    /**
     * displays an ascii art of the portal exit
     */
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

    /**
     * displays ascii art of the intro scene
     */
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
        myConsole.nextLine();
    }

}
