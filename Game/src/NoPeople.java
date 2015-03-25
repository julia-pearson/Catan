import java.util.Random;


public class NoPeople {
	private RunGame rg;
	private GameLogic gl;
	int[] possibleActions;
	//will store int that corresponds to action type used in RunGame  
	//0= nothing, 1 = settlement, 2 = city, 3 = road,
	//4 = trade 4 to 1, 5 = trade other player, 6 = move robber, 7 = monopoly 8 = year of plenty
	//9 = road builder, 10 = knight
	int actionCount;
	int[] settlementVertices;
	int settlementVerticesCount = 0;
	
	boolean debug = true;
	
	public NoPeople(RunGame r, GameLogic g){
		rg = r;
		gl = g;
		possibleActions = new int[10];
	}
	
	public void doFirstRound(){
		boolean settlementPlaced = false;
		while (!settlementPlaced){
			settlementPlaced = 	rg.placeSettlementFirstRound(firstRoundChoice());
		}
	}
	
	public int firstRoundChoice(){
		Random generator =  new Random();
		int vertexToBuild = generator.nextInt(54);
		return vertexToBuild;
	}
	
	/*
	public void startGame(){
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
	}*/

	public void turn(int p){
		// check available actions
		checkPossible(p);
		//pick action randomly
		
		if (actionCount == 0){
			return;
		}
		Random generator =  new Random();
		int randIndex = generator.nextInt(actionCount);
		int actionToTake = possibleActions[randIndex];
		
		switch (actionToTake) {
			case (1):
				rg.setActionType (1);
				randIndex = generator.nextInt(settlementVerticesCount);
				int vertexToBuild = settlementVertices[randIndex];
				rg.setVertex(vertexToBuild);
				break;
		}
				
			
			
			
				
	//	int actionType = pickAction();

		//complete action with gl
		//use runGame to update game state
	}
	
	//populate the array possibleActions with list of the moves you can make
	private void checkPossible(int p){
		actionCount = 0;
		if (settlementPossible(p)){
			possibleActions[actionCount] = 1;
			actionCount ++;
		}
		if (devCardPossible(p)){
			possibleActions[actionCount] = 11;
			actionCount ++;
		}
	}
	
	//check if there is any vertex that you can build a settlement on
	private boolean settlementPossible(int p){
		settlementVertices = new int[54];
		settlementVerticesCount = 0;
		boolean toReturn = false;
		for (int i = 0; i<settlementVertices.length; i++){
			if (gl.buildSetCheck(p, i)){
				settlementVertices[settlementVerticesCount] = i;
				toReturn = true;
				if (debug){
					System.out.println("Possible to build a settlment on vertex: "+i+ " for player "+p);
				}
			}
		}
		return toReturn;
	}
	
	private boolean devCardPossible(int p){
		boolean toReturn = gl.buildDevCheck(p);
			if (toReturn && debug ){
				System.out.println("Possible to buy a dev card for player "+p);
			}
		return toReturn;
	}
	
	
}
