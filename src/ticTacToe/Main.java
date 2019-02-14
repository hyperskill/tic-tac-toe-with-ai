
import java.util.*;

class Main {
    private static char[][] field = new char[][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private final static Scanner sc = new Scanner(System.in);
    private final static Random ran = new Random();
    private static boolean isEnabled = true;
    private static difficulty firstPlayer;
    private static difficulty secondPlayer;


    private enum gameState {
        PlayerWon,
        AIWon,
        Draw,
        notFinished;
    }


    private enum difficulty {
        user,
        easy,
        medium,
        hard;
    }


    private static class occupiedCellException extends RuntimeException {}


    private static void enterStartMenu() {
        System.out.print("Input command: ");
        try {
            String[] commands = sc.nextLine().split("\\s+");
            if (commands[0].equals("start")) {
                firstPlayer = difficulty.valueOf(commands[1]);
                secondPlayer = difficulty.valueOf(commands[2]);
                startGame();
            } else if (commands[0].equals("exit")) {
                isEnabled = false;
            }  else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("Bad parameters!");
        }
    }


    private static void clearField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
    }


    private static void startGame() {
        printField();
        while (checkResult().equals(gameState.notFinished)) {
            if (checkResult().equals(gameState.notFinished)) {
                makeAIMove(firstPlayer, 'X');
                printField();
            }
            if (checkResult().equals(gameState.notFinished)) {
                makeAIMove(secondPlayer, 'O');
                printField();
            }
        }

        switch (checkResult()) {
            case PlayerWon:
                System.out.println("X wins");
                break;
            case AIWon:
                System.out.println("O wins");
                break;
            case Draw:
                System.out.println("Draw");
                break;
        }
    }


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


    private static gameState checkResult() {
        boolean haveEmptyCells = false;
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == 'X') {
                return gameState.PlayerWon;
            } else if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == 'O') {
                return gameState.AIWon;
            } else if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] == 'X') {
                return gameState.PlayerWon;
            } else if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] == 'O') {
                return gameState.AIWon;
            }

            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    haveEmptyCells = true;
                }
            }
        }

        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] == 'X') {
            return gameState.PlayerWon;
        } else if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] == 'O') {
            return gameState.AIWon;
        } else if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] == 'X') {
            return gameState.PlayerWon;
        } else if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] == 'O') {
            return gameState.AIWon;
        } else if (haveEmptyCells) {
            return gameState.notFinished;
        } else {
            return gameState.Draw;
        }
    }


    private static void putInCell(char symbol) {
        System.out.print("Enter the coordinates: ");
        String[] splitLine = sc.nextLine().split("\\s+");
        try {
            int i = Integer.parseInt(splitLine[0]);
            int j = Integer.parseInt(splitLine[1]);
            if (field[i-1][j-1] == ' ') {
                field[i-1][j-1] = symbol;
            } else {
                throw new occupiedCellException();
            }
        } catch (occupiedCellException e) {
            System.out.println("This cell is occupied! Choose another one!");
            putInCell(symbol);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter two numbers! Coordinates should be from 1 to 3!");
            putInCell(symbol);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            putInCell(symbol);
        }
    }

    private static void makeAIMove(difficulty dif, char symbol) {
        int cell;
        switch (dif) {
            case user:
                putInCell(symbol);
                break;
            case easy:
                System.out.println("Making move level \"easy\"");
                cell = easyLevelMove(symbol);
                field[cell / 10][cell % 10] = symbol;
                break;
            case medium:
                System.out.println("Making move level \"medium\"");
                cell = mediumLevelMove(symbol);
                field[cell / 10][cell % 10] = symbol;
                break;
        }
    }


    private static int easyLevelMove(char symbol) {
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    emptyCells.add(i*10+j);
                }
            }
        }
        return emptyCells.get(ran.nextInt(emptyCells.size()));
    }


    private static int findImmediatelyWin(char symbol) {

        int counterOfSymbol;
        int counterOfEmptyCells;
        int emptyCell = -1;
        int cell = -1;

        for (int i = 0; i < 3; i++) {
            counterOfSymbol = 0;
            counterOfEmptyCells = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == symbol) {
                    counterOfSymbol++;
                } else if (field[i][j] == ' ') {
                    counterOfEmptyCells++;
                    emptyCell = 10*i + j;
                }
            }
            if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
                return emptyCell;
            }
        }

        for (int i = 0; i < 3; i++) {
            counterOfSymbol = 0;
            counterOfEmptyCells = 0;
            for (int j = 0; j < 3; j++) {
                if (field[j][i] == symbol) {
                    counterOfSymbol++;
                } else if (field[j][i] == ' ') {
                    counterOfEmptyCells++;
                    emptyCell = 10*j + i;
                }
            }
            if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
                return emptyCell;
            }
        }

        counterOfSymbol = 0;
        counterOfEmptyCells = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] == symbol) {
                counterOfSymbol++;
            } else if (field[i][i] == ' ') {
                counterOfEmptyCells++;
                emptyCell= 11 * i;
            }
        }
        if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
            return emptyCell;
        }

        counterOfSymbol = 0;
        counterOfEmptyCells = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][2-i] == symbol) {
                counterOfSymbol++;
            } else if (field[i][2-i] == ' ') {
                counterOfEmptyCells++;
                emptyCell= 9*i + 2;
            }
        }

        if (counterOfSymbol == 2 && counterOfEmptyCells == 1) {
            return emptyCell;
        }
        return cell;
    }


    private static int mediumLevelMove(char symbol) {
        char enemySymbol;
        int cell;
        int intendedCell;
        if (symbol == 'O') {
            enemySymbol = 'X';
        } else {
            enemySymbol = 'O';
        }

        cell = easyLevelMove(symbol);
        System.out.print("random: " + cell);
        intendedCell = findImmediatelyWin(enemySymbol);
        System.out.print("; enemy way: " + intendedCell);
        if (intendedCell != -1) {
            cell = intendedCell;
        }
        intendedCell = findImmediatelyWin(symbol);
        System.out.println("; winning way: " + intendedCell);
        if (intendedCell != -1) {
            cell = intendedCell;
        }
        return cell;
    }


    public static void main(String[] args) {
        while (isEnabled) {
            enterStartMenu();
            clearField();
        }
    }

}
