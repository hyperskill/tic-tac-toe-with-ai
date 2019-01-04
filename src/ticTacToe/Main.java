package ticTacToe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        Random random = new Random(A + B);
        int sum = 0;
        for(int i = 0; i < N; i++){     //0 - 5
            sum += random.nextInt(B - A + 1) + A;
        }
        System.out.print(sum);
        // write your code here
    }
}