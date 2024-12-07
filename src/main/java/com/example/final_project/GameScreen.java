package com.example.final_project;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Paths;

// Game screen for both the betting and actual game

public class GameScreen extends Application {
    private BlackjackGame game;
    private Label playerBalanceLabel;
    private HBox playerHandBox;
    private HBox dealerHandBox;
    private Label messageLabel;
    private ImageView dealerSecondCardImageView;
    private Label currentBetLabel;
    private int currentBet;
    private Stage primaryStage;
    private Button newRoundButton;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        game = new BlackjackGame();
        SoundManager.playBackgroundAmbience();
        showBettingScreen();
    }

    // Create the betting screen
    private void showBettingScreen() {

        VBox bettingRoot = new VBox(10);
        bettingRoot.setAlignment(Pos.CENTER);

        // Create Labels
        Label placeYourBetLabel = new Label("Place your bet!");
        placeYourBetLabel.setTextFill(Color.WHITE);
        placeYourBetLabel.setFont(Font.font("Georgia", 24));

        playerBalanceLabel = new Label("Balance: $" + game.getPlayerBalance());
        playerBalanceLabel.setTextFill(Color.WHITE);
        playerBalanceLabel.setFont(Font.font("Georgia", 18));

        currentBetLabel = new Label("Current Bet: $0");
        currentBetLabel.setTextFill(Color.WHITE);
        currentBetLabel.setFont(Font.font("Georgia", 18));

        messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 18));

        currentBet = 0;

        // Load images for the bets and set them as clickable
        ImageView bet10ImageView = createBetImageView("/buttons/btBet10.png", 10);
        bet10ImageView.setFitWidth(120);
        bet10ImageView.setFitHeight(120);
        ImageView bet50ImageView = createBetImageView("/buttons/btBet50.png", 50);
        bet50ImageView.setFitWidth(120);
        bet50ImageView.setFitHeight(120);
        ImageView bet100ImageView = createBetImageView("/buttons/btBet100.png", 100);
        bet100ImageView.setFitWidth(120);
        bet100ImageView.setFitHeight(120);

        // Add scale animation on hover
        addScaleAnimation(bet10ImageView);
        addScaleAnimation(bet50ImageView);
        addScaleAnimation(bet100ImageView);

        // Create Buttons
        Button undoBetButton = new Button("Undo Bet");
        undoBetButton.setOnAction(e -> {
            undoBet();
            SoundManager.playMenuClick();
        });

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e -> {
            if (currentBet > 0) {
                game.setCurrentBet(currentBet);
                showGameScreen(currentBet); // Pass currentBet to showGameScreen
            } else {
                messageLabel.setText("Please place a bet to start the game.");
            }
            SoundManager.playMenuClick();
        });

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> {
            saveBalance();
            messageLabel.setText("Game Saved!");
            SoundManager.playMenuClick();
        });

        Button loadButton = new Button("Load Game");
        loadButton.setOnAction(e -> {
            loadBalance();
            updateUI();
            SoundManager.playMenuClick();
        });

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(primaryStage);
            SoundManager.stopBackgroundAmbience();
            SoundManager.playMenuClick();
        });

        // Store page elements in HBoxs
        HBox betButtons = new HBox(10, undoBetButton, startGameButton, backButton);
        betButtons.setAlignment(Pos.CENTER);

        HBox dataButtons = new HBox(20, saveButton, loadButton);
        dataButtons.setAlignment((Pos.CENTER));

        HBox betImages = new HBox(10, bet10ImageView, bet50ImageView, bet100ImageView);
        betImages.setAlignment(Pos.CENTER);

        // Place HBoxs on screen
        bettingRoot.getChildren().addAll(placeYourBetLabel, playerBalanceLabel, currentBetLabel, betImages, betButtons, dataButtons, messageLabel);

        // Load and set background image
        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        bettingRoot.setBackground(new Background(background));

        Scene bettingScene = new Scene(bettingRoot, 800, 800);
        primaryStage.setTitle("Blackjack - Place Your Bet");
        primaryStage.setScene(bettingScene);
        primaryStage.show();

        // Add fade-in animation to bet images
        addFadeInAnimation(bet10ImageView);
        addFadeInAnimation(bet50ImageView);
        addFadeInAnimation(bet100ImageView);

        // Add slide-in animation to labels
        addSlideInAnimation(placeYourBetLabel);
        addSlideInAnimation(playerBalanceLabel);
        addSlideInAnimation(currentBetLabel);
    }

    // Make chip pictures able to bet
    private ImageView createBetImageView(String imagePath, int betAmount) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setOnMouseClicked(event -> {
            updateBet(betAmount);
            SoundManager.playButtonClickSound();
        });
        imageView.setOnMouseEntered(e -> imageView.setCursor(Cursor.HAND)); // Change cursor to hand
        return imageView;
    }

    private void updateBet(int bet) {
        if (game.getPlayerBalance() >= currentBet + bet) {
            currentBet += bet;
            currentBetLabel.setText("Current Bet: $" + currentBet);
            playerBalanceLabel.setText("Balance: $" + (game.getPlayerBalance() - currentBet));
        } else {
            messageLabel.setText("Insufficient balance to place this bet.");
        }
    }

    private void undoBet() {
        currentBet = 0;
        currentBetLabel.setText("Current Bet: $0");
        playerBalanceLabel.setText("Balance: $" + game.getPlayerBalance());
    }

    private void addFadeInAnimation(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void addScaleAnimation(ImageView imageView) {
        ScaleTransition enlargeTransition = new ScaleTransition(Duration.millis(200), imageView);
        enlargeTransition.setToX(1.1);
        enlargeTransition.setToY(1.1);

        ScaleTransition shrinkTransition = new ScaleTransition(Duration.millis(200), imageView);
        shrinkTransition.setToX(1.0);
        shrinkTransition.setToY(1.0);

        imageView.setOnMouseEntered(e -> {
            enlargeTransition.playFromStart();
            imageView.setCursor(Cursor.HAND);
        });
        imageView.setOnMouseExited(e -> shrinkTransition.playFromStart());
    }

    private void addSlideInAnimation(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromY(-50);
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public static void saveGame(int playerBalance, int currentBet) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            outputStream.writeInt(playerBalance);
            outputStream.writeInt(currentBet);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    private void saveBalance() {
        int playerBalance = game.getPlayerBalance();
        int currentBet = getCurrentBet();
        saveGame(playerBalance, currentBet);
    }

    private void loadBalance() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            int playerBalance = inputStream.readInt();
            int currentBet = inputStream.readInt();
            game.setPlayerBalance(playerBalance); // Set player balance in game instance
            setCurrentBet(currentBet); // Set current bet in this class instance
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }

    private void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    //Create Game Screen
    private void showGameScreen(int currentBet) {
        StackPane gameRoot = new StackPane();

        Image backgroundImage = new Image("/pictures/poker_table_game_bg.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        gameRoot.setBackground(new Background(background));

        playerBalanceLabel = new Label("Balance: $" + game.getPlayerBalance());
        playerBalanceLabel.setTextFill(Color.WHITE);
        playerBalanceLabel.setFont(Font.font("Georgia", 24));

        playerHandBox = new HBox(10);
        dealerHandBox = new HBox(10);

        Label playerHandLabel = new Label("Player Hand: ");
        playerHandLabel.setTextFill(Color.WHITE);
        playerHandLabel.setFont(Font.font("Georgia", 18));

        Label dealerHandLabel = new Label("Dealer Hand: ");
        dealerHandLabel.setTextFill(Color.WHITE);
        dealerHandLabel.setFont(Font.font("Georgia", 18));

        messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setFont(Font.font("Georgia", 18));

        dealerSecondCardImageView = new ImageView(game.getBackCardImage());
        dealerSecondCardImageView.setFitWidth(100);
        dealerSecondCardImageView.setFitHeight(150);

        currentBetLabel = new Label("Current Bet: $" + currentBet); // Set current bet label
        currentBetLabel.setTextFill(Color.WHITE);
        currentBetLabel.setFont(Font.font("Georgia", 18));

        Button hitButton = new Button("Hit");
        hitButton.setOnAction(e -> {
            game.playerHit();
            updateUI();
            SoundManager.playCardDealSound();
        });

        Button standButton = new Button("Stand");
        standButton.setOnAction(e -> {
            if (!game.isRoundOver()) {
                game.dealerPlay();
                game.finishRound();
                updateUI();
                SoundManager.playStandKnockSound();
            }
        });

        newRoundButton = new Button("New Round");
        newRoundButton.setDisable(true); // Disable new round button initially
        newRoundButton.setOnAction(e -> {
            showBettingScreen();
            SoundManager.playMenuClick();
        });

        HBox buttons = new HBox(10, hitButton, standButton, newRoundButton);
        buttons.setAlignment(Pos.CENTER);

        VBox gameContent = new VBox(10);
        gameContent.setAlignment(Pos.CENTER);
        gameContent.getChildren().addAll(playerBalanceLabel, currentBetLabel, playerHandLabel, playerHandBox,
                dealerHandLabel, dealerHandBox, messageLabel, buttons);

        gameRoot.getChildren().addAll(gameContent);

        Scene gameScene = new Scene(gameRoot, 800, 800);
        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(gameScene);
        primaryStage.show();

        startNewRound();
    }

    private void startNewRound() {
        game.startNewRound();
        updateUI();
    }

    private void updateUI() {
        playerBalanceLabel.setText("Balance: $" + game.getPlayerBalance());
        updateHandUI(playerHandBox, game.getPlayerHand());

        updateHandUI(dealerHandBox, game.getDealerHand());

        dealerHandBox.getChildren().clear();

        // Add visible cards
        for (int i = 0; i < game.getDealerHand().getCards().size(); i++) {
            Card card = game.getDealerHand().getCards().get(i);
            ImageView cardImageView = new ImageView(card.getImage());
            cardImageView.setFitWidth(100);  // Set width
            cardImageView.setFitHeight(150); // Set height
            dealerHandBox.getChildren().add(cardImageView);

            // Hide the second card if the round is not over and it's the second card
            if (!game.isRoundOver() && i == 1) {
                dealerHandBox.getChildren().remove(cardImageView);
                dealerHandBox.getChildren().add(dealerSecondCardImageView);
            }
        }

        messageLabel.setText(game.getMessage());

        // Enable new round button after the game is over and player still has balance
        if (game.isRoundOver() && game.getPlayerBalance() > 0) {
            newRoundButton.setDisable(false);
        } else {
            newRoundButton.setDisable(true);
        }

        checkGameOver();
    }

    private void updateHandUI(HBox handBox, Hand hand) {
        handBox.getChildren().clear();
        handBox.setAlignment(Pos.CENTER);

        for (Card card : hand.getCards()) {
            ImageView cardImageView = new ImageView(card.getImage());
            cardImageView.setFitWidth(100);  // Set width
            cardImageView.setFitHeight(150); // Set height
            handBox.getChildren().add(cardImageView);
        }
    }

    private void checkGameOver() {
        if (game.getPlayerBalance() <= 0 || game.getPlayerBalance() >= 1000) {
            GameOverScreen gameOverScreen = new GameOverScreen(game.getPlayerBalance() >= 1000);
            gameOverScreen.start(primaryStage);
        }
    }

    public int getCurrentBet() {
        return currentBet;
    }
}
