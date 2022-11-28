import controller.GameControllerUI;
import unitTest.Monster;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

//        controller.GameController game = new controller.GameController();
//        game.createAndShowUI();

        System.out.println("test");
        List<String> testList = Arrays.asList("bandit", "bandit", "bandit", "guard", "guard", "gateKeeper");
        System.out.println(testList.toString());
//        GameControllerUI game = new GameControllerUI();
        Monster mon = new Monster(3,2,1);
        System.out.println(mon.getmList());
        int matchElement = 0;
        System.out.println(testList.size());
        System.out.println(mon.getmList().size());



        System.out.println("total match elem: " + matchElement);
//        model.Maze ma = new model.Maze(6,2,1);
//        ma.m2String();

    }

}