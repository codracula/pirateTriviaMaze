package model;

import java.util.Random;

public class Maze {
    private final int myRowCount;
    private final int myColCount;
    private int[] myCurrentLoc;
    private int[] myExit;
    private int[] myPlayerSpawn;
    private Room[][] myMaze;
    private Monster mon;
    private int myKeyCount;

    private Random myRan;
    public Maze(int theRow, int theCol, int theMonsterA, int theMonsterB,
                int theMonsterC, int theKeyCount){
        myCurrentLoc = new int[10];
        myRowCount = theRow;
        myColCount = theCol;
        myKeyCount = theKeyCount;
        mazeGenerate();
//        System.out.println("mxn: " + myRowCount + " " + myColCount);
        myRan = new Random();
        mon = new Monster(theMonsterA,theMonsterB,theMonsterC);
        genMon();
        genPlayerSpawn();
        genKey();
        //conditional when all keys are found
        genExit();
        myCurrentLoc = myPlayerSpawn;
        //populate keys
    }
    private void genMon(){

        //while the list isn't empty generate ran x, and y and put the monster there
        int monLeft = mon.getmList().size();
        //System.out.println("mon size: " + mon.getmList().size());
        while (monLeft > 0) {
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()){
                myMaze[ranRow][ranCol].setOccupant((String) mon.getmList().get(monLeft - 1));
                monLeft--;
            }
        }
    }

    private void genExit(){
        int exit2gen = 1;

        while (exit2gen > 0) {
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()){
                myMaze[ranRow][ranCol].setOccupant("E");
                myExit = new int[2];
                myExit[0] = ranRow;
                myExit[1] = ranCol;
            }
            exit2gen--;
        }
    }
    private void genPlayerSpawn(){
        int player2Spawn = 1;
        while (player2Spawn > 0){
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("P");
                myPlayerSpawn = new int[2];
                myPlayerSpawn[0] = ranRow;
                myPlayerSpawn[1] = ranCol;
            }
            player2Spawn--;
        }
    }

    private void genKey(){
        int tempKey = myKeyCount;
        while (tempKey > 0){
            int ranRow = myRan.nextInt(myRowCount);
            int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("K");

            }
            tempKey--;
        }
    }
    protected int getExitPosR(){
        return this.myExit[0];
    }
    protected int getExitPosL(){
        return this.myExit[1];
    }
    protected int getRowPos() {
            return this.myCurrentLoc[0];
    }
    protected int getColPos(){
        return this.myCurrentLoc[1];
    }
    protected void setPCurrent(int thePos, int theValue){
        this.myCurrentLoc[thePos] = theValue;
    }
    protected int getMazeR(){
        return this.myRowCount;
    }
    protected int getMazeC(){
        return this.myColCount;
    }
    protected int getKeyCount(){
        return this.myKeyCount;
    }
    protected Monster getMon() {
        return mon;
    }

    void mazeGenerate(){
        myMaze = new Room[myRowCount][myColCount];
        for (int i = 0; i < myMaze.length; i++){
            for (int j = 0 ; j < myMaze[i].length; j++) {
            myMaze[i][j] = new Room(null);
            }
        }
        //System.out.println("maze length: " + myMaze.length);
    }
    protected String getOccupant(int theRow, int theCol){
        return myMaze[theRow][theCol].getOccupant();
    }
    protected void setOccupant(int theRow, int theCol, String theOccupant){
        myMaze[theRow][theCol].setOccupant(theOccupant);
    }

    protected boolean roomHasKey(int theRow, int theCol){
        return myMaze[theRow][theCol].hasKey();
    }
    public String roomCheckOc(int theRow, int theCol){
        return myMaze[theRow][theCol].getOccupant();
    }
    public boolean roomCheckEmpty(int theRow, int theCol){
        return myMaze[theRow][theCol].isEmpty();
    }
    public boolean roomCheckVisit(int theRow, int theCol){
        return myMaze[theRow][theCol].getVisited();
    }
    public void roomSetVisit(int theRow, int theCol){
        myMaze[theRow][theCol].setVisited();
    }
    public void roomSetEmpty(int theRow, int theCol){
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
    }

    protected void roomRemoveKey(int theRow, int theCol){
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
        this.myKeyCount--;
    }
    protected String room2String(int theRow, int theCol){
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
