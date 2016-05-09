package ui;

import java.awt.*;
import javax.swing.*;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GridPanel extends JPanel {

    public static final int GRID_HEIGHT = 25;
    public static final int GRID_WIDTH  = 25;

    Color currentColor = Color.lightGray;

    public GridPanel() {
        setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
    }

    //sets the color of the oval that will be drawn when the GridPanel is repainted
    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    //paints a small oval with currentColor inside the GridPanel
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(currentColor);
        g.fillOval(0, 0, GRID_WIDTH - 1, GRID_HEIGHT - 1);
    }
}
