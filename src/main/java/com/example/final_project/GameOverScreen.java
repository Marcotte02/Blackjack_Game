package com.example.final_project;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

// Create game over screen

public class GameOverScreen extends Application {
    private boolean playerWon;

    public GameOverScreen(boolean playerWon) {
        this.playerWon = playerWon;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Over");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        root.setBackground(new Background(background));

        Label resultLabel;
        if (playerWon) {
            resultLabel = new Label("Congratulations! You won the game!");
        } else {
            resultLabel = new Label("Game over! You lost all your money.");
        }
        resultLabel.setFont(Font.font("Georgia", 24));
        resultLabel.setTextFill(Color.WHITE);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setFont(Font.font("Georgia", 18));
        mainMenuButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(primaryStage);
        });

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setFont(Font.font("Georgia", 18));
        playAgainButton.setOnAction(e -> {
            GameScreen gameScreen = new GameScreen();
            gameScreen.start(primaryStage);
        });

        root.getChildren().addAll(resultLabel, mainMenuButton, playAgainButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create the TranslateTransition animation for the resultLabel
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), resultLabel);
        transition.setFromY(-scene.getHeight());
        transition.setToY(0);
        transition.play();
    }
}
