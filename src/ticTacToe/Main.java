package ticTacToe;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

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
		if (array[dim-x][y-1] == ' ') {
			array[dim-x][y-1] = symbol;
			return true;
		} 
		else {return false;}
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
	//Medium level move implementation
	static void mediumMove(char[][]array, char symbol) {
		System.out.println("Making move level \"medium\"");
		char enemy;
		if (symbol=='X') {enemy='O';}
		else {enemy = 'X';}
		int count = 0;
		//First step
		for (int i = 0; i < dim; i++) {
			count = 0;
			for (int j = 0; j < dim; j++) {
				if (array[i][j] == symbol) {
					count++;
				}
				if (array[i][j] == enemy) {
					count--;
				}
			}
			if (count == 2) {
				Arrays.fill(array[i], symbol);
				displayField(array);
				return;
			}
		}
		for (int i = 0; i < dim; i++) {
			count = 0;
			for (int j = 0; j < dim; j++) {
				if (array[j][i] == symbol) {
					count++;
				}
				if (array[j][i] == enemy) {
					count--;
				}
			}
			if (count == 2) {
				for (int j = 0; j < dim; j++) {
					array[j][i] = symbol;
				}
				displayField(array);
				return;
			}
		}
		count = 0;
		for (int i = 0; i < dim; i++) {
			if (array[i][i] == symbol) {count++;}
			if (array[i][i] == enemy) {
				count--;
			}
		}
		if (count == 2) {
			for (int i = 0; i < dim; i++) {
				array[i][i] = symbol;
			}
			displayField(array);
			return;
		}
		count = 0;
		for (int i = 0; i < dim; i++) {
			if (array[i][dim-i-1] == symbol) {count++;}
			if (array[i][dim-i-1] == enemy) {
				count--;
			}
		}
		if (count == 2) {
			for (int i = 0; i < dim; i++) {
				array[i][dim-1-i] = symbol;
			}
			displayField(array);
			return;
		}
		//Second step
		for (int i = 0; i < dim; i++) {
			count = 0;
			for (int j = 0; j < dim; j++) {
				if (array[i][j] == enemy) {
					count++;
				}
				if (array[i][j] == symbol) {
					count--;
				}
			}
			if (count == 2) {
				for (int j =0; j<dim;j++) {
					if (array[i][j]==' ') {array[i][j] = symbol;}
				}
				displayField(array);
				return;
			}
		}
		for (int i = 0; i < dim; i++) {
			count = 0;
			for (int j = 0; j < dim; j++) {
				if (array[j][i] == enemy) {
					count++;
				}
				if (array[j][i] == symbol) {
					count--;
				}
			}
			if (count == 2) {
				for (int j = 0; j < dim; j++) {
					if (array[j][i]==' ') {array[j][i] = symbol;}
				}
				displayField(array);
				return;
			}
		}
		count = 0;
		for (int i = 0; i < dim; i++) {
			if (array[i][i] == enemy) {count++;}
			if (array[i][i] == symbol) {count--;}
		}
		if (count == 2) {
			for (int i = 0; i < dim; i++) {
				if (array[i][i]==' ') {array[i][i] = symbol;}
			}
			displayField(array);
			return;
		}
		count = 0;
		for (int i = 0; i < dim; i++) {
			if (array[i][dim-i-1] == enemy) {count++;}
			if (array[i][dim-i-1] == symbol) {count--;}
		}
		if (count == 2) {
			for (int i = 0; i < dim; i++) {
				if (array[i][dim-1-i] == ' ') {array[i][dim-1-i] = symbol;}
			}
			displayField(array);
			return;
		}
		//Third step
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
    	Scanner scanner = new Scanner(System.in);    	
		do {
    		generateField(array);
        	displayField(array);
        	System.out.print("Input command:");
	    	switch (scanner.nextLine()) {
	    		case "start user user":
	    			while (true) {
	    	    		userMove(array, scanner, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		userMove(array, scanner, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start user easy":
	    			while (true) {
	    	    		userMove(array, scanner, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		easyMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start user medium":
	    			while (true) {
	    	    		userMove(array, scanner, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		mediumMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start easy user":
	    			while (true) {
	    	    		easyMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		userMove(array, scanner, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start easy easy":
	    			while (true) {
	    	    		easyMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		easyMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start easy medium":
	    			while (true) {
	    	    		easyMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		mediumMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start medium user":
	    			while (true) {
	    	    		mediumMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		userMove(array, scanner, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start medium easy":
	    			while (true) {
	    	    		mediumMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		easyMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "start medium medium":
	    			while (true) {
	    	    		mediumMove(array, 'X');
	    	    		if (displayStage(array)) {break;}
	    	    		mediumMove(array, 'O');
	    	    		if (displayStage(array)) {break;}
	    	    	}
	    			break;
	    		case "exit":
	    			scanner.close();
	    			return;
	    		default:
	    			System.out.println("Bad parameters!");
	    	}
		} while(true);
	}
}