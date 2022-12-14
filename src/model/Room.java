package model;

/**
 *  Room class
 * @author Jeep Naarkom
 */
public class Room {
    /**
     *  variable to store if is visited.
     */
    private boolean myIsVisited;
    /**
     *  variable to store if the room is empty.
     */
    private boolean myIsEmpty;

    /**
     *  variable to store thing inside the room.
     */
    private String myOccupant;

    /**
     *  constructor to set up the room.
     */
    public Room() {
        myOccupant = null;
        setEmpty();
        myIsVisited = false;
    }

    /**
     *  check if the room has the key.
     * @return true if the room has the key.
     */
    boolean hasKey() {
        return myIsEmpty == true? true: false;
    }

    /**
     *  get occupant from the room.
     * @return  occupant in the room.
     */
    public String getOccupant() {
        return myOccupant;
    }

    /**
     *  set the room to empty room.
     */
    void setEmpty() {
        myOccupant = null;
        myIsEmpty = true;
    }

    /**
     *  check if the room is empty.
     * @return  return true if the room is empty.
     */
    boolean isEmpty() {
        return (myOccupant == null? true: false);
    }

    /**
     *  check if the room has been visited.
     * @return  boolean if the room has been visited.
     */
    boolean getVisited() {
        return myIsVisited;
    }

    /**
     *  set room visit variable.
     */
    void setVisited() {
        myIsVisited = true;
    }

    /**
     *  set occupant to the room.
     * @param theName   occupant name.
     */
    public void setOccupant(final String theName) {
        myOccupant = theName;
        myIsEmpty = false;
    }

    /**
     *  return to a string.
     * @return  room's occupant to string.
     */
    @Override
    public String toString() {
        return myOccupant;
    }

}