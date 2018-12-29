package ticTacToe;

public class Main {
    public static void main(String[] args) {
        boolean continue_game = true;

        Field field = new Field(3);

        Player human = new Player('X');
        Player comp = new Player('O');
        field.DrawField();

        while (continue_game) {
            field.SetValue(human.pic);
            field.DrawField();
            continue_game = field.IfWinner(human.pic);
        }

    }
}
