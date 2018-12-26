package ticTacToe;

import java.util.Arrays;
import java.util.Random;

public class Field {
  private String[][] matrix;
  private Random random;

  public Field() {
    this.matrix = new String[3][3];
    random = new Random();
  }

  public void draw() {
    String O = "O";
    String X = "X";
    String space = " ";
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        int y = random.nextInt(3);
        if (y == 0) {
          matrix[i][j] = O;
        } else if (y == 1) {
          matrix[i][j] = X;
        } else {
          matrix[i][j] = space;
        }
      }
    }
    for (String[] ch : matrix) {
      for (String c : ch) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }

  private boolean checkDiagonal(String symbol) {
    boolean toRight = true;
    boolean toLeft = true;
    for (int i = 0; i < 3; i++) {
      toRight &= matrix[i][i].equals(symbol);
      toLeft &= matrix[3 - i - 1][i].equals(symbol);
    }
    return toRight || toLeft;
  }

  private boolean checkLanes(String symbol) {
    boolean cols, raws;
    for (int column = 0; column < 3; column++) {
      cols = true;
      raws = true;
      for (int raw = 0; raw < 3; raw++) {
        cols &= matrix[column][raw].equals(symbol);
        raws &= matrix[raw][column].equals(symbol);
      }
      if (cols || raws)
        return true;
    }
    return false;
  }

  public boolean isMatrixFull() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (matrix[i][j].equals(" "))
          return false;
      }
    }
    return true;
  }

  public void printResult() {
    if (checkDiagonal("O") || checkLanes("O")) {
      System.out.println("O wins");
    } else if (checkDiagonal("X") || checkLanes("X")) {
      System.out.println("X wins");
    } else if (checkLanes("O") && checkLanes("X")) {
      System.out.println("Draw");
    } else if (isMatrixFull()) {
      System.out.println("Draw");
    } else {
      System.out.println("Game not finished");
    }
  }
}
