package ticTacToe;

import java.util.Random;

public class PlayerEasy implements Player {

    private char figure;

    public PlayerEasy(char figure) {
        this.figure = figure;
    }

    @Override
    public char[][] move(char[][] fieldValues, String message) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (fieldValues[x][y] == ' ') {
                System.out.println(message);
                fieldValues[x][y] = getFigure();
                break;
            }
        }

        return fieldValues;
    }

    @Override
    public char getFigure() {
        return figure;
    }
}
