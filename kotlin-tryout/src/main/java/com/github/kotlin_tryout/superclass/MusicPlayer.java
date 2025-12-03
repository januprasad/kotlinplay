package com.github.kotlin_tryout.superclass;

public interface MusicPlayer {
    private void play() {
        System.out.println("playing");
    }
    default void pause() {
        System.out.println("playing");
    }
}

