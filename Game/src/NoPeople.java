import java.util.Random;


public class NoPeople {
	private RunGame rg;
	private GameLogic gl;
	int[] possibleActions;
	Random generator;
	//will store int that corresponds to action type used in RunGame  
	//0= nothing, 1 = settlement, 2 = city, 3 = road,
	//4 = trade 4 to 1, 5 = trade other player, 6 = move robber, 7 = monopoly 8 = year of plenty
	//9 = road builder, 10 = knight
	int actionCount;
	int[] settlementVertices;
	int settlementVerticesCount;
	int[] cityVertices;
	int cityVerticesCount;
	int[][] roadVertices;
	int roadVerticesCount;
	boolean debug = true;
	boolean printActions = true;
	
	public NoPeople(RunGame r, GameLogic g){
		rg = r;
		gl = g;
		possibleActions = new int[10];
		generator =  new Random();
	}
	
	public void firstRoundPlaceSettlement(){
		boolean settlementPlaced = false;
		while (!settlementPlaced){
			settlementPlaced = 	rg.placeSettlementFirstRound(firstRoundSettlementChoice());
		}
	}
	
	public int firstRoundSettlementChoice(){
		Random generator =  new Random();
		int vertexToBuild = generator.nextInt(54);
		return vertexToBuild;
	}
	
	public void firstRoundRoad(int p){
		findLegalRound1Road(p);
		int randIndex = generator.nextInt(roadVerticesCount);
		int v1 = roadVertices[randIndex][0];
		int v2 =  roadVertices[randIndex][1];
		rg.placeRoadFirstRound(v1);
		rg.placeRoadFirstRound(v2);
		
	}
	
	private void findLegalRound1Road(int p){
		roadVertices = new int[54*54][2];
		roadVerticesCount = 0;
		for (int i = 0; i<54; i++){
			for (int j=0; j<54; j++){
				if (gl.round1RoadCheck(i,j,p)){
					roadVertices[roadVerticesCount][0] = i;
					roadVertices[roadVerticesCount][1] = j;
					roadVerticesCount ++;
				}
			}
		}
	}

	public void turn(int p){
		// check available actions
		checkPossible(p);
		//pick action randomly
		
		if (actionCount == 0){
			return;
		}
		
		int randIndex = generator.nextInt(actionCount);
		int actionToTake = possibleActions[randIndex];
		if (printActions){
			System.out.println("Taking action "+actionToTake);
		}
		
		switch (actionToTake) {
			case (1):
				rg.setActionType (1);
				randIndex = generator.nextInt(settlementVerticesCount);
				int vertexToBuild = settlementVertices[randIndex];
				rg.setVertex(vertexToBuild);
				break;
			case (2):
				rg.setActionType(2);
				int cityIndex = generator.nextInt(cityVerticesCount);
				int cityToBuild = cityVertices[cityIndex];
				rg.setVertex(cityToBuild);
				break;
			case (3):
				rg.setActionType(3);
				int roadIndex = generator.nextInt(roadVerticesCount);
				int v1 = roadVertices[roadIndex][0];
				int v2 =  roadVertices[roadIndex][1];
				rg.setVertex(v1);
				rg.setVertex(v2);
				break;
			case(11):
				rg.setActionType(11);
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
		if (cityPossible(p)){
			possibleActions[actionCount] = 2;
			actionCount ++;
		}
		if (devCardPossible(p)){
			possibleActions[actionCount] = 11;
			actionCount ++;
		}
		if (roadPossible(p)){
			possibleActions[actionCount] = 3;
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
	
	private boolean cityPossible(int p){
		cityVertices = new int[10];
		cityVerticesCount = 0;
		int[] settlements = gl.getVerticesWithSettlements(p);
		boolean toReturn = false;
		for (int i=0; i<settlements.length; i++){
			toReturn = gl.buildCityCheck(p, settlements[i]);
			if (toReturn){
				cityVertices[cityVerticesCount] = settlements[i];
				cityVerticesCount ++;
			}
		}
		return  toReturn;
	}
	
	private boolean devCardPossible(int p){
		boolean toReturn = gl.buildDevCheck(p);
			if (toReturn && debug ){
				System.out.println("Possible to buy a dev card for player "+p);
			}
		return toReturn;
	}
	
	private boolean roadPossible(int p){
		roadVertices = new int[54*54][2];
		roadVerticesCount = 0;
		for (int i = 0; i<54; i++){
			for (int j=0; j<54; j++){
				if (gl.buildRoadCheck(p,i,j)){
					roadVertices[roadVerticesCount][0] = i;
					roadVertices[roadVerticesCount][1] = j;
					roadVerticesCount ++;
				}
			}
		}
		if (roadVerticesCount > 0){
			return true;
		} else {
			return false;
		}
	}
	
	
}
