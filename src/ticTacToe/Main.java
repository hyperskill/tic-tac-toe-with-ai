package ticTacToe;

public class Main {
    public static void main(String[] args)
    {
        char[] arr = {'0',' ', 'O', 'X', 'X', 'O', ' ', 'X', 'X'};
        for( int i = 0; i < 9; ++i)
        {
            System.out.printf("%c ",arr[i]);
            if(i % 3 == 2)
            {
                System.out.println();
            }
        }

    }
}