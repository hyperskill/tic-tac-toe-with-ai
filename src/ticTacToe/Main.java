package ticTacToe;

public class Main {
    private static char SYMBOL_0 = 'X';
    private static char SYMBOL_1 = 'O';
    public static void main(String[] args) {
        printMatrix();
    }
    private static void printMatrix(){
        System.out.print(Main.SYMBOL_0);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_1);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_0);
        System.out.print("\t");

        System.out.println();

        System.out.print(Main.SYMBOL_0);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_0);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_1);
        System.out.print("\t");

        System.out.println();

        System.out.print(Main.SYMBOL_1);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_1);
        System.out.print("\t");
        System.out.print(Main.SYMBOL_0);
        System.out.print("\t");

    }
}