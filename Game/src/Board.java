//CJ
//Feb 12: This is working code. It will produce a 4x19 array with all the necessary info to produce a board. See Board Interpretation Key for details

import java.util.Random;

public class Board {
//creates the random map and tile allocation
	private int[][] theBoard;

	public Board() {
		theBoard = new int[4][19];
		initialize();
	}
	
	//randomly create a legal arrangement of resources, probabilities, robber placement, port placement
	private void initialize(){
		/*
		Red numbers (6 and 8) cannot be adjacent.
		The robber should be on the desert to start.
		The desert should not have a probability - contains 0. 
		*/
 
		Random rand = new Random();

		//Randomize resource placements first (4 sheep/wheat/wood and 3 brick/ore and 1 desert)
		int [] tracker = {0,0,0,0,0,0};
		int [] trackerMax = {1,3,4,3,4,4};
		for (int i=0; i<19; i++){
			boolean keepgoing = true;
			while(keepgoing){
				int selection = rand.nextInt(6);
				if (tracker[selection]<trackerMax[selection]){
					tracker[selection]++;
					theBoard[0][i]=selection;
					keepgoing=false;
				}
			}
		}

		//Based on resource placements we know where to put the desert and where to put probability 0
		for(int j=0; j<19; j++){
			if(theBoard[0][j]==0){
				theBoard[3][j]=1;
				theBoard[1][j]=0;
			}
			else{
				theBoard[3][j]=0;
				theBoard[1][j]=1;
			}
		}

		//Randomize the ports. (4 three-for-ones and 1 of each other type)
		tracker = new int[]{0,0,0,0,0,0};
		trackerMax = new int[]{4,1,1,1,1,1};
		for (int k=0; k<9; k++){
			boolean keepgoing = true;
			while(keepgoing){
				int selection = rand.nextInt(6);
				if (tracker[selection]<trackerMax[selection]){
					tracker[selection]++;
					theBoard[2][k]=selection;
					keepgoing=false;
				}
			}
		}

		//Randomize the probabilities. Create a new layout until it is legal (no adjacent red numbers)
		boolean keeptrying = true;
		while (keeptrying){
			setProbs();
			if(checkProbs()==true)
				keeptrying=false;
		}
		

	}

	private void setProbs(){
		Random rand = new Random();
		int [] tracker = new int[]{0,0,0,0,0,0,0,0,0,0,0};
		int [] trackerMax = new int[]{1,2,2,2,2,0,2,2,2,2,1};
		for (int i=0; i<19; i++){
			if(theBoard[1][i]!=0){
				boolean keepgoing = true;
				while(keepgoing){
					int selection = rand.nextInt(11);
					if (tracker[selection]<trackerMax[selection]){
						tracker[selection]++;
						theBoard[1][i]=selection+2;
						keepgoing=false;
					}
				}
			}
		}
	}

	//this is really poorly hard coded...
	private boolean checkProbs(){
		for(int i=0; i<19; i++){
			if(theBoard[1][i]==6 || theBoard[1][i]==8){
				if(i==0 && (theBoard[1][1]==6 || theBoard[1][3]==6 || theBoard[1][4]==6 || theBoard[1][1]==8 || theBoard[1][3]==8 || theBoard[1][4]==8))
					return false;
				if(i==1 && (theBoard[1][0]==6 || theBoard[1][2]==6 || theBoard[1][4]==6 || theBoard[1][5]==6 || theBoard[1][0]==8 || theBoard[1][2]==8 || theBoard[1][4]==8 || theBoard[1][5]==8))
					return false;
				if(i==2 && (theBoard[1][1]==6 || theBoard[1][5]==6 || theBoard[1][6]==6 || theBoard[1][1]==8 || theBoard[1][5]==8 || theBoard[1][6]==8))
					return false;
				if(i==3 && (theBoard[1][0]==6 || theBoard[1][4]==6 || theBoard[1][7]==6 || theBoard[1][8]==6 || theBoard[1][0]==8 || theBoard[1][4]==8 || theBoard[1][7]==8 || theBoard[1][8]==8))
					return false;
				if(i==4 && (theBoard[1][0]==6 || theBoard[1][1]==6 || theBoard[1][3]==6 || theBoard[1][5]==6 || theBoard[1][8]==6 || theBoard[1][9]==6 ||theBoard[1][0]==8 || theBoard[1][1]==8 || theBoard[1][3]==8 || theBoard[1][5]==8 || theBoard[1][8]==8 || theBoard[1][9]==8))
					return false;
				if(i==5 && (theBoard[1][1]==6 || theBoard[1][2]==6 || theBoard[1][4]==6 || theBoard[1][6]==6 || theBoard[1][9]==6 || theBoard[1][10]==6 ||theBoard[1][1]==8 || theBoard[1][2]==8 || theBoard[1][4]==8 || theBoard[1][6]==8 || theBoard[1][9]==8 || theBoard[1][10]==8))
					return false;
				if(i==6 && (theBoard[1][2]==6 || theBoard[1][5]==6 || theBoard[1][10]==6 || theBoard[1][11]==6 || theBoard[1][2]==8 || theBoard[1][5]==8 || theBoard[1][10]==8 || theBoard[1][11]==8))
					return false;
				if(i==7 && (theBoard[1][3]==6 || theBoard[1][8]==6 || theBoard[1][12]==6 || theBoard[1][3]==8 || theBoard[1][8]==8 || theBoard[1][12]==8))
					return false;
				if(i==8 && (theBoard[1][3]==6 || theBoard[1][4]==6 || theBoard[1][7]==6 || theBoard[1][9]==6 || theBoard[1][12]==6 || theBoard[1][13]==6 || theBoard[1][3]==8 || theBoard[1][4]==8 || theBoard[1][7]==8 || theBoard[1][9]==8 || theBoard[1][12]==8 || theBoard[1][13]==8))
					return false;
				if(i==9 && (theBoard[1][4]==6 || theBoard[1][5]==6 || theBoard[1][8]==6 || theBoard[1][10]==6 || theBoard[1][13]==6 || theBoard[1][14]==6 || theBoard[1][4]==8 || theBoard[1][5]==8 || theBoard[1][8]==8 || theBoard[1][10]==8 || theBoard[1][13]==8 || theBoard[1][14]==8))
					return false;
				if(i==10 && (theBoard[1][5]==6 || theBoard[1][6]==6 || theBoard[1][9]==6 || theBoard[1][11]==6 || theBoard[1][14]==6 || theBoard[1][15]==6 || theBoard[1][5]==8 || theBoard[1][6]==8 || theBoard[1][9]==8 || theBoard[1][11]==8 || theBoard[1][14]==8 || theBoard[1][15]==8))
					return false;
				if(i==11 && (theBoard[1][6]==6 || theBoard[1][10]==6 || theBoard[1][15]==6 || theBoard[1][6]==8 || theBoard[1][10]==8 || theBoard[1][15]==8))
					return false;
				if(i==12 && (theBoard[1][7]==6 || theBoard[1][8]==6 || theBoard[1][13]==6 || theBoard[1][16]==6 || theBoard[1][7]==8 || theBoard[1][8]==8 || theBoard[1][13]==8 || theBoard[1][16]==8))
					return false;
				if(i==13 && (theBoard[1][8]==6 || theBoard[1][9]==6 || theBoard[1][12]==6 || theBoard[1][14]==6 || theBoard[1][16]==6 || theBoard[1][17]==6 || theBoard[1][8]==8 || theBoard[1][9]==8 || theBoard[1][12]==8 || theBoard[1][14]==8 || theBoard[1][16]==8 || theBoard[1][17]==8))
					return false;
				if(i==14 && (theBoard[1][9]==6 || theBoard[1][10]==6 || theBoard[1][13]==6 || theBoard[1][15]==6 || theBoard[1][17]==6 || theBoard[1][18]==6 || theBoard[1][9]==8 || theBoard[1][10]==8 || theBoard[1][13]==8 || theBoard[1][15]==8 || theBoard[1][17]==8 || theBoard[1][18]==8))
					return false;
				if(i==15 && (theBoard[1][10]==6 || theBoard[1][11]==6 || theBoard[1][14]==6 || theBoard[1][18]==6 || theBoard[1][10]==8 || theBoard[1][11]==8 || theBoard[1][14]==8 || theBoard[1][18]==8))
					return false;
				if(i==16 && (theBoard[1][12]==6 || theBoard[1][13]==6 || theBoard[1][17]==6 || theBoard[1][12]==8 || theBoard[1][13]==8 || theBoard[1][17]==8))
					return false;
				if(i==17 && (theBoard[1][13]==6 || theBoard[1][14]==6 || theBoard[1][16]==6 || theBoard[1][18]==6 || theBoard[1][13]==8 || theBoard[1][14]==8 || theBoard[1][16]==8 || theBoard[1][18]==8))
					return false;
				if(i==18 && (theBoard[1][14]==6 || theBoard[1][15]==6 || theBoard[1][17]==6 || theBoard[1][14]==8 || theBoard[1][15]==8 || theBoard[1][17]==8))
					return false;
			}
		}
		return true;
	}
	
	//method that will give the GamePlayer access to the graph
	public int[][] getBoard(){
		return theBoard;
	}

	//method that will return a test board
	public int[][] getTestBoard(){
		theBoard[0][0] = 4;
		theBoard[0][1] = 5;
		theBoard[0][2] = 2;
		theBoard[0][3] = 3;
		theBoard[0][4] = 1;
		theBoard[0][5] = 3;
		theBoard[0][6] = 5;
		theBoard[0][7] = 0;
		theBoard[0][8] = 4;
		theBoard[0][9] = 2;
		theBoard[0][10] = 4;
		theBoard[0][11] = 2;
		theBoard[0][12] = 3;
		theBoard[0][13] = 5;
		theBoard[0][14] = 5;
		theBoard[0][15] = 1;
		theBoard[0][16] = 1;
		theBoard[0][17] = 2;
		theBoard[0][18] = 4;

		theBoard[1][0] = 11;
		theBoard[1][1] = 12;
		theBoard[1][2] = 9;
		theBoard[1][3] = 4;
		theBoard[1][4] = 6;
		theBoard[1][5] = 5;
		theBoard[1][6] = 10;
		theBoard[1][7] = 0;
		theBoard[1][8] = 3;
		theBoard[1][9] = 11;
		theBoard[1][10] = 4;
		theBoard[1][11] = 8;
		theBoard[1][12] = 8;
		theBoard[1][13] = 10;
		theBoard[1][14] = 9;
		theBoard[1][15] = 3;
		theBoard[1][16] = 5;
		theBoard[1][17] = 2;
		theBoard[1][18] = 6;

		theBoard[2][0] = 1;
		theBoard[2][1] = 0;
		theBoard[2][2] = 5;
		theBoard[2][3] = 0;
		theBoard[2][4] = 0;
		theBoard[2][5] = 3;
		theBoard[2][6] = 4;
		theBoard[2][7] = 0;
		theBoard[2][8] = 2;

		theBoard[3][7] = 1;
		return theBoard;
	}
}
