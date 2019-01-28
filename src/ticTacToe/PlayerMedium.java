package ticTacToe;

public class PlayerMedium implements Player {

    private char[][] fieldValues;
    private char figure;
    private int x;
    private int y;

    public PlayerMedium(char figure) {
        this.figure = figure;
    }

    @Override
    public char[][] move(char[][] fieldValues, String message) {
        this.fieldValues = fieldValues;

        // check to win
        if (checkState(getFigure())) {
            System.out.println(message);
            this.fieldValues[x][y] = getFigure();
            return this.fieldValues;
        }

        char opponentFigure = getFigure() == 'X' ? 'O' : 'X';

        // check to not lose
        if (checkState(opponentFigure)) {
            System.out.println(message);
            this.fieldValues[x][y] = getFigure();
            return this.fieldValues;
        }

        this.fieldValues = new PlayerEasy(getFigure()).move(fieldValues, message);

        return this.fieldValues;
    }

    private char getFigure() {
        return figure;
    }

    private boolean checkState(char figure) {
        if (checkRow(figure)) return true;
        if (checkCol(figure)) return true;
        if (checkDiagonal(figure)) return true;
        return  false;
    }

    private boolean checkDiagonal(char figure) {
        //diagonal check
        if ((fieldValues[0][0] == figure && fieldValues[2][2]  == figure)
                || (fieldValues[0][2] == figure && fieldValues[0][2] == figure)
                && fieldValues[1][1] == ' ') {
            x = 1;
            y = 1;
            return true;
        }

        if (fieldValues[0][0] == figure && figure == fieldValues[1][1] && fieldValues[2][2] == ' ') {
            x = 2;
            y = 2;
            return true;
        }

        if (fieldValues[1][1] == figure && figure == fieldValues[2][2] && fieldValues[0][0] == ' ') {
            x = 0;
            y = 0;
            return true;
        }

        if (fieldValues[0][2] == figure && figure == fieldValues[1][1] && fieldValues[2][0] == ' ') {
            x = 2;
            y = 0;
            return true;
        }

        if (fieldValues[2][0] == figure && figure == fieldValues[1][1] && fieldValues[0][2] == ' ') {
            x = 0;
            y = 2;
            return true;
        }
        return false;
    }

    private boolean checkCol(char figure) {
        for (int i = 0; i < 3; i++) {
            //col check
            if (fieldValues[0][i] == figure && figure == fieldValues[1][i] && fieldValues[2][i] == ' ') {
                x = 2;
                y = i;
                return true;
            }

            if (fieldValues[0][i] == figure && figure == fieldValues[2][i] && fieldValues[1][i] == ' ') {
                x = 1;
                y = i;
                return true;
            }

            if (fieldValues[1][i] == figure && figure == fieldValues[2][i] && fieldValues[0][i] == ' ') {
                x = 0;
                y = i;
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(char figure) {
        for (int i = 0; i < 3; i++) {
            if (fieldValues[i][0] == figure && figure == fieldValues[i][1] && fieldValues[i][2] == ' ') {
                x = i;
                y = 2;
                return true;
            }

            if (fieldValues[i][0] == figure && figure == fieldValues[i][2] && fieldValues[i][1] == ' ') {
                x = i;
                y = 1;
                return true;
            }

            if (fieldValues[i][1] == figure && figure == fieldValues[i][2] && fieldValues[i][0] == ' ') {
                x = i;
                y = 0;
                return true;
            }
        }
        return false;
    }
}
