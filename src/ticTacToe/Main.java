package ticTacToe;

public class Main {
    public static void main(String[] args) {
        int width = 3;
        Field(width);
    }

    static void Field(int width) {
        char[][] field = new char[width][width];
        for (int i=0; i<width; i++) {
            for (var j = 0; j < width; j++) {
                field[i][j] = GenerateXorO();
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        IfWinner(field, width);
    }

    static char GenerateXorO () {
        char xoro;
        xoro = 'O';
        if ((int)(Math.random() * 3) + 0 == 1) xoro = 'X';
        if ((int)(Math.random() * 3) + 0 == 2) xoro = ' ';

        return xoro;
    }

    static boolean IfWinner(char[][] field, int width) {
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;

        /*
        Checking first diagonal
        X__
        _X_
        __X
         */
        for (int i=0; i<width; i++) {
            if (field[i][i]=='X') count_x++;
            if (field[i][i]=='O') count_o++;
        }
        if (!(count_x>0 && count_o>0)) posibilities = true;
        if (ChekingWinner(count_x,count_o,width)) return false;

            count_x = 0;
            count_o = 0;

            ;
        /*
        Checking second diagonal
        __X
        _X_
        X__
        */
            for (int i = width - 1; i >= 0; i--) {
                    if (field[i][width-1-i] == 'X') count_x++;
                    if (field[i][width-1-i] == 'O') count_o++;
            }
        if (!(count_x>0 && count_o>0)) posibilities = true;
        if (ChekingWinner(count_x,count_o,width)) return false;

            count_x = 0;
            count_o = 0;

        /*
        Checking columns
        X__
        X__
        X__
        */

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j]=='X') count_x++;
                if (field[i][j]=='O') count_o++;
                }
            if (!(count_x>0 && count_o>0)) posibilities = true;
            if (ChekingWinner(count_x,count_o,width)) return false;
            count_x = 0;
            count_o = 0;

            }




         /*
        Checking lines
        ___
        XXX
        ___
         */

            for (int j = 0; j < width; j++) {
                for (int i = 0; i < width; i++) {
                    if (field[i][j]=='X') count_x++;
                    if (field[i][j]=='O') count_o++;
                    }
                if (!(count_x>0 && count_o>0)) posibilities = true;
                if (ChekingWinner(count_x,count_o,width)) return false;
                count_x = 0;
                count_o = 0;

                }

        if (posibilities == true) {System.out.println("Game not over yet");return false;}

        System.out.println("Draw");
            return true;
    }

    static boolean ChekingWinner(int count_x, int count_o, int width){
        if (count_x==width) {System.out.println("X wins"); return true;}
        if (count_o==width) {System.out.println("O wins"); return true;}
        return false;
    }

}
