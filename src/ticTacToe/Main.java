package ticTacToe;

public class Main {
    public static void main(String[] args) {


        Field field = new Field(7);
        field.DrawField();

        IfWinner(field.xo_positions, 7);
    }


    private static boolean IfWinner(char[][] field, int width) {
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;

        for (int i=0; i<width; i++) {
            if (field[i][i]=='X') count_x++;
            if (field[i][i]=='O') count_o++;
        }
        if (!(count_x>0 && count_o>0)) posibilities = true;
        if (ChekingWinner(count_x,count_o,width)) return false;

            count_x = 0;
            count_o = 0;


            for (int i = width - 1; i >= 0; i--) {
                    if (field[i][width-1-i] == 'X') count_x++;
                    if (field[i][width-1-i] == 'O') count_o++;
            }
        if (!(count_x>0 && count_o>0)) posibilities = true;
        if (ChekingWinner(count_x,count_o,width)) return false;

            count_x = 0;
            count_o = 0;


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

    private static boolean ChekingWinner(int count_x, int count_o, int width) {
        if (count_x==width) {System.out.println("X wins"); return true;}
        if (count_o==width) {System.out.println("O wins"); return true;}
        return false;
    }

}
