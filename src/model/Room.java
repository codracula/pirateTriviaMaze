package model;

public class Room {

    protected boolean myIsVisited;
    protected boolean myIsEmpty;
    protected boolean myHasKey;
    protected String myOccupant;

    public Room(){
        this.myOccupant = null;
        setEmpty();
        myIsVisited = false;
    }

    Room(String theThing){
        setOccupant(theThing);
    }
    protected void setKey(boolean boo){
        myHasKey = boo;
    }

    protected boolean hasKey(){
        return myIsEmpty == true ? true: false;
    }
    protected String getOccupant(){
        return this.myOccupant;
    }
    protected void setEmpty(){
        this.myOccupant = null;
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

}