package com.example.projekt_sew_3;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObstacleManager {
    /**
     * ObstacleManager class - Handles obstacle generation and collision detection
     */
    private Pane root;
    private List<Rectangle> obstacles;
    private Random random;
    private static final int UNIT_SIZE = 20;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    /**
     * Constructor - Initialize obstacle manager
     *
     * @param root The root pane to add obstacles to
     */
    public ObstacleManager(Pane root) {
        this.root = root;
        this.obstacles = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Generate random obstacles for the game
     */
    public void generateObstacles() {
        clearObstacles();
        int numObstacles = 10; // Number of obstacles to generate

        for (int i = 0; i < numObstacles; i++) {
            double x, y;
            do {
                x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
                y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            } while (!isPositionValid(x, y));

            Rectangle obstacle = new Rectangle(UNIT_SIZE, UNIT_SIZE);
            obstacle.setFill(Color.GRAY);
            obstacle.setX(x);
            obstacle.setY(y);
            obstacles.add(obstacle);
            root.getChildren().add(obstacle);
        }
    }

    /**
     * Remove all obstacles from the game
     */
    public void clearObstacles() {
        for (Rectangle obstacle : obstacles) {
            root.getChildren().remove(obstacle);
        }
        obstacles.clear();
    }

    /**
     * Check if a position is valid (within bounds and not occupied by an obstacle)
     *
     * @param x X coordinate to check
     * @param y Y coordinate to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isPositionValid(double x, double y) {
        // Check if position is within bounds
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }

        // Check if position overlaps with any obstacle
        for (Rectangle obstacle : obstacles) {
            if (obstacle.getX() == x && obstacle.getY() == y) {
                return false;
            }
        }

        return true;
    }
}
