package ticTacToe.game;

public class FieldCopier {
    public Integer[][]  copy(Integer[][] move) {
        Integer[][] copy = new Integer[move.length][move.length];

        for (int s = 0; s < move.length; s++) {
            System.arraycopy(move[s], 0, copy[s], 0, move.length);
        }

        return copy;
    }
}
