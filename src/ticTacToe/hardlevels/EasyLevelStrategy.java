package ticTacToe.hardlevels;

import ticTacToe.Main;
import ticTacToe.Player;

import java.util.Random;

public class EasyLevelStrategy extends LevelStrategy {
    @Override
    public boolean move(char[][] matrix, Player player) {
        return randomMove(matrix, player, "easy");
    }
    protected boolean randomMove(char[][] matrix, Player player, String type){
        int x, y;
        int[][] emptyCells = getEmptyCoords(matrix);
        Random rnd = new Random();
        if(emptyCells .length > 0){
            int[] coord = emptyCells[rnd.nextInt(emptyCells.length)];
            boolean res = player.set(matrix, coord[0], coord[1], player.getSymbol());
            if (res){
                System.out.println("Making move level \"" + type + "\"");
                return true;
            }
        }
        return false;
    }

    protected int[][] getEmptyCoords(char[][] matrix){
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
        //counter--;
        int[][] resArr = new int[counter][2];
        for (int i = 0; i < counter; i++) {
            resArr[i] = arr[i];
        }
        return resArr;
    }
}
