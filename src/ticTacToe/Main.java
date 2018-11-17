package ticTacToe;

public class Main {

    public static void main(String[] args) {

        String [] fightDemo1 = {
                "X", "X", "X",
                "O", "O", " ",
                "O", " ", " "
        };

        Matrix3d fightDemo1Matrix = new Matrix3d(fightDemo1);
        System.out.println(fightDemo1Matrix);
        System.out.println(Game.getState(fightDemo1Matrix));

        String [] fightDemo2 = {
                "X", "O", "X",
                "O", "X", "O",
                "X", "X", "O"
        };

        Matrix3d fightDemo2Matrix = new Matrix3d(fightDemo2);
        System.out.println(fightDemo2Matrix);
        System.out.println(Game.getState(fightDemo2Matrix));

        String [] fightDemo3 = {
                "X", "O", "O",
                "O", "X", "O",
                "X", "X", "O",
        };

        Matrix3d fightDemo3Matrix = new Matrix3d(fightDemo3);
        System.out.println(fightDemo3Matrix);
        System.out.println(Game.getState(fightDemo3Matrix));

        String [] fightDemo4 = {
                "X", "O", "X",
                "O", "O", "X",
                "X", "X", "O"
        };

        Matrix3d fightDemo4Matrix = new Matrix3d(fightDemo4);
        System.out.println(fightDemo4Matrix);
        System.out.println(Game.getState(fightDemo4Matrix));

        String [] fightDemo5 = {
                "X", "O", " ",
                "O", "O", "X",
                "X", " ", " "
        };

        Matrix3d fightDemo5Matrix = new Matrix3d(fightDemo5);
        System.out.println(fightDemo5Matrix);
        System.out.println(Game.getState(fightDemo5Matrix));
    }


}