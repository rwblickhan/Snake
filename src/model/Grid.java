package model;

import model.exception.OutOfGridException;

import java.util.Vector;

/**
 * Created by rwblickhan on 4/21/16.
 */
public class Grid {

    private Vector<Vector<Location>> grid;
    private int size;

    //produces an nxn grid
    public Grid(int n) {
        size = n;
        grid = new Vector<Vector<Location>>();
        for (int i = 0; i < size; i++) {
            Vector<Location> v = new Vector<Location>();
            for (int j = 0; j < size; j++) {
                v.add(j, new Location(i, j));
            }
            grid.add(i, v);
        }
    }

    //returns the Location stored in the grid with the given x and y coordinates
    //throws an OutOfGridException if the requested coordinates are not in the grid
    public Location getLoc(int x, int y) throws OutOfGridException {
        if (x >= size) {
            throw new OutOfGridException("x coordinate too high");
        }
        if (y >= size) {
            throw new OutOfGridException("y coordinate too high");
        }
        if (x < 0) {
            throw new OutOfGridException("x coordinate too low");
        }
        if (y < 0) {
            throw new OutOfGridException("y coordinate too low");
        }
        return grid.get(x).get(y);
    }

    //returns the grid's horizontal size
    //that is, the grid has size columns and size rows
    public int getSize() {
        return size;
    }
}
