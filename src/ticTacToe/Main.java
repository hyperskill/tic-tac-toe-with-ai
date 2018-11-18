package ticTacToe;

public class Main {

    public static void main(String[] args) {

        do {
            Game game = new Game();
            game.makeTurn();
            System.out.println("New round");
        }while (true);

    }


}