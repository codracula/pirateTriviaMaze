package unitTest;

public class Room {

    boolean myIsVisited;
    boolean myIsEmpty;
    boolean myHasKey;
    String myOccupant;


    public Room(){

        setEmpty();
        myIsVisited = false;
    }

    protected Room(String theThing){
        setOccupant(theThing);

    }
    protected void setKey(boolean boo){
        myHasKey = boo;
    }

    protected boolean hasKey(){
        return myHasKey == true ? true: false;
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
    protected String getOccupant(){
        return this.myOccupant;
    }

    @Override
    public String toString(){

        return this.myOccupant;
    }

}