package com.example.projekt_sew_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Main game class for the Snake Game
 */
public class SnakeGame extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int INITIAL_SNAKE_LENGTH = 3;

    private List<Rectangle> snake = new ArrayList<>();
    private Rectangle food;
    private int direction = 0; // 0: right, 1: down, 2: left, 3: up
    private boolean gameOver = false;
    private boolean gameStarted = false;
    private Random random = new Random();
    private Pane root;
    private AnimationTimer gameLoop;
    private GameScreen gameScreen;
    private ScoreManager scoreManager;
    private PlayerData playerData;
    private DifficultyManager difficultyManager;
    private ObstacleManager obstacleManager;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);

        gameScreen = new GameScreen(root);
        scoreManager = new ScoreManager(root);
        difficultyManager = new DifficultyManager(root);
        obstacleManager = new ObstacleManager(root);
        gameScreen.getStartButton().setOnAction(e -> startGame());
        gameScreen.getRestartButton().setOnAction(e -> restartGame());

        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= difficultyManager.getGameSpeed()) {
                    update();
                    lastUpdate = now;
                }
            }
        };

        scene.setOnKeyPressed(this::handleKeyPress);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame() {
        String playerName = gameScreen.getPlayerName();
        playerData = new PlayerData(playerName);

        gameScreen.hideStartScreen();
        difficultyManager.hideDifficultySelector();

        if (difficultyManager.getSelectedDifficulty().equals("Hard")) {
            obstacleManager.generateObstacles();
        }

        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            Rectangle segment = new Rectangle(UNIT_SIZE, UNIT_SIZE);
            segment.setFill(Color.GREEN);
            segment.setX(WIDTH / 2 - i * UNIT_SIZE);
            segment.setY(HEIGHT / 2);
            snake.add(segment);
            root.getChildren().add(segment);
        }

        food = new Rectangle(UNIT_SIZE, UNIT_SIZE);
        food.setFill(Color.RED);
        spawnFood();
        root.getChildren().add(food);

        scoreManager.resetScore();

        gameStarted = true;
        gameLoop.start();
    }

    private void handleKeyPress(KeyEvent event) {
        if (!gameStarted || gameOver) return;

        if (event.getCode() == KeyCode.RIGHT && direction != 2) {
            direction = 0;
        } else if (event.getCode() == KeyCode.DOWN && direction != 3) {
            direction = 1;
        } else if (event.getCode() == KeyCode.LEFT && direction != 0) {
            direction = 2;
        } else if (event.getCode() == KeyCode.UP && direction != 1) {
            direction = 3;
        }
    }

    private void update() {
        if (gameOver) return;

        double headX = snake.get(0).getX();
        double headY = snake.get(0).getY();
        switch (direction) {
            case 0: headX += UNIT_SIZE; break; // right
            case 1: headY += UNIT_SIZE; break; // down
            case 2: headX -= UNIT_SIZE; break; // left
            case 3: headY -= UNIT_SIZE; break; // up
        }

        if (!obstacleManager.isPositionValid(headX, headY)) {
            gameOver = true;
            showGameOver();
            return;
        }

        for (Rectangle segment : snake) {
            if (segment.getX() == headX && segment.getY() == headY) {
                gameOver = true;
                showGameOver();
                return;
            }
        }

        Rectangle newHead = new Rectangle(UNIT_SIZE, UNIT_SIZE);
        newHead.setFill(Color.GREEN);
        newHead.setX(headX);
        newHead.setY(headY);
        snake.add(0, newHead);
        root.getChildren().add(newHead);

        if (headX == food.getX() && headY == food.getY()) {
            spawnFood();
            scoreManager.incrementScore();
        } else {
            Rectangle tail = snake.remove(snake.size() - 1);
            root.getChildren().remove(tail);
        }
    }

    private void showGameOver() {
        gameLoop.stop();
        int currentScore = scoreManager.getCurrentScore();
        playerData.updateHighScore(currentScore);
        gameScreen.showGameOver(playerData.getPlayerName(), currentScore, playerData.getHighScore());
    }

    private void restartGame() {
        root.getChildren().clear();

        gameOver = false;
        gameStarted = false;
        direction = 0;
        snake.clear();
        obstacleManager.clearObstacles();

        gameScreen = new GameScreen(root);
        scoreManager = new ScoreManager(root);
        difficultyManager = new DifficultyManager(root);
        obstacleManager = new ObstacleManager(root);
        gameScreen.getStartButton().setOnAction(e -> startGame());
        gameScreen.getRestartButton().setOnAction(e -> restartGame());
    }

    private void spawnFood() {
        double x, y;
        do {
            x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
            y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        } while (!obstacleManager.isPositionValid(x, y));

        food.setX(x);
        food.setY(y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}