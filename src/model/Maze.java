package model;

import java.util.Random;

public class Maze {
    private final  int myRowCount;
    private final int myColCount;
    private int[] myCurrentLoc;
    private int myCurrentRow;
    private int myCurrentCol;
    private int[] myExit;
    private int myExitRow;
    private int myExitCol;
    private int[] myPlayerSpawn;
    private int myPlayerSpawnRow;
    private int myPlayerSpawnCol;
    private Room[][] myMaze;
    private Monsters myMon;
    private int myKeyCount;

    private Random myRan;
    public Maze(int theRow, int theCol, int theMonsterA, int theMonsterB,
                int theMonsterC, int theKeyCount){

        myRowCount = theRow;
        myColCount = theCol;
        myKeyCount = theKeyCount;
        mazeGenerate();
//        System.out.println("mxn: " + myRowCount + " " + myColCount);
        myRan = new Random();

        myMon = new Monsters(theMonsterA,theMonsterB,theMonsterC);

        genMon();

        genPlayerSpawn();
        genKey();
        //conditional when all keys are found
        genExit();
        myCurrentLoc = myPlayerSpawn;
        //populate keys
    }
    void genMon(){

        //while the list isn't empty generate ran x, and y and put the monster there
        int monLeft = myMon.getmList().size();
//        System.out.println("mon size: " + myMon.getmList().size());
        while (monLeft > 0) {

            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()){
                myMaze[ranRow][ranCol].setOccupant((String) myMon.getmList().get(monLeft - 1));
                monLeft--;
            }
        }
    }

    void genExit(){
        int exit2gen = 1;
        while (exit2gen > 0) {
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()){
                myMaze[ranRow][ranCol].setOccupant("E");
                myExitRow = ranRow;
                myExitCol = ranCol;
                exit2gen--;
            }

        }
    }
    private void genPlayerSpawn(){
        int player2Spawn = 1;
        while (player2Spawn > 0){
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("P");
                myPlayerSpawnRow = ranRow;
                myPlayerSpawnCol = ranCol;
                player2Spawn--;
            }

        }
    }

    private void genKey(){
        int tempKey = myKeyCount;
        while (tempKey > 0){
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("K");
                tempKey--;
            }

        }
    }
    int getExitRow(){
        return myExitRow;
    }
    int getExitCol(){
        return myExitRow;
    }
    int getRowPos(){
        return myCurrentRow;
    }
    int getColPos(){
        return myCurrentCol;
    }
    void setPCurrent(int theRow, int theCol){
        myCurrentRow = theRow;
        myCurrentCol = theCol;
    }
    //----------maze dimension
    int getMazeR(){
        return myRowCount;
    }
    int getMazeC(){
        return myColCount;
    }

    //-----------key count to first populate the maze
    int getKeyCount(){
        return this.myKeyCount;
    }
    Monsters getMon() {
        return myMon;
    }

    void setOccupant(int theRow, int theCol, String theOccupant){
        myMaze[theRow][theCol].setOccupant(theOccupant);
    }

    String getOccupant(int theRow, int theCol){
        return myMaze[theRow][theCol].getOccupant();
    }

    private void mazeGenerate(){
        myMaze = new Room[myRowCount][myColCount];
        for (int i = 0; i < myMaze.length; i++){
            for (int j = 0 ; j < myMaze[i].length; j++) {
            myMaze[i][j] = new Room();
            }
        }
        //System.out.println("maze length: " + myMaze.length);
    }
    String roomCheckOc(int theRow, int theCol){
        return myMaze[theRow][theCol].getOccupant();
    }
    boolean roomCheckEmpty(int theRow, int theCol){
        return myMaze[theRow][theCol].isEmpty();
    }
    boolean roomCheckVisit(int theRow, int theCol){
        return myMaze[theRow][theCol].getVisited();
    }
    void roomSetVisit(int theRow, int theCol){
        myMaze[theRow][theCol].setVisited();
    }
    void roomSetEmpty(int theRow, int theCol){
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
    }
    boolean roomHasKey(int theRow, int theCol){
        return myMaze[theRow][theCol].hasKey();
    }
    void roomRemoveKey(int theRow, int theCol){
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
        this.myKeyCount--;
    }
    String room2String(int theRow, int theCol){
        return myMaze[theRow][theCol].toString();
    }
    public String m2String(){
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < myMaze.length; i++){
            sb.append ("___________________________________________\n");
            sb.append("|     |     |     |     |     |     |     |\n");
            sb.append("|  ");
            for (int j = 0 ; j < myMaze[i].length; j++) {

                if (myMaze[i][j].getOccupant() == null){
                    sb.append(" ");
                } else if (myMaze[i][j].getOccupant() == "bandit"){
                    sb.append("1");
                } else if (myMaze[i][j].getOccupant() == "guard"){
                    sb.append("2");
                } else if (myMaze[i][j].getOccupant() == "gateKeeper"){
                    sb.append("3");
                } else {
                    sb.append(myMaze[i][j].getOccupant().toString());
                }
                sb.append("  |  ");
            }
            sb.append("\n");
            sb.append("|     |     |     |     |     |     |     |\n");
        }
        sb.append ("___________________________________________\n");
        sb.append("P = player, E = exit, 1 = bandit, 2 = guard, 3 = gatekeeper, K = key\n");

        return sb.toString();
    }

}
