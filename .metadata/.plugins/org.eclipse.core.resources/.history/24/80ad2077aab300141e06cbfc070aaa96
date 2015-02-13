/* JE
 * 
 */

public class Vertex {
	private Tile[] adjacentTiles; //the list of Tiles that this vertex touches
	private Player owner;
	private boolean	isOccupied; 
	private int 	settlementType; //1= settlement, 2= city
	
	public Vertex(Tile[] t){
		adjacentTiles = t;
		isOccupied = false;
	}
	
	
	public void setOwner(Player p){
		owner = p;
		isOccupied = true;
	}
	
	public Tile[] getAdjacentTiles(){
		return adjacentTiles;
	}
	
	public void printResources(){
		for (int i=0; i<adjacentTiles.length; i++){
			adjacentTiles[i].printTile();
		}
		System.out.println("Done printing resources in vertex");
	}
}
