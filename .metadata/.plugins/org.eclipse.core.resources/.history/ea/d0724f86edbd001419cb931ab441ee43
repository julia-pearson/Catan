/*
 * JE 
 * This is the class that we will use to handle the logic of building settlements/roads/checking for cards
 */
public class BackendInterface {
	static FrontEndInterface fei;
	static GamePlayer	gp;
	
	public BackendInterface (GamePlayer player, FrontEndInterface f){
		fei = f;
		gp = player;
	}
	
	public static void placeSettlement(int p, int v){
		gp.placeSettlement(p, v);
	}
	
	public static void buildSettlement (int p, int v){
		gp.buildSettlement(p, v);
	}
	

}
