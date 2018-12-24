package ticTacToe.game;

import java.util.ArrayList;

public class GameLog {
    private ArrayList<Long> log = new ArrayList<>();


    public void addMove(Long moveCode) {
        log.add(moveCode);
    }

    public void clear() {
        log.clear();
    }

    public ArrayList<Long> get() {
        return log;
    }
}
