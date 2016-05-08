package controller;

import controller.delegate.GameOverDelegate;
import controller.delegate.KeyDelegate;
import controller.delegate.ResetDelegate;
import model.*;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.DirectColorModel;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GameController implements ResetDelegate, GameOverDelegate, KeyDelegate {

    private int size;
    private GameModel model;
    private Timer ticker;
    private GameWindow window;
    private GamePanel mainPanel;

    public GameController(int size) {
        this.size = size;
        model = new GameModel(size);
        window = new GameWindow(this, this);

        setupMainPanel();
        setupTicker();

        window.pack();
        window.repaint();

    }

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

    private void setupTicker() {
        if (ticker != null) {
            ticker.stop();
        }
        ticker = new Timer(100, new TickerActionListener());
        ticker.start();
    }

    private void tick() {
        model.tick();
        updateUI();
        mainPanel.repaint();
    }

    private void updateUI() {
        LinkedList<Location> body = model.getBody();
        Location food = model.getFood();

        clearMainPanel();

        for (Location loc: body) {
            paintBody(loc);
        }
        paintFood(food);
    }

    private void clearMainPanel() {
        for (int i = 0; i < size * size; i++) {
            GridPanel panel = (GridPanel) mainPanel.getComponent(i);
            panel.setCurrentColor(Color.lightGray);
        }
    }

    private void paintBody(Location loc) {
        int x = loc.getX();
        int y = loc.getY();
        GridPanel bodyPanel = (GridPanel) mainPanel.getComponent(y * size + x);
        bodyPanel.setCurrentColor(Color.black);
    }

    private void paintFood(Location loc) {
        int x = loc.getX();
        int y = loc.getY();
        GridPanel foodPanel = (GridPanel) mainPanel.getComponent(y * size + x);
        foodPanel.setCurrentColor(Color.red);
    }



    public void reset() {
        //TODO: implement this!
        model = new GameModel(size);
        window.repaint();
        setupTicker();
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public void changeDir(Direction dir) {
        model.changeDir(dir);
    }


    private class TickerActionListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isGameOver()) {
                tick();
            } else {
                mainPanel.repaint();
                ticker.stop();
            }
        }
    }
}
