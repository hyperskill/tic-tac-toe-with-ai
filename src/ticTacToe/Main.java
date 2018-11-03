package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[] field = new char[] {'O', ' ', 'O', 'X', 'X', 'O', ' ', 'X', 'X'};
        System.out.println(new char[] {field[0], ' ', field[1], ' ', field[2]});
        System.out.println(new char[] {field[3], ' ', field[4], ' ', field[5]});
        System.out.println(new char[] {field[6], ' ', field[7], ' ', field[8]});
    }
}