package ticTacToe;

import ticTacToe.players.Player;
import ticTacToe.players.PlayersFactory;

import java.util.Scanner;

import static ticTacToe.enums.PlayerSign.O;
import static ticTacToe.enums.PlayerSign.X;

public class Main {

    public static void main(String[] args) {

//        while (true) {
//            System.out.println("Input command:");
//            Scanner scanner = new Scanner(System.in);
//            String cmd = scanner.nextLine();
//
//            if (cmd == null) continue;
//
//            String[] cmds = cmd.split(" ");
//
//            if (cmds[0] == null) continue;
//            if (cmds[0].equals("exit")) return;
//            if (cmds[0].equals("start") && cmds.length < 3) continue;
//
//            Player first = PlayersFactory.getPlayer(cmds[1], X);
//            Player second = PlayersFactory.getPlayer(cmds[2], O);
//
//            if (first == null || second == null) continue;
//
//            Game game = new Game(first, second);
//            game.makeTurn();
//        }

        for (int i = 0; i < 100; i++) {
            Player first = PlayersFactory.getPlayer("hard", X);
            Player second = PlayersFactory.getPlayer("medium", O);
            Game game = new Game(first, second);
            game.makeTurn();

        }
    }
}