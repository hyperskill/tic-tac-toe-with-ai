package ticTacToe;

import java.util.Scanner;

public class PlayerHuman implements Player {

    private char figure;
    Scanner scanner = new Scanner(System.in);

    public PlayerHuman(char figure) {
        this.figure = figure;
    }

    @Override
    public char[][] move(char[][] fieldValues, String message) {

        System.out.print(message);

        String input = scanner.next();

        while (!checkUserInput(input, fieldValues)) {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine();
        }

        int x = Character.getNumericValue(input.charAt(0)) - 1;
        int y = Character.getNumericValue(input.charAt(1)) - 1;

        fieldValues[x][y] = getFigure();

        return fieldValues;
    }

    private boolean checkUserInput(String input, char[][] fieldValues) {

        if (input.isEmpty() || input.equals("") || input == null) {
            System.out.println("You should enter numbers!");
            return false;
        }

        input = input.replaceAll(" ", "");
        System.out.println();

        if ("exit".equals(input)) {
            System.out.println("Game over");
            System.exit(0);
        }

        if (input.length() != 2) {
            System.out.println("Enter two numbers separated by space");
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("You should enter numbers!");
                return false;
            }
        }

        int x = Character.getNumericValue(input.charAt(0)) - 1;
        int y = Character.getNumericValue(input.charAt(1)) - 1;

        if ((x > 2 || y > 2) || (x < 0 || y < 0)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (fieldValues[x][y] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    private char getFigure() {
        return figure;
    }
}
