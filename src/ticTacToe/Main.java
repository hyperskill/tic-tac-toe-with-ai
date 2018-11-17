package ticTacToe;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[][] s = new String[][]{
                  new String[]{"X", "O", "X"}
                , new String[]{"O", "X", "O"}
                , new String[]{"X", "X", "O"}};

        Arrays.stream(s).forEach(x-> {Arrays.stream(x).forEach(System.out::print);
            System.out.println();
        });
    }


}