package ticTacToe.ai;

import ticTacToe.game.*;

import java.io.*;
import java.util.*;

import static ticTacToe.game.Game.EMPTY;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class for storing and load information about fields for better use. And making move
 *  using computer experience.
 */
public class LearningAlgorithm extends Thread implements Serializable {
    private static final long serialVersionUID = 123L;

    /**
     *  field size to work with
     */
    private int fieldSize;

    /**
     *  is a ready status that all fields are loaded from file (because on big field sizes it could takes a really big
     *  time to load all information)
     */
    private boolean loadedFromFile;

    /**
     *  it is a pearl of this class - collection of fields with its rates. Key is a field converted to long value like
     *  a threefold.
     */
    Map<Long, Rate> fieldsMap;

    /**
     *  Constructor sets field values to a default
     *  @param filedSize is size of field with class should work with
     */
    public LearningAlgorithm(int filedSize) {
        this.loadedFromFile = false;
        this.fieldSize = filedSize;
    }

    /**
     *  Run method load fields from file. For it game created a new thread, because it could takes a long time
     */
    @Override
    public void run() {
        File savedFields;
        loadedFromFile = false;
        switch (fieldSize) {
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
        loadedFromFile = true;
    }

    /**
     * Method checks fields map and returns coordinate of better move, it exists
     * @param field
     * @return coordinate of move
     */
    public Cell makeMove(int[][] field){
        List<Cell> emptyCells = new GameResult().emptyCells(field);
        int currentRate = Integer.MIN_VALUE;
        int selectedMoveIndex = 0;
        Cell cell;
        int activeFigure = game.getActiveFigure();

        for (int i = 0; i < emptyCells.size(); i++) {
            long code;
            cell = emptyCells.get(i);
            field[cell.s][cell.r] = activeFigure;
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

    /**
     * Method updates fields collection and rates of existing elements
     * @param result which figure wins- X or O
     * @param movesLog list of fields converted to long. it's moves that were made in this game
     */
    public void updateFieldsMap(int result, List<Long> movesLog) {
        int rateX = 0;
        int rate0 = 0;

        if (result == Game.CROSS) {
            rateX = 1;
            rate0 = -1;
        } else if (result == Game.ZERO) {
            rateX = -1;
            rate0 = 1;
        }

        for (long move : movesLog) {
            Rate prevRate = fieldsMap.putIfAbsent(move, new Rate(rateX,rate0));
            if (prevRate != null) {
                prevRate.updateRates(new Rate(rateX,rate0));
                fieldsMap.put(move, prevRate);
            }
        }
    }

    /**
     * Method saved fields map to file
     */
    public void save() {
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

    /**
     * getter for status of fields map
     * @return true if fields are loaded from file
     */
    public boolean isLoadedFromFile() {
        return loadedFromFile;
    }

    /**
     *  Class contains rates which are used in learning process and in move making
     *  It's bound to a key value in fields map and characterised move for X player and for O player
     */
    private class Rate implements Serializable{
        private int rateX;
        private int rate0;

        private Rate(int rateX, int rate0) {
            this.rateX = rateX;
            this.rate0 = rate0;
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
