package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.GameView;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MazeTest {

    @Test
    void testConstructor() {  //done
        Maze maze1 = new Maze(6,5,3,2,1,4);
        assertEquals(6, maze1.myRowCount, "Row count doesn't equal.");
        assertEquals(5, maze1.myColCount, "Col count doesn't equal.");
        assertEquals(4, maze1.myKeyCount, "Key count doesn't equal.");
        assertEquals(3, maze1.myMon.myM_AmaxCount, "monster A count doesn't equal.");
        assertEquals(2, maze1.myMon.myM_BmaxCount, "monster B count doesn't equal.");
        assertEquals(1, maze1.myMon.myM_CmaxCount, "monster C count doesn't equal.");
        //test mymonster
        assertNotNull(maze1.myMon.myMList.size(), "My monster list is null.");
        int monsterCount = 0;
        for(int i = 0; i < maze1.myMon.myMList.size(); i ++){
            monsterCount++;
        }
        assertEquals(monsterCount, maze1.myMon.myM_AmaxCount
                    + maze1.myMon.myM_BmaxCount
                    + maze1.myMon.myM_CmaxCount, "My monster total isn't equal.");

        //test mazeGenerate() and populateroom() in their respective test cases

    }
    @Test
    void testPopulateRoom() {  //done
        Maze maze2 = new Maze(6,5,3,2,1,4);
        //count key, player, monster altogether
        int itemCount = 0;
        for (int i = 0; i < maze2.myRowCount; i++) {
            for (int j = 0; j < maze2.myColCount; j++) {
                if (maze2.getOccupant(i,j) == "bandit"
                        || maze2.getOccupant(i,j) == "guard"
                        || maze2.getOccupant(i,j) == "gateKeeper"
                        || maze2.getOccupant(i,j) == "K"
                        || maze2.getOccupant(i,j) == "P"){
                    itemCount++;
                }
            }
        }
        assertEquals(11, itemCount, "Not all monsters, player,keys are in the maze");
    }
    
    @Test
    void testGenMon() {  //done

        Maze maze3 = new Maze(6,5,3,2,1,4);
        //maze3.genMon();
        int monCount = 0;
        for (int i = 0; i < maze3.myRowCount; i++) {
            for (int j = 0; j < maze3.myColCount; j++) {
                if (maze3.getOccupant(i,j) == "bandit"
                                    || maze3.getOccupant(i,j) == "guard"
                                    || maze3.getOccupant(i,j) == "gateKeeper"){
                    monCount++;
                }
            }
        }
        assertEquals(6, monCount, "Not all monsters are in the maze");

    }
    @Test
    void testGenExit() {  //done
        Maze maze4 = new Maze(6,5,3,2,0,4);
        maze4.genExit();
        int exitCount = 0;
        for (int i = 0; i < maze4.myRowCount; i++) {
            for (int j = 0; j < maze4.myColCount; j++) {
                if (maze4.getOccupant(i,j) == "E"){
                    exitCount++;
                }
            }
        }
        assertEquals(1, exitCount, "Exit is not generated correctly");
    }
    @Test
    void testGenPlayerSpawn() {  //done
        Maze maze5 = new Maze(6,5,3,2,0,4);
//        maze5.genExit();
        int player = 0;
        for (int i = 0; i < maze5.myRowCount; i++) {
            for (int j = 0; j < maze5.myColCount; j++) {
                if (maze5.getOccupant(i,j) == "P"){
                    player++;
                }
            }
        }
        assertEquals(1, player, "Player is not generated correctly");
    }
    @Test
    void testGenKey() {  //done
        Maze maze6 = new Maze(6,5,3,2,0,4);

        int key = 0;
        for (int i = 0; i < maze6.myRowCount; i++) {
            for (int j = 0; j < maze6.myColCount; j++) {
                if (maze6.getOccupant(i,j) == "K"){
                    key++;
                }
            }
        }
        assertEquals(4, key, "Keys are not generated correctly");


    }
    @Test
    void testGetExitRow() {  //done
        Maze maze7 = new Maze(6,5,3,2,0,4);
        maze7.genExit();
        int exitRowFound = -5;
        int exitColFound = -5;
        Boolean inRange = false;    //check if exit coordinate is in the range of the maze
        for (int i = 0; i < maze7.myRowCount; i++) {
            for (int j = 0; j < maze7.myColCount; j++) {
                if (maze7.getOccupant(i,j) == "E"){
                    exitRowFound = i;
                    exitColFound = j;
                    inRange = true;
                }

            }
        }
        assertTrue(inRange, "exit room row isn't in the range of the maze");
        assertNotEquals(-5, exitRowFound, "Exit row doesn't exist in the maze");
    }
    @Test
    void testGetExitCol() {  //done
        Maze maze8 = new Maze(6,5,3,2,0,4);
        maze8.genExit();
        int exitRowFound = -5;
        int exitColFound = -5;
        Boolean inRange = false;    //check if exit coordinate is in the range of the maze
        for (int i = 0; i < maze8.myRowCount; i++) {
            for (int j = 0; j < maze8.myColCount; j++) {
                if (maze8.getOccupant(i,j) == "E"){
                    exitRowFound = i;
                    exitColFound = j;
                    inRange = true;
                }
            }
        }
        assertTrue(inRange, "exit room col isn't in the range of the maze");
        assertNotEquals(-5, exitColFound, "Exit col doesn't exist in the maze");
    }
    @Test
    void testGetRowPos() {  //done
        Maze maze8 = new Maze(6,5,3,2,0,4);
        int currentRowFound = -5;

        Boolean inRange = false;    //check if exit coordinate is in the range of the maze
        for (int i = 0; i < maze8.myRowCount; i++) {
            for (int j = 0; j < maze8.myColCount; j++) {
                if (maze8.getOccupant(i,j) == "P"){
                    currentRowFound = i;

                    inRange = true;
                }
            }
        }
        assertTrue(inRange, "current row isn't in the range of the maze");
        assertNotEquals(-5, currentRowFound, "Current row position doesn't exist in the maze");
    }
    @Test
    void testGetColPos() {  //done
        Maze maze9 = new Maze(6,5,3,2,0,4);

        int currentColFound = -5;
        Boolean inRange = false;    //check if exit coordinate is in the range of the maze
        for (int i = 0; i < maze9.myRowCount; i++) {
            for (int j = 0; j < maze9.myColCount; j++) {
                if (maze9.getOccupant(i,j) == "P"){

                    currentColFound = j;
                    inRange = true;
                }
            }
        }
        assertTrue(inRange, "current col isn't in the range of the maze");
        assertNotEquals(-5, currentColFound, "Current col position doesn't exist in the maze");
    }
    @Test
    void testSetPCurrent() {  //done
        Maze maze10 = new Maze(6,5,3,2,0,4);
        maze10.setPCurrent(2,3);

        Boolean rowNew = false;    //check if player coordinate is in the range of the maze
        Boolean colNew = false;    //check if player coordinate is in the range of the maze
        if (maze10.getRowPos() == 2){
            rowNew = true;
        }
        if (maze10.getColPos() == 3){
            colNew = true;
        }
        assertTrue(rowNew, "Player row not in given position");
        assertTrue(colNew, "Player col not in given position");

        //test move player to different values
        maze10.setPCurrent(5,4);
        Boolean rowNew2 = false;    //check if player coordinate is in the range of the maze
        Boolean colNew2 = false;    //check if player coordinate is in the range of the maze
        if (maze10.getRowPos() == 5){
            rowNew2 = true;
        }
        if (maze10.getColPos() == 4){
            colNew2 = true;
        }
        assertTrue(rowNew2, "Player row not in given position");
        assertTrue(colNew2, "Player col not in given position");
    }
    @Test
    void testSetPlayerLocation() {  //done
        Maze maze11 = new Maze(6,5,3,2,0,4);
        clearRoom(maze11, 6,5);
        maze11.setPlayerLocation(1,4);

        boolean inRange = false;
        int playerRowFound = -5;
        int playerColFound = -5;
        for (int i = 0; i < maze11.myRowCount; i++) {
            for (int j = 0; j < maze11.myColCount; j++) {
                if (maze11.getOccupant(i,j) == "P"){
                    playerRowFound = i;
                    playerColFound = j;
                    inRange = true;
                }
            }
        }
        assertTrue(inRange, "Player isn't not in the maze's range");
        assertEquals(1, playerRowFound, "Player is not generated correctly");
        assertEquals(4, playerColFound, "Player is not generated correctly");

    }
    @Test
    void testGetMon() {  //done
        Maze maze12 = new Maze(6,5,3,2,0,4);

        assertEquals(5, maze12.myMon.myMList.size(), "Key count doesn't equal.");
        assertEquals(5, maze12.myMon.getmList().size(), "Key count doesn't equal.");
        assertEquals(3, maze12.myMon.myM_AmaxCount, "Key count doesn't equal.");
        assertEquals(2, maze12.myMon.myM_BmaxCount, "Key count doesn't equal.");
        assertEquals(0, maze12.myMon.myM_CmaxCount, "Key count doesn't equal.");

    }
    @Test
    void testCheckRoomRange() {  //done
        Maze maze13 = new Maze(6,5,3,2,0,4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze13.checkRoomRange(-500,80);
            });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze13.checkRoomRange(4,-80);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze13.checkRoomRange(-500,-80);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze13.checkRoomRange(-500,4);
        });
    }
    @Test
    void testSetOccupant() {  //done
        Maze maze14 = new Maze(6,5,3,2,0,4);
        maze14.setOccupant(2,4,"yahoo");
        boolean inRange = false;
        int occupantRow = -5;
        int occupantCol = -5;
        for (int i = 0; i < maze14.myRowCount; i++) {
            for (int j = 0; j < maze14.myColCount; j++) {
                if (maze14.getOccupant(i,j) == "yahoo"){
                    occupantRow = i;
                    occupantCol = j;
                    inRange = true;
                }
            }
        }
        assertTrue(inRange, "occupant is in the maze");
        assertEquals(2, occupantRow, "row doesn't match.");
        assertEquals(4, occupantCol, "col doesn't match.");
    }
    @Test
    void testGetOccupant() {  //done
        Maze maze15 = new Maze(6,5,3,2,0,4);
        maze15.setOccupant(2,4,"opt");

        assertEquals("opt", maze15.getOccupant(2,4), "Occupant doesn't match.");

    }
    @Test
    void testMazeGenerate() {  //done
        Maze maze16 = new Maze(6,5,3,2,0,4);

        int nullCount = 0;
        for (int i = 0; i < maze16.myRowCount; i ++) {
            for (int j = 0; j < maze16.myColCount; j ++) {
                if (maze16.myMaze[i][j] == null) {
                    nullCount++;
                }
            }
        }
        assertEquals(0, nullCount, "Null count not zero, some rooms aren't created properly");

    }
    @Test
    void testRoomCheckEmpty() {  //done
        Maze maze17 = new Maze(6,5,3,2,0,4);
        int emptyRoom = (6*5)-(3+2+0+4);
        int emptyCount = 0;
        for (int i = 0; i < maze17.myRowCount; i ++) {
            for (int j = 0; j < maze17.myColCount; j ++) {
                //count all occupied rooms + player room
                if (maze17.roomCheckEmpty(i,j) || maze17.getOccupant(i,j) == "P") {
                    emptyCount++;
                }
            }
        }
        assertEquals(emptyRoom, emptyCount, "Empty rooms do not match");
    }
    @Test
    void testRoomSetEmpty() {  //done
        Maze maze18 = new Maze(6,5,3,2,0,4);
        maze18.setOccupant(2,2, "item");
        assertFalse(maze18.myMaze[2][2].isEmpty(), "Room is empty.");
        maze18.roomSetEmpty(2,2);
        assertTrue(maze18.myMaze[2][2].isEmpty(), "Room is not empty.");
    }
    @Test
    void testGetMaze() {  //done
        Maze maze19 = new Maze(6,5,3,2,0,4);

        //return maze
        assertEquals(maze19.myMaze, maze19.getMaze(), "Mazes are not the same.");
    }
    @Test
    void testGetRoom() {  //done
        Maze maze20 = new Maze(6,5,3,2,0,4);
        maze20.setOccupant(2,3,"me");
        assertEquals("me", maze20.getRoom(2,3).getOccupant(), "Occupant doesn't match.");
        assertFalse(maze20.getRoom(2,3).isEmpty(), "Room is empty.");
        assertEquals(maze20.myMaze[2][3], maze20.getRoom(2,3), "Key count doesn't equal.");

    }
    @Test
    void testCheckPosInt() {  //
        Maze maze21 = new Maze(6,5,3,2,0,4);
        final int num = -14;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maze21.checkPosInt(num);;
        });

    }
    //helper function
    static void clearRoom(Maze maze, int theRow, int theCol){
    for (int i = 0; i < theRow; i++) {
        for (int j = 0; j < theCol; j++) {
            maze.myMaze[i][j].setEmpty();
        }
    }
    }
}
