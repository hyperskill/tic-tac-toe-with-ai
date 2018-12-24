package ticTacToe.ai;

import ticTacToe.game.*;

import java.io.*;
import java.util.*;

import static ticTacToe.game.Game.EMPTY;
import static ticTacToe.ui.UserInterface.game;

public class LearningAlgorithm implements Serializable {
    private static final long serialVersionUID = 123L;

    private Map<Long, Rate> fieldsMap;

    public LearningAlgorithm() {
        init();
    }

    public void init() {
        File savedFields;
        switch (game.getFieldSize()) {
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
            fieldsMap = (Map<Long, Rate>) objectInputStream.readObject();
            System.out.println("\nFields  are load from file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Cell makeMove(int[][] field){
        List<Cell> emptyCells = new GameResult().emptyCells(field);
        int currentRate = Integer.MIN_VALUE;
        int selectedMoveIndex = 0;
        Cell cell;
        int activeFigure = game.getActiveFigure();

        for (int i = 0; i < emptyCells.size(); i++) {
            long code;
            cell = emptyCells.get(i);
            field[cell.s][cell.r] = game.getActiveFigure();
            code = new FieldCoder().getCode(field);

            if (fieldsMap.containsKey(code)) {
                cell.rate = fieldsMap.get(code).getRate(activeFigure);
            }

            if (cell.rate > currentRate) {
                selectedMoveIndex = i;
                currentRate = cell.rate;
            }

            field[cell.s][cell.r] = EMPTY;
        }


        cell = emptyCells.get(selectedMoveIndex);
        field[cell.s][cell.r] = game.getActiveFigure();
        return cell;
    }

    public void writeResults(int result) {
        int rateX = 0;
        int rate0 = 0;

        List<Long> movesLog = game.getLog().get();

        if (result == Game.CROSS) {
            rateX = 1;
            rate0 = -1;
        } else if (result == Game.ZERO) {
            rateX = -1;
            rate0 = 1;
        } else if (result == EMPTY) {
            return;
        }

        for (long move : movesLog) {
            Rate prevRate = fieldsMap.putIfAbsent(move, new Rate(rateX,rate0));
            if (prevRate != null) {
                prevRate.updateRates(new Rate(rateX,rate0));
                fieldsMap.put(move, prevRate);
            }
        }

        save();
    }

    private void save() {
        File savedFields;
        switch (game.getFieldSize()) {
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
            System.out.println("Moves saved to file, size" + fieldsMap.keySet().size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void selfLearning(int iterations) {

        for (int i =0; i < iterations; i++) {
            game.getLog().clear();
            selfLearningGame();
        }
        System.out.println("Learning with " + iterations + " completed. Fields collection now is " + fieldsMap.keySet().size());
    }

    private void selfLearningGame() {
        GameResult gameResult= new GameResult();
        int[][] field = game.getFieldValues();
        Cell cell;
        int avtiveFigure;

        for ( int i = 0; i < field.length; i++) {
            for ( int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY;
            }
        }

        while (true) {

           // cell = makeMove(field);
            cell = ComputerRival.easy(field);
            avtiveFigure = Game.CROSS;
            field[cell.s][cell.r] = avtiveFigure;
            game.getLog().addMove(new FieldCoder().getCode(field));
            if (gameResult.win(field,0)) {
                writeResults(0);
                return;
            } else if (gameResult.win(field,1)) {
                writeResults(1);
                return;
            } else if (gameResult.emptyCells(field).isEmpty()) {
                return;
            }

            avtiveFigure = Game.ZERO;
            cell = ComputerRival.easy(field);
            field[cell.s][cell.r] = avtiveFigure;
            game.getLog().addMove(new FieldCoder().getCode(field));
            if (gameResult.win(field,0)) {
                writeResults(0);
                return;
            } else if (gameResult.win(field,1)) {
                writeResults(1);
                return;
            } else if (gameResult.emptyCells(field).isEmpty()) {
                return;
            }
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

    }
}
