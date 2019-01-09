package ticTacToe;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        System.out.println("Hello! This is Tic-Tac-Toe game! \n" +
                "You can play vs other player or vs computer AI\n" +
                "or make two AI fight against each other.");
        System.out.println();

        while (InputCommand().equals("start")) {


            int continue_game = 0;

            int param1 = 0;
            char param2 = ' ';
            char param3 = ' ';
            String player1 = "";
            String player2 = "";
            boolean test = false;


            System.out.println("\nNow enter parameters separated by spaces:\n" +
                    "\tField width: 3 or 5;\n" +
                    "\tPlayer type: 'human' or computer AI level: 'easy/middle/hard';\n" +
                    "\tSymbol for player: any character, e.g. X or O or ♥ or ♡;\n" +
                    "\tEntering order: " +
                    "\t<field width> <1st player type> <its symbol> <2nd player type> <its symbol>\n\n");

            do {
                System.out.print("Enter parameters: ");
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    param1 = sc.nextInt();
                }
                if (sc.hasNext()) {
                    player1 = sc.next();
                }
                if (sc.hasNext()) {
                    param2 = sc.next().charAt(0);
                }
                if (sc.hasNext()) {
                    player2 = sc.next();
                }
                if (sc.hasNext()) {
                    param3 = sc.next().charAt(0);
                }

                if ((param1 == 3 || param1 == 5) &&
                        (player1.equals("easy") || player1.equals("medium") || player1.equals("hard") || player1.equals("human")) &&
                        (player2.equals("easy") || player2.equals("medium") || player2.equals("hard") || player2.equals("human")) &&
                        (param2 != ' ' && param2 != ' ')) {
                    test = true;
                } else {
                    System.out.println("Bad parameters!");
                }
            } while (test == false);



            Player turn1 = new Player(param2);
            Player turn2 = new Player(param3);
            Field.pic1 = turn1.pic;
            Field.pic2 = turn2.pic;
            Field.GenerateEmptyField(param1);
            Field.DrawField();
            System.out.println();

            while (continue_game == 0) {
            //1st Player turn start
            do {
                turn1.InputValue(Field.width, player1, turn1.pic, turn2.pic);
            } while (Field.SetValue(turn1.GetFirst(), turn1.GetSecond(), turn1.pic, player1, false) == false);
                Field.DrawField();
                continue_game = Field.IfWinner(false);
                if (continue_game != 0) break;
            //1st Player turn ends

            //********************

            //2nd Player turn start
            do {
                turn2.InputValue(Field.width, player2, turn2.pic, turn1.pic);
            } while (Field.SetValue(turn2.GetFirst(), turn2.GetSecond(), turn2.pic, player2, false) == false);
                Field.DrawField();
                continue_game = Field.IfWinner(false);
            //2nd Player turn end
        }
        }


    }

    static String InputCommand() {
        String param1 = "";
        do {
            System.out.print("Input command (<start/exit>):");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNext()) param1 = sc.next();
            if (!param1.equals("exit") && !param1.equals("start")) {
                System.out.println("Bad parameters!");
            }
        } while (!param1.equals("exit") && !param1.equals("start"));

        return param1;


    }
}
