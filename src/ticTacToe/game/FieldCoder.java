package ticTacToe.game;

/**
 *  This class using to convert two-level array to a long number
 *
 * @see ticTacToe.ai.LearningAlgorithm
 */
public class FieldCoder {
    /**
     * Method converts two-level array to a long number
     * @param field
     * @return field in long number equivalent
     */
    public long getCode(int[][] field) {
        long code = 0;
        for (int s = 0; s < field.length; s++) {
            for (int r = 0; r < field.length; r++) {
                code += field[s][r] * (Math.pow(3, (s*field.length + r)));
            }
        }
        return code;
    }
}
