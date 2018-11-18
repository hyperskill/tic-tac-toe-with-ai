package ticTacToe;

public class Main {
    public static void main(String[] args) {
        for (int i=0; i<3; i++) {
        	for (int j=0; j<3; j++) {
        		if (Math.random()>=0.5) {
        			System.out.print("X ");
        		}
        		else {System.out.print("O ");}
        	}
        	System.out.println();
        }
    }
}