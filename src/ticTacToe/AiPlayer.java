package ticTacToe;

import ticTacToe.hardlevels.EasyLevelStrategy;
import ticTacToe.hardlevels.HardLevelStrategy;
import ticTacToe.hardlevels.LevelStrategy;
import ticTacToe.hardlevels.MediumLevelStrategy;

import java.util.Random;

public class AiPlayer extends Player {
    private String level;
    private LevelStrategy strategy;
    public AiPlayer(char symbol) {
        super(symbol);
    }

    public AiPlayer(char symbol, String level) {
        super(symbol);
        this.level = level;
        switch (level){
            case "easy":
                this.strategy = new EasyLevelStrategy();
                break;
            case "medium":
                this.strategy = new MediumLevelStrategy();
                break;
            case "hard":
                this.strategy = new HardLevelStrategy();
                break;
        }
    }

    @Override
    boolean request(char[][] matrix) {
       return  strategy.move(matrix, this);
    }


}
