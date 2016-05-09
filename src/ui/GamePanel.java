package ui;

import controller.delegate.GameOverDelegate;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GamePanel extends JPanel {

    GameOverDelegate gameOverDelegate;

    public GamePanel(GameOverDelegate gameOverDelegate) {
        this.gameOverDelegate = gameOverDelegate;
    }


    //if game is over, presents game over screen
    //otherwise paints normally
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (gameOverDelegate.isGameOver()) {
            Color saved = g.getColor();
            g.setColor(Color.RED);
            g.setFont(new Font("Arial Bold", Font.PLAIN, 20));
            FontMetrics fm = g.getFontMetrics();
            centerString("GAME OVER!", g, fm, getHeight() / 2);
            centerString("RESET THE GAME TO REPLAY.", g, fm, getHeight() / 2 + 50);
            g.setColor(saved);
        }
    }

    //helper method to create a centered string for game over screen
    private void centerString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (getWidth() - width) / 2, yPos);
    }

}
