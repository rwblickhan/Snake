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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (gameOverDelegate.isGameOver()) {
            Color saved = g.getColor();
            g.setColor(Color.RED);
            g.setFont(new Font("Arial Bold", 20, 20));
            FontMetrics fm = g.getFontMetrics();
            centreString("GAME OVER!", g, fm, getHeight() / 2);
            centreString("RESET THE GAME TO REPLAY.", g, fm, getHeight() / 2 + 50);
            g.setColor(saved);
        }
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (getWidth() - width) / 2, yPos);
    }

}
