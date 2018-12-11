package ticTacToe;

public class DisplayPlayer {

    public static void display() {
        Game.Players combination = Game.getCurrentPlayerName();
        switch (combination) {
            case NOT_STARTED : {
                UserInterface.whoMoves.setText("Not Started");
                break;
            }
            case PLAYER1 : {
                UserInterface.whoMoves.setText(UserInterface.player1.getText());
                break;
            }
            case PLAYER2 : {
                UserInterface.whoMoves.setText(UserInterface.player2.getText());
                break;
            }
        }
    }
}
