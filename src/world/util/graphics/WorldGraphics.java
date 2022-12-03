package world.util.graphics;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.*;
import javax.swing.JFrame;

/**
 * Graphics class to render robot world.
 * 
 */
public class WorldGraphics extends JFrame {
    int width;
    int height;
    int robot_size;
    int radar_size;
    public int x = 4;
    public int y = 4;

    public WorldGraphics(int world_width, int world_height) {
        // Title our frame
        super("Robot's World");
        // Set world attributes
        this.width = world_width;
        this.height = world_height;
        this.robot_size = (int) (Math.min(this.width, this.height) / 30);
        this.radar_size = (int) (Math.min(this.width, this.height) / 30);
        // Frame Size
        setSize(this.width, this.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Make frame visible
        setVisible(true);
    }

    void drawBackground(Graphics g) {
        // Create new Graphics2D instance using Graphics parent
        Graphics2D g2 = (Graphics2D) g;
        // Set color (RGBa)
        Color backgroundColor = new Color(223, 229, 179, 100);
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, this.width, this.height);
    }

    void drawRobot(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(203, 218, 164, 100));
        g2.setStroke(new BasicStroke(1.6f));
        g2.fillOval(x, y, this.robot_size, this.robot_size);
        g2.setColor(new Color(203, 218, 164, 255));
        g2.drawOval(x, y, this.robot_size, this.robot_size);
        g2.drawLine(x + this.robot_size / 2, y, x + this.robot_size / 2, y + this.robot_size / 2);
    }

    void drawRadar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(204, 204, 204, 150));
        g2.fillRoundRect(300, 300, this.radar_size, this.radar_size, this.radar_size / 10, this.radar_size / 10);
        g2.setStroke(new BasicStroke(1.6f));
        g2.setColor(new Color(204, 204, 204, 255));
        g2.drawRoundRect(300, 300, this.radar_size, this.radar_size, this.radar_size / 10, this.radar_size / 10);
    }

    void drawEstimation(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(203, 218, 164, 220));
        g2.fillOval(500, 300, 10, 10);
    }

    public void updateWorld() {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        drawRobot(g);
        drawRadar(g);
        drawEstimation(g);
    }

}
