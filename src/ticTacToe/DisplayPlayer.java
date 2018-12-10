package ticTacToe;

public class DisplayPlayer {

    public static void display() {
        int combination = Game.getCurrentPlayerName();
        switch (combination) {
            case Game.NOT_STARTED : {
                UserInterface.whoMoves.setText("Not Started");
                break;
            }
            case Game.PLAYER1 : {
                UserInterface.whoMoves.setText(UserInterface.player1.getText());
                break;
            }
            case Game.PLAYER2 : {
                UserInterface.whoMoves.setText(UserInterface.player2.getText());
                break;
            }
        }
    }

}
