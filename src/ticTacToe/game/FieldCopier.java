package ticTacToe.game;

import java.util.Arrays;

public class FieldCopier {

    Integer[][] copy;

    public Integer[][]  copy(Integer[][] move) {
        this.copy = move.clone();
        return copy;
    }
}
