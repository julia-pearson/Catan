//JE +CJ

public class GameLogic {
	/*
	-Tasks:
	1.initialize the board
	2. Handle all logic for changing state of board/player hands
	*/
	private GraphController graph;
	private DevCardDeck devDeck;

	private boolean debugSet = false;

	private Player[] players;

	public GameLogic(int[][] board, Player[] pArray) {
		GraphMaker gm = new GraphMaker(board);
		graph = new GraphController(gm.getVertexArray(), gm.tilesInBoard);
		devDeck = new DevCardDeck();
		players = pArray;
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
		boolean build = graph.checkPlaceSettlement(vertexNumber, players[p], debugSet);
		if (debugSet){
			System.out.println("Place settlement at "+vertexNumber+" " + build);
		}
		if (build == true){
			//update stats in player class
			players[p].placeSettlement(vertexNumber);
			graph.addSettlementToGraph(vertexNumber, players[p]);
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
	
	public boolean buildSetCheck(int p, int vertexNumber){
		//check player resources and the graph
		boolean toReturn = players[p].buildSetCheck();
		if(toReturn == false)
			return false;
		return  graph.checkBuildSettlement(vertexNumber, players[p], debugSet);
	}
	
	//method to be called during game play when player wants to build settlement
	public boolean buildSettlement(int p, int vertexNumber){
		boolean build = buildSetCheck(p, vertexNumber);
		if (debugSet){
			System.out.println("Trying to build settlement at "+vertexNumber+" " + build);
		}

		if (build == true){
			//update stats in player class
			players[p].buildSettlement(vertexNumber);
			graph.addSettlementToGraph(vertexNumber, players[p]);
			return true;
		}

		else{
			System.out.println("You cannot build on this location.");
			return false;
		}

	}
	
	public boolean buildCityCheck(int p, int v){
		//check that the player has resources to build a city and has cities left
		if (players[p].buildCityCheck() == false)
			return false;

		return graph.checkBuildCity(v, players[p], debugSet);
		
	}
	
	public boolean buildCity(int p, int vertexNumber){
		boolean build = buildCityCheck(p, vertexNumber);
		
		if (build == false){
			System.out.println("You cannot build a city on this location.");
			return false;
		}

		else{
			players[p].buildCity();
			graph.addCityToGraph(vertexNumber, players[p]);
			return true;
		}

	}

	public void moveRobber(int destinationTile, int playerMovingRobber){
		Player playerToLoose = graph.moveRobber(destinationTile);
		int resourceGained = playerToLoose.stealResource();
		players[playerMovingRobber].addResource(resourceGained, 1);
	} 
	
	public boolean buildRoadCheck(int p, int v1, int v2){
		//check that the player has resources to build a road and has roads left
		if(players[p].buildRoadCheck() == false)
			return false;

		return graph.checkBuildRoad(v1,v2, players[p], debugSet); 
	}
	
	public boolean buildRoad(int p, int v1, int v2){
		boolean build = buildRoadCheck(p, v1, v2);
		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		} else{
			players[p].buildRoad();
			longRoadChecker(p);
			graph.addRoadToGraph(v1, v2, players[p]);
			return true;
		}
		//longest road check
	}
	
	public boolean legalRoadCheck(int p, int v1, int v2){
		return graph.checkPlaceRoad(v1,v2, players[p], debugSet); 
	}
	
	public boolean round1RoadCheck(int v1, int v2, int p){
		return graph.checkPlaceRound1Road(v1,v2, players[p], debugSet); 
	}

	//used at beginning
	public boolean placeRoad(int p, int v1, int v2){
		boolean build = graph.checkPlaceRound1Road(v1,v2, players[p], debugSet); 
		if (build == false){
			System.out.println("You cannot build a road on this location.");
			return false;
		}

		else{
			players[p].placeRoad();
			longRoadChecker(p);
			System.out.println("Road placed successfully");
			graph.addRoadToGraph(v1,v2,players[p]);
			return true;
		}
	}
	
	public int[] getVerticesWithSettlements(int p){
		return players[p].getSettlementVertices();
	}

	public boolean buildDevCheck(int p ){
		boolean hasResources = players[p].buildDevCheck();
		if (hasResources == false){
			return false;
		}
		int i = devDeck.drawDevCard();
		if(i ==10){
			System.out.println("There are no development cards left.");
			return false;
		}
		return true;
	}
	
	public boolean buildDevCard(int p){
		boolean build = buildDevCheck(p);
		if (build == false){
			return false;
		} else {
			int i = devDeck.drawDevCard();
//			System.out.println("used dev resources");
			players[p].buildDev(i);
			return true;	
		}
	}

	//i is which dev card! 0 knight, 3 rb, 4 monopoly, 5 yop
	//this will return whether they can play that d card and then julia needs to handle the rest 
	public boolean useDevCard(int p, int i){
		boolean build;
		build = players[p].useDevCard(i);

		//for largest army
		if(i==0 && build){
			if(players[p].getArmySize()>=3 && players[p].checkLgArmy()==false){
				for(int m=0; m<players.length; m++){
					if(players[m].checkLgArmy() == true){
						if(players[p].getArmySize() > players[m].getArmySize()){
							players[p].changeLgArmy();
							players[m].changeLgArmy();
						}
						break;
					}
				}
			}
		}

		return build;

	}

	//this should be called after any instance of someone building a road (placeRoad and buildRoad)
	public void longRoadChecker(int p){
		if(graph.getRoadSize(players[p])>=5 && players[p].checkLongRoad()==false){
				for(int m=0; m<players.length; m++){
					if(players[m].checkLongRoad() == true){
						if(graph.getRoadSize(players[p]) > graph.getRoadSize(players[m])){
							players[p].changeLongRoad();
							players[m].changeLongRoad();
						}
						break;
					}
				}
		}
	}

	public void useMonopoly(int p, int r){
		//r is the resource we are monopolizing
		int total = 0;
		for(int i=0; i<players.length; i++){
			if(i!=p)
				total = total + players[p].getAllX(r);
		}
		players[p].addResource(r,total);
	}

	public void useYearOfPlenty(int p, int r1, int r2){
		players[p].addResource(r1, 1);
		players[p].addResource(r2, 1);
	}


	public boolean usePort(int p, int x, int r, int y){
		//p is the player, x is which port they want to use, r is what resource they want, y is what resource they are using
		boolean build = players[p].buildPortCheck(x,y);
		if (build == false)
			return false;
		else{
			if(x==0)
				players[p].looseResource(y,3);
			else
				players[p].looseResource(y,2);
			players[p].addResource(r,1);
			return true;
		}
	}

	public void trade(int[][] tradeStats){
		//tradeStats[0]= {type you want, amount, playerID to give}, tradeStats[1] = {type you'll give away, amount, playerID initiating trade}
		// if playerID to give is 0, the player initiating trade is trading with bank
		if (tradeStats[0][2]!=0){ //not trading with computer
			Player a = players[tradeStats[0][2]];
			System.out.println("Player a: "+a.getID() +"gaining: "+tradeStats[1][0]);
			a.printStats();
			//player a gives away resources and gains some
			a.looseResource(tradeStats[0][0], tradeStats[0][1]);
			a.addResource(tradeStats[1][0], tradeStats[1][1]);
		}
		Player b = players[tradeStats[1][2]];	
		b.printStats();
		//player b gives away resources and gains some
		System.out.println("Player b: "+b.getID()+ "gaining: "+tradeStats[0][0]);
		b.addResource(tradeStats[0][0], tradeStats[0][1]);
		b.looseResource(tradeStats[1][0], tradeStats[1][1]);
	}

	
}
