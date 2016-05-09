package controller;

import controller.delegate.GameOverDelegate;
import controller.delegate.KeyDelegate;
import controller.delegate.ResetDelegate;
import model.*;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GameController implements ResetDelegate, GameOverDelegate, KeyDelegate {

    private int size;
    private GameModel model;
    private Timer timer;
    private GameWindow window;
    private GamePanel mainPanel;

    public GameController(int size) {
        this.size = size;
        model = new GameModel(size);
        window = new GameWindow(this, this);

        setupMainPanel();
        setupTimer();

        window.pack();
        window.repaint();

    }

    //creates game panel filled with GridPanels to represent model's grid
    //and adds game panel to game window
    private void setupMainPanel() {
        mainPanel = new GamePanel(this);
        mainPanel.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size * size; i++) {
            JPanel j = new GridPanel();
            j.setVisible(true);
            mainPanel.add(j);
        }
        mainPanel.setVisible(true);
        window.add(mainPanel);
    }

    //sets up timer with a 100ms delay
    private void setupTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(100, new TimerActionListener());
        timer.start();
    }

    //updates model and UI on clock tick
    private void tick() {
        model.tick();
        updateUI();
        mainPanel.repaint();
    }

    //clears game panel and then updates grid panels that contain food or body parts
    private void updateUI() {
        LinkedList<Location> body = model.getBody();
        Location food = model.getFood();

        clearMainPanel();

        for (Location loc: body) {
            paintBody(loc);
        }
        paintFood(food);
    }

    //clears main panel by setting every grid panel to light grey
    private void clearMainPanel() {
        for (int i = 0; i < size * size; i++) {
            GridPanel panel = (GridPanel) mainPanel.getComponent(i);
            panel.setCurrentColor(Color.lightGray);
        }
    }

    //calculates position of grid panel representing given location
    //and sets its colour to black to represent body part
    private void paintBody(Location loc) {
        int x = loc.getX();
        int y = loc.getY();
        GridPanel bodyPanel = (GridPanel) mainPanel.getComponent(y * size + x);
        bodyPanel.setCurrentColor(Color.black);
    }

    //calculates position of grid panel representing given locatoin
    //and sets its colour to black to represent food
    private void paintFood(Location loc) {
        int x = loc.getX();
        int y = loc.getY();
        GridPanel foodPanel = (GridPanel) mainPanel.getComponent(y * size + x);
        foodPanel.setCurrentColor(Color.red);
    }

    //resets game by creating a new model,
    //resetting game window and setting up a new timer
    public void reset() {
        model = new GameModel(size);
        window.repaint();
        setupTimer();
    }

    //returns true iff game is over
    //returns false otherwise
    public boolean isGameOver() {
        return model.isGameOver();
    }

    //sets direction for next tick in the model
    public void changeDir(Direction dir) {
        model.changeDir(dir);
    }

    //listens for timer
    //calls tick if game is not over
    //presents game over screen otherwise
    private class TimerActionListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isGameOver()) {
                tick();
            } else {
                mainPanel.repaint();
                timer.stop();
            }
        }
    }
}
