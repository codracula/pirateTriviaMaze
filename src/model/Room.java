package model;

public class Room {

    private boolean myIsVisited;
    private boolean myIsEmpty;
    private boolean myHasKey;
    private String myOccupant;

    public Room(){
        myOccupant = null;
        setEmpty();
        myIsVisited = false;
    }

    Room(String theThing){
        setOccupant(theThing);
    }
    protected void setKey(final boolean boo){
        myHasKey = boo;
    }

    protected boolean hasKey(){
        return myIsEmpty == true ? true: false;
    }
    protected String getOccupant(){
        return myOccupant;
    }
    protected void setEmpty(){
        myOccupant = null;
        myIsEmpty = true;
    }
    protected boolean isEmpty(){
        return (myOccupant == null? true: false);
    }

    protected boolean getVisited(){
        return myIsVisited;
    }

    protected void setVisited(){
        myIsVisited = true;
    }
    protected void setOccupant(String theName){
        myOccupant = theName;
        myIsEmpty = false;
    }

    @Override
    public String toString(){
        return myOccupant;
    }

}