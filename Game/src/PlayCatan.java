import java.util.Scanner;


public class PlayCatan {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args){
		//creates Player classes for each player and stores in players[]
		System.out.println("Enter number of players: ");
		int numPlayers = sc.nextInt();
		
		boolean graphics = false;
		System.out.println("Enter 1 if using graphics: ");
		int input = sc.nextInt();
		if (input == 1){
			graphics = true;
		}
		
		boolean AI = false;
		System.out.println("Enter 1 if using AI: ");
		input = sc.nextInt();
		if (input == 1){
			AI = true;
		}
		
		RunGame gameRunner = new RunGame(numPlayers, graphics,AI);
	}
}
