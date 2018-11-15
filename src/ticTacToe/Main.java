import java.util.Scanner;
public class Main {
	
	static String[][] arr = new String [3][3];
	static Scanner in = new Scanner (System.in);

	public static void main (String args[]) {
	
		String state;
		String player1;
		String player2;
		
		do {
			System.out.print("Input command: ");
			state = in.next();
			if (state.equals("start")) {
				player1 = in.next();
				player2 = in.next();
				
				if ( (player1.equals("user") || player1.equals("easy")) && (player2.equals("user") || player2.equals("easy"))) {
					NewField();
					PrintGame();
					GameMechanic(player1,player2);
				}
			}
			if (!(state.equals("start") || state.equals("exit")))
				System.out.println("Unlnown command");	
		} while (!state.equals("exit"));
	}
	
	public static void GameMechanic(String player1, String player2) {
		int label;
		do {
			NextMove("X",player1);	
			PrintGame();
			GameState(label = CheckWinner());
			if (label != 3)
				break;
			NextMove("O",player2);	
			PrintGame();
			GameState(label = CheckWinner());
			if (label != 3)
				break;
		    } while (true); 
	}
	
	public static int GameState (int label) {
		switch (label) {
    	case 1:
    		System.out.println("X wins");
    		break;
    	case 2:
    		System.out.println("O wins");
    		break;
    	case 3:
    		break;
    	case 0:
    		System.out.println("Draw");
    		break;
    	}
		return label;
	}
	
	public static void NewField() {
		for (int i = 0; i < 3; i++) 
	    	for (int j = 0; j < 3; j++) {
	    		arr[i][j] = " ";
	    	}
	}
	
	public static int CheckWinner() {
		String line = null;
		for (int i = 0; i < 8; i++) {
			switch(i) {
				case 0:
					line = arr[0][0] + arr[0][1] + arr[0][2];
					break;
				case 1:
					line = arr[1][0] + arr[1][1] + arr[1][2];
					break;
				case 2:
					line = arr[2][0] + arr[2][1] + arr[2][2];
					break;
				case 3:
					line = arr[0][0] + arr[1][0] + arr[2][0];
					break;
				case 4:
					line = arr[0][1] + arr[1][1] + arr[2][1];
					break;
				case 5:
					line = arr[0][2] + arr[1][2] + arr[2][2];
					break;
				case 6:
					line = arr[0][0] + arr[1][1] + arr[2][2];
					break;
				case 7:
					line = arr[2][0] + arr[1][1] + arr[0][2];
					break;
			}
			if (line.equals("XXX"))
				return 1;
			else if (line.equals("OOO"))
				return 2;
			}	
		if (CheckWhitespace() == true) 
			return 3;
		else
			return 0;
	}
	
	
	public static boolean CheckWhitespace() {
		boolean label = false;  
	    for (int i = 0; i < 3; i++) 
	    	for (int j = 0; j < 3; j++) 
	    		if (arr[i][j] == " ")
	    			label = true;
	    return label;
	}
	
	public static void PrintGame() {
		System.out.println("---------");
		System.out.println("|" + " " + arr[2][0] + " " + arr[2][1] + " " + arr[2][2] + " " +"|");
		System.out.println("|" + " " + arr[1][0] + " " + arr[1][1] + " " + arr[1][2] + " " +"|");
		System.out.println("|" + " " + arr[0][0] + " " + arr[0][1] + " " + arr[0][2] + " " +"|");
		System.out.println("---------");
	}
	
	public static void NextMove(String s, String player) {
		int x = 0, y = 0;
		boolean b = false;

		if (player.equals("user")) {
			do {
				System.out.println("Enter the coordinates: ");
				while (!in.hasNextInt()) {
					System.out.println("You should enter coordinates!");
					in.next();
				}
				x = in.nextInt();
				
				while (!in.hasNextInt()) {
					System.out.println("You should enter coordinates!");
					in.next();
				}
				y = in.nextInt();
				b = CheckMove (x, y);
				} while (b == false);
			arr[x-1][y-1] = s;
			} 
		
		if (player.equals("easy")) 
			easyMode(s);
		if (player.equals("medium")) 
			mediumMode(s);
		if (player.equals("hard")) 
			hardMode(s);	
	}
	
	public static boolean CheckMove (int x, int y) {
		boolean b;
		if (!(x >= 1 && x <= 3) && (y >= 1 && y <= 3))
			b = false;
		else if ((arr[x-1][y-1].equals(" ")))
			 b = true;
		else
			b = false;
		return b;
	}
	
	public static void easyMode (String s) {
		int x,y;
		boolean b;
		do {
			x = (int) (1 + Math.random()*3);
			y = (int) (1 + Math.random()*3);
			b = CheckMove (x, y);
		} while (b == false);
		arr[x-1][y-1] = s;
	}
	
	public static void mediumMode (String s) {
	
	 }
		

	public static void hardMode (String s) {
		
	}
}
