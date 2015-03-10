//JE +CJ

public class GameLogic {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. create FrontEndInterface to communicate with GUI and pass instructions
	*/
	private GraphController graph;
	private Player[] players;
	private DevCardDeck devDeck;

	private boolean debugSet = true;

	public GameLogic(int[][] board, Player[] pArray) {
		GraphMaker gm = new GraphMaker(board);
		graph = new GraphController(gm.getVertexArray());
		devDeck = new DevCardDeck();
		players = pArray;
	}
	
	public void testGraphFeatures(){
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
		System.out.println("Place road for test between ("+v1+","+v2+") "+ graph.placeRoad(v1,v2, testb, true) );
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
	}

	public void diceRoll(int numRoll){
		if(numRoll == 7){
			for(int i=1; i<players.length; i++)
				players[i].sevenRoll();
			//initiate robber movement stealing sequence (same as for knight)
		}
		else{
			graph.distributeResources(numRoll);
		}

	}
	
	//method to be called at start of game. will not check that player has enough resources
	public boolean placeSettlement(int p, int vertexNumber){
		boolean build = graph.placeSettlement(vertexNumber, players[p], debugSet);
		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}
		if (build == true){
			//update stats in player class
			players[p].placeSettlement();
			return true;
		}
		else{
			System.out.println("You cannot build on this location.");
			return false;
		}
	}
	
	//method to give player the resource for their second settlement
	public void giveResourcesStartGame(int vertexNumber){
		graph.firstRoundResource(vertexNumber);
	}
	
	//method to be called during game play when player wants to build settlement
	public boolean buildSettlement(int p, int vertexNumber){
		//check that the player has resources to build a settlement and has settlements left
		boolean build = players[p].buildSetCheck();
		if(build == false)
			return false;

		build = graph.buildSettlement(vertexNumber, players[p], debugSet);

		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}

		if (build == true){
			//update stats in player class
			players[p].buildSettlement();
			return true;
		}

		else{
			System.out.println("You cannot build on this location.");
			return false;
		}

	}

	public boolean buildCity(int p, int vertexNumber){
		//check that the player has resources to build a city and has cities left
		boolean build = players[p].buildCityCheck();

		if (build == false)
			return false;

		build = graph.buildCity(vertexNumber, players[p], debugSet);

		if (build == false){
			System.out.println("You cannot build a city on this location.");
			return false;
		}

		else{
			players[p].buildCity();
			return true;
		}

	}
	
	public boolean buildRoad(int p, int v1, int v2){
		//check that the player has resources to build a road and has roads left
		boolean build = players[p].buildRoadCheck();

		if(build == false)
			return false;

		build = graph.buildRoad(v1,v2, players[p], debugSet); 

		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		} else{
			players[p].buildRoad();
			return true;
		}
		//longest road check
	}

	//used at beginning!
	public boolean placeRoad(int p, int v1, int v2){
		//check that the player has resources to build a road and has roads left
		boolean build = graph.placeRound1Road(v1,v2, players[p], debugSet); 
		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		}

		else{
			players[p].placeRoad();
			System.out.println("Road placed successfully");
			return true;
		}
	}

	public boolean buildDevCard(int p){
		boolean build = players[p].buildDevCheck();

		if (build == false)
			return false;
		int i = devDeck.drawDevCard();
		
		
		if(i ==10){
			System.out.println("There are no development cards left.");
			return false;
		}

		
		return true;
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

	public void trade(int[][] tradeStats){
		//tradeStats[0]= {type you want, amount, playerID to give}, tradeStats[1] = {type you'll give away, amount, playerID initiating trade}
		// if playerID to give is 0, the player initiating trade is trading with bank
		if (tradeStats[0][2]!=0){ //not trading with computer
			Player a = players[tradeStats[0][2]];
			//player a gives away resources and gains some
			for (int i = 0; i<tradeStats[0][1]; i++){
				a.looseResource(tradeStats[0][0]);
			} 
			for (int i = 0; i<tradeStats[1][1]; i++){
				a.addResource(tradeStats[1][0]);
			} 
		}
		Player b = players[tradeStats[1][2]];	
		//player b gives away resources and gains some
		for (int i = 0; i<tradeStats[0][1]; i++){
			b.addResource(tradeStats[0][0]);
		} 
		for (int i = 0; i<tradeStats[1][1]; i++){
			b.looseResource(tradeStats[1][0]);
		} 
	}

	public void moveRobber(){
		//this is going to need to be used for 7 rolls and knights
	} 
	
	
}