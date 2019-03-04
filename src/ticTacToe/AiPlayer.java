package ticTacToe;

import java.util.Random;

public class AiPlayer extends Player {
    public AiPlayer(char symbol) {
        super(symbol);
    }

    @Override
    boolean request(char[][] matrix) {
        int x, y;
        int[][] emptyCells = getEmptyCoords(matrix);
        Random rnd = new Random();
        if(emptyCells .length > 0){
            int[] coord = emptyCells[rnd.nextInt(emptyCells.length)];
            boolean res = set(matrix, coord[0], coord[1], symbol);
            if (res){
                System.out.println("Making move level \"easy\"");
                return true;
            }
        }
        return false;
    }

    private static int[][] getEmptyCoords(char[][] matrix){
        int[][] arr = new int[Main.SIZE][2];
        int counter = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if(matrix[x][y] == Main.EMPTY){
                    arr[counter++] = new int[]{x,y};
                }
            }
        }
        //clear
        counter--;
        int[][] resArr = new int[counter][2];
        for (int i = 0; i < counter; i++) {
            resArr[i] = arr[i];
        }
        return resArr;
    }
}
