package ticTacToe.enums;

public enum PlayerSign {
    X, O;
    public PlayerSign opposite() {
        switch (this) {
            case O:
                return X;
            case X:
                return O;
            default:
                throw new IllegalArgumentException("Play sign was not defined");
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
