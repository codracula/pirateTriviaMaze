public class MazePanel {
    private String[][] travelMap;
    private Maze ma;

    public MazePanel(){
        Maze ma = new Maze(6,2,1);
        //populate fog of war
        for(int i = 0; i< ma.myRowCount; i++){
            for(int j = 0; j < ma.myColCount; i++){
                if (ma.myMaze[i][j].getOccupant() != "P"){
                    travelMap[i][j] = "X";

                } else {
                    travelMap[i][j] = "O";
                    //show empty room

                }
            }
        }
    }

    private void layoutComponents() {

    }
}
