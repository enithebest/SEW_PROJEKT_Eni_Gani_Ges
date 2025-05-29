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
    public class ObstacleManager {
        private static final int WIDTH = 600;
        private static final int HEIGHT = 400;
        private static final int UNIT_SIZE = 20;
        private static final int NUM_OBSTACLES = 5;

        private List<Rectangle> obstacles;
        private Pane root;
        private Random random;

        /**
         * Constructor - Initialize obstacle manager
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

            for (int i = 0; i < NUM_OBSTACLES; i++) {
                Rectangle obstacle = new Rectangle(UNIT_SIZE, UNIT_SIZE);
                obstacle.setFill(Color.GRAY);

                boolean validPosition;
                do {
                    validPosition = true;
                    double x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
                    double y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;

                    for (Rectangle existingObstacle : obstacles) {
                        if (existingObstacle.getX() == x && existingObstacle.getY() == y) {
                            validPosition = false;
                            break;
                        }
                    }

                    if (validPosition) {
                        obstacle.setX(x);
                        obstacle.setY(y);
                    }
                } while (!validPosition);

                obstacles.add(obstacle);
                root.getChildren().add(obstacle);
            }
        }


    }
