/* JE
 * 
 */

public class Vertex {
	private Tile[] adjacentTiles; //the list of Tiles that this vertex touches
	private Player owner;
	private boolean	isOccupied; 
	private int 	settlementType; //1= settlement, 2= city
	private Edge[] edges;
	private int numEdgesSet;
	
	public Vertex(Tile[] t){
		adjacentTiles = t;
		edges = new Edge[3];
		numEdgesSet= 0;
		isOccupied = false;
	}
	
	public void addEdge(Edge e){
		edges[numEdgesSet] = e;
		numEdgesSet ++;
	}
	
	public void setOwner(Player p){
		owner = p;
		isOccupied = true;
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
		System.out.println("Done printing resources in vertex");
	}
}
