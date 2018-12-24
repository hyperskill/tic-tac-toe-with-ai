package ticTacToe;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    char[][] matrix = new char[3][3];
    Random random = new Random();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        int y = random.nextInt(2);
        if (y == 0) {
        matrix[j][i] = 'O';
        } else {
          matrix[j][i] = 'X';
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
