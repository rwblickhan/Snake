package model;

import model.exception.AlreadyOccupiedException;

/**
 * Created by rwblickhan on 4/21/16.
 */
public class Location {

    private int x;
    private int y;
    private boolean hasFood;
    private boolean hasBody;

    public Location(int x, int y) {
        //creates an object representing the location with coordinates (x,y)
        this.x = x;
        this.y = y;
        hasFood = false;
        hasBody = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return hasFood || hasBody;
    }

    public boolean hasFood() {
        return hasFood;
    }

    public boolean hasBody() {
        return hasBody;
    }

    public void addFood() throws AlreadyOccupiedException {
        if (!isOccupied()) {
            hasFood = true;
        } else {
            throw new AlreadyOccupiedException("The cell is already occupied");
        }
    }

    public void addBody() throws AlreadyOccupiedException {
        if (!isOccupied()) {
            hasBody = true;
        } else {
            throw new AlreadyOccupiedException("The cell is already occupied");
        }
    }

    public void clear() {
        hasFood = false;
        hasBody = false;
    }

}
