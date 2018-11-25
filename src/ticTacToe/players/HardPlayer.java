package ticTacToe.players;


import ticTacToe.enums.PlayerSign;
import ticTacToe.utils.Matrix3d;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.pow;

public class HardPlayer extends AbstractPlayer implements Player {

    private Matrix3d fieldCopy;
    private Matrix3d miniPlayer = new Matrix3d();
    Matrix3d.Coordinates bestMovie = null;

    public HardPlayer(PlayerSign playerSign) {
        super(playerSign);
    }

    @Override
    public void makeTurn(Matrix3d fightField) {
        fieldCopy = new Matrix3d(fightField);
        System.out.println("Making move level \"hard\"");

        minmax(2, playerSign);

        fightField.set(bestMovie, playerSign.toString());
    }

    private int minmax(int depth, PlayerSign predictionPlayer) {

        List<Matrix3d.Coordinates> avalibleMoves = fieldCopy.freeCoords();
        int bestScore = (predictionPlayer == playerSign) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int currentScore = 0;

        if (avalibleMoves.size() == 0 || depth == 0) {
            bestScore = evaluate();
        } else {
            miniPlayer= new Matrix3d();
            for (Matrix3d.Coordinates freeMove : avalibleMoves) {
                fieldCopy.set(freeMove, predictionPlayer.toString());
                if (predictionPlayer == playerSign) {
                    currentScore = minmax(depth - 1, playerSign.opposite());
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMovie = freeMove;
                    }
                }
                else{
                    currentScore = minmax(depth - 1, playerSign);
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                    }
                }
                miniPlayer.set(freeMove,String.valueOf(currentScore));
                fieldCopy.set(freeMove, " ");
            }
//            printFightField(miniPlayer);
        }

        return bestScore;
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

    private int evaluate() {
        int score = 0;

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            score += evaluateline(fieldCopy.getRow(i));
            score += evaluateline(fieldCopy.getColon(i));
        }

        score += evaluateline(fieldCopy.getDiagonal(Matrix3d.DiagonalType.POSITIVE));
        score += evaluateline(fieldCopy.getDiagonal(Matrix3d.DiagonalType.NEGATIVE));

        return score;
    }

    private int evaluateline(String[] row) {
        return getRate(row, playerSign.toString()) - getRate(row, playerSign.opposite().toString());
    }

    private int getRate(String[] line, String sig) {
        long matches = Arrays.stream(line).filter(s -> s.equals(sig)).count();
        if (matches == 0)
            return 0;
        else
            return (int) pow(10, matches - 1);
    }


}
