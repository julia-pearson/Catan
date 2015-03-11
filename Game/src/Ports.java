//CJ

public class Ports{
	
	private boolean [] portKey;

	public Ports(){
		portKey = new boolean[6];

		portKey[0] = false;
		portKey[1] = false;
		portKey[2] = false;
		portKey[3] = false;
		portKey[4] = false;
		portKey[5] = false;
	}

	//0 = 3 for 1
	//1 = rock port
	//2 = wheat port
	//3 = brick port
	//4 = wood port
	//5 = sheep port

	public boolean getxPort(int x){
		return portKey[x];
	}

	public boolean getSheepPort(){
		return portKey[5];
	}

	public boolean getRockPort(){
		return portKey[1];
	}

	public boolean getWheatPort(){
		return portKey[2];
	}

	public boolean getBrickPort(){
		return portKey[3];
	}

	public boolean getWoodPort(){
		return portKey[4];
	}

	public boolean getThreePort(){
		return portKey[0];
	}

	public void addSheepPort(){
		portKey[5] = true;
	}

	public void addRockPort(){
		portKey[1] = true;
	}

	public void addWheatPort(){
		portKey[2] = true;
	}

	public void addBrickPort(){
		portKey[3] = true;
	}

	public void addWoodPort(){
		portKey[4] = true;
	}

	public void addThreePort(){
		portKey[0] = true;
	}


}