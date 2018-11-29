package ticTacToe;

import java.util.Scanner;

public class Main {
	static int dim = 3;
	static char[][] array = new char[dim][dim];
	//SHOW CURRENT FIELD
	static void displayField(char[][] array) {
		System.out.println("---------");
		for (int i = 0; i < dim; i++) {
			System.out.print("| ");
			for (int j = 0; j < dim; j++) {
				System.out.print(array[i][j]);
				System.out.print(' ');
			}
			System.out.print("| ");
			System.out.println();
		}
		System.out.println("---------");
	}
	//DISPLAY STAGE
	static String displayStage(char[][] array) {
		char current;
		boolean flag = false;
		for (int i = 0; i < dim; i++) {
			current = 'z';
			if (array[i][0] != ' ') {current = array[i][0];}
			else {continue;}
			for (int j = 0; j < dim; j++) {
				if (array[i][j] == current) {
					current = array[i][j];
					flag = true;
				}
				else {flag = false;break;}
				}
			if (flag) {return current+" wins";}
			}
		for (int i = 0; i < dim; i++) {
			current = 'z';
			if (array[0][i] != ' ') {current = array[0][i];}
			else {continue;}
			for (int j = 0; j < dim; j++) {
				if (array[j][i] == current) {
					current = array[j][i];
					flag = true;
				}
				else {flag = false;break;}
				}
			if (flag) {return current+" wins";}
			}
		
		current = 'z';
		if (array[0][0] != ' ') {
			current = array[0][0];
			for (int i = 0; i < dim; i++) {
				if (array[i][i] == current) {
					current = array[i][i];
					flag = true;
					}
				else {flag = false;break;}
			}
			if (flag) {return current+" wins";}
		}
			
		current = 'z';
		if (array[0][dim-1] != ' ') {
			current = array[0][dim-1];
			for (int i = 0; i < dim; i++) {
				if (array[i][dim-1-i] == current) {
					current = array[i][dim-1-i];
					flag = true;
					}
				else {flag = false;break;}
				}
			if (flag) {return current+" wins";}

		}
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (array[i][j] == ' ') {return "Game not finished";}
			}
		}
		return "Draw";
	}
	//GENERATE RANDOM FIELD
	static void generateField(char[][] array) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (Math.random()>=0.66) {
	        		array[i][j] = 'X';
	       		}
	       		else if (Math.random() <= 0.33) {
	       			array[i][j] = 'O';
	       			}
	       		else {
	       			array[i][j] = ' ';
	       		}
			}
		}
	}
	
    public static void main(String[] args) {
    	generateField(array);
    	displayField(array);
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter the coordinates: ");
    	String coordinates = scanner.nextLine();
    	scanner.close();
    	scanner = new Scanner(coordinates);
    	int x = scanner.nextInt();
    	int y = scanner.nextInt();
    	scanner.close();
    	array[dim-x][y-1] = 'X';
    	displayField(array);
    	//System.out.println(displayStage(array));
    }
}