/*JE */

public class GamePlayer {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. control user input
	4. update tables/board when user gives input 
	*/
	
	Player[] players;
	
	
	public GamePlayer(int numPlayers){
		
	}
	
	public static void main (String[] args){
		
		//create stats table for players
		//create Player classes for each player and store in players[]
	//	int[][] board= new Board().getBoard();
		
		int[][] testBoard = new Board().getTestBoard();
		
		new GraphMaker(testBoard);
		//Visualizer GUI = new Visualizer(board);
		//add event listeners to gUI and figure out how GamePlayer will capture the click events 

	}
	
	public void buildSettlement(Player p, int VertexNumber){
		//check that the player has resource to build
		//check that it is a legal move
	}

	public void buildCity(Player p, int VertexNumber){
		//check that the player has resource to build
		//check that it is a legal move
	}
	
	public void buildRoad(Player p, int roadNum){
		//check that the player has resource to build
		//check that it is a legal move
	}
	
	private static void playGame(){
		//method that will enable users to roll dice and interact
	}
}
