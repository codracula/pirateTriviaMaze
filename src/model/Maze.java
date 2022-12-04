package model;

import java.util.Random;

public final class Maze {
    /**
     *  reusable random object.
     */
    private static Random myRan;
    /**
     *  number of rows for the maze.
     */
    private final  int myRowCount;
    /**
     *  number of column for the maze.
     */
    private final int myColCount;
    /**
     *  current row position.
     */
    private int myCurrentRow;
    /**
     *  current col position.
     */
    private int myCurrentCol;
    /**
     *  exit row spawn position.
     */
    private int myExitRow;
    /**
     *  exit col spawn position.
     */
    private int myExitCol;
    /**
     *  player row spawn position.
     */
    private int myPlayerSpawnRow;
    /**
     *  player col spawn position.
     */
    private int myPlayerSpawnCol;
    /**
     *  2d array of room for the maze.
     */
    private Room[][] myMaze;
    /**
     *  initialize monster class.
     */
    private final Monsters myMon;
    /**
     *  initialized key count.
     */
    private int myKeyCount;

    /** constructor to build the maze.
     *
     * @param theRow the row dimension of the maze.
     * @param theCol the col dimension of the maze.
     * @param theMonsterA number of monsterA.
     * @param theMonsterB number of monsterB.
     * @param theMonsterC number of monsterC.
     * @param theKeyCount number of key generated for the maze.
     */
    public Maze(final int theRow, final int theCol, final int theMonsterA, final int theMonsterB,
                final int theMonsterC, final int theKeyCount) {

        myRowCount = theRow;
        myColCount = theCol;
        myKeyCount = theKeyCount;
        mazeGenerate();
        myRan = new Random();
        myMon = new Monsters(theMonsterA, theMonsterB, theMonsterC);
        populateRoom();
        myCurrentRow = myPlayerSpawnRow;
        myCurrentCol = myPlayerSpawnCol;
    }

    /**
     *  populate monsters, player, key and position.
     */
    void populateRoom() {
        genMon();
        genPlayerSpawn();
        genKey();
//        genExit();
    }

    /**
     *  generate monsters by taking a list and populate the maze.
     */
    void genMon() {
        //while the list isn't empty generate ran x, and y and put the monster there
        int monLeft = myMon.getmList().size();

        while (monLeft > 0) {

            final int ranRow = myRan.nextInt(myRowCount);
            final int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant((String) myMon.getmList().get(monLeft - 1));
                monLeft--;
            }
        }
    }

    /**
     *  generate exit on the maze.
     */
    void genExit() {
        int exit2gen = 1;
        while (exit2gen > 0) {
            final int ranRow = myRan.nextInt(myRowCount);
            final int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("E");
                myExitRow = ranRow;
                myExitCol = ranCol;
                exit2gen--;
            }
        }
    }

    /**
     *  generate player starting position.
     */
    private void genPlayerSpawn() {
        int player2Spawn = 1;
        while (player2Spawn > 0) {
            final int ranRow = myRan.nextInt(myRowCount);
            final int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("P");
                myPlayerSpawnRow = ranRow;
                myPlayerSpawnCol = ranCol;
                setPCurrent(ranRow, ranCol);
                player2Spawn--;
            }

        }
    }

    /**
     *  generate all the keys on the maze.
     */
    private void genKey() {
        int tempKey = myKeyCount;
        while (tempKey > 0) {
            final int ranRow = myRan.nextInt(myRowCount);
            final int ranCol = myRan.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("K");
                tempKey--;
            }
        }
    }

    /**
     *  get row position for the exit.
     * @return  row position.
     */
    int getExitRow() {
        return myExitRow;
    }

    /**
     *  get col position for the exit.
     * @return  col position.
     */
    int getExitCol() {
        return myExitCol;
    }

    /**
     *  get row of current position.
     * @return  row of current position.
     */
    int getRowPos() {
        return myCurrentRow;
    }

    /**
     *  get col of current position.
     * @return  col of current position.
     */
    int getColPos() {
        return myCurrentCol;
    }

    /**
     *  set current position of the player.
     * @param theRow    the row position.
     * @param theCol    the col position.
     */
    void setPCurrent(final int theRow, final int theCol) {
        if (theRow > 0 && theCol > 0) {
            myCurrentRow = theRow;
            myCurrentCol = theCol;
        }
    }
    //----------maze dimension

    /**
     *  get maze row dimension.
     * @return  row dimension of the maze.
     */
    int getMazeRow() {
        return myRowCount;
    }

    /**
     *  get maze col dimension.
     * @return  col dimension of the maze.
     */
    int getMazeCol() {
        return myColCount;
    }

    //-----------key count to first populate the maze

    /**
     *  get the key count.
     * @return  key count.
     */
    int getKeyCount() {
        return myKeyCount;
    }

    /**
     *  get monster class.
     * @return  monster class.
     */
    Monsters getMon() {
        return myMon;
    }

    /**
     *  set occupant for the given room position.
     * @param theRow    row position.
     * @param theCol    col position.
     * @param theOccupant   set the occupant String.
     */
    void setOccupant(final int theRow, final int theCol, final String theOccupant) {
        myMaze[theRow][theCol].setOccupant(theOccupant);
    }

    /**
     *  get occupant for the given room position.
     * @param theRow    row position.
     * @param theCol    col position.
     * @return  return the occupant of the room.
     */
    String getOccupant(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].getOccupant();
    }

    /**
     *  generate the maze and initialize all the rooms.
     */
    private void mazeGenerate() {
        myMaze = new Room[myRowCount][myColCount];
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                myMaze[i][j] = new Room();
            }
        }
    }

    /**
     *  check room for occupant.
     * @param theRow    row position of the room.
     * @param theCol    col position of the room.
     * @return  occupant of the given room.
     */
    String roomCheckOc(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].getOccupant();
    }

    /**
     *  check if the given room position is empty.
     * @param theRow    row position.
     * @param theCol    col position.
     * @return  boolean true if the room is empty.
     */
    boolean roomCheckEmpty(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].isEmpty();
    }
    boolean roomCheckVisit(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].getVisited();
    }
    void roomSetVisit(final int theRow, final int theCol) {
        myMaze[theRow][theCol].setVisited();
    }
    void roomSetEmpty(final int theRow, final int theCol) {
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
    }
    boolean roomHasKey(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].hasKey();
    }
    void roomRemoveKey(final int theRow, final int theCol) {
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
        myKeyCount--;
    }
    String room2String(final int theRow, final int theCol) {
        return myMaze[theRow][theCol].toString();
    }
    public String m2String() {
        final StringBuilder sb = new StringBuilder();
        final String breakLine = "___________________________________________\n";
        final String breakRoom = "|     |     |     |     |     |     |     |\n";
        for (Room[] rooms : myMaze) {

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

        return sb.toString();
    }

}
