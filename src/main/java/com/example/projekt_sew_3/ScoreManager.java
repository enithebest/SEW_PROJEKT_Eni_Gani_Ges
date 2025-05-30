package com.example.projekt_sew_3;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class ScoreManager {
    private Pane root;
    private Text scoreText;
    private int currentScore;

    public ScoreManager(Pane root) {
        this.root = root;
        this.currentScore = 0;
        initializeScoreDisplay();
    }

    private void initializeScoreDisplay() {
        scoreText = new Text("Score: 0");
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font(20));
        scoreText.setX(10);
        scoreText.setY(30);
        root.getChildren().add(scoreText);
    }

    public void incrementScore() {
        currentScore++;
        updateScoreDisplay();
    }

    public void resetScore() {
        currentScore = 0;
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        scoreText.setText("Score: " + currentScore);
    }

    public int getCurrentScore() {
        return currentScore;
    }
} 