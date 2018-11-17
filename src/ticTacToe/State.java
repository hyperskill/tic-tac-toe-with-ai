package ticTacToe;

public enum State {
    NOT_FINISHED, DRAW, X_WIN, O_WIN;

    @Override
    public String toString() {
        switch (this){
            case DRAW:
                return "Draw";
            case O_WIN:
                return "O wins";
            case X_WIN:
                return "X wins";
            case NOT_FINISHED:
                return "Game not finished";
            default:
                throw new IllegalArgumentException("Game state is not declared " + this);
        }
    }
}
