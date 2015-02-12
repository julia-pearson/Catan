//CJ

//CJ do what you did with ports with other things!

public class Player {

	private int playerID;
	private int [] dCards;
	private int victoryPoints;
	private boolean largestArmy;
	private boolean longestRoad;
	
	private Ports portTracker;

	private ResourceCards resourceTracker;


	//settlements and cities and roads!

	Player(int id){
		playerID = id;
		victoryPoints = 0;

		largestArmy = false;
		longestRoad = false;

		resourceTracker = new ResourceCards();

		//each index stores the number of dcards the player holds of the type of dcard the index corresponds to (there are 5 types)
		dCards = new int {0,0,0,0,0};

		portTracker = new Ports();

	}

	//Port handling!

	//if someone builds on a port, add port

	//if someone tries to use the port, check to make sure they have it



}
