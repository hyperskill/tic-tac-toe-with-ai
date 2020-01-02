package ticTacToe;

import java.util.Random;
import java.util.Scanner;


class Player {
    char pic;
    int first;
    int second;
    int max = 0;
    int result = 0;
    char aiPlayer;
    int hardfirst = 0;

    Player(char pic) {
        this.pic = pic;
    }

    void InputValue(int width, String mode, char pic1, char pic2) {
        if (mode.equals("human")) {
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
                hardfirst++;
                return;

            }
        }

        if (!mode.equals("human")) {
            Random random = new Random();
            first = random.nextInt(width);
            second = random.nextInt(width);
        }

        if (mode.equals("medium")) {
            int count_pic = 0;
            int count_space = 0;
            int space_first = 0, space_second = 0;
            hardfirst++;

            for (int i = 0; i < width; i++) {
                if (Field.xo_positions[i][i] == pic) count_pic++;
                if (Field.xo_positions[i][i] == ' ') {
                    count_space++;
                    space_first = i;
                    space_second = i;
                }
            }
            if (count_pic == width - 1 && count_space == 1) {
                first = space_first;
                second = space_second;
                return;
            }
            if (count_pic == 0 && count_space == 1) {
                first = space_first;
                second = space_second;
            }
            count_pic = 0;
            count_space = 0;

            for (int i = width - 1; i >= 0; i--) {
                if (Field.xo_positions[i][width - 1 - i] == pic) count_pic++;
                if (Field.xo_positions[i][width - 1 - i] == ' ') {
                    count_space++;
                    space_first = i;
                    space_second = width - 1 - i;
                }
            }
            if (count_pic == width - 1 && count_space == 1) {
                first = space_first;
                second = space_second;
                return;
            }
            if (count_pic == 0 && count_space == 1) {
                first = space_first;
                second = space_second;
            }
            count_pic = 0;
            count_space = 0;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (Field.xo_positions[i][j] == pic) count_pic++;
                    if (Field.xo_positions[i][j] == ' ') {
                        count_space++;
                        space_first = i;
                        space_second = j;
                    }
                }
                if (count_pic == width - 1 && count_space == 1) {
                    first = space_first;
                    second = space_second;
                    return;
                }
                if (count_pic == 0 && count_space == 1) {
                    first = space_first;
                    second = space_second;
                }
                count_pic = 0;
                count_space = 0;
            }

            for (int j = 0; j < width; j++) {
                for (int i = 0; i < width; i++) {
                    if (Field.xo_positions[i][j] == pic) count_pic++;
                    if (Field.xo_positions[i][j] == ' ') {
                        count_space++;
                        space_first = i;
                        space_second = j;
                    }
                }
                if (count_pic == width - 1 && count_space == 1) {
                    first = space_first;
                    second = space_second;
                    return;
                }
                if (count_pic == 0 && count_space == 1) {
                    first = space_first;
                    second = space_second;
                }
                count_pic = 0;
                count_space = 0;
            }

        }
        if (mode.equals("hard")) {
            if (this.hardfirst != 0) {
                TestMiniMaxStart(pic1, pic2);
            }
            hardfirst++;
        }

    }

    void TestMiniMaxStart(char pic1, char pic2) {
        aiPlayer = pic1;
        Field.xo_positions_simulator = Field.xo_positions.clone();
        TestMiniMax(pic1, pic2);
    }

    int TestMiniMax(char pic1, char pic2) {
        int k = 0;
        int[][] score = new int[3][Field.width * Field.width];
        for (int i = 0; i < Field.width; i++) {
            for (int j = 0; j < Field.width; j++) {

                if (Field.xo_positions_simulator[i][j] == ' ') {
                    Field.SetValue(i, j, pic1, "hard", true);
                    result = Field.IfWinner(true);
                    if ((aiPlayer == Field.pic2) && (result == 10 || result == -10)) {
                        result = -1 * result;
                    }
                    if (result == 0) {
                        result = TestMiniMax(pic2, pic1);


                    }
                    Field.xo_positions_simulator[i][j] = ' ';

                    score[0][k] = i;
                    score[1][k] = j;
                    score[2][k] = result;


                    k++;
                    result = 0;

                }
            }
        }


        max = 0;
        for (int i = 0; i < k; i++) { //ищем высший рейтинг хода

            if (pic1 == aiPlayer) {

                if (score[2][i] <= score[2][max]) {
                    max = i;
                }

            } else {

                if (score[2][i] >= score[2][max]) {
                    max = i;
                }
            }
        }


        first = score[0][max];
        second = score[1][max];
        return score[2][max];
    }


    int GetFirst() {
        return first;
    }

    int GetSecond() {
        return second;
    }
}
