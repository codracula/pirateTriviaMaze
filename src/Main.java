import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {
    public static void main(String[] args) {

        GameView myView = new GameView();
        GameModel myModel = new GameModel(myView);
        GameController myController = new GameController(myModel, myView);

        myController.playGame();

    }
}