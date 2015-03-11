import java.util.Random;


public class TurnOrderManager {

	private 	int playerCount;
	private  int[] turnOrder; //turn order contains the ids of the players turnOrder[0] = the id of the first player; 
	//turnOrder[4] = the id of the last;
	private  int turnCounter;
	private int firstRoundCount; //how many players have placed first settlements
	
	public TurnOrderManager(int numPlayers){
		playerCount = numPlayers;
		turnOrder = new int[numPlayers];
		determineTurnOrder();
		turnCounter = 0;
		firstRoundCount = 0;
	}
	
	public void determineTurnOrder(){
		boolean debugTurnOrder = false;
		Random generator =  new Random();
		int first = generator.nextInt(playerCount);
		int firstPlayerID = first+1;
		turnOrder [0] = firstPlayerID;
		int nextPlayer = firstPlayerID;
		for (int i = 1; i< playerCount ; i++) {
			if (nextPlayer == playerCount){
				nextPlayer = 1;
				turnOrder[i] = nextPlayer;
			} else {
				nextPlayer = nextPlayer +1;
				turnOrder[i] = nextPlayer;
			}
		}
		if (debugTurnOrder){
			System.out.println("Turn order is:");
			for (int i=0; i<turnOrder.length; i++){
				System.out.println(" Player "+turnOrder[i]);
			}
		}
	}
	
	public int getNextPlayer(){
		if(turnCounter == turnOrder.length-1){
			turnCounter = 0;
		} else {
			turnCounter ++;
		}
		System.out.println("Get next player. new player is :"+turnOrder[turnCounter]);
		return turnOrder[turnCounter];
	}
	
	public int getCurrentPlayer(){
		return turnOrder[turnCounter];
	}
	
	public int getNextPlayerGameStart(){
		firstRoundCount++;
		System.out.println("getting next player in start round FirstRoundCount: "+firstRoundCount);
		if (firstRoundCount < turnOrder.length){
			return turnOrder[++turnCounter];
		} else if (firstRoundCount==turnOrder.length){
			return turnOrder[turnCounter];
		} else {
			return turnOrder[--turnCounter];
		}
	}
	
	public boolean firstRoundSettlementDone(){
		return firstRoundCount == (playerCount*2-1);
	}
	
	
}
