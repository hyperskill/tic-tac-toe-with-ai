package ticTacToe.players;


import ticTacToe.enums.PlayerSign;
import ticTacToe.utils.Matrix3d;
import ticTacToe.exceptions.TicTakToeException;

import java.util.*;

public class EasyPlayer extends AbstractPlayer implements Player {

    public EasyPlayer(PlayerSign playerSign){
        super(playerSign);
    }

    @Override
    public void makeTurn(Matrix3d fightField) {

        List<Matrix3d.Coordinates> free = fightField.freeTile();

        Collections.shuffle(free);

        if (free.size() == 0 )
            throw new TicTakToeException("Failed to make turn: empty list of free cells");

        System.out.println("Making move level \"easy\"");

        fightField.set(free.get(0), playerSign);
    }


}
