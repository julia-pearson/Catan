//CJ

public class Ports{
	
	private boolean [] portKey;

	Ports(){
		portKey = {false, false, false, false, false};
	}

	//0 = 3 for 1
	//1 = rock port
	//2 = wheat port
	//3 = brick port
	//4 = wood port
	//5 = sheep port

	public boolean getSheepPort(){
		if (portKey[5] == true)
			return true;
		return false;
	}

	public boolean getRockPort(){
		if (portKey[1] == true)
			return true;
		return false;
	}

	public boolean getWheatPort(){
		if (portKey[2] == true)
			return true;
		return false;
	}

	public boolean getBrickPort(){
		if (portKey[3] == true)
			return true;
		return false;
	}

	public boolean getWoodPort(){
		if (portKey[4] == true)
			return true;
		return false;
	}

	public boolean getThreePort(){
		if (portKey[0] == true)
			return true;
		return false;
	}

	public boolean addSheepPort(){
		portKey[5] == true;
	}

	public boolean addRockPort(){
		portKey[1] == true;
	}

	public boolean addWheatPort(){
		portKey[2] == true;
	}

	public boolean addBrickPort(){
		portKey[3] == true;
	}

	public boolean addWoodPort(){
		portKey[4] == true;
	}

	public boolean addThreePort(){
		portKey[0] == true;
	}


}