package ticTacToe.players;


public class PlayersFactory {
    PlayersFactory(){}

    public static Player getPlayer(String type) {
        switch (type){
            case "human":
                return new HumanPlayer();
            case "easy":
                return new EasyPlayer();
                default:
                    return null;
        }
    }
}
