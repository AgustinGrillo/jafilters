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
    int radar_size = (int) (Math.min(this.render_cols, this.render_rows) / 50);
    int render_border = 2 * this.robot_size / 2;
    // World dimensions
    float width;
    float height;
    // World items
    private ArrayList<BaseSpawnable> robots = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> radars = new ArrayList<BaseSpawnable>();
    private ArrayList<BaseSpawnable> estimations = new ArrayList<BaseSpawnable>();

    public WorldGraphics(float world_width, float world_height, ArrayList<BaseSpawnable> robots,
            ArrayList<BaseSpawnable> radars, ArrayList<BaseSpawnable> estimations) {
        // Title our frame
        super("Robot's World");
        // Set world attributes
        this.width = world_width;
        this.height = world_height;
        // TODO: Create copy of arrays
        this.robots = robots;
        this.radars = radars;
        this.estimations = estimations;
        // Frame Size
        setSize(this.render_cols + 2 * this.render_border, this.render_rows + 2 * this.render_border);
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
        g2.fillRect(0, 0, this.render_cols + 2 * this.render_border, this.render_rows + 2 * this.render_border);
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
            int robot_upperleft_corner_x = x - this.robot_size / 2 + this.render_border;
            int robot_upperleft_corner_y = y - this.robot_size / 2 + this.render_border;
            g2.setColor(new Color(204, 204, 204, 180));
            g2.setStroke(new BasicStroke(2f));
            g2.fillOval(robot_upperleft_corner_x, robot_upperleft_corner_y, this.robot_size, this.robot_size);
            g2.setColor(new Color(204, 204, 204, 255));
            g2.drawOval(robot_upperleft_corner_x, robot_upperleft_corner_y, this.robot_size, this.robot_size);

            int robot_center_x = x + this.render_border;
            int robot_center_y = y + this.render_border;
            int robot_heading_x = x + (int) ((this.robot_size / 2) * Math.cos(theta)) + this.render_border;
            int robot_heading_y = y - (int) ((this.robot_size / 2) * Math.sin(theta)) + this.render_border;
            g2.drawLine(robot_center_x, robot_center_y, robot_heading_x, robot_heading_y);
        }
    }

    void drawRadars(Graphics g) {
        for (BaseSpawnable radar : this.radars) {
            // Get robot coordinate
            float[] radar_coord = radar.getXYPosition();
            // Convert coordinate to cell
            int[] radar_cell = coord2Cell(radar_coord);
            int y = radar_cell[0];
            int x = radar_cell[1];
            // Plot
            Graphics2D g2 = (Graphics2D) g;
            int radar_upperleft_corner_x = x - this.radar_size / 2 + this.render_border;
            int radar_upperleft_corner_y = y - this.radar_size / 2 + this.render_border;
            g2.setColor(new Color(204, 204, 204, 150));
            g2.fillRoundRect(radar_upperleft_corner_x, radar_upperleft_corner_y, this.radar_size, this.radar_size,
                    this.radar_size / 10, this.radar_size / 10);
            g2.setStroke(new BasicStroke(1.6f));
            g2.setColor(new Color(204, 204, 204, 255));
            g2.drawRoundRect(radar_upperleft_corner_x, radar_upperleft_corner_y, this.radar_size, this.radar_size,
                    this.radar_size / 10, this.radar_size / 10);
        }
    }

    void drawEstimations(Graphics g) {
        for (BaseSpawnable estimation : this.estimations) {
            Graphics2D g2 = (Graphics2D) g;
            // Get coordinate
            float[] estimation_coord = estimation.getXYPosition();
            // Convert coordinate to cell
            int[] estimation_cell = coord2Cell(estimation_coord);
            int y = estimation_cell[0] + this.render_border;
            int x = estimation_cell[1] + this.render_border;
            g2.setColor(new Color(203, 218, 164, 40));
            int size = 6;
            g2.fillOval(x-size/2, y-size/2, size, size);
        }
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
