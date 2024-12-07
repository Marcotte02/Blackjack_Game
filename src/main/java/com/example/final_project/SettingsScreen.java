package com.example.final_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// The settings screen for the Blackjack game application.

public class SettingsScreen {
    private Stage primaryStage;
    private Button musicToggleButton;
    public SettingsScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Display the settings screen
    public void showSettings() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Load and set background image
        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));

        Label titleLabel = new Label("Settings");
        titleLabel.setFont(Font.font("Georgia", 24));
        titleLabel.setTextFill(Color.WHITE); // Set text color to white for better visibility on the background

        musicToggleButton = createToggleButton("Background Music", SoundManager.getBackgroundMusicVolume() > 0);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            updateSoundVolumes();
            SoundManager.playMenuClick();
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(primaryStage);
            SoundManager.playMenuClick();
        });

        root.getChildren().addAll(titleLabel, musicToggleButton, saveButton, backButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Creates a toggle button for music settings.

    private Button createToggleButton(String label, boolean initialState) {
        Button toggleButton = new Button(label + " OFF");
        toggleButton.setOnAction(e -> {
            if (toggleButton.getText().endsWith("OFF")) {
                toggleButton.setText(label + " ON");
            } else {
                toggleButton.setText(label + " OFF");
            }
            SoundManager.playMenuClick();
        });
        toggleButton.setPrefWidth(150);
        if (initialState) {
            toggleButton.setText(label + " ON");
        }

        return toggleButton;
    }

    // Updates the sound volumes based on the state of the music toggle button
    private void updateSoundVolumes() {
        if (musicToggleButton.getText().endsWith("OFF")) {
            SoundManager.setBackgroundMusicVolume(0);
        } else {
            SoundManager.setBackgroundMusicVolume(0.1);
        }
    }
}
