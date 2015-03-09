import java.awt.Graphics;
import java.awt.Polygon; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Arc2D;
import java.awt.font.TextAttribute;
import java.awt.Font;
import java.awt.RenderingHints;


//import java.awt.event;


public class Hexanew extends JFrame{
  int gamePlayers=4;

  FrontEndInterface interaction;

  //Making colors
  Color water = new Color (54, 183, 235);
  Color brick = new Color (152, 0, 0);
  Color wood = new Color (0, 102, 0);
  Color stone = new Color (115, 115, 115);
  Color wheat = new Color (247, 244, 57);
  Color sheep = new Color (102, 255, 102);
  Color dessert = new Color (235, 177, 54);
  Color circles = new Color (255,248,220);
  Color darkBlue = new Color (0,0,204);
  Color darkYellow = new Color (219,223,93);
  Color gray = new Color (160,160,160);
  Color white = new Color (224,224,224);
  Color orange = new Color (255,128,0);
  Color blue = new Color (0,0,224);
  Color red = new Color (218,46,46);
  Color robberGray = new Color (144,144,144);
  Color newGray = new Color (159,159,159);
  Graphics g;

  //Setting up fake board
  int resource=0;
  //initializing String that will be numbers on circles
  String value = Integer.toString(0);

  //size of each small polygol, one side=2a
  int a = 40;
  //board coordinates, at the lowest left corner of the lowest left tile, chosen for asthetics
  int x = 250;
  int y = 720;
  //width=1/2 of full width of hexagon, only initiallizing to change from double
  double width=2*a/1.155;
  int w= (int) width;

  //fake board
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

  //x,y,(nothing=0,settlement=1,city=2,road=3),road angle1,road angle2,currentPlayer
  int[][] vertex = new int[][]{
    {x, y-14*a,0,0,0,0},
    {x+w, y-15*a,0,0,0,0},
    {x+2*w, y-14*a,0,0,0,0},
    {x+3*w, y-15*a,0,0,0,0},
    {x+4*w, y-14*a,0,0,0,0},
    {x+5*w, y-15*a,0,0,0,0},
    {x+6*w, y-14*a,0,0,0,0},
    {x-w, y-11*a,0,0,0,0},
    {x, y-12*a,0,0,0,0},
    {x+w, y-11*a,0,0,0,0},
    {x+2*w, y-12*a,0,0,0,0},
    {x+3*w, y-11*a,0,0,0,0},
    {x+4*w, y-12*a,0,0,0,0},
    {x+5*w, y-11*a,0,0,0,0},
    {x+6*w, y-12*a,0,0,0,0},
    {x+7*w, y-11*a,0,0,0,0},
    {x-2*w, y-8*a,0,0,0,0},
    {x-w, y-9*a,0,0,0,0},
    {x, y-8*a,0,0,0,0},
    {x+w, y-9*a,0,0,0,0},
    {x+2*w, y-8*a,0,0,0,0},
    {x+3*w, y-9*a,0,0,0,0},
    {x+4*w, y-8*a,0,0,0,0},
    {x+5*w, y-9*a,0,0,0,0},
    {x+6*w, y-8*a,0,0,0,0},
    {x+7*w, y-9*a,0,0,0,0},
    {x+8*w, y-8*a,0,0,0,0},
    {x-2*w, y-6*a,0,0,0,0},
    {x-w, y-5*a,0,0,0,0},
    {x, y-6*a,0,0,0,0},
    {x+w, y-5*a,0,0,0,0},
    {x+2*w, y-6*a,0,0,0,0},
    {x+3*w, y-5*a,0,0,0,0},
    {x+4*w, y-6*a,0,0,0,0},
    {x+5*w, y-5*a,0,0,0,0},
    {x+6*w, y-6*a,0,0,0,0},
    {x+7*w, y-5*a,0,0,0,0},
    {x+8*w, y-6*a,0,0,0,0},
    {x-w, y-3*a,0,0,0,0},
    {x, y-2*a,0,0,0,0},
    {x+w, y-3*a,0,0,0,0},
    {x+2*w, y-2*a,0,0,0,0},
    {x+3*w, y-3*a,0,0,0,0},
    {x+4*w, y-2*a,0,0,0,0},
    {x+5*w, y-3*a,0,0,0,0},
    {x+6*w, y-2*a,0,0,0,0},
    {x+7*w, y-3*a,0,0,0,0},
    {x, y,0,0,0,0},
    {x+w, y+a,0,0,0,0},
    {x+2*w, y,0,0,0,0},
    {x+3*w, y+a,0,0,0,0},
    {x+4*w, y,0,0,0,0},
    {x+5*w, y+a,0,0,0,0},
    {x+6*w, y,0,0,0,0}
  };

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

  //Gameplay variables
  //hex (start[i][0],start[i][1]) you move the robber to
  int rollOne = 6;
  int rollTwo = 6;

  int robber=0;
  boolean buildSettlement=false;
  boolean buildCity=false;
  boolean buildRoad=false;
  int road1=0;
  int road2=0;

  //sets up screen and size
  public Hexanew( FrontEndInterface f){
      setTitle("Hexanew");
      setSize(1500, 1000);
      setVisible(true);
      addMouseListener(new Clicks(this, f));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      interaction = f;
  }

  //draws the Hexagons
  public void paint(Graphics graphics){
      g = graphics;
      //allows me to print text!
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      System.out.println(interaction.currentPlayerID);
      drawBoard(g2,interaction.currentPlayerID);
      drawRobber(start[robber][0], start[robber][1]);        
      updateBoard();
  }

  public void updateBoard(){

      for (int i=0; i<54; i++){
        if (vertex[i][3]>0){
          buildRoad(vertex[i][0],vertex[i][1], vertex[i][3], vertex[i][5]);
            if (vertex[i][2]==1){
              buildSetty(vertex[i][0],vertex[i][1], vertex[i][5]);
            }
            if (vertex[i][2]==2){
              buildCity(vertex[i][0],vertex[i][1], vertex[i][5]);
            }
            System.out.println("got here");
        }  
        if (vertex[i][4]>0){
          buildRoad(vertex[i][0],vertex[i][1], vertex[i][4], vertex[i][5]);
            if (vertex[i][2]==1){
              buildSetty(vertex[i][0],vertex[i][1], vertex[i][5]);
            }
            if (vertex[i][2]==2){
              buildCity(vertex[i][0],vertex[i][1], vertex[i][5]);
            }
            System.out.println("got here");
        }   
        if (vertex[i][2]==1){
          buildSetty(vertex[i][0],vertex[i][1], vertex[i][5]);
        }
        if (vertex[i][2]==2){
          buildCity(vertex[i][0],vertex[i][1], vertex[i][5]);
        }   
      }
  }

  public void drawBoard(Graphics2D g2, int currentPlayer){
      //drawing border
      drawBorderHex(a1, x1, y1);
      System.out.println(currentPlayer);

      //Filling in Board
      for (int i=0; i<19; i++){
        //get resource ID
        resource= res[i][0];
        //Draw each small hexagon
        drawHexalex(start[i][0],start[i][1], resource);
        //Makes sure it's not the dessert
        if(resource != 0){
          g.setColor(circles);
         //circle radius is simply shifted from the left lower coordinates in start[][]
          drawCircle(start[i][0]+w, start[i][1]-a, 20);
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
      drawDice(25, 800, rollOne, false);
      drawDice(110, 800, rollTwo, true);

      //Things to build
      //x values are pretty arbitrary, just moving things over
      drawCity(x+750-3*a, y-560, currentPlayer);
      drawSetty(x+750, y-560, currentPlayer);
      drawRoad(x+750+2*a, y-560, currentPlayer);
      drawDevelopement(3*a, x+750+4*a+30, y-560);
      drawCards(890, 260);
      //Setting players
      drawPlayers(g2, 900, 300, gamePlayers);
  }

  public void drawHexalex(int x, int y, int resource){

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

  public void drawBorderHex(int a1, int x, int y){

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
  
  public void drawDice(int x, int y, int roll, boolean yellow){
      //l must be divisible by 10 for rounding of dice
      int l = 3*a/2;
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
        drawCircle(x+l/2, y-2*l/5, radius);
      }
      if (roll==2){
        drawCircle(x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==3){
        drawCircle(x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+l/2, y-2*l/5, radius);
        drawCircle(x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==4){
        drawCircle(x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+l/10+2*radius, y-2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==5){
        drawCircle(x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+l/2, y-2*l/5, radius);
        drawCircle(x+l/10+2*radius, y-2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-2*radius, radius);
      }
      if (roll==6){
        drawCircle(x+l/10+2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-4*l/5+2*radius, radius);
        drawCircle(x+l/10+2*radius, y-2*l/5, radius);
        drawCircle(x+9*l/10-2*radius, y-2*l/5, radius);
        drawCircle(x+l/10+2*radius, y-2*radius, radius);
        drawCircle(x+9*l/10-2*radius, y-2*radius, radius);
      }
  }

  public void drawRobber(int x, int y){
    g.setColor(robberGray);
    drawCircle(x+w, y-a, 20);
    g.setColor(newGray);
    drawCircle(x+w, y-a, 17);
    g.setColor(robberGray);
    drawCircle(x+w, y-a, 10);
  }
  
  //angle is the angle at which the road should be tilted: 0=vertical, 1=forward tilt, 2=backwards tilt
  public void drawRoad(int x, int y, int currentPlayer){

      g.setColor(blue);
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
      }

      //for building a road
      int xpoints[] = {x, x, x+2*a, x+2*a};
      int ypoints[] = {y, y-a/4, y-a/4, y};
      int npoints = 4;
      g.fillPolygon(xpoints, ypoints, npoints);
      if(currentPlayer==4){
        g.setColor(white);
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(Color.black);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
  }

  public void buildRoad(int x, int y, int angle, int currentPlayer){
      double three=(((3)^(1/2))*a)/2;
      int sqrthree= (int) three;
      double two=a/(4*(2^(1/2)));
      int sqrtwo= (int) two; 
      //sets road color to player
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
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

  public void drawSetty(int x, int y, int currentPlayer){
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
      }
      //city
      int xpoints[] = {x, x, x+a/2, x+a, x+a};
      int ypoints[] = {y, y-a, y-3*a/2, y-a, y};
      int npoints = 5;

      g.fillPolygon(xpoints, ypoints, npoints);

      if(currentPlayer==4){
        g.setColor(white);
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(Color.black);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
  }
  public void buildSettlement(int v){
    vertex[v][2]=1;
    vertex[v][5]=interaction.currentPlayerID;
    repaint();
  }

  public void drawCity(int x, int y, int currentPlayer){
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
      }

      //city
      int xpoints[] = {x, x, x+a/2, x+a, x+a, x+2*a, x+2*a};
      int ypoints[] = {y, y-3*a/2, y-2*a, y-3*a/2, y-a, y-a, y};
      int npoints = 7;
      g.fillPolygon(xpoints, ypoints, npoints);

      if(currentPlayer==4){
        g.setColor(white);
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(Color.black);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
  }

  public void buildSetty(int x, int y, int currentPlayer){
      x=x-15;
      y=y+15;
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
      }
      //city
      int xpoints[] = {x, x, x+3*a/8, x+3*a/4, x+3*a/4};
      int ypoints[] = {y, y-3*a/4, y-9*a/8, y-3*a/4, y};
      int npoints = 5;

      g.fillPolygon(xpoints, ypoints, npoints);
      g.setColor(circles);
      g.drawPolygon(xpoints, ypoints, npoints);
  }

  public void buildCity(int x, int y, int currentPlayer){
      x=x-15;
      y=y+15;
      if(currentPlayer==1){
        g.setColor(blue);
      }
      if(currentPlayer==2){
        g.setColor(red);
      }
      if(currentPlayer==3){
        g.setColor(orange);
      }
      if(currentPlayer==4){
        g.setColor(white);
      }

      //city
      int xpoints[] = {x, x, x+3*a/8, x+3*a/4, x+3*a/4, x+3*a/2, x+3*a/2};
      int ypoints[] = {y, y-9*a/8, y-3*a/2, y-9*a/8, y-3*a/4, y-3*a/4, y};
      int npoints = 7;

      g.fillPolygon(xpoints, ypoints, npoints);
      g.setColor(circles);
      g.drawPolygon(xpoints, ypoints, npoints);
  }
  
  public void drawDevelopement(int l, int x, int y){

          //Draw card outline
          g.setColor(Color.white);  
          drawCardOutline(3*a/2, x, y);
          g.setColor(circles);
          drawCardOutline(3*(a-2)/2, x+2, y-4);

          //inside circles
          x=x-2;
          y=y+4;
          l=l+8;
          int radius=35;
          g.setColor(Color.lightGray);
          drawCircle(x+6*l/20, y-l/2, radius+1);
          g.setColor(brick);
          drawCircle(x+6*l/20, y-l/2, radius-2);
          g.setColor(darkBlue);
          g.fillArc(x+6*l/20-32, y-l/2-21, radius+29, radius+19, 0, -180);
          g.setColor(darkYellow);
          drawCircle(x+6*l/20, y-l/2, radius-15);
          g.setColor(Color.lightGray);
          drawCircle(x+6*l/20, y-l/2, radius-25);
          g.setColor(stone);
          drawCircle(x+6*l/20, y-l/2, radius-27);
  }

  public void drawCardOutline(int l, int x, int y){
          //This b is an altered a
          int xpoints[] = {x, x-l/10, x-l/10, x, x+6*l/5, x+13*l/10, x+13*l/10,x+6*l/5};
          int ypoints[] = {y, y-l/10, y-19*l/10, y-2*l, y-2*l, y-19*l/10, y-l/10, y};
          int npoints = 8;
          g.fillPolygon(xpoints, ypoints, npoints);
  }

  public void drawResourceCards(int x, int y, int resource){
          //Setting Card Outline
          g.setColor(Color.white); 
          drawCardOutline(a, x, y);

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
          drawCardOutline(a-4, x+2, y-4);
  }

  public void drawCards(int x, int y){
      //Drawing cards under PLayers
      for(int j=0; j<5; j++){
        drawResourceCards(x+j*2*a, y, 5-j);
      }
  }

  public void drawPlayers(Graphics2D g2, int x, int y, int players){
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      Font font = new Font("Gill Sans", Font.PLAIN, 20);
      g2.setFont(font);

      g.setColor(circles);
      int xpoints[]={x-15,x+400,x+400,x-15};
      int ypoints[]={y-20,y-20,y+550,y+550};
      g.fillPolygon(xpoints,ypoints,4);

      //Writes Player names
      g.setColor(blue);  
      g2.drawString("Player One", x, y);
      g.setColor(red);
      g2.drawString("Player Two", x, y+150);
      g.setColor(orange);
      g2.drawString("Player Three", x, y+300);
      
      //Adds fourth Player
      if(players==4){
        g.setColor(newGray);
        g2.drawString("Player Four", x, y+450);
      }  

      font = new Font("Gill Sans", Font.PLAIN, 15);
      g2.setFont(font);
      g2.setColor(sheep);
      for(int i=0; i<5;i++){
        g2.drawString("Sheep", x, y+25+i*150);
      }  
      g2.setColor(wood);
      for(int i=0; i<5;i++){
        g2.drawString("Wood", x, y+40+i*150);
      }  
      g2.setColor(brick);
      for(int i=0; i<5;i++){
        g2.drawString("Brick", x, y+55+i*150);
      }  
      g2.setColor(darkYellow);
      for(int i=0; i<5;i++){
        g2.drawString("Wheat", x, y+70+i*150);
      }  
      g2.setColor(Color.darkGray);
      for(int i=0; i<5;i++){
        g2.drawString("Stone", x, y+85+i*150);
      }  
  }

  private void drawCircle(int x, int y, int radius) {

    g.fillOval(x-radius, y-radius, radius*2, radius*2);
  }
 

  //public static void main(String[] args){
  //  Hexanew h = new Hexanew();
  //}
}
