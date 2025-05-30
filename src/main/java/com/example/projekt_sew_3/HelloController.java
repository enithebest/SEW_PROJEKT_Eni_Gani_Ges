package com.example.projekt_sew_3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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