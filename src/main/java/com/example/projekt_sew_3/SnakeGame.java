package com.example.projekt_sew_3;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int INITIAL_SNAKE_LENGTH = 3;

    private List<Rectangle> snake = new ArrayList<>();
    private Rectangle food;
    private int direction = 0;
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