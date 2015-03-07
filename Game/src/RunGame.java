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
	private  GameLogic gl;
	private  FrontEndInterface fei;
	
	
	private  Player[] players;
	private  int playerCount;
	private  int currentPlayerID;
	private  int[] turnOrder; //turn order contains the ids of the players turnOrder[0] = the id of the first player; 
	//turnOrder[4] = the id of the last;
	private  int turnCounter;
	private  int actionType; //0= nothing, 1 = settlement, 2 = road, 3 = city
	private  int vertexToAct;
	
	//RUN WITH NUMBER OF PLAYERS AS COMMAND LINE ARGUMENT

	
	public RunGame(int numPlayers, boolean useGraphics){
		players = new Player[numPlayers+1];
		turnOrder = new int[numPlayers];
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);
		this.playerCount = numPlayers;
		usingGraphics = useGraphics;
		
		//testboard gives a predetermined board
		//int[][] board= new Board().getBoard();
		int[][] testBoard = new Board().getTestBoard();
		//pass this to gl 
		gl = new GameLogic(testBoard);
		determineTurnOrder();
	
		if (usingGraphics){
			//FEI will draw the graph
			fei = new FrontEndInterface (this, testBoard);
			fei.currentPlayerID = currentPlayerID;
		} else{
			for (int i=0; i<4; i++){
				currentPlayerID = turnOrder[turnCounter];
				turn();
				nextPlayer();
			}
		}
		startGame();
	}
	
	private void startGame(){
		System.out.println("Let's start playing!");
		determineTurnOrder();
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
	
	public int roll(){
		//pick a random int between 1 and 6
		Random generator =  new Random();
		int roll = generator.nextInt(6);
		return roll+1;
	}
	
	public void determineTurnOrder(){
		boolean debugTurnOrder = false;
		Random generator =  new Random();
		int first = generator.nextInt(playerCount);
		int firstPlayerID = first+1;
		turnOrder [0] = firstPlayerID;
		int nextPlayer = firstPlayerID;
		for (int i = 1; i< playerCount ; i++) {
			if (nextPlayer == playerCount){
				nextPlayer = 1;
				turnOrder[i] = nextPlayer;
			} else {
				nextPlayer = nextPlayer +1;
				turnOrder[i] = nextPlayer;
			}
		}
		if (debugTurnOrder){
			System.out.println("Turn order is:");
			for (int i=0; i<turnOrder.length; i++){
				System.out.println(" Player "+turnOrder[i]);
			}
		}
	}
	
	private void nextPlayer(){
		if(turnCounter == turnOrder.length-1){
			turnCounter = 0;
		} else {
			turnCounter ++;
		}
	}
	
	private void firstRound(Player p){
		System.out.println("Player "+ currentPlayerID + "'s turn");
		System.out.println("Where would you like to place your settlement?");
		int vertexNum = sc.nextInt();
		gl.placeSettlement(p.getID(),vertexNum);
		System.out.println("What are vertexes you would ilke to place your road between?");
		int v1= sc.nextInt();
		int v2 = sc.nextInt();
		//gl.buildRoad();
	}
	
	private void sevenRolled(){
		for(int i=1; i<=players.length; i++)
			players[i].sevenRoll();
			//initiate robber movement stealing sequence (same as for knight)
	}
	
	public void setActionType (int t){
		actionType = t;
	}
	
	public void setVertex (int v){
		vertexToAct = v;
	}
}
