package test;
import model.exception.AlreadyOccupiedException;
import model.exception.OutOfGridException;
import org.junit.*;
import model.*;

/**
 * Created by rwblickhan on 4/21/16.
 */
public class TestModel {

    Location testLoc;
    Grid testGrid;
    GameModel testModel;
    int size = 10;
    int x = 5;
    int y = 5;
    final int TEST_ITERATIONS = 1000;

    @Before
    public void testSetup() {
        testLoc = new Location(x, y);
        testGrid = new Grid(size);
        testModel = new GameModel(size);
    }

    @Test
    public void testLocation() {
        assert (testLoc.getX() == 5);
        assert (testLoc.getY() == 5);
        assert (!testLoc.isOccupied());
        assert (!testLoc.hasBody());
        assert (!testLoc.hasFood());
        try {
            testLoc.addBody();
        } catch (AlreadyOccupiedException e) {
            Assert.fail();
        }
        assert (testLoc.hasBody());
        assert (!testLoc.hasFood());
        assert (testLoc.isOccupied());
        testLoc.clear();
        assert (!testLoc.isOccupied());
        assert (!testLoc.hasBody());
        assert (!testLoc.hasFood());
        try {
            testLoc.addFood();
        } catch (AlreadyOccupiedException e) {
            Assert.fail();
        }
        assert (!testLoc.hasBody());
        assert (testLoc.hasFood());
        assert (testLoc.isOccupied());
    }

    @Test (expected=AlreadyOccupiedException.class)
    public void testLocationException() throws AlreadyOccupiedException{
        assert (!testLoc.isOccupied());
        assert (!testLoc.hasBody());
        assert (!testLoc.hasFood());
        try {
            testLoc.addBody();
        } catch (AlreadyOccupiedException e) {
            Assert.fail();
        }
        assert (testLoc.hasBody());
        assert (!testLoc.hasFood());
        assert (testLoc.isOccupied());
        testLoc.addFood();
        Assert.fail();
    }

    @Test public void testGrid() {
        int n = size;
        Location locInGrid = null;
        assert (testGrid.getSize() == n);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    Location loc = testGrid.getLoc(x, y);
                } catch (OutOfGridException e) {
                    Assert.fail();
                }
            }
        }
        try {
            locInGrid = testGrid.getLoc(x, y);
        } catch (OutOfGridException e) {
            Assert.fail();
        }
        try {
            assert (locInGrid.getX() == x);
            assert (locInGrid.getY() == y);
        } catch (NullPointerException e) {
            Assert.fail();
        }

    }

    @Test (expected = OutOfGridException.class)
    public void testGridXTooSmall() throws OutOfGridException {
        testGrid.getLoc(-5, 5);
    }

    @Test (expected = OutOfGridException.class)
    public void testGridYTooSmall() throws OutOfGridException {
        testGrid.getLoc(5, -5);
    }

    @Test (expected = OutOfGridException.class)
    public void testGridXTooBig() throws OutOfGridException {
        testGrid.getLoc(25, 5);
    }

    @Test (expected = OutOfGridException.class)
    public void testGridYTooBig() throws OutOfGridException {
        testGrid.getLoc(5, 25);
    }

    @Test
    public void gameOverGoingUp() {
        testModel.changeDir(Direction.UP);
        testModel.tick();
        assert(testModel.isGameOver());
    }

    @Test
    public void gameOverGoingLeft() {
        testModel.changeDir(Direction.LEFT);
        testModel.tick();
        assert(testModel.isGameOver());
    }

    @Test
    public void gameOverGoingRight() {
        for (int i = 0; i < size - 1; i++) {
            testModel.tick();
            assert (!testModel.isGameOver());
        }
        testModel.tick();
        assert(testModel.isGameOver());

    }

    @Test
    public void gameOverGoingDown() {
        testModel.changeDir(Direction.DOWN);
        for (int i = 0; i < size - 1; i++) {
            testModel.tick();
            assert (!testModel.isGameOver());
        }
        testModel.tick();
        assert(testModel.isGameOver());
    }

    @Test
    public void testCreateNewFood() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            testModel.testCreateNewFoodHelper();
            Location testFood = testModel.getFood();
            int foodX = testFood.getX();
            int foodY = testFood.getY();
            assert (foodX < size);
            assert (foodY < size);
            assert (foodX >= 0);
            assert (foodY >= 0);
        }
    }
}
