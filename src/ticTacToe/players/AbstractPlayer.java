package ticTacToe.players;

import ticTacToe.enums.PlayerSign;

public class AbstractPlayer {

    protected final PlayerSign playerSign;

    public AbstractPlayer(PlayerSign playerSign) {
        this.playerSign = playerSign;
    }

}
