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
}
