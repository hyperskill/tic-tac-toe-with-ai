package ticTacToe;

import ticTacToe.players.Player;
import ticTacToe.players.PlayersFactory;

import java.util.Scanner;

import static ticTacToe.enums.PlayerSign.O;
import static ticTacToe.enums.PlayerSign.X;

public class Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("Input command:");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();

            if (cmd == null) continue;

            String[] cods = cmd.split(" ");

            if (cods[0] == null) continue;
            if (cods[0].equals("exit")) return;
            if (cods[0].equals("start") && cods.length < 3) continue;

            Player first = PlayersFactory.getPlayer(cods[1], X);
            Player second = PlayersFactory.getPlayer(cods[2], O);

            if (first == null || second == null) continue;

            Game game = new Game(first, second);
            game.makeTurn();
        }
    }
}