package ui;

import controller.delegate.KeyDelegate;
import controller.delegate.ResetDelegate;
import model.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GameWindow extends JFrame {

    ResetDelegate resetDelegate;
    KeyDelegate keyDelegate;

    public GameWindow(ResetDelegate resetDelegate, KeyDelegate keyDelegate) {

        this.resetDelegate = resetDelegate;
        this.keyDelegate = keyDelegate;

        createMenus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        repaint();
        setFocusable(true);
        setVisible(true);

        addKeyListener(new KeyboardController());

    }

    private void createMenus() {
        JMenuBar menuBar = new JMenuBar();
        JMenu drawingMenu = new JMenu("Snake");

        JMenuItem resetGame = new JMenuItem("Reset");
        resetGame.addActionListener(new ResetGameAction());
        drawingMenu.add(resetGame);

        menuBar.add(drawingMenu);
        setJMenuBar(menuBar);
    }

    private class ResetGameAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            resetDelegate.reset();
        }

    }

    private class KeyboardController implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyDelegate.changeDir(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    keyDelegate.changeDir(Direction.DOWN);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyDelegate.changeDir(Direction.RIGHT);
                    break;
                case KeyEvent.VK_LEFT:
                    keyDelegate.changeDir(Direction.LEFT);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}
