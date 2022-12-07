import controller.GameController;
import model.GameModel;
import model.Maze;


public class Main {
    public static void main(String[] args) {



        GameModel Model = new GameModel();
        GameController theController = new GameController(Model);

        System.out.println("myPlayerName: " + Model.getMyPlayerName());
        System.out.println("myPlayerClassName: " + Model.getMyPlayerClass());
        System.out.println("spawnRow: "+Model.getPlayerRow());
        System.out.println("spawnCol: "+Model.getPlayerCol());



        while(true) {

            theController.movePlayer();
            System.out.println(Model.maze2String());


        }

    }
}