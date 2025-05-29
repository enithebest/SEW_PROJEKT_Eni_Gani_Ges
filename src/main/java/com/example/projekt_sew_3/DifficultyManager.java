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
}
