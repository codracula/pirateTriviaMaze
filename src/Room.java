import java.util.ArrayList;
import java.util.List;

public class Room {

    boolean myIsVisited;
    boolean myIsEmpty;
    boolean myHasKey;
    String myOccupant;


    public Room(){

        setEmpty();
        setVisited(false);
    }

    Room(String theThing){
        setOccupant(theThing);

    }
    private void setKey(boolean boo){
        myHasKey = boo;
    }

    private boolean hasKey(){
        return myIsEmpty == true ? true: false;
    }
    protected String getOccupant(){
        return this.myOccupant;
    }
    protected void setEmpty(){

        this.myIsEmpty = true;
    }
    protected boolean isEmpty(){
        if (this.myOccupant == null){
            return true;
        } else {
            return false;
        }
    }

    protected boolean Isvisited(){
        return this.myIsVisited;
    }

    public void setVisited(boolean boo){
        this.myIsVisited = boo;
    }
    protected void setOccupant(String theName){
        this.myOccupant = theName;
        this.myIsEmpty = false;
    }

    @Override
    public String toString(){

        return this.myOccupant;
    }

//    public int compareTo(Room other) {
//        if (this.myOccupant.compareTo(other.myOccupant) == 0){
//            return 0;
//        } else if (this.myOccupant.compareTo(other.myOccupant) > 0){
//            return 1;
//        } else {
//            return -1;
//        }
//    }
}