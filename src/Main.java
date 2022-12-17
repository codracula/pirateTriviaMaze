import controller.GameController;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GameView myView = new GameView();
        GameModel myModel = new GameModel(myView);
        GameController myController = new GameController(myModel, myView);

        myController.playGame();

    }
}