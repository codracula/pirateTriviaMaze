import controller.GameController;
import model.GameModel;
import model.Maze;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        final int mazeRow = 4;
        final int mazeCol = 7;
        final int monsterA_count = 6;
        final int monsterB_count = 2;
        final int monsterC_count = 0;
        final int keyCount = 4;

        GameModel Model = new GameModel();
        Maze myMaze = new Maze(mazeRow, mazeCol, monsterA_count, monsterB_count, monsterC_count, keyCount);
        GameController theController = new GameController(Model);

        System.out.println("myPlayerName: " + Model.getMyPlayerName());
        System.out.println("myPlayerClassName: " + Model.getMyPlayerClass());
        System.out.println("spawnRow: "+Model.getPlayerRow());
        System.out.println("spawnCol: "+Model.getPlayerCol());
        System.out.println("ROWS1: " + myMaze.getMyCurrentRow());
        System.out.println("COLS1: " + myMaze.getMyCurrentCol());


        while(true) {

            theController.movePlayer();
            //System.out.println(theModel.maze2String());


        }

    }
}