package ticTacToe;

/**
 * Created by home on 25.01.2019.
 */
public class CreateSimple {

    public static void main(String[] args) {

        String[] simple = new String[]{"O", " ", "O", "X", "X", "O", " ", "X", "X"};
        for (int i = 0; i <simple.length ; i++) {
            if (i % 3 == 0 && i != 0){
                System.out.println('\n');
            }
            System.out.printf(simple[i]);
        }

    }

}
