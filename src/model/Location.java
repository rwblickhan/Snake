package model;

import model.exception.AlreadyOccupiedException;

/**
 * Created by rwblickhan on 4/21/16.
 */
public class Location {

    private int x;
    private int y;
    private boolean hasFood = false;
    private boolean hasBody = false;

    public Location(int x, int y) {
        //creates an object representing the location with coordinates (x,y)
        this.x = x;
        this.y = y;
    }

    //returns the location's x coordinate
    public int getX() {
        return x;
    }

    //returns the location's y coordinate
    public int getY() {
        return y;
    }

    //returns true iff the location has food or a body part
    //returns false otherwise
    public boolean isOccupied() {
        return hasFood || hasBody;
    }

    //returns true iff the location has food
    //returns false otherwise
    public boolean hasFood() {
        return hasFood;
    }

    //returns true iff the location has a body part
    //returns false otherwise
    public boolean hasBody() {
        return hasBody;
    }

    //adds food to the location
    //throws AlreadyOccupiedException if the location is already occupied
    public void addFood() throws AlreadyOccupiedException {
        if (!isOccupied()) {
            hasFood = true;
        } else {
            throw new AlreadyOccupiedException("The cell is already occupied");
        }
    }

    //adds a body part to the location
    //throws AlreadyOccupiedException if the location is already occupied
    public void addBody() throws AlreadyOccupiedException {
        if (!isOccupied()) {
            hasBody = true;
        } else {
            throw new AlreadyOccupiedException("The cell is already occupied");
        }
    }

    //clears the location
    public void clear() {
        hasFood = false;
        hasBody = false;
    }

}
