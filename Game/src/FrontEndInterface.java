/*
 * This is the class that we will use to communicate with the GUI and interpret requests from players
 * 
 * Game flow will be
 * 1. User makes an action
 * 2. FrontEndInterface gets request
 * 3. FEI sends request to GamePlayer
 * 4. GamePlayer gives result to FEI, which will call redraw on GUI
 */
public class FrontEndInterface {
	private RunGame rg;
	private Hexanew h;
	public int currentPlayerID;
	
	public FrontEndInterface (RunGame r, int[][] board, int n, int[] ports, boolean mouseListener){
		rg = r;
		//must convert board (4X19) to the correct shape for hexanew (19x2)
		
		int[][] newBoard = new int[19][2];
		for (int i = 0; i<newBoard.length; i++){
			newBoard[i][0]= board[0][i];
			newBoard[i][1]= board[1][i];
		}
		h = new Hexanew( this, newBoard, n, mouseListener);
		h.addPorts(ports);
	}
	
	public void updateCurrentPlayer (int cp){
		currentPlayerID = cp;
		h.updateBoard();
	}
	
	public void tradeClicked(){
		rg.setActionType(4);
	}
	
	public void playerClicked(int playerID){
		rg.playerClicked(playerID);
	}
	
	public void resourceClicked (int resourceNum){
		System.out.println("resource clicked "+resourceNum);
		rg.resourceClicked(resourceNum);
	}

	public void settyClicked (){
		rg.setActionType(1);
		System.out.println("Action: Build Settlement. Please click the vertex you want to build on");
	}
	
	public void cityClicked (){
		rg.setActionType(2);
		System.out.println("Action: Build City. Please click the vertex you want to build on");
	}
	
	public void roadClicked (){
		rg.setActionType(3);
		System.out.println("Action: Build Road. Please click the 2 vertexes you want to connect");
	}
	
	public void vertexClicked (int v){
		if (rg.inFirstRound){
			rg.vertexClickedFirstRound(v);
		} else{
			rg.setVertex(v);
		}
	}

	public int[] diceClicked (){
		return rg.rollDice();
	}
	
	public void devCardClicked(){
		rg.setActionType(11);
	}
	
	public void monopolyClicked(){
		rg.setActionType(7);
	}
	
	public void yearOfPlentyClicked(){
		rg.setActionType(8);
	}
	
	public void knightClicked(){
		rg.setActionType(10);
	}
	
	public void roadBuilderClicked(){
		rg.setActionType(9);
	}
	
	public void robberClicked(){
		rg.setActionType(6);
	}
	
	public void tileClicked(int t){
		rg.setTileClicked(t);
	}

	public void drawSettlement(int v){
		h.addSettlement(v);
	}
	
	public void drawRoad (int v1, int v2){
		h.addRoad(v1,v2, currentPlayerID);
	}
	
	public void drawCity(int v){
		h.addCity(v);
	}
	
	public void updateRobberPosition (int t){
		h.addRobber(t);
	}
	
	public void updateVP (int playerID, int[] stats){
		h.addStatistics(stats, playerID);
	}
	
	public void updateResources(int playerID, int[] resources){
		h.addStatistics(resources, playerID);
	}

	public void nullClick(){
		rg.clearVerticesAndAction();
	}
}
