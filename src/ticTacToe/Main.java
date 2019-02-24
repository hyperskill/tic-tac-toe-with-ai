package ticTacToe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[][] arr = { { ' ', 'X', 'X' }, { 'O', 'O', ' ' }, { 'O', 'X', ' ' } };

		System.out.println("---------");

		for (int i = 0; i < 3; i++) {
			System.out.print('|');
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.print(" ");
			System.out.println('|');
		}

		System.out.println("---------");

		while (true) {
			System.out.print("Enter the coordinates: ");

			String str = sc.nextLine();

			String a = "";
			String b = "";
			boolean afterWhiteSpace = false;

			for (char ch : str.toCharArray()) {
				if (ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9' && ch != '9' && ch != ' ') {
					System.out.println("You should enter numbers!");
					break;
				} else if (ch == ' ') {
					afterWhiteSpace = true;
				} else if (!afterWhiteSpace) {
					a += ch;
				} else {
					b += ch;
				}
			}

			if (a.length() == 0 || b.length() == 0) {
				continue;
			}

			int x = (int) Long.parseLong(a, 10);
			int y = (int) Long.parseLong(b, 10);

			if (x == 1)
				x = 0;
			else if (x == 2)
				x = 1;
			else if (x == 3)
				x = 2;

			if (y == 1)
				y = 2;
			else if (y == 2)
				y = 1;
			else if (y == 3)
				y = 0;

			if (x > 3 || y > 3) {
				System.out.println("Coordinates should be from 1 to 3!");
				continue;
			} else if (arr[y][x] != ' ') {
				System.out.println("This cell is occupied! Choose another one!");
				continue;
			}

			arr[y][x] = 'X';
			break;
		}

		System.out.println("---------");

		for (int i = 0; i < 3; i++) {
			System.out.print('|');
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.print(" ");
			System.out.println('|');
		}

		System.out.println("---------");

		sc.close();
	}
}
