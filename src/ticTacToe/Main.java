package ticTacToe;

public class Main {
    public static void main(String[] args) {

        boolean continue_game = true;

        Field field = new Field(3);

        Player human = new Player('X');
        Player comp = new Player('O');
        String mode = "easy";
        field.DrawField();
        System.out.println();
        while (continue_game) {

            do {
                human.InputValue(field.width);
            } while (field.SetValueHuman(human.GetFirst(), human.GetSecond(), human.pic) == false);
            field.DrawField();
            continue_game = field.IfWinner(human.pic, comp.pic);

            if (continue_game == false) break;
            do {
                comp.RandomValue(field.width, mode);
            } while (field.SetValueComp(comp.GetFirst(), comp.GetSecond(), comp.pic, mode) == false);
            field.DrawField();
            continue_game = field.IfWinner(human.pic, comp.pic);

        }


    }
}
