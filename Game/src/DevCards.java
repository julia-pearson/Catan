public class DevCards{
	
	private int[] cardKey;

	public DevCards(){
		cardKey = new int[6];

		cardKey[0]=0;
		cardKey[1]=0;
		cardKey[2]=0;
		cardKey[3]=0;
		cardKey[4]=0;
		cardKey[5]=0;

	}

	// 0 = 14 Knights UNUSED
	// 1 = used knight
	// 2 = 5 Victory Points
	// 3 = 2 Road Building Cards
	// 4 = 2 Monopoly Cards
	// 5 = 2 Year of Plenty Cards

	public int armySize(){
		return cardKey[1];
	}

	public boolean useKnight(){
		if(cardKey[0]>0){
			cardKey[0]--;
			cardKey[1]++;
			return true;
		}
		return false;
	}

	public void addKnight(){
		cardKey[0]++;
	}

	public void addVictory(){
		cardKey[2]++;
		//this is kind of useless as victory point d cards are automatically added to your total
	}

	public void addRoadBuilder(){
		cardKey[3]++;
	}

	public boolean useRoadBuilder(){
		if(cardKey[3]>0){
			cardKey[3]--;
			return true;
		}
		return false;
	}

	public void addMonopoly(){
		cardKey[4]++;
	}

	public boolean useMonopoly(){
		if(cardKey[4]>0){
			cardKey[4]--;
			return true;
		}
		return false;
	}

	public void addYearOfPlenty(){
		cardKey[5]++;
	}

	public boolean useYearOfPlenty(){
		if(cardKey[5]>0){
			cardKey[5]--;
			return true;
		}
		return false;
	}

	public int getK(){
		return cardKey[0];
	}

	public int getRB(){
		return cardKey[3];
	}

	public int getM(){
		return cardKey[4];
	}

	public int getYoP(){
		return cardKey[5];
	}


}