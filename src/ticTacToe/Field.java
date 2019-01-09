package ticTacToe;
class Field {

    static char pic1;
    static char pic2;

    /*   static char[][] xo_positions = {
              {'x',' ','o'},
               {'x',' ',' '},
               {'o',' ',' '}
        };
   */
    static char[][] xo_positions;
    static char[][] xo_positions_simulator;
    static int width;

    static void GenerateEmptyField(int width) { //Конструктор создает пустое игровое поле (Constructor creates an empty gaming field)
        Field.width = width;
        xo_positions = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                xo_positions[i][j] = ' ';
            }
        }

    }

    static void DrawField() { //Метод отрисовывает игровое поле с имеющимися значениями
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

    static boolean SetValue(int i, int j, char pic, String mode, boolean test) { //Метод добавляет значение на поле, локальная переменная pic - символ выбранный пользователем в качестве крестика или нолика
            if (xo_positions[i][j] != ' ') {
                if (mode.equals("human")) System.out.println("This cell is occupied! Choose another one!");
                return false;
            }
            xo_positions[i][j] = pic; //если тест прошел, то присваиваем значения
        if (!mode.equals("human") && !test) {
            System.out.println("Making move level \"" + mode + "\"");
        }
        return true;
    }

    static int IfWinner(boolean test) { //Метод проверяет есть ли победитель с текущим состоянием игрового поля
        int count_x = 0;
        int count_o = 0;
        boolean posibilities = false;
        char[][] xo_positions;
        int what_to_return;

        if (test) {
            xo_positions = xo_positions_simulator.clone();
        } else {
            xo_positions = Field.xo_positions.clone();
        }


        for (int i = 0; i < width; i++) {
            if (xo_positions[i][i] == Field.pic1) count_x++;
            if (xo_positions[i][i] == Field.pic2) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        what_to_return = ChekingWinner(count_x, count_o, test);

        if (what_to_return != 0) {
            return what_to_return;
        }

        count_x = 0;
        count_o = 0;


        for (int i = width - 1; i >= 0; i--) {
            if (xo_positions[i][width - 1 - i] == Field.pic1) count_x++;
            if (xo_positions[i][width - 1 - i] == Field.pic2) count_o++;
        }
        if (!(count_x > 0 && count_o > 0)) posibilities = true;
        what_to_return = ChekingWinner(count_x, count_o, test);
        if (what_to_return != 0) {
            return what_to_return;
        }

        count_x = 0;
        count_o = 0;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (xo_positions[i][j] == pic1) count_x++;
                if (xo_positions[i][j] == pic2) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            what_to_return = ChekingWinner(count_x, count_o, test);

            if (what_to_return != 0) {
                return what_to_return;
            }
            count_x = 0;
            count_o = 0;

        }

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < width; i++) {
                if (xo_positions[i][j] == pic1) count_x++;
                if (xo_positions[i][j] == pic2) count_o++;
            }
            if (!(count_x > 0 && count_o > 0)) posibilities = true;
            what_to_return = ChekingWinner(count_x, count_o, test);

            if (what_to_return != 0) {
                return what_to_return;
            }
            count_x = 0;
            count_o = 0;

        }

        if (posibilities) {
            if (!test) System.out.println(); //Ещё есть возможные ходы для победы, продолжаем игру
            return 0;
        }

        if (!test) System.out.println("Draw!"); //Ничья или ходов для победы нет
        return -5;
    }

    static private int ChekingWinner(int count_x, int count_o, boolean test) {
        if (count_x == width) {
            if (!test) System.out.println(Field.pic1 + " wins!");
            return -10;
        }
        if (count_o == width) {
            if (!test) System.out.println(Field.pic2 + " wins!");
            return 10;
        }
        return 0;
    }

}
