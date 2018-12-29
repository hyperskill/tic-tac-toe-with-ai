package ticTacToe;

import java.util.Scanner;

class Player {
    char pic;
    private int first;
    private int second;

    Player(char pic) {
    }

    void InputValue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Куда поставить " + pic + "?");
        first = sc.nextInt();
        second = sc.nextInt();
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }


}
