package ticTacToe;

public class Main {


    public static void main(String[] args) {
        Game.fillFieldRand();
        UserInterface userInterface = new UserInterface();
        userInterface.window();
    }
}
