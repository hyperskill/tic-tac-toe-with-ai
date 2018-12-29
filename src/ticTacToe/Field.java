package ticTacToe;

import java.util.Scanner;

class Field {

    char[][] xo_positions;
    int width;


    Field(int w) { //Конструктор создает пустое игровое поле (Constructor creates an empty gaming field)
        width = w;
        xo_positions = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                xo_positions[i][j] = ' ';
            }
        }
    }

    void DrawField() { //Метод отрисовывает игровое поле с имеющимися значениями
        /* Оформление */
        System.out.print("  -"); //полосочки
        for (int i = 1; i <= width; i++) {
            System.out.print("--");
        }
        System.out.println();
        /* Суть метода */
        for (int i = 0; i < width; i++) {
            System.out.print((width - i) + "| "); //нумерация (оформление)
            for (var j = 0; j < width; j++) {
                System.out.print(xo_positions[i][j] + " "); //рисуем значение в позиции
            }
            System.out.println("|");
        }
        /* Опять оформление */
        System.out.print("  -"); //полосочки
        for (int i = 1; i <= width; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 1; i <= width; i++) {
            System.out.print(i + " "); //нумерация
        }
        System.out.println();
    }

    void SetValue(char pic) { //Метод добавляет значение на поле, локальная переменная pic - символ выбранный пользователем в качестве крестика или нолика
        int first;
        int second;
        boolean test = false;
        while (test == false) { //начало проверки введенных значений
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите координаты для " + pic + ": ");
            if (sc.hasNextInt()) {
                first = sc.nextInt();
            } else {
                System.out.println("Ошибка! Нужно ввести два числа через пробел.");
                test = false;
                continue;
            }
            if (sc.hasNextInt()) {
                second = sc.nextInt();
            } else {
                System.out.println("Ошибка! Нужно ввести два числа через пробел.");
                test = false;
                continue;
            }
            if (first < 0 || second < 0 || first > width || second > width) {
                System.out.println("Ошибка! Ваши значения за пределами поля.");
                test = false;
                continue;
            }
            int i = width - first;
            int j = second - 1;
            if (xo_positions[i][j] != ' ') {
                System.out.println("Ошибка! Эта позиция уже занята.");
                test = false;
                continue;
            } //конец проверки введенных значений
            xo_positions[i][j] = pic; //если тест прошел, то присваиваем значения
            test = true;
        }
    }

    boolean IfWinner(char x_or_o) { //Метод проверяет есть ли победитель с текущим состоянием игрового поля
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;

        for (int i = 0; i < width; i++) {
            if (xo_positions[i][i] == x_or_o) count_x++;
            if (xo_positions[i][i] != x_or_o && xo_positions[i][i] != ' ') count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, x_or_o)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = width - 1; i >= 0; i--) {
            if (xo_positions[i][width - 1 - i] == x_or_o) count_x++;
            if (xo_positions[i][i] != x_or_o && xo_positions[i][i] != ' ') count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, x_or_o)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (xo_positions[i][j] == x_or_o) count_x++;
                if (xo_positions[i][i] != x_or_o && xo_positions[i][i] != ' ') count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, x_or_o)) return false;
            count_x = 0;
            count_o = 0;

        }


        for (int j = 0; j < width; j++) {
            for (int i = 0; i < width; i++) {
                if (xo_positions[i][j] == x_or_o) count_x++;
                if (xo_positions[i][i] != x_or_o && xo_positions[i][i] != ' ') count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, x_or_o)) return false;
            count_x = 0;
            count_o = 0;

        }

        if (posibilities) {
            System.out.println(); //Ещё есть возможные ходы для победы, продолжаем игру
            return true;
        }

        System.out.println("Draw"); //Ничья или ходов для победы нет
        return false;
    }

    private boolean ChekingWinner(int count_x, int count_o, char pic) {
        if (count_x == width) {
            System.out.println(pic + " wins!");
            return true;
        }
        if (count_o == width) {
            System.out.println(pic + " wins!");
            return true;
        }
        return false;
    }

}
