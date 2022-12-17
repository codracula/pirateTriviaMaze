package model;

import java.util.Random;

/**
 *  Maze class
 * @author  Jeep Naarkom
 */
public final class Maze {
    /**
     *  reusable random object.
     */
    static Random myRan;
    /**
     *  number of rows for the maze.
     */
    final  int myRowCount;
    /**
     *  number of column for the maze.
     */
    final int myColCount;
    /**
     *  current row position.
     */
    int myCurrentRow;
    /**
     *  current col position.
     */
    int myCurrentCol;
    /**
     *  exit row spawn position.
     */
    int myExitRow;
    /**
     *  exit col spawn position.
     */
    int myExitCol;
    /**
     *  player row spawn position.
     */
    int myPlayerSpawnRow;
    /**
     *  player col spawn position.
     */
    int myPlayerSpawnCol;
    /**
     *  2d array of room for the maze.
     */
    Room[][] myMaze;
    /**
     *  initialize monster class.
     */
    final Monsters myMon;
    /**
     *  initialized key count.
     */
    int myKeyCount;

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

        checkPosInt(theRow);
        checkPosInt(theCol);
        checkPosInt(theKeyCount);
        myRowCount = theRow;
        myColCount = theCol;

        checkPosInt(theMonsterA);
        checkPosInt(theMonsterB);
        checkPosInt(theMonsterC);

        if (theKeyCount >= 0 && theKeyCount <= (theRow * theCol)) {
            myKeyCount = theKeyCount;
        }

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
                myMaze[ranRow][ranCol].setOccupant(myMon.getmList().get(monLeft - 1));
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
    public int getRowPos() {
        return myCurrentRow;
    }

    /**
     *  get col of current position.
     * @return  col of current position.
     */
    public int getColPos() {
        return myCurrentCol;
    }

    /**
     *  set current position of the player.
     * @param theRow    the row position.
     * @param theCol    the col position.
     */
    void setPCurrent(final int theRow, final int theCol) {
        if (theRow >= 0 && theRow < myRowCount
                && theCol >= 0 && theCol < myColCount) {
            myCurrentRow = theRow;
            myCurrentCol = theCol;
        } else {
            System.out.println("either row or col is out of bound");
        }
    }

    /**
     *  helper method to set player location.
     * @param theRow    row position.
     * @param theCol    col position.
     */
    public void setPlayerLocation(int theRow, int theCol){
        myMaze[theRow][theCol].setOccupant("P");
        setPCurrent(theRow, theCol);
    }
    //----------maze dimension

//    /**
//     *  get maze row dimension.
//     * @return  row dimension of the maze.
//     */
//    int getMazeRow() {
//        return myRowCount;
//    }
//
//    /**
//     *  get maze col dimension.
//     * @return  col dimension of the maze.
//     */
//    int getMazeCol() {
//        return myColCount;
//    }
//
//    //-----------key count to first populate the maze
//    /**
//     *  get the key count.
//     * @return  key count.
//     */
//    int getKeyCount() {
//        return myKeyCount;
//    }

    /**
     *  get monster class.
     * @return  monster class.
     */
    Monsters getMon() {
        return myMon;
    }

    /**
     *  check if the parameters are in the range of the maze.
     * @param theRow    row position
     * @param theCol    col position
     */
    void checkRoomRange(final int theRow, final int theCol) {
        if (theRow < 0 || theRow >= myRowCount
                || theCol < 0 || theCol >= myColCount) {
            throw new IllegalArgumentException("Numbers not in range");
        }
    }
    /**
     *  set occupant for the given room position.
     * @param theRow    row position.
     * @param theCol    col position.
     * @param theOccupant   set the occupant String.
     */
    void setOccupant(final int theRow, final int theCol, final String theOccupant) {
        checkRoomRange(theRow, theCol);
        myMaze[theRow][theCol].setOccupant(theOccupant);
    }

    /**
     *  get occupant for the given room position.
     * @param theRow    row position.
     * @param theCol    col position.
     * @return  return the occupant of the room.
     */
    String getOccupant(final int theRow, final int theCol) {
        checkRoomRange(theRow, theCol);
        return myMaze[theRow][theCol].getOccupant();
    }

    /**
     *  generate the maze and initialize all the rooms.
     */
    void mazeGenerate() {
        myMaze = new Room[myRowCount][myColCount];
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                myMaze[i][j] = new Room();
            }
        }
    }

//    /**
//     *  check room for occupant.
//     * @param theRow    row position of the room.
//     * @param theCol    col position of the room.
//     * @return  occupant of the given room.
//     */
//    String roomCheckOc(final int theRow, final int theCol) {
//        checkRoomRange(theRow, theCol);
//        return myMaze[theRow][theCol].getOccupant();
//    }

    /**
     *  check if the given room position is empty.
     * @param theRow    row position.
     * @param theCol    col position.
     * @return  boolean true if the room is empty.
     */
    boolean roomCheckEmpty(final int theRow, final int theCol) {
        checkRoomRange(theRow, theCol);
        return myMaze[theRow][theCol].isEmpty();
    }

//    /**
//     *  check if the given room has been visited.
//     * @param theRow    row position.
//     * @param theCol    col position.
//     * @return  boolean true if the room has been visited.
//     */
//    boolean roomCheckVisit(final int theRow, final int theCol) {
//        checkRoomRange(theRow, theCol);
//        return myMaze[theRow][theCol].getVisited();
//    }
//
//    /**
//     *  set the room to true once visited.
//     * @param theRow    row position.
//     * @param theCol    col position.
//     */
//    void roomSetVisit(final int theRow, final int theCol) {
//        checkRoomRange(theRow, theCol);
//        myMaze[theRow][theCol].setVisited();
//    }

    /**
     *  set the room to empty.
     * @param theRow    row position.
     * @param theCol    col position.
     */
    void roomSetEmpty(final int theRow, final int theCol) {
        checkRoomRange(theRow, theCol);
        myMaze[theRow][theCol].setOccupant(null);
        myMaze[theRow][theCol].setEmpty();
    }


//    /**
//     *  remove key from the room and reduce keyCount.
//     * @param theRow    row position.
//     * @param theCol    col position.
//     */
//    void roomRemoveKey(final int theRow, final int theCol) {
//        checkRoomRange(theRow, theCol);
//        myMaze[theRow][theCol].setOccupant(null);
//        myMaze[theRow][theCol].setEmpty();
//    }

//    /**
//     *  print occupant of the room.
//     * @param theRow    row position.
//     * @param theCol    col position.
//     * @return  room's occupant string.
//     */
//    String room2String(final int theRow, final int theCol) {
//        checkRoomRange(theRow, theCol);
//        return myMaze[theRow][theCol].toString();
//    }

    /**
     *  get 2d array of rooms
     * @return  myMaze
     */
    public Room[][] getMaze() {
        return myMaze;
    }

    /**
     *  get room from coordinates
     * @param theRow    the row position.
     * @param theCol    the col position.
     * @return  room.
     */
    public Room getRoom(final int theRow, final int theCol) {
        return myMaze[theRow][theCol];
    }

    void checkPosInt(int theValue) {
        if (theValue < 0) {
            throw new IllegalArgumentException("The entered value must be greater than 0.");
        }
    }

}
