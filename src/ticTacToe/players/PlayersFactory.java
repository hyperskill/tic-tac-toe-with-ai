package ticTacToe.players;


import ticTacToe.enums.PlayerSign;

public class PlayersFactory {
    public static Player getPlayer(String type, PlayerSign sign) {
        switch (type){
            case "human":
                return new HumanPlayer(sign);
            case "easy":
                return new EasyPlayer(sign);
            case "medium":
                return new MediumPlayer(sign);
            case "hard":
                return new HardPlayer(sign);
                default:
                    return null;
        }
    }
}
