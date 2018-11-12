package ticTacToe;

public class Main {
    
    public static String[][] arr = new String [3][3];
    
    public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
	    int random_number;
	    int label;
	       
	    for (int i = 0; i < 3; i++) 
	    	for (int j = 0; j < 3; j++) {
				if ((random_number = (int) (Math.random() * 3)) == 0)
					arr[i][j] = "X";
				else if (random_number == 1)
					arr[i][j] = "O";
				else if (random_number == 2)
					arr[i][j] = "X";
	    	}
	    
	    for (int i = 0; i < 3; i++) {
	    	for (int j = 0; j < 3; j++) {
	    		System.out.print(arr[i][j] + " ");
	    	}	
	    	System.out.println();
	    }
	    
	    switch (label = CheckWinner() ) {
	    case 1:
	    	System.out.println("X wins");
	    	break;
	    case 2:
	    	System.out.println("O wins");
	    	break;
	    case 3:
	    	System.out.println("Game not finished");
	    	break;
	    case 0:
	    	System.out.println("Draw");
	    	break;
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
					line = arr[0][2] + arr[1][1] + arr[2][2];
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
			else if (CheckWhitespace() == true) 
				return 3;
			
			}
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
}
