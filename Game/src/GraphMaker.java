/*JE
 * Class that will take the int[][] board (the resource tiles)and create a graph containing vertices and edges according to a fixed order.
 * 
 */
public class GraphMaker {
	static int[][] board;
	static Tile[] tilesInBoard;
	static Vertex[] vertices;
	
	//each list in adjacentSpots contains the index within tilesInBoard that a given vertex lies between
	static int[][] adjacentSpots = {
			{ 0 },//1
			{ 0 },
		  { 0, 1 },
		  { 1 },
		  { 1,2},//5
		  {2},
		  {2},
		  {3},//8
		  {3,0},
		  {3,0,4},//10
		  {0,4,1},
		  {1,4,0},
		  {4,1,5},
		  {1,5,2},
		  {5,2,6},
		  {2,6},//15
		  {6},
		  {7},
		  {7,3},
		  {7,3,8},
		  {3,8,4},//20
		  {8,4,9},
		  {4,9,5},
		  {9,5,10},
		  {5,10,6},
		  {10,6,11},//25
		  {6,11},
		  {11},
		  {7},
		  {7,12}, //30
		  {7,12,8},
		  {12,8,13},
		  {8,13,9},
		  {13,9,14},
		  {9,14,10}, //35
		  {14,10,15},
		  {10,15,11},
		  {15,11},
		  {11},
		  {12},//40
		  {12,16},
		  {12,16,13},
		  {13,17,14},
		  {17,14,18}, //45
		  {14,15,18},
		  {15},
		  {16},
		  {16},//50
		  {16,17},
		  {17},
		  {17,18},
		  {18},
		  {18},
		};
	
	
	public GraphMaker(int[][] boardFromGamePlayer){
		board = boardFromGamePlayer;
		vertices = new Vertex[adjacentSpots.length];
		convertBoardToTiles();
		createVertices();
	}
	
	//method will decode the int[][]board into a single Tiles[]
	private static void convertBoardToTiles(){
		boolean debugCB = false;
		
		tilesInBoard = new Tile[board[0].length];
		//board[0][i]= the resource type of tile i
		//board[1][i] = the probability of tile i
		for (int i=0; i<board[0].length; i++){
			Tile newTile = new Tile(board[0][i], board[1][i]);
			if (debugCB){
				System.out.println("Created tile "+i);
				newTile.printTile();
			}
			tilesInBoard[i] = newTile;
		}
	}
	
	
	//Go through the board and create vertices 
	private static void createVertices(){
		boolean debugCG = true;		
		//adjacentTiles[0] = the resource type of tile x, adjacentTiles[1]= roll #
		
		//the first vertex will be on the upper left tile and we are assuming no port (only one resource)

	
		for (int i= 0; i < adjacentSpots.length; i++){
			//looking at first vertex
			int[] indices = adjacentSpots[i];
			//indices of the tiles, now create tiles[]
			Tile[] adjTiles = new Tile[indices.length];
			for (int j=0; j<indices.length; j++){
				adjTiles[j] = tilesInBoard[indices[j]];
			}
			Vertex toAdd = new Vertex(adjTiles);
			if (debugCG){
				System.out.println("Created vertex "+i);
				System.out.println("Vertex lies between Tiles:");
				toAdd.printResources();
			}
			//put toAdd in graph
			vertices[i] = toAdd;
		}
		
	}
	
	private static void addEdges(){
		//create the edge objects and link the 
	}
}