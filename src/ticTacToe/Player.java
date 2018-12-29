package ticTacToe;

import java.util.Scanner;

class Player {
    char pic;
    private int first;
    private int second;

    Player(char pic) {
        this.pic = pic;
    }

    void InputValue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Куда поставить " + pic + "? Ваши значения через пробел-> ");
        first = sc.nextInt();
        second = sc.nextInt();
    }

    private int getFirst() {
        return first;
    }

    private int getSecond() {
        return second;
    }


}
