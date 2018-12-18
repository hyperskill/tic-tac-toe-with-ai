package ticTacToe.ai;

import ticTacToe.game.Game;
import ticTacToe.game.GameResult;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearningAlgorithm implements Serializable {
    private Map<Integer[][],Integer> map3x3Fields;
    private Map<Integer[][],Integer> map4x4Fields;
    private Map<Integer[][],Integer> map5x5Fields;
    private Map<Integer[][],Integer> map6x6Fields;

    private List<Integer[][]> movesInCurrentGame = new ArrayList<>(); //must be cleared at the end of game!!!

    public LearningAlgorithm() {
        File savedFields = new File("fields.tmp");
        if (!savedFields.isFile()) {
            map3x3Fields = new HashMap<>();
            map4x4Fields = new HashMap<>();
            map5x5Fields = new HashMap<>();
            map6x6Fields = new HashMap<>();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(savedFields);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            map3x3Fields = (Map<Integer[][], Integer>) objectInputStream.readObject();
            map4x4Fields = (Map<Integer[][], Integer>) objectInputStream.readObject();
            map5x5Fields = (Map<Integer[][], Integer>) objectInputStream.readObject();
            map6x6Fields = (Map<Integer[][], Integer>) objectInputStream.readObject();
            System.out.println("\nFields  are load from file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public GameResult.Cell makeMove(int[][] field){
        List<GameResult.Cell> emptyCells = new GameResult().emptyCells();
        int currentRate = Integer.MIN_VALUE;
        int selectedMoveIndex = 0;

        for (int i = 0; i < emptyCells.size(); i++) {
            GameResult.Cell cell = emptyCells.get(i);
            field[cell.s][cell.r] = Game.getActiveFigure();
            switch (Game.getFieldSize()) {
                case 3 : {
                    if (map3x3Fields.get(field) != null) {
                        cell.rate = map3x3Fields.get(field);
                    }
                    break;
                }
                case 4 : {
                    if (map4x4Fields.get(field) != null) {
                        cell.rate = map3x3Fields.get(field);
                    }
                    break;
                }
                case 5 : {
                    if (map5x5Fields.get(field) != null) {
                        cell.rate = map3x3Fields.get(field);
                    }
                    break;
                }
                case 6 : {
                    if (map6x6Fields.get(field) != null) {
                        cell.rate = map3x3Fields.get(field);
                    }
                    break;
                }
            }
            //add to current game array
            if (cell.rate > currentRate) {
                selectedMoveIndex = i;
            }
            field[cell.s][cell.r] = Game.EMPTY;
        }


        
        return emptyCells.get(selectedMoveIndex);
    }



}
