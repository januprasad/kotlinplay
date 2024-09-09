package com.github.interview_prep.superclass;

public interface MusicPlayer {
    private void play() {
        System.out.println("playing");
    }
    default void pause() {
        System.out.println("playing");
    }
}

