/*JE
 * This class refers to a specific tile on the board and contains information about the tile's:
 *  resource type 
 *  roll
 *  robber status
 */
public class Tile {
	int resource;
	int roll;
	boolean hasRobber = false;
	
	public Tile(int resourceNum, int rollNum){
		resource = resourceNum;
		roll = rollNum;
	}
	
	public void printTile(){
		System.out.println(Resource.getType(resource)+"("+roll+")");
	}
	
	public String getString(){
		return Resource.getType(resource)+"("+roll+")";
	}
}
