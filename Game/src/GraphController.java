/*JE
 * The GraphController class will mantian the state of the graph as game play progresses.
 * Actions like: build settlement, build road, and build city will all be validated and carried out in this class.  
 */
public class GraphController {
	static Vertex[] vertices; //pointers to the vertex objects in graph (connected by edge objects)
	
	public GraphController (Vertex[] v){
		vertices = v;
	}
	
	/*check if it is legal to build a settlement here (ie- no other settlements at a neighbor vertex 
	 * and no settlement already at this vertex)
	 * If so, place settlement and return true
	 * if not, make no change to the graph and return false
	 */
	public boolean placeSettlement(int v, Player p){
		Vertex vert = vertices[v];
		if (vert.getSettlementType() != 0) { 
			//ensure vertex is empty
			return false;
		} else {
			//check that position is legal
			Edge[] es = vert.getEdges();
			for (int i=0; i< es.length; i++){
				if (es[i].v1.getSettlementType() != 0  || es[i].v2.getSettlementType() != 0){
					//there is another settlement one edge away(neighbor vertex)
					return false;
				}
			}
			//position is legal, so build settlement
			vert.buildSettlement(p);
			return true;
		}
	}
	
	/*
	 * will first check that the player has roads on an edge leading to this settlement,
	 * then will call placeSettlement
	 */
	public boolean buildSettlement(int v, Player p){
		if (connectedRoads(v,p)){
			return placeSettlement(v,p);
		} else{
			return false;
		}
	}
	
	/*check if it is legal to build a city here (ie- Player p already has a settlement at this vertex)
	 * If so, place city and return true
	 * if not, make no change to the graph and return false
	 */
	public boolean buildCity(int v, Player p){
		Vertex settlement = vertices[v];
		if (settlement.getSettlementType() == 1 && settlement.getOwner() == p){
			settlement.buildCity();
			return true;
		} else{
			return false;
		}
	}
	
	/*check if it is legal to place a road between given vertices
	 * If so, place road and return true
	 * if not, make no change to the graph and return false	
	*/
	public boolean placeRoad(int a, int b, Player p){
		//find edge object that links v1 and v2, then check if edge is free
		Edge toConsider = null;
		Edge[] e1 = vertices[a].getEdges();
		for (int i = 0; i<e1.length; i++ ){
			//must find the edge object that has either combination of {v1,v2} = {a,b} or {b,a}
			if (e1[i].v1 == vertices[a] && e1[i].v2 == vertices[b] || 
					e1[i].v1 == vertices[b] && e1[i].v2 == vertices[a]){
				toConsider = e1[i];
			}
		}
		if (toConsider == null){
			//there is no edge that connects those two vertices, invalid input
			return false;
		} else {
			 if (toConsider.hasRoad){
				 //there is already a road here!
				 return false;
			 } else {
				 toConsider.buildRoad(p);
				 return true;
			 }
		}
	}
	
	/*
	 * will check that player has road connecting to either v1 or v2, then will call placeRoad
	 *TODO: incorporate longest road
	 */
	public boolean buildRoad(int v1, int v2, Player p){
		if (connectedRoads(v1,p) || connectedRoads(v2,p)){
			return placeRoad(v1,v2,p);
		} else{
			return false;
		}
	}
	
	
	/*
	 * Method will check that player p has at least one road leading to vertex v
	 */
	private boolean connectedRoads(int v, Player p){
		Edge[] edges = vertices[v].getEdges();
			for (int i = 0; i<edges.length; i++){
				if (edges[i].owner == p ){
					return true;
				}
			}
		return false;
	}
	
	
	/*
	 * Go through vertices and determine which players should receive resources for the given roll. 
	 * Allocate those resources using methods in the player class.
	 */
	public void distributeResources(int roll){
		boolean debug = true;
		for (int i =0; i<vertices.length; i++){
			Vertex v = vertices[i];
			if (v.getSettlementType() != 0){
				// this vertex contains either a settlement or a city
				Tile[] tiles = v.getAdjacentTiles();
				Player owner = v.getOwner();
				for (int j = 0; j<tiles.length; j++){
					if (tiles[j].roll == roll && !tiles[j].hasRobber){
						if (v.getSettlementType()==1){
							if (debug) {
								System.out.println("resource of type: "+Resource.getType(tiles[j].resource)+
										" given on roll"+ roll+ "to owner");
							}
							//owner.addResource(tiles[j].resource);
						} else if (v.getSettlementType() == 2){
							if (debug) {
								System.out.println("2 resources of type: "+Resource.getType(tiles[j].resource)+
										" given on roll"+ roll+ "to owner");
							}
							//owner.addResource(tiles[j].resource);
							//owner.addResource(tiles[j].resource);
						}
						
					
					}
				}
			}
			
		}
	}
}
