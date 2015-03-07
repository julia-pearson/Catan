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
	


	public void settyClicked (){
		rg.setActionType(1);
		System.out.println("Setty Clicked in FRONT END INTERFACE");
	}
	
	public void cityClicked (){
		rg.setActionType(2);
	}
	
	public void vertexClicked (int v){
		rg.setVertex(v);
		System.out.println("Vertex "+v+" Clicked in FRONT END INTERFACE");
		drawSettlement(v);
	}

	public int[] diceClicked (){
		System.out.println("Dice Clicked in FRONT END INTERFACE");
	//	int r1 = rg.roll();
	//	int r2 = rg.roll();
		int[] toPass = new int[]{3,3};
		currentPlayerID = 3;
		return toPass;
	}

	public void drawSettlement(int v){
		h.buildSettlement(v);
	}

	public void nullClick(){
	//reset all action values and vertex etc
	}

	/*
	public static void main(String [] args){
		new FrontEndInterface();
	}
	*/
	
}
