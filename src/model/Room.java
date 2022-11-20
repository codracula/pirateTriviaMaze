package model;

import java.util.ArrayList;
import java.util.List;

public class Room {

    boolean myIsVisited;
    boolean myIsEmpty;
    boolean myHasKey;
    String myOccupant;


    public Room(){
        this.myOccupant = "0";
        setEmpty();
        myIsVisited = false;
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
        this.myOccupant = "0";
        this.myIsEmpty = true;
    }
    protected boolean isEmpty(){
        return (this.myOccupant == null? true: false);
    }

    protected boolean getVisited(){
        return this.myIsVisited;
    }

    protected void setVisited(){
        this.myIsVisited = true;
    }
    protected void setOccupant(String theName){
        this.myOccupant = theName;
        this.myIsEmpty = false;
    }

    @Override
    public String toString(){

        return this.myOccupant;
    }

//    public int compareTo(model.Room other) {
//        if (this.myOccupant.compareTo(other.myOccupant) == 0){
//            return 0;
//        } else if (this.myOccupant.compareTo(other.myOccupant) > 0){
//            return 1;
//        } else {
//            return -1;
//        }
//    }
}