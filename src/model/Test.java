package model;

import Controller.Controller;

public class Test {

    public static void main(String[] args){

//       java.lang.Character obj = new java.lang.Character(3,  "Bard");
//       java.lang.Character.Inventory test = new java.lang.Character.Inventory(1, 1);
//       System.out.println(obj.toString());
//       System.out.println(test.toString());
//       System.out.println("------------------------");
//       System.out.println("Testing useHintpass() if count at least 1... \n");
//       obj.add();
//       obj.displayQuestion();
//       obj.hasLifeline();
//       System.out.println("------------------------");
//
//       java.lang.Character obj2 = new java.lang.Character(3,  "Bard");
//       System.out.println(test.toString());
//       System.out.println("------------------------");
//       System.out.println("Testing useHintpass() if count = 0... \n");
//       obj2.add();
//       obj2.displayQuestion();
//       obj2.hasLifeline();v

       //        controller.GameController game = new controller.GameController();
//        game.createAndShowUI();

//       System.out.println("test");
//       List<String> testList = Arrays.asList("bandit", "bandit", "bandit", "guard", "guard", "gateKeeper");
//       System.out.println(testList.toString());
////        GameControllerUI game = new GameControllerUI();
//       Monster mon = new Monster(3,2,1);
//       System.out.println(mon.getmList());
//       int matchElement = 0;
//       System.out.println(testList.size());
//       System.out.println(mon.getmList().size());
//
//
//
//       System.out.println("total match elem: " + matchElement);
////        model.Maze ma = new model.Maze(6,2,1);
////        ma.m2String();
        int test = 1;
        GameModel theModel = new GameModel();
        Controller theController = new Controller(theModel);
        System.out.println(theModel.myPlayer);
        System.out.println("spawnRow: "+theModel.getPlayerRow());
        System.out.println("spawnCol: "+theModel.getPlayerCol());

        while(test > 0) {

            theController.displayDirections();
            System.out.println(theModel.maze2String());

        }

    }

}
