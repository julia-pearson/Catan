import java.awt.Graphics;
import java.awt.Polygon; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hexanew extends JFrame{

   //size of polygol, side=2a
   int a = 40;
   //board coordinates, at the lowest left corner of the lowest left tile, chosen for asthetics
   int x = 250;
   int y = 720;

   //distance between starting points of each hexagon in row (distance=1/2 width of pointed top hexagon)
   double width=4*a/1.155;
   int w= (int) width;
   double halfw=2*a/1.155;
   int hw= (int) halfw;

   Color water = new Color (54, 183, 235);
   Color brick = new Color (152, 0, 0);
   Color wood = new Color (0, 102, 0);
   Color stone = new Color (115, 115, 115);
   Color wheat = new Color (247, 244, 57);
   Color sheep = new Color (97, 237, 92);
   Color dessert = new Color (235, 177, 54);
   Color circles = new Color (255,248,220);


   int[][] start = new int[][]{
      {x,y-12*a},
      {x+w, y-12*a},
      {x+2*w, y-12*a},
      {x-hw, y-9*a},
      {x+hw, y-9*a},
      {x+3*hw, y-9*a},
      {x+5*hw, y-9*a},
      {x-2*hw, y-6*a},
      {x, y-6*a},
      {x+w, y-6*a},
      {x+2*w, y-6*a},
      {x+6*hw, y-6*a},
      {x-hw, y-3*a},
      {x+hw, y-3*a},
      {x+3*hw, y-3*a},
      {x+5*hw, y-3*a},
      {x, y},
      {x+w, y},
      {x+2*w, y}
   };

  //FAKE ARRAY
  int resource=0;
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

   //sets up screen and size
   public Hexanew(){
      setTitle("Hexanew");
      //What size do we want?
      setSize(1500, 1000);
      //Can we later make cards invisible to some players?
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   //draws the Hexagons
   public void paint(Graphics g){

      //ratio of little hexagon side to big hexagon side
      double anew = 6*a/1.155;
      int a1 = (int) anew;
      //starting location for border (from lowest left corner)
      int x1 = x;
      int y1 = y+2*a;
      //drawing border
      drawFlatHex(g, a1, x1, y1);

      //Things to build
      int l=80;
      //l must be divisible by 4
      drawDice(g, 60, 25, 800, false);
      drawDice(g, 60, 110, 800, true);

      drawCity(g, l, x+750-3*l/2, y-600);
      drawSetty(g, l, x+750, y-600);
      drawRoad(g, a, x+750+l, y-600, 0);

      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      String value = Integer.toString(0);

      for (int i=0; i<19; i++){
        resource= res[i][0];
        drawHexalex(g,a,start[i][0],start[i][1], resource);

        //Makes sure it's not the desser
        if(resource != 0){
          g.setColor(circles);
         //circle radius is simply shifted from the left lower coordinates in start[][]
          drawCircle(g,start[i][0]+hw, start[i][1]-a, 20);
          value = Integer.toString(res[i][1]);
          g.setColor(Color.black);
          //making 8 and 6 red
          if (value.equals("6") || value.equals("8")){
            g.setColor(Color.red);
          }
          //6 and five are centering text

          g2.drawString(value, start[i][0]+hw-6, start[i][1]-a+5);
        }
        
      }


   }

   public void drawHexalex(Graphics g, int a, int x, int y, int resource){
      //array of coordinates

      int xpoints[] = {x, x, x+w/2, x+w, x+w, x+w/2};
      int ypoints[] = {y, y-2*a, y-3*a, y-2*a, y, y+a};
      int npoints = 6;

      g.setColor(Color.white);
      g.drawPolygon(xpoints, ypoints, npoints);

      //checks the resource
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

   //Dont Touch!!!!!!!
   public void drawFlatHex(Graphics g, int a, int x, int y){

      double ytwo=y-2*a/1.155;
      int y2= (int) ytwo;

      double ythree=y-4*a/1.155;
      int y3= (int) ythree;

      int xpoints[] = {x, x-a, x, x+2*a, x+3*a, x+2*a};
      int ypoints[] = {y, y2, y3, y3, y2, y};
      int npoints = 6;
      
      g.setColor(water);
      g.fillPolygon(xpoints, ypoints, npoints);
   }
   public void drawDice(Graphics g, int l, int x, int y, boolean yellow){
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
      int roll = 3;
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
   public void drawRoad(Graphics g, int l, int x, int y, int angle){
      //city
      int xpoints[] = {x, x, x+2*l, x+2*l};
      int ypoints[] = {y, y-l/4, y-l/4, y};
      int npoints = 4;

      g.setColor(Color.blue);
      g.fillPolygon(xpoints, ypoints, npoints);
   }

   public void drawSetty(Graphics g, int l, int x, int y){
      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2};
      int ypoints[] = {y, y-l/2, y-3*l/4, y-l/2, y};
      int npoints = 5;

      g.setColor(Color.blue);
      g.fillPolygon(xpoints, ypoints, npoints);
   }

   public void drawCity(Graphics g, int l, int x, int y){
      //city
      int xpoints[] = {x, x, x+l/4, x+l/2, x+l/2, x+l, x+l};
      int ypoints[] = {y, y-3*l/4, y-l, y-3*l/4, y-l/2, y-l/2, y};
      int npoints = 7;

      g.setColor(Color.blue);
      g.fillPolygon(xpoints, ypoints, npoints);
   }

   private void drawCircle(Graphics g, int x, int y, int radius) {
      g.fillOval(x-radius, y-radius, radius*2, radius*2);
   }
 
   public static void main(String[] args){
      Hexanew h = new Hexanew();
   }
}