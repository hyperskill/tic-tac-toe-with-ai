package ticTacToe;

import java.util.ArrayList;
import java.util.List;

public class PlayerHard implements Player {

    private char figure;

    public PlayerHard(char figure) {
        this.figure = figure;
    }

    @Override
    public char[][] move(char[][] fieldValues, String message) {
        System.out.println(message);

        int index = 0;
        List<Character> board = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.add(index++, fieldValues[i][j]);
            }
        }

        Cell move = minimax(board, getFigure());
        board.set(move.index, getFigure());

        index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldValues[i][j] = board.get(index++);
            }
        }

        return fieldValues;
    }

    private char getFigure() {
        return this.figure;
    }

    private List<Integer> emptyIndexes(List<Character> board) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) == ' ') {
                temp.add(i);
            }
        }
        return temp;
    }

    private boolean winning(List<Character> board, char player) {
        if((board.get(0) == player && board.get(1) == player && board.get(2) == player) ||
                (board.get(3) == player && board.get(4) == player && board.get(5) == player) ||
                (board.get(6) == player && board.get(7) == player && board.get(8) == player) ||
                (board.get(0) == player && board.get(3) == player && board.get(6) == player) ||
                (board.get(1) == player && board.get(4) == player && board.get(7) == player) ||
                (board.get(2) == player && board.get(5) == player && board.get(8) == player) ||
                (board.get(0) == player && board.get(4) == player && board.get(8) == player) ||
                (board.get(2) == player && board.get(4) == player && board.get(6) == player)
                ) {
            return true;
        } else {
            return false;
        }
    }

    private Cell minimax(List<Character> newBoard, char player) {
        List<Integer> availSpots = emptyIndexes(newBoard);
        Cell cell = new Cell();
        char aiPlayer = getFigure();
        char huPlayer = aiPlayer == 'X' ? 'O' : 'X';

        // win opponent
        if (winning(newBoard, huPlayer)) {
            cell.score = -10;
            return cell;
        }

        // win ai
        if (winning(newBoard, aiPlayer)) {
            cell.score = 10;
            return cell;
        }

        // draw
        if (availSpots.size() == 0) {
            cell.score = 0;
            return cell;
        }

        List<Cell> moves = new ArrayList<>();

        for (int i = 0; i < availSpots.size(); i++) {
            Cell move = new Cell();
            move.index = availSpots.get(i);
            newBoard.set(move.index, player);

            if (player == aiPlayer) {
                move.score = minimax(newBoard, huPlayer).score;
            } else {
                move.score = minimax(newBoard, aiPlayer).score;
            }

            newBoard.set(move.index, ' ');
            moves.add(move);
        }

        int bestMove = 0;
        if (player == aiPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }
        return moves.get(bestMove);
    }

    private static class Cell {
        int score;
        int index;
    }

}
