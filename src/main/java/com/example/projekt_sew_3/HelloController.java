package com.example.projekt_sew_3;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField playerNameField;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button startButton;

    @FXML
    private ListView<String> highScoresList;

    @FXML
    public void initialize() {
        // Set default values
        difficultyComboBox.setValue("Medium");

        // Add some sample high scores
        highScoresList.getItems().addAll(
                "Player1 - 100 points",
                "Player2 - 85 points",
                "Player3 - 70 points"
        );
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onPlaySnakeButtonClick() {
        Stage snakeStage = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        scene.setFill(Color.BLACK);

        // Initialize snake
        List<Rectangle> snake = new ArrayList<>();
        int unitSize = 20;
        int initialLength = 3;
        for (int i = 0; i < initialLength; i++) {
            Rectangle segment = new Rectangle(unitSize, unitSize);
            segment.setFill(Color.GREEN);
            segment.setX(300 - i * unitSize);
            segment.setY(200);
            snake.add(segment);
            root.getChildren().add(segment);
        }

        // Create food
        Rectangle food = new Rectangle(unitSize, unitSize);
        food.setFill(Color.RED);
        Random random = new Random();
        double foodX = random.nextInt(30) * unitSize;
        double foodY = random.nextInt(20) * unitSize;
        food.setX(foodX);
        food.setY(foodY);
        root.getChildren().add(food);

        // Game variables
        int[] direction = {0}; // 0: right, 1: down, 2: left, 3: up
        boolean[] gameOver = {false};
        // Handle key events
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT && direction[0] != 2) {
                direction[0] = 0;
            } else if (event.getCode() == KeyCode.DOWN && direction[0] != 3) {
                direction[0] = 1;
            } else if (event.getCode() == KeyCode.LEFT && direction[0] != 0) {
                direction[0] = 2;
            } else if (event.getCode() == KeyCode.UP && direction[0] != 1) {
                direction[0] = 3;
            }
        });
        // Game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) { // 100ms delay
                    if (!gameOver[0]) {
                        // Move the snake
                        double headX = snake.get(0).getX();
                        double headY = snake.get(0).getY();
                        switch (direction[0]) {
                            case 0: headX += unitSize; break; // right
                            case 1: headY += unitSize; break; // down
                            case 2: headX -= unitSize; break; // left
                            case 3: headY -= unitSize; break; // up
                        }
                        // Check for collision with walls
                        if (headX < 0 || headX >= 600 || headY < 0 || headY >= 400) {
                            gameOver[0] = true;
                            return;
                        }

                        // Check for collision with self
                        for (Rectangle segment : snake) {
                            if (segment.getX() == headX && segment.getY() == headY) {
                                gameOver[0] = true;
                                return;
                            }
                        }
                        // Create new head
                        Rectangle newHead = new Rectangle(unitSize, unitSize);
                        newHead.setFill(Color.BLACK);
                        newHead.setX(headX);
                        newHead.setY(headY);
                        snake.add(0, newHead);
                        root.getChildren().add(newHead);

                        // Check if food is eaten
                        if (headX == food.getX() && headY == food.getY()) {
                            double newFoodX = random.nextInt(30) * unitSize;
                            double newFoodY = random.nextInt(20) * unitSize;
                            food.setX(newFoodX);
                            food.setY(newFoodY);
                        } else {
                            Rectangle tail = snake.remove(snake.size() - 1);
                            root.getChildren().remove(tail);
                        }
                    }
                    lastUpdate = now;
                }
            }
        }; gameLoop.start();

        snakeStage.setTitle("Snake Game");
        snakeStage.setScene(scene);
        snakeStage.show();
    }

    @FXML
    protected void onStartButtonClick() {
        String playerName = playerNameField.getText().trim();
        if (playerName.isEmpty()) {
            playerName = "Player";
        }

        // Start the game with selected difficulty
        String difficulty = difficultyComboBox.getValue();
        // TODO: Implement game start logic
    }@FXML
    protected void onButtonHover() {
        startButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-cursor: hand;");
    }

    @FXML
    protected void onButtonExit() {
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-cursor: hand;");
    }
}