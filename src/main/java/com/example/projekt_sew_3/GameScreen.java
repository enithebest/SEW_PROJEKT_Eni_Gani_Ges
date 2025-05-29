package com.example.projekt_sew_3;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GameScreen {
    private Pane root;
    private Text titleText;
    private TextField nameInput;
    private Button startButton;
    private Text gameOverText;
    private Text scoreText;
    private Text highScoreText;
    private Text playerNameText;
    private Button restartButton;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public GameScreen(Pane root) {
        this.root = root;
        initializeStartScreen();
        initializeGameOverScreen();
    }

    private void initializeStartScreen() {
        titleText = new Text("SNAKE GAME");
        titleText.setFill(Color.BLACK);
        titleText.setFont(Font.font(40));
        titleText.setX(WIDTH / 2 - 120);
        titleText.setY(HEIGHT / 2 - 120);
        root.getChildren().add(titleText);

        nameInput = new TextField();
        nameInput.setPromptText("Enter your name");
        nameInput.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16px; -fx-border-color: black;");
        nameInput.setLayoutX(WIDTH / 2 - 100);
        nameInput.setLayoutY(HEIGHT / 2 - 60);
        nameInput.setPrefWidth(200);
        root.getChildren().add(nameInput);

        startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        startButton.setLayoutX(WIDTH / 2 - 60);
        startButton.setLayoutY(HEIGHT / 2 + 20);
        root.getChildren().add(startButton);
    }

    private void initializeGameOverScreen() {
        gameOverText = new Text("GAME OVER");
        gameOverText.setFill(Color.BLACK);
        gameOverText.setFont(Font.font(40));
        gameOverText.setX(WIDTH / 2 - 100);
        gameOverText.setY(HEIGHT / 2 - 80);
        gameOverText.setVisible(false);
        root.getChildren().add(gameOverText);

        playerNameText = new Text();
        playerNameText.setFill(Color.BLACK);
        playerNameText.setFont(Font.font(20));
        playerNameText.setX(WIDTH / 2 - 80);
        playerNameText.setY(HEIGHT / 2 - 40);
        playerNameText.setVisible(false);
        root.getChildren().add(playerNameText);
        scoreText = new Text();
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font(20));
        scoreText.setX(WIDTH / 2 - 60);
        scoreText.setY(HEIGHT / 2 - 20);
        scoreText.setVisible(false);
        root.getChildren().add(scoreText);

        highScoreText = new Text();
        highScoreText.setFill(Color.BLACK);
        highScoreText.setFont(Font.font(20));
        highScoreText.setX(WIDTH / 2 - 80);
        highScoreText.setY(HEIGHT / 2 + 10);
        highScoreText.setVisible(false);
        root.getChildren().add(highScoreText);

        restartButton = new Button("Play Again");
        restartButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;");
        restartButton.setLayoutX(WIDTH / 2 - 60);
        restartButton.setLayoutY(HEIGHT / 2 + 50);
        restartButton.setVisible(false);
        root.getChildren().add(restartButton);
    }


}
