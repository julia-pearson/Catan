//JE +CJ

import java.util.*;

public class GamePlayer {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. control user input
	4. update tables/board when user gives input 
	*/

	private static Scanner sc = new Scanner(System.in);
	private Player[] players;

	//RUN WITH NUMBER OF PLAYERS AS COMMAND LINE ARGUMENT
	public static void main (String[] args){

		//creates Player classes for each player and stores in players[]
		int numPlayers = sc.nextInt();
		players = new Player[3];
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);

		//create board
		//testboard gives a predetermined board
		int[][] testBoard = new Board().getTestBoard();
		//int[][] board= new Board().getBoard();
		
		//create graph and GUI from the board
		//new GraphMaker(testBoard);
		//Visualizer GUI = new Visualizer(board);


		//add event listeners to gUI and figure out how GamePlayer will capture the click events 

		//play da game

	}
	
	public void buildSettlement(Player p, int vertexNumber){
		//CJ: check that the player has resources to build a settlement and has settlements left
		//Julia E: check that it is a legal move on the graph (ie roads built to there, no one else build there, 2 away)
		//CJ: update resource cards and victory points
		//Julia P: update graphics
	}

	public void buildCity(Player p, int vertexNumber){
		//CJ: check that the player has resources to build a city and has cities left
		//Julia E: check that it is a legal move on the graph (ie settlement already there)
		//CJ: update resource cards and victory points
		//Julia P: update graphics
	}
	
	public void buildRoad(Player p, int roadNum){
		//CJ: check that the player has resources to build a road and has roads left
		//Julia E: check that it is a legal move on the graph (ie no other road there, road connected. if first road, make sure one of each settlement)
		//CJ: update resource cards and longest road stuff
		//Julia P: update graphics
	}

	public void buildDevCard(Player p){
		//CJ: check that the player has resources to build a d card. update shit.
		//Julia P: update graphics
	}

	public void useDevCard(Player p){
		//CJ: ask which one and see if the player has that dev card
		//Julia P: update graphics
	}

	public void usePort(Player p){
		//CJ: check that the player has the port and update stats
		//Julia P: update graphics
	}

	public void trade(Player p){
		//meep
	}
	
	
}
