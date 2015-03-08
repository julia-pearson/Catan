import java.awt.event.*;
import java.awt.Graphics;


public class Clicks implements MouseListener{
	private Hexanew hex;
	private FrontEndInterface interaction;

	public Clicks(Hexanew h, FrontEndInterface f){
		hex = h;
		interaction = f;
	}

	public void mouseClicked(MouseEvent e){

		int x = e.getX();
		int y = e.getY();
		//identifying the Vertex and Hexagon
		int verty= searchVert(x,y);
		int hexy= searchHex(x,y);

		boolean roll=rolledDice(x, y);
		if(roll){
			int [] rolls= interaction.diceClicked();
			hex.rollOne=rolls[0];//random from Julia
			hex.rollTwo=rolls[1];
/*			if(hex.currentPlayer<4){
				hex.currentPlayer=hex.currentPlayer+1;				
			}
			else {
				hex.currentPlayer=1;
			}*/
			hex.repaint();
		}
		boolean city = askCity(x,y);
		if(city){
			hex.buildCity=true;
			System.out.println("city");
		}
		boolean setty = askSetty(x,y);
		if(setty){
			hex.buildSettlement=true;
			System.out.println("setty");
		}
		boolean road = askRoad(x,y);
		if(road){
			hex.buildRoad=true;
			System.out.println("road");
		}
		boolean dev = askDev(x,y);
		if(dev){
			System.out.println("dev");
		}
		boolean sheep = sheep(x,y);
		if(sheep){
			System.out.println("sheep");
		}
		boolean wood = wood(x,y); 
		if(wood){
			System.out.println("wood");
		}
		boolean stone = stone(x,y);
		if(stone){
			System.out.println("stone");
		}
		boolean brick = brick(x,y);
		if(brick){
			System.out.println("brick");
		}
		boolean wheat = wheat(x,y);
		if(wheat){
			System.out.println("wheat");
		}
		if(verty<54){
			interaction.vertexClicked(verty);
			/*
			if(hex.buildSettlement){
				hex.vertex[verty][2]=1;
				hex.vertex[verty][5]=hex.currentPlayer;
				hex.buildSettlement=false;
			}


			if(hex.buildCity){
				hex.vertex[verty][2]=2;
				hex.vertex[verty][5]=hex.currentPlayer;
				hex.buildCity=false;
			}
			if(hex.buildRoad){
				if(hex.road1==0){
					hex.road1=verty;
					hex.vertex[hex.road1][5]=hex.currentPlayer;
				}
				else if(hex.road1!=0 && hex.road2==0) {
					hex.road2=verty;
					hex.vertex[hex.road1][2]=3;
					//checks if there is another road on vertex
					if (hex.vertex[hex.road1][0]<hex.vertex[hex.road2][0]){
						if(hex.vertex[hex.road1][3]>0){
							hex.vertex[hex.road1][4]=2;								
						}
						else{
							hex.vertex[hex.road1][3]=2;							
						}
					}
					else if (hex.vertex[hex.road1][0]>hex.vertex[hex.road2][0]){
						if(hex.vertex[hex.road1][3]>0){
							hex.vertex[hex.road1][4]=1;								
						}
						else{
							hex.vertex[hex.road1][3]=1;							
						}				
					}
					else if (hex.vertex[hex.road1][0]==hex.vertex[hex.road2][0]){
						if(hex.vertex[hex.road1][3]>0){
							hex.vertex[hex.road1][4]=3;								
						}
						else{
							hex.vertex[hex.road1][3]=3;							
						}					
					}					
				}
				hex.buildRoad=false;
				hex.road1=0;
				hex.road2=0;
			}
		hex.repaint();	
		*/
		}

	}

	public int searchVert(int x, int y){
		//15=clickable radius
		int xDifference=15;
		int yDifference=15;
		//The absolute value of the difference between the point you're at and the vertex
		int xPoint=0;
		int yPoint=0;
		//The vertex that will be closest, setting to impossible (54) so will not confuse about 0
		int vert=54;

		for (int i=0; i<54; i++){
			xPoint = abs(x-hex.vertex[i][0]);
			yPoint = abs(y-hex.vertex[i][1]);
			if (xDifference+yDifference>xPoint+yPoint){
				xDifference=xPoint;
				yDifference=yPoint;
				vert=i;
			}
      	}
      	System.out.println(vert);
      	return vert;
	}
	public int searchHex(int x, int y){
		//20=clickable radius
		int xDifference=20;
		int yDifference=20;
		//The absolute value of the difference between the point you're at and the vertex
		int xPoint=0;
		int yPoint=0;
		//The hexagon that will be closest, setting to impossible (19) so will not confuse about 0 
		int hexy=19;

		for (int i=0; i<19; i++){
			xPoint = abs(x-(hex.start[i][0]+hex.w));
			yPoint = abs(y-(hex.start[i][1]-hex.a));
			if (xDifference+yDifference>xPoint+yPoint){
				xDifference=xPoint;
				yDifference=yPoint;
				hexy=i;
			}
      	}
      	System.out.println(hexy);
      	return hexy;
	}
	public boolean rolledDice(int x, int y){
		if(x<175 && x>25 && y<810 && y>745){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean askCity(int x, int y){
		if(x<880 && x>960 && y<163 && y>122){
			return true;
		}
		else if(x<925&& x>880  && y<163 && y>79){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean askSetty(int x, int y){
		if(x<1040 && x>1000 && y<161 && y>100){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean askRoad(int x, int y){
		if(x<1162 && x>1080 && y<161 && y>148){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean askDev(int x, int y){
		if(x<1268 && x>1183 && y<161 && y>40){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean sheep(int x, int y){
		if(x<940 && x>887 && y<260 && y>183){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean wood(int x, int y){
		if(x<1021 && x>967 && y<259 && y>182){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean brick(int x, int y){
		if(x<1103 && x>1045 && y<257 && y>180){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean stone(int x, int y){
		if(x<1263 && x>1208 && y<259 && y>181){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean wheat(int x, int y){
		if(x<1182 && x>1126 && y<263 && y>181){
			return true;
		}
		else{
			return false;
		}
	}
	public int abs(int x){
		int newX=x;
		if (x<0){
			newX=(-1)*x;
		}
		return newX;
	}
	public void mousePressed(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}