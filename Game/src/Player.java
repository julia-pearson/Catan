//CJ

public class Player {
	private int playerID;
	private int victoryPoints;
	private boolean largestArmy;
	private boolean longestRoad;

	private int numberOfSettlements; //max 5
	private int numberOfCities; //max 4
	private int numberOfRoads; //max 15
	
	private Ports portTracker;

	private ResourceCards resourceTracker;
	private DevCards dCardTracker;
	//settlements and cities and roads are pointed to from the graph


	public Player(int id){
		playerID = id;
		victoryPoints = 0;

		numberOfSettlements=0;
		numberOfRoads=0;
		numberOfCities=0;

		resourceTracker = new ResourceCards();

		dCardTracker = new DevCards();

		portTracker = new Ports();
	}

	public void addResource(int i){
		if(i==1)
			resourceTracker.addRock(1);
		if(i==2)
			resourceTracker.addWheat(1);
		if(i==3)
			resourceTracker.addBrick(1);
		if(i==4)
			resourceTracker.addWood(1);
		if(i==5)
			resourceTracker.addSheep(1);
	}

	public boolean buildSetCheck(){
		if(resourceTracker.getSheep()<1 || resourceTracker.getWheat()<1 || resourceTracker.getWood()<1 || resourceTracker.getBrick()<1){
			System.out.println("You do not have enough resources to build a settlement.");
			return false;
		}
		if(numberOfSettlements==5){
			System.out.println("You have already built the maximum number of settlements.");
			return false;
		}
		return true;
	}

	public void placeSettlement(){
		numberOfSettlements ++;
		victoryPoints++;
	}
	
	public void buildSettlement(){
		resourceTracker.useSheep(1);
		resourceTracker.useWheat(1);
		resourceTracker.useWood(1);
		resourceTracker.useBrick(1);
		placeSettlement();

		//add port if we get one

	}

	public boolean buildDevCheck(){
		if(resourceTracker.getSheep()<1 || resourceTracker.getWheat()<1 || resourceTracker.getRock()<1){
			System.out.println("You do not have enough resources to build a development card.");
			return false;
		}
		return true;
	}

	public boolean buildDev(int i){
		resourceTracker.useSheep(1);
		resourceTracker.useRock(1);
		resourceTracker.useWheat(1);
		return true;
	}

	public void sevenRoll(){
		int total = resourceTracker.getNumber();
		if (total>7){
			for (int i=0; i<(total/2); i++)
				resourceTracker.randomDelete();
		}
	}

	public boolean buildCityCheck(){
		if(resourceTracker.getWheat()<2 || resourceTracker.getRock()<3){
			System.out.println("You do not have enough resources to build a city.");
			return false;
		}
		if(numberOfSettlements==4){
			System.out.println("You have already built the maximum number of cities.");
			return false;
		}
		return true;
	}

	public void buildCity(){
		resourceTracker.useRock(3);
		resourceTracker.useWheat(2);
		numberOfCities++;
		victoryPoints++;
	}

	public boolean buildRoadCheck(){
		if(resourceTracker.getBrick()<1 || resourceTracker.getWood()<1){
			System.out.println("You do not have enough resources to build a road.");
			return false;
		}
		if(numberOfRoads==15){
			System.out.println("You have already built the maximum number of roads.");
			return false;
		}
		return true;
	}
	
	public void placeRoad(){
		numberOfRoads++;
	}
	
	public void buildRoad(){
		resourceTracker.useBrick(1);
		resourceTracker.useWood(1);
		placeRoad();
	}
	
	public int getID(){
		return playerID;
	}
	
	public void printStats(){
		System.out.println("Stats for player: "+playerID);
		System.out.println("Number of Settlements: " +numberOfSettlements);
		System.out.println("Number of Cities: " +numberOfCities);
		System.out.println("Number of Roads: " +numberOfRoads);
		System.out.println("Victory Points: " +victoryPoints);
	}

}
