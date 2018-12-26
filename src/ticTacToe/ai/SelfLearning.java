package ticTacToe.ai;

import ticTacToe.game.*;


import javax.swing.*;
import java.util.ArrayList;

import static ticTacToe.ui.UserInterface.game;

/**
 *  Class that implements learning process. It just simulate game with two random going computers and writes results
 */
public class SelfLearning extends Thread{
    /**
     *  list of fields, converted to long values which were made by computers
     */
    private ArrayList<Long> log = new ArrayList<>();

    /**
     * Instance of learning algorithm class used for fields load etc
     */
    private LearningAlgorithm learningAlgorithm = new LearningAlgorithm(game.getFieldSize());

    /**
     *  number of games which should be played in this learning process
     */
    private int iterations;

    /**
     *  Constructor sets field values to a default
     *  @param iterations number of games which should be played in this learning process
     */
    public SelfLearning(int iterations) {
        this.iterations = iterations;
    }

    /**
     *  it's a game process which starts in new thread for non-blocking application work
     */
    @Override
    public void run() {
        game.setLearningInProcess(true);
        learningAlgorithm.start();

        try {
            learningAlgorithm.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String message;
        int i = 0;
        for (; i < iterations; i++) {
            log.clear();
            selfLearningGame();
            System.out.println("Learning iteration " + i);
            if (!game.isLearningInProcess()) {
                break;
            }
        }
        learningAlgorithm.save();
        game.learningAlgorithm.run();
        message =  "Learning with " + i + " iterations completed. " +
                " Fields collection size now is " + learningAlgorithm.fieldsMap.keySet().size();
        System.out.println();
        JOptionPane.showMessageDialog(null, message);
        game.setLearningInProcess(false);
    }

    private void selfLearningGame() {
        int size = game.getFieldSize();
        int[][] field = new int[size][size];
        int activeFigure = Game.CROSS;

        for ( int i = 0; i < field.length; i++) {
            for ( int j = 0; j < field.length; j++) {
                field[i][j] = 2;
            }
        }

        while (true) {
            field = makeMove (field, activeFigure).clone();
            if (isGameFinished(field)) {
                return;
            }

            if (activeFigure == Game.CROSS) {
                activeFigure = Game.ZERO;
            } else {
                activeFigure = Game.CROSS;
            }
        }
    }

    /**
     *  Method makes a random move in given field with active figure and logging it
     * @param field
     * @param activeFigure
     * @return
     */
    private int[][] makeMove(int[][] field, int activeFigure) {
        Cell cell = new ComputerRival().easy(field);
        field[cell.s][cell.r] = activeFigure;
        log.add(new FieldCoder().getCode(field));
        return field;
    }

    /**
     * Method checks for given field is game finished
     * @param field
     * @return true if finished
     */
    private boolean isGameFinished(int[][] field) {
        GameResult gameResult = new GameResult();
        for (int i = 0; i < 2; i++) {
            if (gameResult.win(field, i)) {
                learningAlgorithm.updateFieldsMap(i, log);
                return true;
            }
        }

        if (gameResult.emptyCells(field).isEmpty()) {
            learningAlgorithm.updateFieldsMap(Game.EMPTY,log);
            return true;
        }

        return false;
    }
}
