import controller.GameController;
import model.GameModel;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        GameModel theModel = new GameModel();
        GameController theController = new GameController(theModel);

        System.out.println("myPlayerName: " + theModel.getMyPlayerName());
        System.out.println("myPlayerClassName: " + theModel.getMyPlayerClass());
        System.out.println("spawnRow: "+theModel.getPlayerRow());
        System.out.println("spawnCol: "+theModel.getPlayerCol());

        while(true) {

            theController.movePlayer();
            System.out.println(theModel.maze2String());

        }
    }

}