package com.example.final_project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// The instructions screen displayed to the user.
public class InstructionsScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Instructions");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        // Load and set background image.
        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));

        Label titleLabel = new Label("Game Instructions");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Georgia", 28));

        Label instructionsLabel = new Label(
                "1. Start the game by placing your bet. You can choose to bet $10, $50, or $100.\n\n" +
                        "2. The goal is to get a hand value as close to 21 as possible without exceeding it.\n\n" +
                        "3. You are dealt two cards initially. Number cards are worth their face value, face cards (King, Queen, Jack) are worth 10, and Ace can be worth 1 or 11.\n\n" +
                        "4. If you win a round (by having a higher hand value than the dealer without exceeding 21), you win back double your bet.\n\n" +
                        "5. Reach $1000 to win the game, or lose all your money to lose the game.\n\n" +
                        "6. During your turn, you can click 'Hit' to draw another card or 'Stand' to end your turn.\n\n" +
                        "7. The dealer will then play their hand according to set rules (usually hitting until they have 17 or higher).\n\n" +
                        "8. If your hand value exceeds 21, you bust and lose your bet.\n\n" +
                        "9. Enjoy the game and good luck!"
        );
        instructionsLabel.setTextFill(Color.WHITE);
        instructionsLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        instructionsLabel.setWrapText(true);
        instructionsLabel.setMaxWidth(500);

        Button backButton = new Button("Back to Main Menu");
        backButton.setFont(Font.font("Georgia", 16));
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(primaryStage);
            SoundManager.playMenuClick();
        });

        root.getChildren().addAll(titleLabel, instructionsLabel, backButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
