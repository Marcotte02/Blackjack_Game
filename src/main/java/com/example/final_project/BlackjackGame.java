package com.example.final_project;

import javafx.scene.image.Image;

// Game logic for blackjack

public class BlackjackGame {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private int playerBalance;
    private int currentBet;
    private String message;
    private boolean roundOver;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        playerBalance = 100;
    }

    public void startNewRound() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        message = "";
        roundOver = false;

        // Check for player and dealer blackjacks
        checkInitialBlackjacks();
    }

    public void playerHit() {
        if (!roundOver) {
            playerHand.addCard(deck.drawCard());
            if (playerHand.getValue() > 21) {
                playerBalance -= currentBet;
                message = "You busted! You lose.";
                roundOver = true;
            }
        }
    }

    public void dealerPlay() {
        while (dealerHand.getValue() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
        // Check for dealer blackjack after drawing cards
        checkBlackjack(dealerHand, "Dealer");
    }

    private void checkInitialBlackjacks() {
        // Check for player's blackjack
        checkBlackjack(playerHand, "Player");

        // Check for dealer's blackjack
        checkBlackjack(dealerHand, "Dealer");
    }

    private void checkBlackjack(Hand hand, String playerName) {
        if (hand.getCards().size() == 2 && hand.getValue() == 21) {
            // Blackjack condition met
            roundOver = true;
            message = playerName + " has Blackjack!";
            if (playerName.equals("Player")) {
                playerBalance += currentBet;
            } else {
                playerBalance -= currentBet; // Player loses bet
            }
        }
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Image getBackCardImage() {
        String imagePath = "/card/b1fv.png";
        return new Image(imagePath);
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(int playerBalance) {
        this.playerBalance = playerBalance;
    }

    public void setCurrentBet(int bet) {
        currentBet = bet;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRoundOver() {
        return roundOver;
    }

    public void finishRound() {
        if (!roundOver) {
            int playerValue = playerHand.getValue();
            int dealerValue = dealerHand.getValue();

            if (playerValue > 21) {
                playerBalance -= currentBet;
                message = "You busted! You lose.";
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                playerBalance += currentBet;
                message = "You win!";
            } else if (playerValue < dealerValue) {
                playerBalance -= currentBet;
                message = "Dealer wins. You lose.";
            } else {
                message = "It's a tie!";
            }
            roundOver = true;
        }
    }
}
