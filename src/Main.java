import controller.GameController;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        GameView myView = new GameView();
        GameModel myModel = new GameModel(myView);
        GameController myController = new GameController(myModel, myView);

        System.out.println("myPlayerName: " + myModel.getMyPlayerName());
        System.out.println("myPlayerClassName: " + myModel.getMyPlayerClass());
        System.out.println("spawnRow: "+myModel.getPlayerRow());
        System.out.println("spawnCol: "+myModel.getPlayerCol());
//
//        while(true) {
//
//            theController.movePlayer();
//            System.out.println(theModel.maze2String());
//
//        }

        myView.showTitleScreen();
        myController.menu();
        myModel.setQuestion();

        while (myModel.getMyLive() != 0) {
            myView.showMaze(myModel.myMaze);
            myController.movePlayer();
            myView.showRoom(myModel.myMaze.getRoom(myModel.getPlayerRow(), myModel.getPlayerCol()));
        }
    }

}