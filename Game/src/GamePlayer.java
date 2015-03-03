//JE +CJ

import java.util.*;

public class GamePlayer {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. control user input
	4. update tables/board when user gives input 
	*/
<<<<<<< HEAD
	
	static Player[] players;
	static GraphController graph;
	
	
	public GamePlayer(int numPlayers){
		//initialize and fill the Player[]
	}
	
=======

	private static Scanner sc = new Scanner(System.in);
	private static Player[] players;

	//RUN WITH NUMBER OF PLAYERS AS COMMAND LINE ARGUMENT
>>>>>>> 941216f2a7e2f5b3d0710118e26c63cc146af1ab
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
		
<<<<<<< HEAD
		GraphMaker gm = new GraphMaker(testBoard);
		graph = new GraphController(gm.getVertexArray());
		testGraphFeatures();
		
=======
		//create graph and GUI from the board
		//new GraphMaker(testBoard);
>>>>>>> 941216f2a7e2f5b3d0710118e26c63cc146af1ab
		//Visualizer GUI = new Visualizer(board);

		//play da game

	}
	
<<<<<<< HEAD
	private static void testGraphFeatures(){
		System.out.println("Testing Graph Features");
		Player test = new Player(0);
		Player testb = new Player(1);
		
		System.out.println("Testing Place Settlement");
		int i = 30;
		System.out.println("Place settlement at "+i+graph.placeSettlement(i, test));
		i = 19;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test));
		i = 18;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test) );
	
		System.out.println("Testing Build City");
		i = 17;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test) );
		System.out.println("Build city for testb at "+i+ graph.buildCity(i, test) );
		
		System.out.println("Testing Resource Distribution:");
		int roll = 4;
		graph.distributeResources(roll);
		
		System.out.println("Testing Road Placement:");
		int v1 = 17;
		int v2 = 18;
		System.out.println("Place road for test between ("+v1+","+v2+") "+ graph.placeRoad(v1,v2, testb) );
			
		
	}
	public void buildSettlement(Player p, int VertexNumber){
		//check that the player has resource to build
		//check that it is a legal move
=======
	public void diceRoll(int numRoll){
		//call a method in Julia's graph that will check who gets what resources and return a structure handling them
		//add the resources for each player to their player class
		//send updated resource stats for display in the GUI

		//if the number rolled was a 7, initiate the robber movement sequence

		//also if 7 was rolled players with more than 7 cards must lose half their cards!
		for(int i=0; i<players.length; i++)
			players[i].sevenRoll();
		//update GUI
		
>>>>>>> 941216f2a7e2f5b3d0710118e26c63cc146af1ab
	}

	//int p is the player id!
	public void buildSettlement(int p, int vertexNumber){
		//check that the player has resources to build a settlement and has settlements left
		boolean build = players[p].buildSetCheck();

		if (build == true){
			//call a method in the graph that checks if the spot is valid. return build = true if yes, false if no. also return something about if we add a port
			
			if (build == true){
				//update stats in player class
				//add parameter for the port
				players[p].buildSettlement();

				//tell the GUI to put the settlement in that spot and update the resources and victory points for that player
			}
			else
				;//tell GUI that the player cannot build on that location
		}
		else
			;//tell GUI that the player does not have valid cards or has already built 5 settlements
	}

	public void buildCity(int p, int vertexNumber){
		//check that the player has resources to build a city and has cities left
		boolean build = players[p].buildCityCheck();

		if (build == true){
			//call a method in the graph that checks if the spot is valid. return build = true if yes, false if no
			
			if (build == true){
				//update stats in player class
				players[p].buildCity();

				//tell the GUI to put the city in that spot and update the resources and victory points for that player
			}
			else
				;//tell GUI that the player cannot build on that location
		}
		else
			;//tell GUI that the player does not have valid cards or has already built 4 cities
	}
	
	public void buildRoad(int p, int roadNum){
		//check that the player has resources to build a road and has roads left
		boolean build = players[p].buildRoadCheck();

		if (build == true){
			//call a method in the graph that checks if the spot is valid. return build = true if yes, false if no
			
			if (build == true){
				//update stats in player class
				players[p].buildRoad();

				//tell the GUI to put the road in that spot and update the resources and victory points for that player (and longest road indicator?)
			}
			else
				;//tell GUI that the player cannot build on that location
		}
		else
			;//tell GUI that the player does not have valid cards or has already built 15 roads


//Q how do we do longest road?????
	}







	public void buildDevCard(int p){
		//CJ: check that the player has resources to build a d card. update shit.
		//Julia P: update graphics

		//IF VP UPDATE PLAYER STAT
	}

	public void useDevCard(int p){
		//CJ: ask which one and see if the player has that dev card
		//Julia P: update graphics
	}

	public void usePort(int p){
		//CJ: check that the player has the port and update stats
		//Julia P: update graphics
	}

	public void trade(int p){
		//meep
	}

	public void moveRobber(){
		//this is going to need to be used for 7 rolls and knights
	} 
	
	
}