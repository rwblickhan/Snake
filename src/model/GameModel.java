package model;

import model.exception.AlreadyOccupiedException;
import model.exception.OutOfGridException;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GameModel {

    private Grid grid;
    private LinkedList<Location> body;
    private Location food;
    private boolean gameOver;
    private Direction dir;
    private int size;

    public GameModel(int n) {
        size = n;
        grid = new Grid(size);
        body = new LinkedList<Location>();
        gameOver = false;
        try {
            body.add(grid.getLoc(0,0));
            grid.getLoc(0,0).addBody();
        } catch (AlreadyOccupiedException e) {
            System.out.println("Fatal error creating snake head");
            System.exit(0);
        } catch (OutOfGridException e) {
            System.out.println("Fatal error creating snake head");
            System.exit(0);
        }
        dir = Direction.RIGHT;
        createNewFood();
    }

    //returns the list of locations representing the snake's body
    public LinkedList<Location> getBody() {
        return body;
    }

    //returns the location representing the food
    public Location getFood() {
        return food;
    }

    //sets the head's direction to dir
    public void changeDir(Direction dir) {
        this.dir = dir;
    }

    //updates the model by moving the head
    public void tick() {
        Location head = null;
        try {
            head = grid.getLoc(body.peekLast().getX(), body.peekLast().getY());
        } catch (OutOfGridException e) {
            System.out.println("Fatal exception while getting head");
            System.exit(0);
        }
        Location newLoc = null;
        switch (dir) {
            case UP:
                try {
                    newLoc = grid.getLoc(head.getX(), head.getY() - 1);
                } catch (OutOfGridException e) {
                    gameOver = true;
                    return;
                }
                break;
            case DOWN:
                try {
                    newLoc = grid.getLoc(head.getX(), head.getY() + 1);
                } catch (OutOfGridException e) {
                    gameOver = true;
                    return;
                }
                break;
            case LEFT:
                try {
                    newLoc = grid.getLoc(head.getX() - 1, head.getY());
                } catch (OutOfGridException e) {
                    gameOver = true;
                    return;
                }
                break;
            case RIGHT:
                try {
                    newLoc = grid.getLoc(head.getX() + 1, head.getY());
                } catch (OutOfGridException e) {
                    gameOver = true;
                    return;
                }
                break;
        }
        if (body.contains(newLoc)) {
            gameOver = true;
            return;
        }
        if (newLoc.hasFood()) {
            newLoc.clear();
            body.add(newLoc);
            try {
                newLoc.addBody();
                createNewFood();
            } catch (AlreadyOccupiedException e) {
                System.out.println("Fatal error while eating food");
                System.exit(0);
            }
        } else {
            newLoc.clear();
            body.add(newLoc);
            try {
                newLoc.addBody();
            } catch (AlreadyOccupiedException e) {
                System.out.println("Fatal error moving head");
                System.exit(0);
            }
            body.peekFirst().clear();
            body.removeFirst();
        }

    }

    //creates a new food at a random location in the grid
    private void createNewFood() {
        boolean newFoodCreated = false;
        Random random = new Random();
        while (!newFoodCreated) {
            int newX = random.nextInt(size);
            int newY = random.nextInt(size);
            try {
                Location newLoc = grid.getLoc(newX, newY);
                newLoc.addFood();
                newFoodCreated = true;
                food = newLoc;
            } catch (AlreadyOccupiedException e) {
                newFoodCreated = false;
            } catch (OutOfGridException e) {
                newFoodCreated = false;
            }
        }
    }

    //helper method for testing createNewFood()
    public void testCreateNewFoodHelper() {
        for (int i = 0; i < body.size(); i++) {
            body.get(i).clear();
        }
        food.clear();
        createNewFood();
    }


    //returns true iff the game is over
    //returns false otherwise
    public boolean isGameOver() {
        return gameOver;
    }

}
