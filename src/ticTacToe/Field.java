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

    void SetValue(int i, int j, char x_or_o) {
        xo_positions[i][j] = x_or_o;
    } //Метод добавляет значение на поле, локальная переменная x_or_o - символ выбранный пользователем в качестве крестика или нолика

}
