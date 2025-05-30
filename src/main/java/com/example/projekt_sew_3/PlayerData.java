package com.example.projekt_sew_3;

public class PlayerData {

    private String playerName;
    private int highScore;

    /**
     * Constructor - Initialize player data
     * @param playerName The name of the player
     */
    public PlayerData(String playerName) {
        this.playerName = playerName;
        this.highScore = 0;
    }

    // Getters and setters (Person 2)
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Updates the high score if the new score is higher
     * @param newScore The score to compare against the current high score
     */
    public void updateHighScore(int newScore) {
        if (newScore > highScore) {
            highScore = newScore;
        }
    }
}