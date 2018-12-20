package ticTacToe.game;

import java.util.ArrayList;

public class GameLog {
    private ArrayList<Integer[][]> log = new ArrayList<>();


    public void addMove(Integer[][] move) {
        log.add(move);
    }

    public void clear() {
        log.clear();
    }

    public ArrayList<Integer[][]> get() {
        return log;
    }
}
