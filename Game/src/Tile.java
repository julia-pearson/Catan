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
	boolean isPort = false;
	int portType; //{0= 3 to 1, 1 = rock, 2 = wheat, 3 = brick,  4= wood, 5 = sheep}
	
	public Tile(int resourceNum, int rollNum){
		resource = resourceNum;
		roll = rollNum;
	}
	
	public Tile(int resourceNum, boolean port, int type){
		resource = resourceNum;
		roll = 0;
		isPort = port;
		portType = type;
	}
	
	public void printTile(){
		System.out.println(Resource.getType(resource)+"("+roll+")");
	}
	
	public String getString(){
		return Resource.getType(resource)+"("+roll+")";
	}
}
