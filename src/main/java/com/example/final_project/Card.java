package com.example.final_project;

import javafx.scene.image.Image;

// For the cards to fill the decks

public class Card {

    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        String imagePath = "/card/" + rank.toLowerCase() + "_of_" + suit.toLowerCase() + ".png";
        return new Image(imagePath);
    }
}