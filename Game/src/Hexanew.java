import java.awt.Graphics;
import java.awt.Polygon; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Arc2D;

public class Hexanew extends JFrame{
  //Making colors
  Color water = new Color (54, 183, 235);
  Color brick = new Color (152, 0, 0);
  Color wood = new Color (0, 102, 0);
  Color stone = new Color (115, 115, 115);
  Color wheat = new Color (247, 244, 57);
  Color sheep = new Color (97, 237, 92);
  Color dessert = new Color (235, 177, 54);
  //Number circles
  Color circles = new Color (255,248,220);
  //Development Card Colors
  Color darkBlue = new Color (0,0,204);
  Color darkYellow = new Color (219,223,93);
  Color gray = new Color (160,160,160);

  //Setting up fake board
  int resource=0;
  //initializing String that will be numbers on circles
  String value = Integer.toString(0);

  int[][] res = new int[][]{
    {0,0},
    {5,12},
    {2,11},
    {1,8},
    {4,5},       
    {5,4},
    {3,8},
    {3,10},
    {4,10},
    {5,6},
    {5,3},
    {1,4},
    {2,6},
    {3,9},       
    {2,11},
    {1,5},
    {2,3},    
    {4,9},
    {4,4},
  };

   //size of each small polygol, one side=2a
   int a = 40;
   //board coordinates, at the lowest left corner of the lowest left tile, chosen for asthetics
   int x = 250;
   int y = 720;
   //width=1/2 of full width of hexagon, only initiallizing to change from double
   double width=2*a/1.155;
   int w= (int) width;

   //array of bottom left corners of Hexagons
   int[][] start = new int[][]{
      {x,y-12*a},
      {x+2*w, y-12*a},
      {x+4*w, y-12*a},
      {x-w, y-9*a},
      {x+w, y-9*a},
      {x+3*w, y-9*a},
      {x+5*w, y-9*a},
      {x-2*w, y-6*a},
      {x, y-6*a},
      {x+2*w, y-6*a},
      {x+4*w, y-6*a},
      {x+6*w, y-6*a},
      {x-w, y-3*a},
      {x+w, y-3*a},
      {x+3*w, y-3*a},
      {x+5*w, y-3*a},
      {x, y},
      {x+2*w, y},
      {x+4*w, y}
   };
   //ratio of little hexagon side to big hexagon side, only initiallizing to change from double
   double aBorder = 6*a/1.155;
   int a1 = (int) aBorder;
   //starting location for border (from lowest left corner)
   int x1 = x;
   int y1 = y+2*a;



   //sets up screen and size
   public Hexanew(){
      setTitle("Hexanew");
      setSize(1500, 1000);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   //draws the Hexagons
   public void paint(Graphics g){
      //allows me to print text!
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      drawBoard(g, g2);

      //playing 
      //buildRoad(g, x+5*w, y-11*a, 1,1);
      buildRoad(g, x+5*w, y-11*a, 2,2);
      buildRoad(g, x, y-6*a, 3,3);
      buildSetty(g, 3*a/2, x+5*w, y-11*a, 2);
      buildCity(g, 3*a/2, x, y-6*a, 3);
   }

  public void drawBoard(Graphics g, Graphics2D g2){
      //drawing border
      drawBorderHex(g, a1, x1, y1);

      //Filling in Board
      for (int i=0; i<19; i++){
        //get resource ID
        resource= res[i][0];
        //Draw each small hexagon
        drawHexalex(g,a,start[i][0],start[i][1], resource);
        //Makes sure it's not the dessert
        if(resource != 0){
          g.setColor(circles);
         //circle radius is simply shifted from the left lower coordinates in start[][]
          drawCircle(g,start[i][0]+w, start[i][1]-a, 20);
          //Getting number for circle
          value = Integer.toString(res[i][1]);
          g.setColor(Color.black);
          //making 8 and 6 red
          if (value.equals("6") || value.equals("8")){
            g.setColor(Color.red);
          }
          //6 and five are centering text
          g2.drawString(value, start[i][0]+w-6, start[i][1]-a+5);
        }
      }

      //Setting Dice
      int rollOne = 6;
      int rollTwo = 6;
      drawDice(g, 60, 25, 800, rollOne, false);
      drawDice(g, 60, 110, 800, rollTwo, true);

      //Things to build
      //x values are pretty arbitrary, just moving things over
      drawCity(g, 2*a, x+750-3*a, y-560, 1);
      drawSetty(g, 2*a, x+750, y-560, 1);
      drawRoad(g, x+750+2*a, y-560, 1);
      drawDevelopement(g, 3*a, x+750+4*a+30, y-560);
      int gamePlayers=4;
      drawCards(g, g2, 890, 260, gamePlayers);
      //Setting players
      drawPlayers(g, g2, 900, 300, gamePlayers);
  }

  public void drawHexalex(Graphics g, int a, int x, int y, int resource){

      //array of coordinates, starting at lower left corner of the hexagon
      int xpoints[] = {x, x, x+w, x+2*w, x+2*w, x+w};
      int ypoints[] = {y, y-2*a, y-3*a, y-2*a, y, y+a};
      int npoints = 6;

      //draws outline
      g.setColor(Color.white);
      g.drawPolygon(xpoints, ypoints, npoints);

      //checks the resource and sets the appropriate color
      if (resource==0){
         g.setColor(dessert);
      }
      else if (resource==1){
         g.setColor(stone);
      }
      else if (resource==2){
         g.setColor(wheat);
      }
      else if (resource==3){
         g.setColor(brick);
      }
      else if (resource==4){
         g.setColor(wood);
      }      
      else if (resource==5){
         g.setColor(sheep);
      }
      //inputs the resource
      g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void drawBorderHex(Graphics g, int a1, int x, int y){

      double yTwo=y-2*a1/1.155;
      int y2= (int) yTwo;

      double ythree=y-4*a1/1.155;
      int y3= (int) ythree;

      int xpoints[] = {x, x-a1, x, x+2*a1, x+3*a1, x+2*a1};
      int ypoints[] = {y, y2, y3, y3, y2, y};
      int npoints = 6;
      
      g.setColor(water);
      g.fillPolygon(xpoints, ypoints, npoints);
  }
  
  public void drawDice(Graphics g, int l, int x, int y, int roll, boolean yellow){
      //l must be divisible by 10 for rounding of dice
      int xpoints[] = {x, x, x+l/10, x+9*l/10, x+l, x+l, x+9*l/10, x+l/10};
      int ypoints[] = {y, y-8*l/10, y-9*l/10, y-9*l/10, y-8*l/10, y, y+l/10, y+l/10};
      int npoints = 8;
      g.setColor(Color.red);  
      if (yellow==true){
         g.setColor(Color.yellow);  
      }
      g.fillPolygon(xpoints, ypoints, npoints);

      //numbers
      int radius = 5;
      g.setColor(Color.yellow);
      if (yellow==true){
         g.setColor(Color.red);  
      }

      if (roll==1){
        drawCircle(g, x+l/2, y-2*l/5, radius);
      }
      if (roll==2){
        drawCircle(g, x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==3){
        drawCircle(g, x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+l/2, y-2*l/5, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==4){
        drawCircle(g, x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+l/10+2*radius, y-2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==5){
        drawCircle(g, x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+l/2, y-2*l/5, radius);
        drawCircle(g, x+l/10+2*radius, y-2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==6){
        drawCircle(g, x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(g, x+l/10+2*radius, y-2*l/5, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*l/5, radius);
        drawCircle(g, x+l/10+2*radius, y-2*radius, radius);
        drawCircle(g, x+9*l/10-2*radius, y-2*radius, radius);
      }
  }
  
  //angle is the angle at which the road should be tilted: 0=vertical, 1=forward tilt, 2=backwards tilt
  public void drawRoad(Graphics g, int x, int y, int player){
      g.setColor(Color.blue);
      if(player==1){
        g.setColor(Color.blue);
      }
      if(player==2){
        g.setColor(Color.red);
      }
      if(player==3){
        g.setColor(wood);
      }
      if(player==4){
        g.setColor(Color.black);
      }
      //for building a road
      int xpoints[] = {x, x, x+2*a, x+2*a};
      int ypoints[] = {y, y-a/4, y-a/4, y};
      int npoints = 4;
      g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void buildRoad(Graphics g, int x, int y, int angle, int player){
      double three=(((3)^(1/2))*a)/2;
      int sqrthree= (int) three;
      double two=a/(4*(2^(1/2)));
      int sqrtwo= (int) two; 
      //sets road color to player
      if(player==1){
        g.setColor(Color.blue);
      }
      if(player==2){
        g.setColor(Color.red);
      }
      if(player==3){
        g.setColor(wood);
      }
      if(player==4){
        g.setColor(Color.black);
      }
      //back slant
      if (angle==1){
        int xpoints[] = {x-5, x-sqrthree-5, x-sqrthree+sqrtwo-7, x+sqrtwo-7};
        int ypoints[] = {y+2, y-a+6, y-a-sqrtwo+6, y-sqrtwo+2};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
      //forward slant
      if (angle==2){ 
        int xpoints[] = {x+5, x+sqrthree+5, x+sqrthree-sqrtwo+7, x-sqrtwo+7};
        int ypoints[] = {y, y-a+4, y-a-sqrtwo+4, y-sqrtwo};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
      //Vertical
      if (angle==3){
        int xpoints[] = {x-3, x+a/4-7, x+a/4-7, x-3};
        int ypoints[] = {y-7, y-7, y-2*a+3, y-2*a+3};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
  }

  public void drawSetty(Graphics g, int l, int x, int y, int player){
      g.setColor(Color.blue);
      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2};
      int ypoints[] = {y, y-l/2, y-3*l/4, y-l/2, y};
      int npoints = 5;

      g.setColor(Color.blue);
      g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void drawCity(Graphics g, int l, int x, int y, int player){
      g.setColor(Color.blue);

      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2, x+l, x+l};
      int ypoints[] = {y, y-3*l/4, y-l, y-3*l/4, y-l/2, y-l/2, y};
      int npoints = 7;

      g.setColor(Color.blue);
      g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void buildSetty(Graphics g, int l, int x, int y, int player){
      x=x-15;
      y=y+15;
      if(player==1){
        g.setColor(Color.blue);
      }
      if(player==2){
        g.setColor(Color.red);
      }
      if(player==3){
        g.setColor(wood);
      }
      if(player==4){
        g.setColor(Color.black);
      }
      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2};
      int ypoints[] = {y, y-l/2, y-3*l/4, y-l/2, y};
      int npoints = 5;

      g.fillPolygon(xpoints, ypoints, npoints);
      g.setColor(circles);
      g.drawPolygon(xpoints, ypoints, npoints);
  }

  public void buildCity(Graphics g, int l, int x, int y, int player){
      x=x-15;
      y=y+15;
      if(player==1){
        g.setColor(Color.blue);
      }
      if(player==2){
        g.setColor(Color.red);
      }
      if(player==3){
        g.setColor(wood);
      }
      if(player==4){
        g.setColor(Color.black);
      }

      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2, x+l, x+l};
      int ypoints[] = {y, y-3*l/4, y-l, y-3*l/4, y-l/2, y-l/2, y};
      int npoints = 7;

      g.fillPolygon(xpoints, ypoints, npoints);
      g.setColor(circles);
      g.drawPolygon(xpoints, ypoints, npoints);
  }
  
  public void drawDevelopement(Graphics g, int l, int x, int y){

          //Draw card outline
          g.setColor(Color.white);  
          drawCardOutline(g, 3*a/2, x, y);
          g.setColor(circles);
          drawCardOutline(g, 3*(a-2)/2, x+2, y-4);

          //inside circles
          x=x-2;
          y=y+4;
          l=l+8;
          int radius=35;
          g.setColor(Color.lightGray);
          drawCircle(g,x+6*l/20, y-l/2, radius+1);
          g.setColor(brick);
          drawCircle(g,x+6*l/20, y-l/2, radius-2);
          g.setColor(darkBlue);
          g.fillArc(x+6*l/20-32, y-l/2-21, radius+29, radius+19, 0, -180);
          g.setColor(darkYellow);
          drawCircle(g,x+6*l/20, y-l/2, radius-15);
          g.setColor(Color.lightGray);
          drawCircle(g,x+6*l/20, y-l/2, radius-25);
          g.setColor(stone);
          drawCircle(g,x+6*l/20, y-l/2, radius-27);
  }

  public void drawCardOutline(Graphics g, int b, int x, int y){
          //This b is an altered a
          int xpoints[] = {x, x-b/10, x-b/10, x, x+12*b/10, x+13*b/10, x+13*b/10,x+12*b/10};
          int ypoints[] = {y, y-b/10, y-19*b/10, y-2*b, y-2*b, y-19*b/10, y-b/10, y};
          int npoints = 8;
          g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void drawResourceCards(Graphics g, int x, int y, int resource){
          //Setting Card Outline
          g.setColor(Color.white); 
          drawCardOutline(g, a, x, y);

          if (resource==1){
             g.setColor(stone);
          }
          else if (resource==2){
             g.setColor(wheat);
          }
          else if (resource==3){
             g.setColor(brick);
          }
          else if (resource==4){
             g.setColor(wood);
          }      
          else if (resource==5){
             g.setColor(sheep);
          }
          drawCardOutline(g, a-4, x+2, y-4);
  }

  public void drawCards(Graphics g, Graphics2D g2, int x, int y, int players){
      //Drawing cards under PLayers
      for(int j=0; j<5; j++){
        drawResourceCards(g, x+j*2*a, y, 5-j);
      }
  }

  public void drawPlayers(Graphics g, Graphics2D g2, int x, int y, int players){
      //Writes Player names
      g.setColor(Color.blue);  
      g2.drawString("Player 1", x, y);
      g.setColor(brick);
      g2.drawString("Player 2", x, y+150);
      g.setColor(wood);
      g2.drawString("Player 3", x, y+300);
      
      //Adds fourth Player
      if(players==4){
        g.setColor(Color.black);
        g2.drawString("Player 4", x, y+450);
      }    
  }

  private void drawCircle(Graphics g, int x, int y, int radius) {

    g.fillOval(x-radius, y-radius, radius*2, radius*2);
  }
 
  public static void main(String[] args){
    Hexanew h = new Hexanew();
  }
}