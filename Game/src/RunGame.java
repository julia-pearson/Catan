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
	private  boolean usingGraphics;
	TurnOrderManager order;
	private  GameLogic gl;
	private  FrontEndInterface fei;
	
	private  Player[] players;
	private  int playerCount;
	private  int currentPlayerID;
	
	private  int actionType; //0= nothing, 1 = settlement, 2 = city, 3 = road, 4 = trade 4 to 1, 5 = trade other player, 6 = move robber
	private  int[] verticesToAct; //at most 2 vertices
	private int vertexCounter;
	private boolean sevenRolled;
	
	public boolean inFirstRound;
	private boolean firstRoundSET;
	private int firstRoundRoadCounter;
	
	private int[][] tradeResources; //tradeResources[0]= {type you want, amount, playerID}, tradeResouces[1] = {type you'll give away, amount, playerID}
	
	public RunGame(int numPlayers, boolean useGraphics){
		players = new Player[numPlayers+1];
		
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);
		this.playerCount = numPlayers;
		order = new TurnOrderManager (numPlayers);
		currentPlayerID = order.getCurrentPlayer();
		verticesToAct = new int[2];
		vertexCounter = 0;
		tradeResources = new int[2][3];
		
		usingGraphics = useGraphics;
		//testboard gives a predetermined board
		//int[][] board= new Board().getBoard();
		int[][] testBoard = new Board().getTestBoard();
		//pass this to gl 
		gl = new GameLogic(testBoard, players);
;
	
		if (usingGraphics){
			//FEI will draw the graph
			fei = new FrontEndInterface (this, testBoard, numPlayers);
			fei.currentPlayerID = currentPlayerID;
			inFirstRound = true;
			firstRoundSET = true;
		} else{
			for (int i=0; i<4; i++){
				currentPlayerID = order.getNextPlayer();
				//turn();
			}
		//	startGame();
		}
	}
	/* void startGame(){
		System.out.println("Let's start playing!");
		for (int i=0; i<turnOrder.length; i++){
			currentPlayerID = turnOrder[i];
			Player currentPlayer = players[currentPlayerID];
			System.out.println("Player who will go is: " +currentPlayerID);
			firstRound(currentPlayer);
		} 
		for (int i=turnOrder.length-1; i>=0; i--){
			currentPlayerID = turnOrder[i];
			Player currentPlayer = players[currentPlayerID];
			System.out.println("Player who will go is: " +currentPlayerID);
			firstRound(currentPlayer);
		}
	}

	private void turn(){
		int r = roll();
		System.out.println("roll was "+r);
		if(r == 7){
			sevenRolled();
		} else {
			gl.diceRoll(r);
		}
		System.out.println("New turn for");
		players[currentPlayerID].printStats();
		actionType = 0;
		System.out.println("Enter your action Type");
		int actionType = sc.nextInt();
		if (actionType == 1){
			System.out.println("Where would you like to place your settlement?");
			vertexToAct = sc.nextInt();
			gl.placeSettlement(currentPlayerID, vertexToAct);
		} else if(actionType == 2){
			System.out.println("What are vertexes you would ilke to place your road between?");
			int v1= sc.nextInt();
			int v2 = sc.nextInt();
			//gl.buildRoad();
		}
	}
	*/
	
	private int roll(){
		//pick a random int between 1 and 6
		Random generator =  new Random();
		int roll = generator.nextInt(6);
		return roll+1;
	}
	
	public int[] rollDice(){
		if ( gameEnd() ){
			//System.exit(0);
			return new int[] {6,6};
		}
		currentPlayerID = order.getNextPlayer();
		fei.updateCurrentPlayer(currentPlayerID);
		int r1 = roll();
		int r2 = roll();
		if ((r1+r2) == 7){
			sevenRolled = true; 
		}
		gl.diceRoll(r1+r2);
		updateAllResources();
		return new int[] {r1,r2};
	}
	
	private void firstRound(Player p){
		System.out.println("Player "+ currentPlayerID + "'s turn");
		if (!usingGraphics){
			System.out.println("Where would you like to place your settlement?");
			int vertexNum = sc.nextInt();
			gl.placeSettlement(p, vertexNum);
			System.out.println("What are vertexes you would ilke to place your road between?");
			int v1= sc.nextInt();
			int v2 = sc.nextInt();
		} 
		//gl.buildRoad();
	}
	
	public void vertexClickedFirstRound( int vertex){
		if (firstRoundSET){
			//settlement building part of first round
			System.out.println("vertex: "+vertex+" clicked in first round. Trying to place settlement for player: "+currentPlayerID);
			if (gl.placeSettlement(players[currentPlayerID], vertex)){
				fei.drawSettlement(vertex);
				if(players[currentPlayerID].numberOfSettlements ==2){
					gl.giveResourcesStartGame(vertex);
					updateAllResources();
				}	
				if (!order.firstRoundSettlementDone()){
					currentPlayerID = order.getNextPlayerGameStart();// switch players
					fei.updateCurrentPlayer(currentPlayerID);
				} else {
					firstRoundSET = false;
					firstRoundRoadCounter = 0;
					System.out.println("Click vertexes for Roads");
				}
			}
		} else {
			//road placement part of Round 1
			System.out.println("vertex: "+vertex+" clicked in first round. Trying to place road for player: "+currentPlayerID);
			verticesToAct[vertexCounter] = vertex;
			vertexCounter ++;
			if (vertexCounter == 2){
				if (gl.placeRoad(currentPlayerID, verticesToAct[0], verticesToAct[1])){
					fei.drawRoad(verticesToAct[0], verticesToAct[1]);
					currentPlayerID = order.getNextPlayer();
					firstRoundRoadCounter ++;
					fei.updateCurrentPlayer(currentPlayerID); //switch players
				} else{
					System.out.println("Road placement didn't work, try again");
				}
				vertexCounter = 0;
				if (firstRoundRoadCounter == 2*playerCount){
					inFirstRound = false;
					System.out.println("Initial Settlement and Road Placement is done");
				}
			}
		}
	}
	
	public void setTileClicked(int t){
		if (actionType == 6 && sevenRolled) {
			robberAction(t, currentPlayerID);
		}
	}
	
	public void robberAction(int tile, int playerID){
		gl.moveRobber(tile, playerID);
		fei.updateRobberPosition(tile);
		updateAllResources();
	}
	
	public void setActionType (int t){
		actionType = t;
		if (actionType == 5){//player already clicked the trade button once
			actionType = 4; //now player wants to trade 4:1
		}
	}
	
	public void setVertex (int v){
		if (vertexCounter >= 2){
			System.out.println("You have clicked too many vertices. Clearning action and vertecies.");
			clearVerticesAndAction();
		}
		verticesToAct[vertexCounter] = v;
		vertexCounter ++;
		if (actionType == 1){
			tryToBuildSettlement();
		} else if (actionType == 2){
			tryToBuildCity();
		} else if (actionType == 3){
			tryToBuildRoad();
		}
		updateSinglePlayerResources();
		updateStats();
	}
	
	public void buyDevCard(){
		if (!gl.buildDevCard(currentPlayerID)){
			System.out.println("Not enough resources to buy dev. card");
		}
		updateStats();
	}
	
	private void tryToBuildSettlement(){
		int v = verticesToAct[0];
		//TEST:
		//players[currentPlayerID].giveSettlementResources();
		boolean success = 	gl.buildSettlement(currentPlayerID, v);
		if (success){
			fei.drawSettlement(v);
		}
		clearVerticesAndAction();
	}
	
	private void tryToBuildCity(){
		int v = verticesToAct[0];
		//TEST:
		//players[currentPlayerID].giveCityResources();
		boolean success = 	gl.buildCity(currentPlayerID, v);
		if (success){
			fei.drawCity(v);
		}
		clearVerticesAndAction();
	}
	
	private void tryToBuildRoad(){
		if (! (vertexCounter == 2)){
			return;
		} else {
			//TEST:
			//players[currentPlayerID].giveRoadResources();
			boolean success = gl.buildRoad(currentPlayerID, verticesToAct[0], verticesToAct[1]);
			if (success){
				fei.drawRoad(verticesToAct[0], verticesToAct[1]);
			}
			clearVerticesAndAction();
		}
	}
	
	public void playerClicked(int playerID){
		if (actionType == 4){
			actionType = 5;
			tradeResources[0][2] = playerID; //want to trade with the player clicked
			tradeResources[1][2] = currentPlayerID;
		}
		trade();
	}
	
	public void tradeResourceButton(){
		if (actionType == 4){ //clicked trade but did not click player
			//4 to one trade
			tradeResources[0][2] = 0;//trading with computer
			tradeResources[1][2] = currentPlayerID;
		}
		trade();
	}
	
	public void trade(){
		System.out.println("Calling trade in game logic ");
		//both elements have been filled in, pass to game logic 
		gl.trade(tradeResources);
		updateAllResources();
	}
	
	//fills the array with type of resources and quantity
	public void resourceClicked( int resourceType ) {
			if (tradeResources[0][0] == 0){
				//nothing has been asked for
				tradeResources[0][0] = resourceType;
				tradeResources[0][1] = 1;
			} else if (tradeResources[0][0] == resourceType) {
				tradeResources[0][1] ++;
			} 
			if (tradeResources[1][0] == 0 && tradeResources[0][0] != 0) {
				tradeResources[1][0] = resourceType; 
				tradeResources[1][1] = 1;
			} else if (tradeResources[1][0]== resourceType) {
				tradeResources[1][1]++;
			}
	}
	
	//dev card methods
	

	public void useMonopoly(){
		
		//logic
	}
	
	public void useYearOfPlenty(){
		//logic
	}

	public void useRoadBuilder(){
		//JULIA WILL HANDLE
	}
	
	public void useKnight(){
		//JULIA will handle
	}
	
	private void updateSinglePlayerResources(){
		int [] r = players[currentPlayerID].getResourceArray();
		fei.updateResources(currentPlayerID, r);
	}
	
	private void updateAllResources(){
		for (int i = 1; i< players.length; i++){
			int [] r = players[i].getResourceArray();
			fei.updateResources(i, r);
		}
	}
	
	private void updateStats(){
		for (int i = 1; i< players.length; i++){
			int [] r = players[i].getPlayerStats();
			fei.updateVP(i, r);
		}
	}
	
	public void clearVerticesAndAction(){
		verticesToAct[0] = 0;
		verticesToAct[1] = 0;
		vertexCounter = 0;
		actionType = 0;
	}
	
	private boolean gameEnd(){
		for (int i = 1; i< players.length; i++){
			int vp = players[i].victoryPoints;
			if (vp>= 3){
				System.out.println("GAME OVER. Winner is Player " + i);
				return true;
			}
		}
		return false;
	}
	
}

