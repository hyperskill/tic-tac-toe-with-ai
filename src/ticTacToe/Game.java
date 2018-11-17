package ticTacToe;

import java.util.*;

public class Game {
    private String player = "X";
    private Matrix3d fightField = new Matrix3d();

    public void makeTurn() {

        printFightField(fightField);

        String[] parametersAsStrings;
        System.out.println("Enter the coordinates:");
        Scanner scannerInput = new Scanner(System.in);
        String parameters = scannerInput.nextLine();
        parametersAsStrings = parameters.split(" ");

        int rowId = Integer.parseInt(parametersAsStrings[0]);
        int colId = Integer.parseInt(parametersAsStrings[1]);

        setFightFieldValue(rowId, colId, fightField);

        printFightField(fightField);


    }

    private void setFightFieldValue(int row, int col, Matrix3d fightField) {
        //convert user input in digital system starts with 0
        row--;
        col--;
        //reverse original coordination to the down to up
        row = Matrix3d.getDIMENSION() - 1 - row;
        fightField.set(row, col, player);
    }

    private void printFightField(Matrix3d fightField) {
        String topBoarderSign = "-";
        String splitter = String.join("", Collections.nCopies(Matrix3d.getDIMENSION() * Matrix3d.getDIMENSION(), topBoarderSign));
        System.out.println(splitter);
        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            System.out.println(String.format("| %s |", String.join(" ", fightField.getRow(i))));
        }
        System.out.println(splitter);
    }


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

    private static boolean drawCheck(Matrix3d m) {
        return Arrays.stream(m.getValues()).noneMatch(s -> s.equals(" "));
    }

}
