package world.util.graphics;

import world.spawnables.*;
import java.util.ArrayList;
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
    // Rendering properties
    int render_rows = 600;
    int render_cols = 1000;
    int robot_size = (int) (Math.min(this.render_cols, this.render_rows) / 30);
    int radar_size = (int) (Math.min(this.render_cols, this.render_rows) / 30);
    // World dimensions
    float width;
    float height;
    // World items
    private ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> estimations = new ArrayList<BaseSpawnable>();

    public WorldGraphics(float world_width, float world_height, ArrayList<BaseSpawnable> robots,
            ArrayList<BaseSpawnable> radars) {
        // Title our frame
        super("Robot's World");
        // Set world attributes
        this.width = world_width;
        this.height = world_height;
        // TODO: Create copy of arrays
        this.robots = robots;
        this.radars = radars;
        // Frame Size
        setSize(this.render_cols, this.render_rows);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Make frame visible
        setVisible(true);
    }

    void drawBackground(Graphics g) {
        // Create new Graphics2D instance using Graphics parent
        Graphics2D g2 = (Graphics2D) g;
        // Set color (RGBa)
        Color backgroundColor = new Color(223, 229, 179, 150);
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, this.render_cols, this.render_rows);
    }

    void drawRobots(Graphics g) {
        for (BaseSpawnable robot : this.robots) {
            // Get robot coordinate
            float[] robot_coord = robot.getXYPosition();
            // Convert coordinate to cell
            int[] robot_cell = coord2Cell(robot_coord);
            int y = robot_cell[0];
            int x = robot_cell[1];
            float theta = robot.getOrientation();
            // Plot
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(203, 218, 164, 100));
            g2.setStroke(new BasicStroke(1.6f));
            g2.fillOval(x, y, this.robot_size, this.robot_size);
            g2.setColor(new Color(203, 218, 164, 255));
            g2.drawOval(x, y, this.robot_size, this.robot_size);

            int robot_center_x = x + this.robot_size / 2;
            int robot_center_y = y + this.robot_size / 2;
            int robot_heading_x = robot_center_x + (int) ((this.robot_size / 2) * Math.cos(theta));
            int robot_heading_y = robot_center_y - (int) ((this.robot_size / 2) * Math.sin(theta));
            g2.drawLine(robot_center_x, robot_center_y, robot_heading_x, robot_heading_y);
        }
    }

    void drawRadars(Graphics g) {
        for (BaseSpawnable radar : this.radars) {
            // Get robot coordinate
            float[] radar_coord = radar.getXYPosition();
            // Convert coordinate to cell
            int[] radar_cell = coord2Cell(radar_coord);
            int x = radar_cell[0];
            int y = radar_cell[1];
            // Plot
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(204, 204, 204, 150));
            g2.fillRoundRect(x, y, this.radar_size, this.radar_size, this.radar_size / 10, this.radar_size / 10);
            g2.setStroke(new BasicStroke(1.6f));
            g2.setColor(new Color(204, 204, 204, 255));
            g2.drawRoundRect(x, y, this.radar_size, this.radar_size, this.radar_size / 10, this.radar_size / 10);
        }
    }

    void drawEstimations(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(203, 218, 164, 220));
        g2.fillOval(500, 300, 10, 10);
    }

    public void plotWorld() {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        drawRobots(g);
        drawRadars(g);
        drawEstimations(g);
    }

    // Utility functions related to spawnables
    private float[] cell2Coord(int[] cell) {
        int cell_row = cell[0];
        int cell_col = cell[1];

        float coord_x = cell_col * this.width / (this.render_cols - 1);
        float coord_y = ((this.render_rows - 1) - cell_row) * this.height / (this.render_rows - 1);

        return new float[] { coord_x, coord_y };
    }

    private int[] coord2Cell(float[] coord) {
        float coord_x = coord[0];
        float coord_y = coord[1];

        int cell_col = Math.round(coord_x * (this.render_cols - 1) / this.width);
        int cell_row = (this.render_rows - 1) - Math.round(coord_y * (this.render_rows - 1) / this.height);

        return new int[] { cell_row, cell_col };

    }

}
