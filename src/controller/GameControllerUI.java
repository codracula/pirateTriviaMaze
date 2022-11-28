package controller;

import model.Maze;

import java.util.Scanner;

public class GameControllerUI {
    private String[][] travelMap;
    private Maze ma;
    private String myPlayerName;
    private int myClass;
    Scanner scan;
    public GameControllerUI(){
//        ma = new model.Maze(6,2,1,4);
        scan = new Scanner(System.in);
        menuIntro();
        //add inventory

    }

    public void menuIntro(){

        System.out.println("Please enter your name");
        myPlayerName = scan.nextLine();
        playerClassSelect();
        //lauch maze
        System.out.println(ma.m2String());
        System.out.println("end");
    }

    private void playerClassSelect(){
        System.out.println("Welcome" + myPlayerName +", please select your class");
        System.out.println("Press 1 for merchant, 2 for sailer, 3 for headhunter");

            myClass = scan.nextInt();

        switch (myClass){
            case 1:
                System.out.println("You've chosen a merchant class, be prepared for arithmetic quest");
                break;
            case 2:
                System.out.println("You're now a sailer..YARRRRR'");
                break;
            case 3:
                System.out.println("You are now a headhunter");

        }

    }
    private void layoutComponents() {

    }
}
