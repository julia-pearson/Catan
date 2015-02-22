/*JE */

public class GamePlayer {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. control user input
	4. update tables/board when user gives input 
	*/
	
	static Player[] players;
	static GraphController graph;
	
	
	public GamePlayer(int numPlayers){
		//initialize and fill the Player[]
	}
	
	public static void main (String[] args){
		
		//create stats table for players
		//create Player classes for each player and store in players[]
	//	int[][] board= new Board().getBoard();
		
		int[][] testBoard = new Board().getTestBoard();
		
		GraphMaker gm = new GraphMaker(testBoard);
		graph = new GraphController(gm.getVertexArray());
		testGraphFeatures();
		
		//Visualizer GUI = new Visualizer(board);
		//add event listeners to gUI and figure out how GamePlayer will capture the click events 

	}
	
	private static void testGraphFeatures(){
		System.out.println("Testing Graph Features");
		Player test = new Player(0);
		Player testb = new Player(1);
		
		System.out.println("Testing Place Settlement");
		int i = 30;
		System.out.println("Place settlement at "+i+graph.placeSettlement(i, test));
		i = 19;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test));
		i = 18;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test) );
	
		System.out.println("Testing Build City");
		i = 17;
		System.out.println("Place settlement at "+i+ graph.placeSettlement(i, test) );
		System.out.println("Build city for testb at "+i+ graph.buildCity(i, test) );
		
		System.out.println("Testing Resource Distribution:");
		int roll = 4;
		graph.distributeResources(roll);
		
		System.out.println("Testing Road Placement:");
		int v1 = 17;
		int v2 = 18;
		System.out.println("Place road for test between ("+v1+","+v2+") "+ graph.placeRoad(v1,v2, testb) );
			
		
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
