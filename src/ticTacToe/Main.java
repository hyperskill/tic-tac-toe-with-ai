package ticTacToe;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            String value = i % 2 == 0 ? "X" : "O";
            System.out.printf("%s %s %s\n", value, value, value);
        }
    }
}