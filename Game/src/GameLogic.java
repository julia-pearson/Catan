//JE +CJ

public class GameLogic {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. create FrontEndInterface to communicate with GUI and pass instructions
	*/
	private GraphController graph;
	private DevCardDeck devDeck;

	private boolean debugSet = true;

	public GameLogic(int[][] board) {
	//julia should be taking this
	//	players = new Player[numPlayers];
	//	for(int i=1; i<(numPlayers+1); i++)
	//		players[i] = new Player(i);
		//testboard gives a predetermined board
	//	int[][] testBoard = new Board().getTestBoard();
		//int[][] board= new Board().getBoard();
		GraphMaker gm = new GraphMaker(board);

		graph = new GraphController(gm.getVertexArray());
		devDeck = new DevCardDeck();
	}
	
	/*public void testGraphFeatures(){
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

	public void testCombinedFeatures(){
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
	}*/

	public void diceRoll(int numRoll){
		graph.distributeResources(numRoll);

	}
	
	//method to be called at start of game. will not check that player has enough resources
	public boolean placeSettlement(Player p, int vertexNumber){
		boolean build = graph.placeSettlement(vertexNumber, p, debugSet);
		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}
		if (build == true){
			//update stats in player class
			p.placeSettlement();
			return true;
		}
		else{
			System.out.println("You cannot build on this location.");
			return false;
		}
	}
	
	//method to be called during game play when player wants to build settlement
	public boolean buildSettlement(Player p, int vertexNumber){
		//check that the player has resources to build a settlement and has settlements left
		boolean build = p.buildSetCheck();
		if(build == false)
			return false;

		build = graph.buildSettlement(vertexNumber, p, debugSet);

		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}

		if (build == true){
			//update stats in player class
			p.buildSettlement();
			return true;
		}

		else{
			System.out.println("You cannot build on this location.");
			return false;
		}

	}

	public boolean buildCity(Player p, int vertexNumber){
		//check that the player has resources to build a city and has cities left
		boolean build = p.buildCityCheck();

		if (build == false)
			return false;

		build = graph.buildCity(vertexNumber, p, debugSet);

		if (build == false){
			System.out.println("You cannot build a city on this location.");
			return false;
		}

		else{
			p.buildCity();
			return true;
		}

	}
	
	public boolean buildRoad(Player p, int v1, int v2){
		//check that the player has resources to build a road and has roads left
		boolean build = p.buildRoadCheck();

		if(build == false)
			return false;

		build = graph.placeRoad(v1,v2, p); 

		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		}

		else{
			p.buildRoad();
			return true;
		}

	}

	//used at beginning!
	public boolean placeRoad(Player p, int v1, int v2){
		//check that the player has resources to build a road and has roads left

		boolean build = graph.placeRoad(v1,v2, p); 

		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		}

		else{
			p.placeRoad();
			return true;
		}

	}

	public boolean buildDevCard(Player p){
		boolean build = p.buildDevCheck();

		if (build == false)
			return false;

		int i = devDeck.drawDevCard();

		if(i ==10){
			System.out.println("There are no development cards left.");
			return false;
		}

		p.buildDev(i);
		return true;
	}

//still working on this
	/*public boolean useDevCard(Player p, int i){
		boolean build = p.useDevCard(i);

		return build;
	}
	*/


	public boolean usePort(Player p){
		//CJ: check that the player has the port and update stats
		//Julia P: update graphics
	}

	public void trade(int p){
		//meep
	}


}
