package com.example.final_project;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

// Intro screen for application

public class IntroScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        // Load and set background image
        Image backgroundImage = new Image("/pictures/poker_chips.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));

        Label text1 = new Label("Created by MuddyNiX...");
        text1.setTextFill(Color.WHITE);
        text1.setFont(Font.font("Georgia", 35));
        text1.setTranslateX(2);
        text1.setTranslateY(2);

        Label text2 = new Label("Created by MuddyNiX...");
        text2.setTextFill(Color.BLACK);
        text2.setFont(Font.font("Georgia", 35));

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(text1, text2);

        root.getChildren().add(stackPane);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Intro Screen");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add transition to move text from top to center
        stackPane.setTranslateY(-800);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), stackPane);
        transition.setToY(0);
        transition.play();

        // Transition to MainMenu after a delay
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> {
            MainMenu mainMenuScreen = new MainMenu();
            mainMenuScreen.start(primaryStage);
        });
        delay.play();
    }

}
