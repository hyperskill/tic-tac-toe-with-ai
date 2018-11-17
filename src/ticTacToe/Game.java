package ticTacToe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Game {

    static State getState(Matrix3d m) {

        Set<State> checks = new HashSet<>();

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            checks.add(winCheck(m.getRow(i)));
            checks.add(winCheck(m.getColon(i)));
            checks.add(winCheck(m.getDiagonal(Matrix3d.DiagonalType.POSITIVE)));
            checks.add(winCheck(m.getDiagonal(Matrix3d.DiagonalType.NEGATIVE)));
        }

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
            if (h.contains("O")) return State.O_WIN;
        }
        return State.NOT_FINISHED;
    }

    private static boolean drawCheck(Matrix3d m){
        return Arrays.stream(m.getValues()).noneMatch(s->s.equals(" "));
    }

}
