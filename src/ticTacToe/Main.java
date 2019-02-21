package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[] ticTac;
        ticTac = new char[]{'0', ' ', '0', 'X', 'X', ' ', '0', 'X', '0'};
        for(int i = 0; i < ticTac.length; i+=3) {
            System.out.println(ticTac[i] + " " + ticTac[i+1] + " " + ticTac[i+2]);
        }
    }
}

