package ticTacToe;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    char[][] matrix = new char[3][3];
    Random random = new Random();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        int y = random.nextInt(3);
        if (y == 0) {
        matrix[j][i] = 'O';
        } else if (y == 1) {
          matrix[j][i] = 'X';
        } else {
          matrix[j][i] = ' ';
        }
      }
    }
    for (char[] ch : matrix) {
      for (char c : ch) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }
}
