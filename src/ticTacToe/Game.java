package ticTacToe;

import ticTacToe.enums.State;
import ticTacToe.players.Player;
import ticTacToe.utils.Matrix3d;

import java.util.*;

import static ticTacToe.enums.State.O_WIN;

class Game {
    private final Matrix3d fightField = new Matrix3d();
    private final Player secondPlayer;
    private final Player firstPlayer;
    private Player current;
    private State gameState = State.NOT_FINISHED;

    Game(Player first, Player second) {
        firstPlayer = first;
        secondPlayer = second;
        current = firstPlayer;
        printFightField(fightField);
    }

    void makeTurn() {

        while (gameState == State.NOT_FINISHED) {
            current.makeTurn(fightField);
            printFightField(fightField);
            gameState = getState(fightField);
            current = switchCurrentPlayer(current);
        }
        System.out.println(gameState);
    }

    private Player switchCurrentPlayer(final Player current) {
        if (current == firstPlayer)
            return secondPlayer;
        else
            return firstPlayer;
    }

    private void printFightField(Matrix3d fightField) {
        String topBoarderSign = "-";
        String splitter = String.join("", Collections.nCopies(Matrix3d.getDIMENSION()*Matrix3d.getDIMENSION(), topBoarderSign));
        System.out.println(splitter);
        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            System.out.println(String.format("| %s |", String.join(" ", fightField.getRow(i))));
        }
        System.out.println(splitter);
    }


    static private State getState(Matrix3d m) {

        Set<State> checks = new HashSet<>();

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            checks.add(winCheck(m.getColon(i)));
            checks.add(winCheck(m.getRow(i)));
        }

        checks.add(winCheck(m.getDiagonal(Matrix3d.DiagonalType.POSITIVE)));
        checks.add(winCheck(m.getDiagonal(Matrix3d.DiagonalType.NEGATIVE)));

        Optional<State> st = checks.stream().filter(s -> s != State.NOT_FINISHED).findFirst();

        if (st.isPresent()) {
            return st.get();
        } else {
            if (drawCheck(m))
                return State.DRAW;
            return State.NOT_FINISHED;
        }
    }

    private static State winCheck(String[] m) {
        Set<String> h = new HashSet<>(Arrays.asList(m));
        if (h.size() == 1) {
            if (h.contains("X")) return State.X_WIN;
            if (h.contains("O")) return O_WIN;
        }
        return State.NOT_FINISHED;
    }

    private static boolean drawCheck(Matrix3d m) {
        return Arrays.stream(m.getValues()).noneMatch(s -> s.equals(" "));
    }

}
