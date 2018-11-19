package ticTacToe.players;

import ticTacToe.enums.PlayerSign;
import ticTacToe.utils.Matrix3d;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MediumPlayer extends AbstractPlayer implements Player {

    public MediumPlayer(PlayerSign playerSign) {
        super(playerSign);
    }

    private static Integer getIdxOfLastFreeSpace(final String p[], final PlayerSign playerS) {
        if(Arrays.stream(p).filter(s->s.equals(playerS.toString())).count() != p.length - 1) return null;

        OptionalInt indexOpt = IntStream.range(0, p.length)
                .filter(i -> " ".equals(p[i]))
                .findFirst();

        if (indexOpt.isPresent())
            return indexOpt.getAsInt();
        else
            return null;
    }

    public Integer occupyLastSlot(Matrix3d fightField, PlayerSign player){
        Integer idx;
        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            idx = getIdxOfLastFreeSpace(fightField.getRow(i), player);
            if (idx != null) {
                fightField.set(i, idx, playerSign.toString());
                return idx;
            }
            idx = getIdxOfLastFreeSpace(fightField.getColon(i), player);
            if (idx != null) {
                fightField.set(idx, i, playerSign.toString());
                return idx;
            }
        }
        idx = getIdxOfLastFreeSpace(fightField.getDiagonal(Matrix3d.DiagonalType.POSITIVE),player);
        if (idx != null) {
            fightField.set(idx, idx, playerSign.toString());
            return idx;
        }
        idx = getIdxOfLastFreeSpace(fightField.getDiagonal(Matrix3d.DiagonalType.POSITIVE),player);
        if (idx != null) {
            fightField.set(Matrix3d.getDIMENSION() - idx - 1, idx, playerSign.toString());
            return idx;
        }
        return idx;
    }

    @Override
    public void makeTurn(Matrix3d fightField) {

        System.out.println("Making move level \"medium\"");

        Integer idx = occupyLastSlot(fightField, playerSign);
        if (idx == null)
            idx = occupyLastSlot(fightField, playerSign.opposite());
        if (idx == null) {
            Player easy = new EasyPlayer(playerSign);
            easy.makeTurn(fightField);
        }
    }

}
