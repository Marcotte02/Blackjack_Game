package com.example.final_project;

import java.util.ArrayList;
import java.util.List;

// Represents a hand of cards.
public class Hand {

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // Adds a card to the hand.
    public void addCard(Card card) {
        cards.add(card);
    }

    // Calculates and returns the value of the hand.
    public int getValue() {
        int value = 0;
        int aces = 0;

        for (Card card : cards) {
            value += card.getValue();
            if (card.getRank().equals("Ace")) {
                aces++;
            }
        }

        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // Returns the list of cards in the hand.
    public List<Card> getCards() {
        return cards;
    }
}
