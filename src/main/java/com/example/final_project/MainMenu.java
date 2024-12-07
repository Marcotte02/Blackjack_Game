package com.example.final_project;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

// The main menu screen for the Blackjack game application.

public class MainMenu extends Application {
    private static boolean isFirstTime = true;

    @Override
    public void start(Stage primaryStage) {
        VBox mainMenuRoot = new VBox(10);
        mainMenuRoot.setAlignment(Pos.CENTER);

        // Load and set background image
        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        mainMenuRoot.setBackground(new Background(background));

        Label titleLabel = new Label("Welcome to Blackjack!");
        titleLabel.setWrapText(true);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 24));

        // Load card images
        Image cardImage = new Image("/card/ace_of_spades.png");
        Image cardImage2 = new Image("/card/king_of_hearts.png");

        // Create card image views
        ImageView leftCard = new ImageView(cardImage);
        ImageView rightCard = new ImageView(cardImage2);

        // Set card image view sizes
        double cardWidth = 72;
        double cardHeight = 96;
        leftCard.setFitWidth(cardWidth);
        leftCard.setFitHeight(cardHeight);
        rightCard.setFitWidth(cardWidth);
        rightCard.setFitHeight(cardHeight);

        // Fade-in effect for the titleLabel
        FadeTransition titleFade = new FadeTransition(Duration.seconds(2), titleLabel);
        titleFade.setFromValue(0);
        titleFade.setToValue(1);

        // Create scale transitions for cards
        ScaleTransition leftCardScale = createScaleTransition(leftCard);
        ScaleTransition rightCardScale = createScaleTransition(rightCard);

        // Play fade and scale transitions
        titleFade.play();
        leftCardScale.play();
        rightCardScale.play();

        // HBox for title and card images
        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(leftCard, titleLabel, rightCard);

        // Create buttons
        Button playButton = new Button("Play");
        playButton.setPrefSize(120, 40);
        playButton.setOnAction(e -> {
            GameScreen gameScreen = new GameScreen();
            gameScreen.start(primaryStage);
            SoundManager.playMenuClick();
        });

        Button instructionsButton = new Button("Instructions");
        instructionsButton.setPrefSize(120, 40);
        instructionsButton.setOnAction(e -> {
            InstructionsScreen instructionsScreen = new InstructionsScreen();
            instructionsScreen.start(primaryStage);
            SoundManager.playMenuClick();
        });

        Button settingsButton = new Button("Settings");
        settingsButton.setPrefSize(120, 40);
        settingsButton.setOnAction(e -> {
            SettingsScreen settingsScreen = new SettingsScreen(primaryStage);
            settingsScreen.showSettings();
            SoundManager.playMenuClick();
        });

        Button creditsButton = new Button("Credits");
        creditsButton.setPrefSize(120, 40);
        creditsButton.setOnAction(e -> {
            CreditScreen creditScreen = new CreditScreen();
            creditScreen.start(primaryStage);
            SoundManager.playMenuClick();
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(120, 40);
        exitButton.setOnAction(e -> primaryStage.close());

        // VBox for buttons
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(playButton, instructionsButton, settingsButton, creditsButton, exitButton);

        // Add titleBox and buttonBox to the main menu layout
        mainMenuRoot.getChildren().addAll(titleBox, buttonBox);
        mainMenuRoot.setSpacing(20);

        Scene mainMenuScene = new Scene(mainMenuRoot, 800, 800);
        primaryStage.setTitle("Blackjack - Main Menu");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

        // Play background music on first launch
        if (isFirstTime) {
            SoundManager.playBackgroundMusic();
            isFirstTime = false;
        }
    }

    // Create Scale transition for ImageView
    private ScaleTransition createScaleTransition(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), imageView);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        return scaleTransition;
    }
}
