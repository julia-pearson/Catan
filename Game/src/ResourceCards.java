//CJ

public class ResourceCards{
	
	private int [] cardKey;

	ResourceCards(){
		cardKey = {0,0,0,0,0}
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

}