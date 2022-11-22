import java.util.Scanner;

public class TitleScreen {

    private static final Scanner nScan = new Scanner(System.in);
    private static String name;

    public static void main(String[] args){

        new TitleScreen();

    }
    TitleScreen(){
        promptTitle();
        menu();
    }

    public static void promptTitle(){
        System.out.println("Welcome to Trivia Maze!");
        System.out.println("=================================");
        System.out.println("Please select an option by entering an integer.");
        System.out.println("1) Start game");
        System.out.println("2) Load game");
        System.out.println("3) Options");
        System.out.println("4) Quit \n");
    }

    public static void menu() {

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

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

    public static void optionMenu(){
        Scanner scanner = new Scanner(System.in);


        int choice = scanner.nextInt();
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

//    public static void loadGame(){
//        try{
//            ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
//            maze = new Maze(5, 5);
//            maze.loadMaze(in);
//            //myPlayer = new Player(5);
//            //myPlayer.loadPlayer(in);
//            in.close();
//        }catch(Exception e){System.out.println(e);}
//    }

//    public void loadMaze(ObjectInputStream theIn) throws IOException, ClassNotFoundException {
//        myMaze = (Room[][]) theIn.readObject();
//        myRows = (int) theIn.readObject();
//        myColumns = (int) theIn.readObject();
//        myArrayMaze = (char[][]) theIn.readObject();
//        mySpawnColMaze = (int) theIn.readObject();
//        mySpawnRowMaze = (int) theIn.readObject();
//        mySpawnColLargeMaze = (int) theIn.readObject();
//        mySpawnRowLargeMaze = (int) theIn.readObject();
//    }
    public static void startGame(){
        System.out.print("Let's get started!" +"\n"+ "What is your name: ");
        name = nScan.nextLine();
        System.out.print("Let's get the adventure going, " + name + "!");
    }
    public static String getName() {
        return name;
    }


}