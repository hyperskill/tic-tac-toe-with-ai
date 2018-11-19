package ticTacToe.players;


import ticTacToe.utils.Matrix3d;
import ticTacToe.exceptions.TicTakToeException;

import java.util.*;

public class EasyPlayer implements Player {

    @Override
    public void setPlayerSign(String sign) {
        playerSign = sign;
    }

    private String playerSign;

    @Override
    public void makeTurn(Matrix3d fightField) {

        List<Integer> free = new ArrayList<>();
        String []field  = fightField.getValues();

        for (int i = 0; i < field.length; i++) {
            if (field[i].equals(" ")) free.add(i);
        }
        Collections.shuffle(free);

        if (free.size() == 0 )
            throw new TicTakToeException("Failed to make turn: empty list of free cells");

        System.out.println("Making move level \"easy\"");

        fightField.set(free.get(0), playerSign);


    }


}
