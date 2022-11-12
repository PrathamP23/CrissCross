import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CrissCross {
	
	static ArrayList<Integer> Playerpositions =new ArrayList<Integer>();
	static ArrayList<Integer> CPUpositions =new ArrayList<Integer>();

	public static void main(String[] args) {

		// Defining Structure of Game board
		char[][] board = {{' ','|',' ','|',' '},
				{'-','*','-','*','-'},
				{' ','|',' ','|',' '},
				{'-','*','-','*','-'},
				{' ','|',' ','|',' '}};
		// Creating and Updating Gameboard
		createBoard(board);
		
		// Stating the Game
		while(true) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter position from 1-9: ");
		int position = scan.nextInt();
		//Avoiding overriding of positions
		while(Playerpositions.contains(position) ||  CPUpositions.contains(position)) {
			System.out.println("INVALID MOVE!! TRY AGAIN!!!");
			position = scan.nextInt();
		}
		System.out.println(position);
		
		//Checking winner
		winMove(board,position,"Prat");
		String result = checkWinner();
		if(result.length() > 0) {
			System.out.println(result);
			createBoard(board);
			break;
		}
		
		Random rand =new Random();
		int CPUmove=rand.nextInt(9) + 1;
		//Avoiding overriding of positions
		while(Playerpositions.contains(CPUmove) ||  CPUpositions.contains(CPUmove)) {
			 CPUmove=rand.nextInt(9) + 1;
		}
		
		//Checking winner
		winMove(board,CPUmove,"CPU");
		
		createBoard(board);
		
		result = checkWinner();
		if(result.length() > 0) {
			createBoard(board);
			System.out.println(result);
			break;
		}
}
	}

	// Function for creating Board
public static void createBoard( char[][] board) {
	
	for(char[] row : board) {
		for(char c : row) {
			System.out.print(c);
		}
			System.out.println();
		}
	}

// Function for playing the move
public static void winMove(char[][] board,int pos,String user ) {
	char symbol='O';
	if(user.equals("CPU")) {
		symbol='O';
		CPUpositions.add(pos);
	}
	else {
		symbol='X';
		Playerpositions.add(pos);
	}
	
	switch(pos) {
	case 1:
		board[0][0]=symbol;
		break;
	case 2:
		board[0][2]=symbol;
		break;
	case 3:
		board[0][4]=symbol;
		break;
	case 4:
		board[2][0]=symbol;
		break;
	case 5:
		board[2][2]=symbol;
		break;
	case 6:
		board[2][4]=symbol;
		break;
	case 7:
		board[4][0]=symbol;
		break;
	case 8:
		board[4][2]=symbol;
		break;
	case 9:
		board[4][4]=symbol;
		break;
	}
	
	
  }

//Function to check winner
public static String checkWinner() {
	//List all the winning conditions
	List TopRow = Arrays.asList(1, 2, 3);
	List MiddleRow = Arrays.asList(4, 5, 6);
	List BottomRow = Arrays.asList(7, 8, 9);
	List LeftColumn = Arrays.asList(1, 4, 7);
	List MiddleColumn = Arrays.asList(2, 5, 8);
	List RightColumn = Arrays.asList(3, 6, 9);
	List Criss = Arrays.asList(1, 5, 9);
	List Cross = Arrays.asList(3, 5, 7);
	
	List<List> winning = new ArrayList<List>();
	winning.add(TopRow);
	winning.add(MiddleRow);
	winning.add(BottomRow);
	winning.add(LeftColumn);
	winning.add(MiddleColumn);
	winning.add(RightColumn);
	winning.add(Criss);
	winning.add(Cross);
	
	for (List l : winning) {
		if(Playerpositions.containsAll(l)) {
			return"Congratulation you won !!!";
		}
		else if(CPUpositions.containsAll(l)){
			return"CPU won. You Lost ;-)";
		}
		else if(Playerpositions.size() + CPUpositions.size() == 9) {
			return "Tied!! Play Again";
		}
	}
	return "";
	
}
}