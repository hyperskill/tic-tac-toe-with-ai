package ticTacToe;

import java.util.Scanner;
import java.util.Random;

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
	static boolean displayStage(char[][] array) {
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
			if (flag) {System.out.println(current+" wins"); return true;}
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
			if (flag) {System.out.println(current+" wins"); return true;}
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
			if (flag) {System.out.println(current+" wins"); return true;}
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
			if (flag) {System.out.println(current+" wins"); return true;}

		}
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (array[i][j] == ' ') {return false;}
			}
		}
		System.out.println("Draw");
		return true;
	}
	//GENERATE RANDOM FIELD
	static void generateField(char[][] array) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				array[i][j] = ' ';
			}
		}
	}
	//CHECK USER INPUT
	static boolean checkUserInput(String input, char symbol) {
		Scanner scannerTemp = new Scanner(input);
		int x;
		int y;
		if (scannerTemp.hasNextInt()) {
			x = scannerTemp.nextInt();
			if (scannerTemp.hasNextInt()) {
				y = scannerTemp.nextInt();
				scannerTemp.close();
			}
			else {
				System.out.println("You should enter numbers!");
				scannerTemp.close();
				return false;
				}
		}
		else {
			System.out.println("You should enter numbers!");
			scannerTemp.close();
			return false;
			}
		if (x < 1 || x > 3 || y < 1 || y > 3) {
			System.out.println("Coordinates should be from 1 to 3!");
			return false;
		}
		array[dim-x][y-1] = symbol;
		return true;
	}
	//User move implementation
	static void userMove(char[][] array, Scanner scanner, char symbol) {
    	System.out.print("Enter the coordinates: ");
		String coordinates = scanner.nextLine();
		while (!checkUserInput(coordinates, symbol)) {
        	System.out.print("Enter the coordinates: ");
        	coordinates = scanner.nextLine();
    	}
		displayField(array);
	}
	//Easy level move implementation
	static void easyMove(char[][] array, char symbol) {
		System.out.println("Making move level \"easy\"");
		Random random = new Random();
		int randomNumber = random.nextInt(dim * dim);
		boolean flag = false;
		while (!flag) {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (randomNumber == 0 && array[i][j] == ' ') {flag = true; array[i][j] = symbol; break;}
					if (array[i][j] == ' ') {randomNumber--;}					
					}
					if (flag == true) {break;}
			}
		}
		displayField(array);		
	}
	//MAIN FUNCTION
	public static void main(String[] args) {
    	generateField(array);
    	displayField(array);
    	Scanner scanner = new Scanner(System.in);
    	while (true) {
    		userMove(array, scanner, 'X');
    		if (displayStage(array)) {break;}
    		easyMove(array, 'O');
    		if (displayStage(array)) {break;}
    	}
    	scanner.close();
    }
}