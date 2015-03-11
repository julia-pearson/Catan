/*JE
 * Class that will take the int[][] board (the resource tiles)and create a graph containing 
 * vertices and edges according to a fixed order.
 */
public class GraphMaker {
	 int[][] board;
	 Tile[] tilesInBoard;
	 Vertex[] vertices;
	
	//each list in adjacentSpots contains the index within tilesInBoard that a given vertex lies between
	static int[][] adjacentSpots = {
			{ 0 },//0
			{ 0 },
		  { 0, 1 },
		  { 1 },
		  { 1,2},
		  {2},
		  {2},//6
		  {3},
		  {3,0},
		  {3,0,4},
		  {0,4,1},//10
		  {1,4,5},
		  {1,5,2},
		  {5,2,6},
		  {2,6},
		  {6},//15
		  {7},
		  {7,3},
		  {7,3,8},
		  {3,8,4},
		  {8,4,9},//20
		  {4,9,5},
		  {9,5,10},
		  {5,10,6},
		  {10,6,11},
		  {6,11},//25
		  {11},//26
		  {7},
		  {7,12}, 
		  {7,12,8},
		  {12,8,13}, //30
		  {8,13,9},
		  {13,9,14},
		  {9,14,10}, 
		  {14,10,15},
		  {10,15,11},//35
		  {15,11},
		  {11},//37
		  {12},
		  {12,16},
		  {12,16,13},//40
		  {16,13,17},
		  {13,17,14},
		  {17,14,18}, 
		  {14,18,15},
		  {15,18},
		  {15},//46
		  {16},
		  {16},
		  {16,17},
		  {17},//50
		  {17,18},
		  {18},
		  {18}, //53
		};
	
	static int[][] VertexPorts = { // index 1 contains list of vertices that touch this port
		{7,8},
		{2,3},
		{5,6},
		{15,25},
		{36,46},
		{53,52},
		{50,49},
		{39,38},
		{27,16},
	};
	
	
	public GraphMaker(int[][] boardFromGamePlayer){
		board = boardFromGamePlayer;
		vertices = new Vertex[adjacentSpots.length];
		convertBoardToTiles();
		createVertices();
		addEdges();
		addPorts();
	}
	
	//method will decode the int[][]board into a single Tiles[]
	private void convertBoardToTiles(){
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
	private void createVertices(){
		boolean debugCG = false;		
		
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
	
	//create the edge objects and link the vertices with correct edges
	private  void addEdges(){
		boolean debugE = false;

		for (int i=0; i<vertices.length; i++){
			if (debugE){
				System.out.println("Starting loop:" +i);
			}
			//first create the links between each vertex and v+1
			if (i != 6 && i !=15 && i!=26 && i !=37 && i!= 45 && i!=53){
				Edge horiz= new Edge(vertices[i],vertices[i+1]);
				vertices[i].addEdge(horiz);
				vertices[i+1].addEdge(horiz);
				if (debugE){
					System.out.println("Added edge between");
					horiz.v1.printResources() ;
					System.out.println("and ");
					horiz.v2.printResources();
				}
			}
			//next create the link between vertex and the vertex beneath it
			//(v+8 (first row), v+10 (second row), etc)
			int verticalIncriment=0;
			if (i<7 && i%2==0){
				verticalIncriment = 8;
			} else if (i>=7 && i<16 && i%2==1){
				verticalIncriment = 10;
			} else if (i>=16 && i<27 && i%2==0){
				verticalIncriment = 11;
			} else if (i>=27 && i<38 && i%2==0){
				verticalIncriment = 10;
			} else if (i>=38 && i<46 && i%2==1){
				verticalIncriment = 8;
			}
			if (verticalIncriment>0){
				if (debugE){
					System.out.println("Vertical incriment: "+verticalIncriment);
				}
				Edge vert= new Edge(vertices[i],vertices[i+verticalIncriment]);
				vert.v1.addEdge(vert);
				vert.v2.addEdge(vert);
				if (debugE){
					System.out.println("Added edge between");
					vert.v1.printResources() ;
					System.out.println("and ");
					vert.v2.printResources();
				}
			}
			
		}
		
	}
	
	private void addPorts(){
		for (int i=0; i<VertexPorts.length; i++){
			for (int j=0; j<2; j++){
				int vertexIndex = VertexPorts[i][j];
				int portType = board[2][i];
				Tile toAdd = new Tile(0, true, portType); //TODO, create a unique port class!
				vertices[vertexIndex].addPort(toAdd);
			}
		}
	}
	
	public Vertex[] getVertexArray(){
		return vertices;
	}
}
