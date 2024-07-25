package com.github.solid;

public class SampleAbs {
    public static void main(String[] args) {
        Player player = new MPlayer();
    }
}

abstract class Player {
    void play() {
        playerInit();
    }

    abstract void playerInit();
}

class MPlayer extends Player {

    MPlayer(){
        play();
    }
    @Override
    void playerInit() {
        System.out.println("Init");
    }
}


