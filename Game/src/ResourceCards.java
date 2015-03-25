//CJ
import java.util.Random;

public class ResourceCards{
	
	private int [] cardKey;

	public ResourceCards(){
		cardKey = new int[5];

		cardKey[0]=0;
		cardKey[1]=0;
		cardKey[2]=0;
		cardKey[3]=0;
		cardKey[4]=0;
	}

	//0=sheep
	//1=rock
	//2=wheat
	//3=brick
	//4=wood

	public int getNumber(){
		int num = 0;
		for (int i=0; i<5; i++)
			num=num+cardKey[i];
		return num;
	}



	public int randomDelete(){
		//returns the int of the resource deleted as referenced in the Resource class dictionary

		//this is not really random because it is not based on the players hand but it is random enough
		Random rand = new Random();

		while(true){
			int x = rand.nextInt(5);
			if (cardKey[x]>0){
				cardKey[x]--;
				//this is because the numbering is different here since there is not a desert
				if (x>0)
					return x;
				else
					return 5;
			}
		}
	}

	public int getSheep(){
		return cardKey[0];
	}

	public void addSheep(int n){
		cardKey[0] = cardKey[0] + n ;
	}

	public boolean useSheep(int n){
		if(cardKey[0]>=n){
			cardKey[0] = cardKey[0]-n;
			return true;
		}
		return false;
	}


	public int getRock(){
		return cardKey[1];
	}

	public void addRock(int n){
		cardKey[1] = cardKey[1] + n;
	}

	public boolean useRock(int n){
		if(cardKey[1]>=n){
			cardKey[1] = cardKey[1]-n;
			return true;
		}
		return false;
	}


	public int getWheat(){
		return cardKey[2];
	}

	public void addWheat(int n){
		cardKey[2] = cardKey[2] + n;
	}

	public boolean useWheat(int n){
		if(cardKey[2]>=n){
			cardKey[2] = cardKey[2]-n;
			return true;
		}
		return false;
	}


	public int getBrick(){
		return cardKey[3];
	}

	public void addBrick(int n){
		cardKey[3] = cardKey[3] + n;
	}

	public boolean useBrick(int n){
		if(cardKey[3]>=n){
			cardKey[3] = cardKey[3]-n;
			return true;
		}
		return false;
	}


	public int getWood(){
		return cardKey[4];
	}

	public void addWood(int n){
		cardKey[4] = cardKey[4] + n;
	}

	public boolean useWood(int n){
		if(cardKey[4]>=n){
			cardKey[4] = cardKey[4]-n;
			return true;
		}
		return false;
	}

	public int getx(int x){
		return cardKey[x];
	}

	public int monopX(int x){
		int getrid = cardKey[x];
		while(cardKey[x]>0){
			cardKey[x]--;
		}
		return getrid;
	}

}