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
			hex.repaint();
		}
		boolean city = askCity(x,y);
		if(city){
			interaction.cityClicked();
		}
		boolean setty = askSetty(x,y);
		if(setty){
			interaction.settyClicked();
		}
		boolean road = askRoad(x,y);
		if(road){
			interaction.roadClicked();
		}
		boolean dev = askDev(x,y);
		if(dev){
			//buy dev vcard
			System.out.println("dev");
		}
		boolean sheep = sheep(x,y);
		if(sheep){
			interaction.resourceClicked(5);
		}
		boolean wood = wood(x,y); 
		if(wood){
			interaction.resourceClicked(4);
		}
		boolean stone = stone(x,y);
		if(stone){
			interaction.resourceClicked(1);
		}
		boolean brick = brick(x,y);
		if(brick){
			interaction.resourceClicked(3);
		}
		boolean wheat = wheat(x,y);
		if(wheat){
			interaction.resourceClicked(2);
		}
		if(verty<54){
			interaction.vertexClicked(verty);
		}
		if(hexy<19){
			interaction.robberClicked();
			interaction.tileClicked(hexy);
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