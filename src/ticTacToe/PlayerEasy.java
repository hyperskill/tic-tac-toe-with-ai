package ticTacToe;

import java.util.Random;

public class PlayerEasy implements Player {

    private char figure;

    public PlayerEasy(char figure) {
        this.figure = figure;
    }

    @Override
    public char[][] move(char[][] fieldValues) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (fieldValues[x][y] == ' ') {
                System.out.println("Making move level \"easy\"...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldValues[x][y] = getFigure();
                break;
            }
        }

        return fieldValues;
    }

    public char getFigure() {
        return figure;
    }
}
