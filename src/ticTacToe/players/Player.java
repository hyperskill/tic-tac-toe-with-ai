package ticTacToe.players;

import ticTacToe.utils.Matrix3d;

public interface Player {
     void makeTurn(Matrix3d fightField);

     String getPlayerSign() ;
}
