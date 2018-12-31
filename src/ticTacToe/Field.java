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

    boolean SetValue(int i, int j, char pic, String mode) { //Метод добавляет значение на поле, локальная переменная pic - символ выбранный пользователем в качестве крестика или нолика
            if (xo_positions[i][j] != ' ') {
                if (mode.equals("human")) System.out.println("This cell is occupied! Choose another one!");
                return false;
            }
            xo_positions[i][j] = pic; //если тест прошел, то присваиваем значения
        if (!mode.equals("human")) {
            System.out.println("Making move level \"" + mode + "\"");
        }
        return true;
    }

    boolean IfWinner(char pic1, char pic2) { //Метод проверяет есть ли победитель с текущим состоянием игрового поля
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;


        for (int i = 0; i < width; i++) {
            if (xo_positions[i][i] == pic1) count_x++;
            if (xo_positions[i][i] == pic2) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, pic1, pic2)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = width - 1; i >= 0; i--) {
            if (xo_positions[i][width - 1 - i] == pic1) count_x++;
            if (xo_positions[i][i] == pic2) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        if (ChekingWinner(count_x, count_o, pic1, pic2)) return false;

        count_x = 0;
        count_o = 0;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (xo_positions[i][j] == pic1) count_x++;
                if (xo_positions[i][j] == pic2) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, pic1, pic2)) return false;
            count_x = 0;
            count_o = 0;

        }


        for (int j = 0; j < width; j++) {
            for (int i = 0; i < width; i++) {
                if (xo_positions[i][j] == pic1) count_x++;
                if (xo_positions[i][j] == pic2) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            if (ChekingWinner(count_x, count_o, pic1, pic2)) return false;
            count_x = 0;
            count_o = 0;

        }

        if (posibilities) {
            System.out.println(); //Ещё есть возможные ходы для победы, продолжаем игру
            return true;
        }

        System.out.println("Draw!"); //Ничья или ходов для победы нет
        return false;
    }

    private boolean ChekingWinner(int count_x, int count_o, char pic1, char pic2) {
        if (count_x == this.width) {
            System.out.println(pic1 + " wins!");
            return true;
        }
        if (count_o == this.width) {
            System.out.println(pic2 + " wins!");
            return true;
        }
        return false;
    }

}
