package com.example.final_project;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

// Credits screen for pictures and sounds used

public class CreditScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Credits");

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

        Label titleLabel = new Label("Credits");
        titleLabel.setFont(Font.font("Georgia", 24));
        titleLabel.setTextFill(Color.WHITE);

        Label creditsLabel = new Label(
                "Poker Table background - https://www.freepik.com/free-photo/green-textile-texture_1462439.htm#query=green%20fabric&position=46&from_view=keyword&track=ais_hybrid&uuid=a413d255-00d2-4923-8358-4734ab736ac3\n\n" +
                        "Poker Cards - Gathered from class files\n\n" +
                        "Chip Buttons - Created by Connor\n\n" +
                        "Sounds:\n" +
                        "Background Music - Classic by HoliznaCC0\n" +
                        "Background Ambience - https://freesound.org/people/bhuveh/sounds/490645/\n" +
                        "Poker Chip - https://freesound.org/people/fartheststar/sounds/201805/\n" +
                        "Card Deal Sound - https://freesound.org/people/el_boss/sounds/571577/\n" +
                        "Stand Knock Sound - https://freesound.org/people/ripper351/sounds/151088/\n" +
                        "Button Click - https://freesound.org/people/NenadSimic/sounds/171697/"
        );
        creditsLabel.setFont(Font.font("Georgia", 18));
        creditsLabel.setTextFill(Color.WHITE);
        creditsLabel.setWrapText(true);
        creditsLabel.setMaxWidth(500);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setFont(Font.font("Georgia", 18));
        mainMenuButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(primaryStage);
        });

        root.getChildren().addAll(titleLabel, creditsLabel, mainMenuButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create the TranslateTransition animation for the creditsLabel
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), creditsLabel);
        transition.setFromY(scene.getHeight());
        transition.setToY(0);
        transition.play();
    }
}
