package ticTacToe;

import java.util.Random;
import java.util.Scanner;


class Player {
    char pic;
    int first;
    int second;

    Player(char pic) {
        this.pic = pic;
    }

    void InputValue(int width) {
        boolean test = false;
        while (!test) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the coordinates: "); //Input test
            if (sc.hasNextInt()) {
                first = sc.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (sc.hasNextInt()) {
                second = sc.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (first < 1 || second < 1 || first > width || second > width) {
                System.out.println("Coordinates should be from 1 to " + width + "!");
            }
            first = width - first;
            second = second - 1;
            test = true;
        }
    }

    void RandomValue(int width, String mode) {
        Random random = new Random();
        first = random.nextInt(width);
        second = random.nextInt(width);
    }

    int GetFirst() {
        return first;
    }

    int GetSecond() {
        return second;
    }
}
