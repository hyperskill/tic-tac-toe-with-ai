package ticTacToe;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
       /*
       First implementation without the use of arrays
        System.out.println("\tX \tO \tX");
        System.out.println("\tO \tX \tO");
        System.out.println("\tX \tX \tO");
        */

       char[] grid1 = {'O',' ' , 'O'};
       char[] grid2 = {'X','X' , 'O'};
       char[] grid3 = {' ','X' , 'X'};


       System.out.println(grid1);
        System.out.println(grid2);
        System.out.println(grid3);

    }
}