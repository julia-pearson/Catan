import java.util.*;
/*
 * The class that will keep track of what's going on in the game
 * 1. Ask for number of players
 * 2. Create GP and have it initialize board
 * 3. Create GUI/build board
 * 4. Create front end interface
 * 
 */
public class RunGame {
	private static Scanner sc = new Scanner(System.in);
	private static GameLogic gl;
	
	//RUN WITH NUMBER OF PLAYERS AS COMMAND LINE ARGUMENT
	public static void main (String[] args){
		//creates Player classes for each player and stores in players[]
		System.out.println("Enter number of players: ");
		int numPlayers = sc.nextInt();
		gl = new GameLogic(numPlayers);

		gl.testCombinedFeatures();

		//create graph and GUI from the board
		//new GraphMaker(testBoard);

		//Visualizer GUI = new Visualizer(board);

		//play da game

	}
}
