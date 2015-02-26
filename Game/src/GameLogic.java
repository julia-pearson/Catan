//JE +CJ

public class GameLogic {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. create FrontEndInterface to communicate with GUI and pass instructions
	*/
	static GraphController graph;
	private static Player[] players;

	public GameLogic(int numPlayers) {
		players = new Player[numPlayers+1];
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);
		
		int[][] testBoard = new Board().getTestBoard();
		GraphMaker gm = new GraphMaker(testBoard);
		graph = new GraphController(gm.getVertexArray());
	}
	
	public static void testGraphFeatures(){
		System.out.println("Testing Graph Features");
		Player test = new Player(0);
		Player testb = new Player(1);
		
		System.out.println("Testing Place Settlement");
		int i = 30;
		System.out.println("Place settlement at "+i+graph.placeSettlement(i, test, true));
		i = 19;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test, true));
		i = 18;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test, true) );
	
		System.out.println("Testing Build City");
		i = 17;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test, true) );
		System.out.println("Build city for testb at "+i+ graph.buildCity(i, test, true) );
		
		System.out.println("Testing Resource Distribution:");
		int roll = 4;
		graph.distributeResources(roll);
		
		System.out.println("Testing Road Placement:");
		int v1 = 17;
		int v2 = 18;
		System.out.println("Place road for test between ("+v1+","+v2+") "+ graph.placeRoad(v1,v2, testb) );
	}

	public static void testCombinedFeatures(){
		System.out.println("Testing Place Settlement");
		int i = 1;
		int v= 17;
		placeSettlement(i, v);
		players[i].printStats();
		v = 19;
		placeSettlement(i, v);
		players[i].printStats();
		v = 18;
		placeSettlement(i, v);
		players[i].printStats();
	}

	public void diceRoll(int numRoll){
		//call a method in Julia's graph that will check who gets what resources and return a structure handling them
		//add the resources for each player to their player class
		//send updated resource stats for display in the GUI

		//if the number rolled was a 7, initiate the robber movement sequence

		//also if 7 was rolled players with more than 7 cards must lose half their cards!
		for(int i=0; i<players.length; i++)
			players[i].sevenRoll();
		//update GUI
		
	}
	
//method to be called at start of game. will not check that player has enough resources
	public static void placeSettlement(int p, int vertexNumber){
		boolean debugSet = true;
		boolean build = graph.placeSettlement(vertexNumber, players[p], debugSet);
		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}
		if (build == true){
			//update stats in player class
			//add parameter for the port
			players[p].buildSettlement();
			//tell the GUI to put the settlement in that spot and update the resources and victory points for that player
		}
		else
			;//tell GUI that the player cannot build on that location
	}
	
	//method to be called during game play when player wants to build settlement
	public static void buildSettlement(int p, int vertexNumber){
		boolean debugSet = true;
		//check that the player has resources to build a settlement and has settlements left
		boolean build = players[p].buildSetCheck();

		if (build == true){
			//call a method in the graph that checks if the spot is valid. return build = true if yes, false if no. also return something about if we add a port
			build = graph.buildSettlement(vertexNumber, players[p],debugSet);
			if (debugSet){
				System.out.println("Build settlement at "+vertexNumber+ " "+build);
			}
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