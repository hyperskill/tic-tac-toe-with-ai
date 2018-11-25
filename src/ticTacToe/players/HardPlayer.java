package ticTacToe.players;


import ticTacToe.enums.PlayerSign;
import ticTacToe.utils.Matrix3d;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class HardPlayer extends AbstractPlayer implements Player {

    private Matrix3d fieldCopy;
    private Matrix3d.Coordinates bestMovie = null;

    HardPlayer(PlayerSign playerSign) {
        super(playerSign);
    }

    @Override
    public void makeTurn(Matrix3d fightField) {
        fieldCopy = new Matrix3d(fightField);
        System.out.println("Making move level \"hard\"");

        minMax(2, playerSign);

        fightField.set(bestMovie, playerSign.toString());
    }

    private int minMax(int depth, PlayerSign predictionPlayer) {

        List<Matrix3d.Coordinates> availableMoves = fieldCopy.freeTile();
        int bestScore = (predictionPlayer == playerSign) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int currentScore;

        if (availableMoves.size() == 0 || depth == 0) {
            bestScore = evaluate();
        } else {
            for (Matrix3d.Coordinates freeMove : availableMoves) {
                fieldCopy.set(freeMove, predictionPlayer.toString());
                if (predictionPlayer == playerSign) {
                    currentScore = minMax(depth - 1, playerSign.opposite());
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMovie = freeMove;
                    }
                }
                else{
                    currentScore = minMax(depth - 1, playerSign);
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                    }
                }
                fieldCopy.set(freeMove, " ");
            }
        }

        return bestScore;
    }

    private int evaluate() {
        int score = 0;

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            score += evaluateLine(fieldCopy.getRow(i));
            score += evaluateLine(fieldCopy.getColon(i));
        }

        score += evaluateLine(fieldCopy.getDiagonal(Matrix3d.DiagonalType.POSITIVE));
        score += evaluateLine(fieldCopy.getDiagonal(Matrix3d.DiagonalType.NEGATIVE));

        return score;
    }

    private int evaluateLine(String[] row) {
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
