//class that represents the connection between verticies
public class Edge {

	Vertex 	v1;
	Vertex v2;
	boolean hasRoad;
	Player 	owner; //person who owns the road 

	public Edge(Vertex start, Vertex end){
		v1= start;
		v2 = end;
	}
	
	public void buildRoad(Player builder){
		if (hasRoad) {
			System.out.println("ERROR, this edge alread has a road!");
		} else {
			hasRoad = true;
			owner = builder;
		}
	}
}
