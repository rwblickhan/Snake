package ui;

import java.awt.*;
import javax.swing.*;

/**
 * Created by rwblickhan on 5/1/16.
 */
public class GridPanel extends JPanel {

    public static final int GRID_HEIGHT = 25;
    public static final int GRID_WIDTH  = 25;

    Color currentColor;

    public GridPanel() {
        setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
        currentColor = Color.lightGray;
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(currentColor);
        g.fillOval(0, 0, GRID_WIDTH - 1, GRID_HEIGHT - 1);
    }
}
