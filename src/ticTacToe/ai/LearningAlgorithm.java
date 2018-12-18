package ticTacToe.ai;

import ticTacToe.game.Game;
import ticTacToe.game.GameResult;

import java.io.*;
import java.util.*;

public class LearningAlgorithm implements Serializable {
    private static final long serialVersionUID = 123L;

    private Map<Integer[][],Rate> fieldsMap;

    private ArrayList<Integer[][]> movesInCurrentGame = new ArrayList<>(); //must be cleared at the end of game!!!

    public LearningAlgorithm() {
        init();
    }

    public void init() {
        File savedFields;
        switch (Game.getFieldSize()) {
            case 3 : {
                savedFields = new File("fields3x3.tmp");
                break;
            }
            case 4 : {
                savedFields = new File("fields4x4.tmp");
                break;
            }
            case 5 : {
                savedFields = new File("fields5x5.tmp");
                break;
            }
            case 6 : {
                savedFields = new File("fields6x6.tmp");
                break;
            }
            default : savedFields = new File("fields3x3.tmp");
        }

        if (!savedFields.isFile()) {
            fieldsMap = new HashMap<>();
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(savedFields);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fieldsMap = (Map<Integer[][], Rate>) objectInputStream.readObject();
            System.out.println("\nFields  are load from file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public GameResult.Cell makeMove(int[][] gameField){
        int fieldSize = Game.getFieldSize();
        Integer[][] field = new Integer[fieldSize][fieldSize];
        List<GameResult.Cell> emptyCells = new GameResult().emptyCells();
        int currentRate = Integer.MIN_VALUE;
        int selectedMoveIndex = 0;
        int activeFigure = Game.getActiveFigure();
        GameResult.Cell cell;


        for (int s = 0; s < fieldSize; s++) {
            for (int r = 0; r < fieldSize; r++) {
                field[s][r] = gameField[s][r];
            }
        }

        for (int i = 0; i < emptyCells.size(); i++) {
            cell = emptyCells.get(i);
            field[cell.s][cell.r] = Game.getActiveFigure();
            if (fieldsMap != null) {
                if (fieldsMap.containsKey(field)) {
                    cell.rate = fieldsMap.get(field).getRate(activeFigure);
                }
            }

            if (cell.rate > currentRate) {
                selectedMoveIndex = i;
            }
            field[cell.s][cell.r] = Game.EMPTY;
        }

        movesInCurrentGame.add(field);
        cell = emptyCells.get(selectedMoveIndex);
        field[cell.s][cell.r] = Game.getActiveFigure();
        movesInCurrentGame.add(field);
        return cell;
    }

    public void writeResults(int result) {
        Rate rate = new Rate();
        Set<Integer[][]> movesSet= new HashSet<>();

        if (result == Game.CROSS) {
            rate.rateX = 1;
            rate.rate0 = -1;
        } else if (result == Game.ZERO){
            rate.rateX = -1;
            rate.rate0 = 1;
        }

        makeMove(Game.getFieldValues());//add last value to collection if winner not learning machine
        movesSet.addAll(movesInCurrentGame);
        movesInCurrentGame.clear();
        movesInCurrentGame.addAll(movesSet);

        for (int i = 0; i < movesSet.size(); i++) {
            Integer[][] move = movesInCurrentGame.get(i);
            Rate prevRate = fieldsMap.putIfAbsent(move, rate);
            if (prevRate != null) {
                prevRate.updateRates(rate);
                fieldsMap.put(move, prevRate);
            }
        }

        movesInCurrentGame.clear();
        save();
    }

    private void save() {
        File savedFields;
        switch (Game.getFieldSize()) {
            case 3 : {
                savedFields = new File("fields3x3.tmp");
                break;
            }
            case 4 : {
                savedFields = new File("fields4x4.tmp");
                break;
            }
            case 5 : {
                savedFields = new File("fields5x5.tmp");
                break;
            }
            case 6 : {
                savedFields = new File("fields6x6.tmp");
                break;
            }
            default : savedFields = new File("fields3x3.tmp");
        }

        if (!savedFields.isFile()) {
            try {
                savedFields.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("\nFile creation is impossible!");
                return;
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(savedFields);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(fieldsMap);
            objectOutputStream.close();
            System.out.println("Neurons saved to file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Rate implements Serializable{
        private int rateX;
        private int rate0;

        private Rate(int rateX, int rate0) {
            this.rateX = rateX;
            this.rate0 = rate0;
        }

        private Rate() {
            this.rateX = 0;
            this.rate0 = 0;
        }


        public int getRate(int activeFigure ) {
            if (activeFigure == Game.CROSS) {
                return rateX;
            } else {
                return rate0;
            }

        }

        public void updateRates(Rate newRate) {
            this.rateX += newRate.rateX;
            this.rate0 += newRate.rate0;
        }

        public void setRate(int rate, int activeFigure) {
            if (activeFigure == Game.CROSS) {
                this.rateX = rate;
            } else {
                this.rate0 = rate;
            }
        }
    }



}
