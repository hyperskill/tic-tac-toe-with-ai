package ticTacToe;

public class Main {


    public static void main(String[] args) {
        for (int i =0; i < 20; i++ ) {
            Game.fillFieldRand();
            Game.printArray();
            Game.checkGameResult();
        }
        UserInterface userInterface = new UserInterface();
        userInterface.window();
    }
}
