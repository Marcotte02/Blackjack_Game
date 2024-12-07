package com.example.final_project;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Manages the sound effects and background music for the Blackjack game application.

public class SoundManager {
    private static MediaPlayer backgroundMusicPlayer;
    private static MediaPlayer backgroundAmbiencePlayer;
    private static double backgroundMusicVolume = 0.1;
    private static double backgroundAmbienceVolume = 0.3;
    private static MediaPlayer buttonClickPlayer;
    private static MediaPlayer cardDealPlayer;
    private static MediaPlayer standKnockPlayer;
    private static MediaPlayer menuClickPlayer;

    // Plays the background music.
    public static void playBackgroundMusic() {
        String musicPath = "/sounds/Background_Music.mp3";
        Media media = new Media(SoundManager.class.getResource(musicPath).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.setVolume(backgroundMusicVolume);
        backgroundMusicPlayer.play();
    }

    // Sets the background music volume.
    public static void setBackgroundMusicVolume(double volume) {
        backgroundMusicVolume = volume;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    // Gets the background music volume.
    public static double getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    // Plays the background ambience sound.
    public static void playBackgroundAmbience() {
        String ambiencePath = "/sounds/Background_Ambience.wav";
        Media media = new Media(SoundManager.class.getResource(ambiencePath).toExternalForm());
        backgroundAmbiencePlayer = new MediaPlayer(media);
        backgroundAmbiencePlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundAmbiencePlayer.setVolume(backgroundAmbienceVolume);
        backgroundAmbiencePlayer.play();
    }

    // Stops the background ambience sound.
    public static void stopBackgroundAmbience() {
        if (backgroundAmbiencePlayer != null) {
            backgroundAmbiencePlayer.stop();
        }
    }

    // Plays the button click sound.
    public static void playButtonClickSound() {
        String clickSoundPath = "/sounds/Poker_Chip.wav";
        Media media = new Media(SoundManager.class.getResource(clickSoundPath).toExternalForm());
        buttonClickPlayer = new MediaPlayer(media);
        buttonClickPlayer.play();
    }

    // Plays the card deal sound.
    public static void playCardDealSound() {
        String cardDealPath = "/sounds/Card_Deal.wav";
        Media media = new Media(SoundManager.class.getResource(cardDealPath).toExternalForm());
        cardDealPlayer = new MediaPlayer(media);
        cardDealPlayer.play();
    }

    // Plays the stand knock sound.
    public static void playStandKnockSound() {
        String standKnockPath = "/sounds/Stand_Knock.wav";
        Media media = new Media(SoundManager.class.getResource(standKnockPath).toExternalForm());
        standKnockPlayer = new MediaPlayer(media);
        standKnockPlayer.play();
    }

    // Plays the menu click sound.
    public static void playMenuClick() {
        String menuClickPath = "/sounds/Button_Click.wav";
        Media media = new Media(SoundManager.class.getResource(menuClickPath).toExternalForm());
        menuClickPlayer = new MediaPlayer(media);
        menuClickPlayer.play();
    }
}
