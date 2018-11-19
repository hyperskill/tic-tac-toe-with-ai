package ticTacToe;

import ticTacToe.players.Player;
import ticTacToe.players.PlayersFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("Input comand:");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();

            if (cmd == null) continue;

            String[] cmds = cmd.split(" ");

            if (cmds[0] == null) continue;
            if (cmds[0].equals("exit")) return;
            if (cmds[0].equals("start") && cmds.length < 3) continue;

            Player first = PlayersFactory.getPlayer(cmds[1]);
            Player second = PlayersFactory.getPlayer(cmds[2]);

            if (first == null || second == null) continue;

            Game game = new Game(first, second);
            game.makeTurn();
        }
    }
}