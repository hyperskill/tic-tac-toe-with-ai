package ticTacToe;

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

    void SetValue(int i, int j, char x_or_o) { //Метод добавляет значение на поле, локальная переменная x_or_o - символ выбранный пользователем в качестве крестика или нолика
        xo_positions[width - i][j - 1] = x_or_o;
    }

    boolean IfWinner(char x_or_o) { //Метод проверяет есть ли победитель с текущим состоянием игрового поля
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;

        for (int i = 0; i < width; i++) {
            if (xo_positions[i][i] == x_or_o) count_x++;
            if (xo_positions[i][i] == x_or_o) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, x_or_o)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = width - 1; i >= 0; i--) {
            if (xo_positions[i][width - 1 - i] == x_or_o) count_x++;
            if (xo_positions[i][width - 1 - i] == x_or_o) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, x_or_o)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (xo_positions[i][j] == x_or_o) count_x++;
                if (xo_positions[i][j] == x_or_o) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, x_or_o)) return false;
            count_x = 0;
            count_o = 0;

        }


        for (int j = 0; j < width; j++) {
            for (int i = 0; i < width; i++) {
                if (xo_positions[i][j] == x_or_o) count_x++;
                if (xo_positions[i][j] == x_or_o) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, x_or_o)) return false;
            count_x = 0;
            count_o = 0;

        }

        if (posibilities) {
            System.out.println();
            return true;
        }

        System.out.println("Draw");
        return false;
    }

    private boolean ChekingWinner(int count_x, int count_o, char x_or_o) {
        if (count_x == width) {
            System.out.println(x_or_o + " wins!");
            return true;
        }
        if (count_o == width) {
            System.out.println(x_or_o + " wins!");
            return true;
        }
        return false;
    }

}
