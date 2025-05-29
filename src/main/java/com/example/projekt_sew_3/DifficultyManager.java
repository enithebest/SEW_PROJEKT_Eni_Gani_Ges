package com.example.projekt_sew_3;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
public class DifficultyManager {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private ComboBox<String> difficultySelector;
    private Text difficultyLabel;
    private Pane root;

    /**
     * Constructor - Initialize difficulty manager
     * @param root The root pane to add UI elements to
     */
    public DifficultyManager(Pane root) {
        this.root = root;
        initializeDifficultySelector();
    }

    /**
     * Initialize the difficulty selection UI
     */
    private void initializeDifficultySelector() {
        difficultyLabel = new Text("Select Difficulty:");
        difficultyLabel.setFill(Color.BLACK);
        difficultyLabel.setFont(Font.font(16));
        difficultyLabel.setX(WIDTH / 2 - 80);
        difficultyLabel.setY(HEIGHT / 2 - 60);
        root.getChildren().add(difficultyLabel);

        difficultySelector = new ComboBox<>();
        difficultySelector.getItems().addAll("Easy", "Medium", "Hard");
        difficultySelector.setValue("Medium"); // Default difficulty
        difficultySelector.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px;");
        difficultySelector.setLayoutX(WIDTH / 2 - 60);
        difficultySelector.setLayoutY(HEIGHT / 2 - 50);
        root.getChildren().add(difficultySelector);
    }

}
