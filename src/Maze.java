import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    int myRowCount;
    int myColCount;
    List myCurrentLoc;
    List myExit;
    List myPlayerSpawn;
    Room[][] myMaze;
    Monster mon;
    int myKeyCount = 4;
    int totalKey;
    Random ran;
    public Maze(){

        myRowCount = 4;
        myColCount = 7;
        totalKey = 4;
        mazeGenerate();
        ran = new Random();
        mon = new Monster();
System.out.println("after maze gen");
        int mAcount = 6, mBcount = 2, mCcount =1;
        double rAchance = .2, rchance = .4, mCchance =.1;
        genMon();
        genExit();
        genPlayerSpawn();

        //populate keys
    }
    private void genMon(){
        //while the list isn't empty generate ran x, and y and put the monster there
        int monLeft = mon.getmList().size();
        System.out.println("mon size: " + mon.getmList().size());
        while (monLeft > 0) {
            int ranRow = ran.nextInt(myRowCount);
            int ranCol = ran.nextInt(myColCount);
            if (myMaze[ranRow][ranCol].isEmpty()){
                myMaze[ranRow][ranCol].setOccupant((String) mon.getmList().get(monLeft - 1));
                monLeft--;
            }
        }

    }

    private void genExit(){

        int ranRow = ran.nextInt(myRowCount);
        int ranCol = ran.nextInt(myColCount);
        while (myMaze[ranRow][ranCol].isEmpty()) {
                myMaze[ranRow][ranCol].setOccupant("E");
                myExit = new ArrayList(2);
                myExit.add(ranRow);
                myExit.add(ranCol);
        }
    }

    private void genPlayerSpawn(){

        int ranRow = ran.nextInt(myRowCount);
        int ranCol = ran.nextInt(myColCount);
        while (myMaze[ranRow][ranCol].isEmpty()) {
            myMaze[ranRow][ranCol].setOccupant("P");
            myPlayerSpawn = new ArrayList(2);
            myPlayerSpawn.add(ranRow);
            myPlayerSpawn.add(ranCol);
        }
    }

//    public void genMonB(){
//        for (int i = 1; 0 < myMaze.length; i++){
//            for (int j = 0 ; j < myMaze[i].length; j++){
//                int mRan = ran.nextInt(100) + 1;
//                if (mRan <= mon.mCspawnRate){
//                    myMaze[i][j].setOccupant((String)mon.myMonsterType.get(2));
//                } else if (mRan <= mon.mBspawnRate){
//                    myMaze[i][j].setOccupant((String)mon.myMonsterType.get(1));
//                } else if (mRan <= mon.mAspawnRate){
//                    myMaze[i][j].setOccupant((String)mon.myMonsterType.get(0));
//                } else if (mRan > mon.mAspawnRate) {
//
//                }
//                //System.out.println(myMaze[i][j].toString());
//                //System.out.println();
//            }
//        }
//    }

    public void mazeGenerate(){
        myMaze = new Room[myRowCount][myColCount];
        for (int i = 0; i < myMaze.length; i++){
            for (int j = 0 ; j < myMaze[i].length; j++) {
            myMaze[i][j] = new Room(null);
            }
        }
        System.out.println("maze length: " + myMaze.length);
    }
    public void m2String(){

        for (int i = 0; i < myMaze.length; i++){
            for (int j = 0 ; j < myMaze[i].length; j++) {
                if (myMaze[i][j].getOccupant() == null){
                    System.out.print("0");
                    //System.out.println(myMaze[i][j].toString());
                } else if (myMaze[i][j].getOccupant() == "bandit"){
                    System.out.print("1");
                    //System.out.println(myMaze[i][j].toString());
                } else if (myMaze[i][j].getOccupant() == "guard"){
                    System.out.print("2");
                    //System.out.println(myMaze[i][j].toString());
                } else if (myMaze[i][j].getOccupant() == "gateKeeper"){
                    System.out.print("3");
                    //System.out.println(myMaze[i][j].toString());
                } else {
                    System.out.print(myMaze[i][j].getOccupant().toString());
                    //System.out.println(myMaze[i][j].toString());
                }
            }
            System.out.println();
        }
    }



}
