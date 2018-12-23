package ticTacToe;

public class Main {
    private static char X = 'X';
    private static char O = 'O';
    private static char EMPTY = ' ';
    private final static int ROW = 3;
    private final static int COL = ROW;
    private final static int SIZE = ROW * COL;

    public static void main(String[] args) {
        char[] matrix = new char[Main.SIZE];
        randomFill(matrix);
        printMatrix(matrix);
    }
    private static void printMatrix(char[] matrix){
        for (int i = 0; i < matrix.length; i++){
            System.out.print(matrix[i]);
                if(i > 0 && (i + 1) % Main.COL == 0){
                    System.out.println();
                } else{
                    System.out.print("\t");
                }
        }
    }
    private static void randomFill(char[] matrix){
        matrix[0] = Main.EMPTY;
        matrix[1] = Main.X;
        matrix[2] = Main.O;

        matrix[3] = Main.O;
        matrix[4] = Main.X;
        matrix[5] = Main.EMPTY;

        matrix[6] = Main.EMPTY;
        matrix[7] = Main.X;
        matrix[8] = Main.X;
    }
}