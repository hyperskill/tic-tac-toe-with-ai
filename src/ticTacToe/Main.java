package ticTacToe;

import java.util.Arrays;

public class Main {
	static char[] array = new char[9];
	static void displayField(char[] array) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(array[k++]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
    public static void main(String[] args) {
    	for (int i=0; i<9; i++) {
        	if (Math.random()>=0.66) {
        		array[i] = 'X';
       		}
       		else if (Math.random() <= 0.33) {
       			array[i] = 'O';
       			}
       		else {
       			array[i] = ' ';
       		}
        }
    	displayField(array);
    }
}