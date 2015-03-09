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
	
	public FrontEndInterface (RunGame r, int[][] board){
		rg = r;
		//must convert board (4X19) to the correct shape for hexanew (19x2)
		
		int[][] newBoard = new int[19][2];
		for (int i = 0; i<newBoard.length; i++){
			newBoard[i][0]= board[0][i];
			newBoard[i][1]= board[1][i];
		}
	
		h = new Hexanew( this, newBoard);
	}
	
	public FrontEndInterface (){
		//h = new Hexanew( this);
		currentPlayerID = 1;
	}
	
	public void updateCurrentPlayer (int cp){
		currentPlayerID = cp;
		h.updateBoard();
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

	public void drawSettlement(int v){
		h.buildSettlement(v);
	}
	
	public void drawRoad (int v1, int v2){
		System.out.println("Draw Road method in FEI called");
		h.drawRoad(v1,v2, currentPlayerID);
		h.repaint();
	}
	
	public void drawCity(int v){
	//	h.buildCity(v);
		System.out.println("Tried to draw city on vertex: "+v);
	}

	public void nullClick(){
		rg.clearVerticesAndAction();
	}
}
