package done;

import java.util.*;
public class ticTacToe {

    /**
     * It is a game field, which contains information about values in cells.
     * Field has three rows and three columns and can store one character
     * in a cell.
     */
    private static char[][] field = new char[3][3];

    /**
     * Scanner can bring information from standard input.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Random will help us to generate moves for AI.
     */
    private static Random ran = new Random();

    /**
     * isEnabled == false   -->   Program will finish its work.
     */
    private static boolean isEnabled = true;

    /**
     * firstPlayer makes the first move and plays 'X'.
     * secondPlayer plays 'O'
     * Variables contains who will play: person or AI. If AI plays, it also has difficulty division.
     */
    private enum player {
        user,
        easy,
        medium,
        hard;
    }
    private static player firstPlayer;
    private static player secondPlayer;

    /**
     * 'state' changes with the flow of a game.
     * NotFinished - there are empty cells and nobody has triple in row.
     * XWins - firstPlayer('X') has triple in row.
     * Owins - secondPlayer('O') has triple in row.
     * Draw - there are no more empty cells and nobody has triple in row.
     */
    private enum gameState {
        NotFinished,
        XWins,
        OWins,
        Draw;
    }
    private static gameState state = gameState.NotFinished;

    /**
     * Some exceptions can be thrown if something will be wrong.
     * occupiedCellException - user entered a cell that already has a value.
     */
    private static class occupiedCellException extends RuntimeException {}

    /**
     * This method works with checkResult() method.
     * cell is (row * 3 + column). It can be any integer number from 0 to 8.
     */
    private static void gameProcess() {
        int cell = 0;
        while (checkResult(field).equals(gameState.NotFinished)) {
            printField();
            switch (firstPlayer) {
                case user:
                    cell = userMove('X');
                    break;
                case easy:
                    System.out.println("Making move level \"easy\"");
                    cell = easyMove('X');
                    break;
                case medium:
                    System.out.println("Making move level \"medium\"");
                    cell = mediumMove('X');
                    break;
                case hard:
                    cell = hardMove('X');
                    break;
            }
            field[cell / 3][cell % 3] = 'X';
            if (checkResult(field).equals(gameState.NotFinished)) {
                printField();
                switch (secondPlayer) {
                    case user:
                        cell = userMove('O');
                        break;
                    case easy:
                        System.out.println("Making move level \"easy\"");
                        cell = easyMove('O');
                        break;
                    case medium:
                        System.out.println("Making move level \"medium\"");
                        cell = mediumMove('O');
                        break;
                    case hard:
                        System.out.println("Making move level \"hard\"");
                        cell = hardMove('O');
                        break;
                }
                field[cell / 3][cell % 3] = 'O';
            }
        }
        printField();
        switch (checkResult(field)) {
            case XWins:
                System.out.println("X wins");
                break;
            case OWins:
                System.out.println("O wins");
                break;
            case Draw:
                System.out.println("Draw");
                break;
        }
    }


    /**
     * This method checks and returns the status of the game.
     */
    private static gameState checkResult(char[][] field) {
        if (field[0][0] == 'X' && field[0][1] == 'X' && field[0][2] == 'X' ||
            field[1][0] == 'X' && field[1][1] == 'X' && field[1][2] == 'X' ||
            field[2][0] == 'X' && field[2][1] == 'X' && field[2][2] == 'X' ||
            field[0][0] == 'X' && field[1][0] == 'X' && field[2][0] == 'X' ||
            field[0][1] == 'X' && field[1][1] == 'X' && field[2][1] == 'X' ||
            field[0][2] == 'X' && field[1][2] == 'X' && field[2][2] == 'X' ||
            field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X' ||
            field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
            return gameState.XWins;
        } else if (field[0][0] == 'O' && field[0][1] == 'O' && field[0][2] == 'O' ||
                field[1][0] == 'O' && field[1][1] == 'O' && field[1][2] == 'O' ||
                field[2][0] == 'O' && field[2][1] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][0] == 'O' && field[2][0] == 'O' ||
                field[0][1] == 'O' && field[1][1] == 'O' && field[2][1] == 'O' ||
                field[0][2] == 'O' && field[1][2] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O' ||
                field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {
            return gameState.OWins;
        } else if (field[0][0] == ' ' || field[0][1] == ' ' || field[0][2] == ' ' ||
                field[1][0] == ' ' || field[1][1] == ' ' || field[1][2] == ' ' ||
                field[2][0] == ' ' || field[2][1] == ' ' || field[2][2] == ' ') {
            return gameState.NotFinished;
        } else {
            return gameState.Draw;
        }
    }


    private static void clearField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
    }

    /**
     * This method prints tic-tac-toe field to the console.
     */
    private static void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    /**
     * This method allows user to input coordinates.
     */
    private static int userMove(char symbol) {
        System.out.print("Enter the coordinates: ");
        String[] splitLine = sc.nextLine().split("\\s+");
        try {
            int cell = Integer.parseInt(splitLine[0]) * 3 + Integer.parseInt(splitLine[1]) - 4;
            if (field[cell / 3][cell % 3] != ' ') {
                throw new occupiedCellException();
            }
            return cell;

        } catch (occupiedCellException e) {
            System.out.println("This cell is occupied! Choose another one!");
        } catch (RuntimeException e) {
            System.out.println("Please enter two integer numbers from 1 to 3!");
        }
        return userMove(symbol);
    }

    /**
     * This method generates pseudo-random cell to put a value.
     */
    private static int easyMove(char symbol) {
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    emptyCells.add(i * 3 + j);
                }
            }
        }

        return emptyCells.get(ran.nextInt(emptyCells.size()));
    }

    /**
     * This method returns opposite character.
     */
    private static char getEnemy(char symbol) {
        if (symbol == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    /**
     * This method finds a possibility to win in one move.
     */
    private static Integer findImmediateWin(char symbol) {
        int counterOfSymbol;
        int counterOfEmptyCells;
        int emptyCell = -1;

        // looking for rows
        for (int i = 0; i < 3; i++) {
            counterOfSymbol = 0;
            counterOfEmptyCells = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == symbol) {
                    counterOfSymbol++;
                } else if (field[i][j] == ' ') {
                    counterOfEmptyCells++;
                    emptyCell = 3*i + j;
                }
            }
            if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
                return emptyCell;
            }
        }

        // looking for columns
        for (int i = 0; i < 3; i++) {
            counterOfSymbol = 0;
            counterOfEmptyCells = 0;
            for (int j = 0; j < 3; j++) {
                if (field[j][i] == symbol) {
                    counterOfSymbol++;
                } else if (field[j][i] == ' ') {
                    counterOfEmptyCells++;
                    emptyCell = 3*j + i;
                }
            }
            if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
                return emptyCell;
            }
        }

        // looking for main diagonal
        counterOfSymbol = 0;
        counterOfEmptyCells = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] == symbol) {
                counterOfSymbol++;
            } else if (field[i][i] == ' ') {
                counterOfEmptyCells++;
                emptyCell= 4*i;
            }
        }
        if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
            return emptyCell;
        }

        // looking for side diagonal
        counterOfSymbol = 0;
        counterOfEmptyCells = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][2-i] == symbol) {
                counterOfSymbol++;
            } else if (field[i][2-i] == ' ') {
                counterOfEmptyCells++;
                emptyCell= 2*i + 2;
            }
        }
        if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
            return emptyCell;
        }

        // returning null if nothing was found
        return null;
    }

    /**
     * This method returns medium level difficulty turn.
     */
    private static int mediumMove(char symbol) {
        Integer intendedCell;
        int cell = easyMove(symbol);
        intendedCell = findImmediateWin(getEnemy(symbol));
        if (intendedCell != null) {
            cell = intendedCell;
        }
        intendedCell = findImmediateWin(symbol);
        if (intendedCell != null) {
            cell = intendedCell;
        }
        System.out.println(cell);
        return cell;
    }

    /**
     * This method checks tic-tac-toe field for the presence of values.
     */
    private static boolean isFieldEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getMax(ArrayList<Integer> arrayList) {
        Integer max = Integer.MIN_VALUE;
        for (Integer value: arrayList) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static int getMin(ArrayList<Integer> arrayList) {
        Integer min = Integer.MAX_VALUE;
        for (Integer value: arrayList) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }


    /**
     * My minimax algorithm
     */
    private static int minimax(char[][] array, char symbol, boolean isMyTurn) {
        ArrayList<Integer> instance = new ArrayList<>();
        switch (checkResult(array)) {
            case XWins:
                if (symbol == 'X') {
                    return 10;
                } else {
                    return -10;
                }
            case OWins:
                if (symbol == 'O') {
                    return 10;
                } else {
                    return -10;
                }
            case Draw:
                return 0;
            case NotFinished:
                if (isMyTurn) {
                    for (int i = 0; i < 9; i++) {
                        if (array[i / 3][i % 3] == ' ') {
                            array[i / 3][i % 3] = symbol;
                            instance.add(minimax(array, symbol, false));
                            array[i / 3][i % 3] = ' ';
                        }
                    }
                    return getMax(instance);
                } else {
                    for (int i = 0; i < 9; i++) {
                        if (array[i / 3][i % 3] == ' ') {
                            array[i / 3][i % 3] = getEnemy(symbol);
                            instance.add(minimax(array, symbol, true));
                            array[i / 3][i % 3] = ' ';
                        }
                    }
                    return getMin(instance);
                }
        }
        return 20;
    }


    private static int getRandomFromMax(Integer[] array) {
        Integer max = Integer.MIN_VALUE;
        for (Integer value: array) {
            if (value != null && value > max) {
                max = value;
            }
        }
        ArrayList<Integer> cellsWithMax = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(max)) {
                cellsWithMax.add(i);
            }
        }
        return cellsWithMax.get(ran.nextInt(cellsWithMax.size()));
    }


    private static int hardMove(char symbol) {
        if (isFieldEmpty()) {
            return 4;
        } else {
            Integer[] analysisField = new Integer[9];
            for (int i = 0; i < 9; i++) {
                if (field[i / 3][i % 3] == ' ') {
                    field[i / 3][i % 3] = symbol;
                    analysisField[i] = minimax(field, symbol, false);
                    field[i / 3][i % 3] = ' ';
                } else {
                    analysisField[i] = null;
                }
            }
            return getRandomFromMax(analysisField);
        }
    }

    public static void main(String[] args) {
        while (isEnabled) {
            System.out.print("Input command (input \"help\" to view the list of commands): ");
            String[] commands = sc.nextLine().split("\\s+");
            try {
                switch (commands[0]) {
                    case "help":
                        System.out.println();
                        System.out.println("start {player} {player}   -   starts the game.");
                        System.out.println("{player} can be: user, easy, medium, hard.");
                        System.out.println("exit   -   closes the program.");
                        break;
                    case "start":
                        firstPlayer = player.valueOf(commands[1]);
                        secondPlayer = player.valueOf(commands[2]);
                        clearField();
                        gameProcess();
                        break;
                    case "exit":
                        isEnabled = false;
                        break;
                    default:
                        System.out.println("\"" + commands[0] + "\" is an invalid command.");
                        break;
                }
            } catch(RuntimeException e) {
                System.out.println("Bad parameters");
            }
            System.out.println();
        }
    }


}
