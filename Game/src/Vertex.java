/* JE
 * The vertex object reflects each intersection of tiles on the catan board. 
 * Each vertex links to three tiles (either a standard resource or a port tile)
 * The vertex may be occupied with a settlement or a city 
 */

public class Vertex {
	private Tile[] adjacentTiles; //the list of Tiles that this vertex touches
	private Player owner;
	private int 	settlementType; //0= empty, 1= settlement, 2= city
	private Edge[] edges;
	private int numEdgesSet;
	
	public Vertex(Tile[] t){
		adjacentTiles = t;
		edges = new Edge[3];
		numEdgesSet= 0;
		settlementType = 0;
	}
	
	public void addEdge(Edge e){
		edges[numEdgesSet] = e;
		numEdgesSet ++;
	}
	
	public Tile[] getAdjacentTiles(){
		return adjacentTiles;
	}
	
	public Edge[] getEdges(){
		return edges;
	}
	
	public void printResources(){
		for (int i=0; i<adjacentTiles.length; i++){
			adjacentTiles[i].printTile();
		}
	}
	
	public void setOwner(Player p){
		owner = p;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public void buildSettlement(Player p){
		settlementType = 1;
		setOwner(p);
	}
	
	public void buildCity(){
		if (settlementType !=1 ){
			System.out.println("Error, trying to build city w/o settlement");
			return;
		}
		settlementType = 2;
	}
	
	public int getSettlementType() {
		return settlementType;
	}
}
