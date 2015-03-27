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

public class Hexanew extends JFrame{
  Graphics g;
  Graphics2D g2;
  FrontEndInterface interaction;
  int totalPlayers;

  //CONSTANT__________

  //size of each small polygol, one side=2a
  int a = 40;
  //board coordinates, at the lowest left corner of the lowest left tile, chosen for asthetics
  int x = 250;
  int y = 720;
  //width=1/2 of full width of hexagon, only initiallizing to change from double
  double width=2*a/1.155;
  int w= (int) width;
  //ratio of little hexagon side to big hexagon side, only initiallizing to change from double
  double aBorder = 6*a/1.155;
  int a1 = (int) aBorder;

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
  Color lightPurps = new Color (150,63,150);

  //UNIVERSAL VARIABLES TO BE CHANGED_____________________________

  //initializing String that will be numbers on circles
  int rollOne = 6;
  int rollTwo = 6;
  //boolean robberClick=false;


  //v1,v2,road?,player
  int[][] roadSide = new int[][]{
    {0,1,0,0},
    {2,3,0,0},
    {4,5,0,0},
    {7,8,0,0},
    {9,10,0,0},
    {11,12,0,0},
    {13,14,0,0},
    {16,17,0,0},
    {18,19,0,0},
    {20,21,0,0},
    {22,23,0,0},
    {24,25,0,0},
    {28,29,0,0},
    {30,31,0,0},
    {32,33,0,0},
    {34,35,0,0},
    {36,37,0,0},   
    {39,40,0,0},
    {41,42,0,0},
    {43,44,0,0},
    {45,46,0,0},   
    {48,49,0,0},
    {50,51,0,0},
    {52,53,0,0},
    {1,2,0,0},
    {3,4,0,0},
    {5,6,0,0},   
    {8,9,0,0},
    {10,11,0,0},
    {12,13,0,0},
    {14,15,0,0},
    {17,18,0,0},
    {19,20,0,0},
    {21,22,0,0},
    {23,24,0,0},
    {25,26,0,0},
    {27,28,0,0},
    {29,30,0,0},
    {31,32,0,0},
    {33,34,0,0},
    {38,39,0,0},
    {35,36,0,0},
    {40,41,0,0},
    {42,43,0,0},
    {44,45,0,0},
    {47,48,0,0},
    {49,50,0,0},
    {51,52,0,0},
    {0,8,0,0},
    {2,10,0,0},
    {4,12,0,0}, 
    {6,14,0,0},
    {7,17,0,0},
    {9,19,0,0},
    {11,21,0,0},
    {13,23,0,0},
    {15,25,0,0},
    {16,27,0,0},
    {18,29,0,0},
    {20,31,0,0},
    {22,33,0,0},
    {24,35,0,0},
    {26,37,0,0},
    {28,38,0,0},
    {30,40,0,0},
    {32,42,0,0},
    {34,44,0,0},
    {36,46,0,0},
    {39,47,0,0},
    {41,49,0,0},
    {43,51,0,0},
    {45,53,0,0}
  };
  //x,y,(nothing=0,settlement=1,city=2), currentPlayer
  int[][] vertex = new int[][]{
    {x, y-14*a,0,0},
    {x+w, y-15*a,0,0},
    {x+2*w, y-14*a,0,0},
    {x+3*w, y-15*a,0,0},
    {x+4*w, y-14*a,0,0},
    {x+5*w, y-15*a,0,0},
    {x+6*w, y-14*a,0,0},
    {x-w, y-11*a,0,0},
    {x, y-12*a,0,0},
    {x+w, y-11*a,0,0},
    {x+2*w, y-12*a,0,0},
    {x+3*w, y-11*a,0,0},
    {x+4*w, y-12*a,0,0},
    {x+5*w, y-11*a,0,0},
    {x+6*w, y-12*a,0,0},
    {x+7*w, y-11*a,0,0},
    {x-2*w, y-8*a,0,0},
    {x-w, y-9*a,0,0},
    {x, y-8*a,0,0},
    {x+w, y-9*a,0,0},
    {x+2*w, y-8*a,0,0},
    {x+3*w, y-9*a,0,0},
    {x+4*w, y-8*a,0,0},
    {x+5*w, y-9*a,0,0},
    {x+6*w, y-8*a,0,0},
    {x+7*w, y-9*a,0,0},
    {x+8*w, y-8*a,0,0},
    {x-2*w, y-6*a,0,0},
    {x-w, y-5*a,0,0},
    {x, y-6*a,0,0},
    {x+w, y-5*a,0,0},
    {x+2*w, y-6*a,0,0},
    {x+3*w, y-5*a,0,0},
    {x+4*w, y-6*a,0,0},
    {x+5*w, y-5*a,0,0},
    {x+6*w, y-6*a,0,0},
    {x+7*w, y-5*a,0,0},
    {x+8*w, y-6*a,0,0},
    {x-w, y-3*a,0,0},
    {x, y-2*a,0,0},
    {x+w, y-3*a,0,0},
    {x+2*w, y-2*a,0,0},
    {x+3*w, y-3*a,0,0},
    {x+4*w, y-2*a,0,0},
    {x+5*w, y-3*a,0,0},
    {x+6*w, y-2*a,0,0},
    {x+7*w, y-3*a,0,0},
    {x, y,0,0},
    {x+w, y+a,0,0},
    {x+2*w, y,0,0},
    {x+3*w, y+a,0,0},
    {x+4*w, y,0,0},
    {x+5*w, y+a,0,0},
    {x+6*w, y,0,0}
  };
  //array of bottom left corners of Hexagons, third is robber
  int[][] start = new int[][]{
    {x,y-12*a,0},
    {x+2*w, y-12*a,0},
    {x+4*w, y-12*a,0},
    {x-w, y-9*a,0},
    {x+w, y-9*a,0},
    {x+3*w, y-9*a,0},
    {x+5*w, y-9*a,0},
    {x-2*w, y-6*a,0},
    {x, y-6*a,0},
    {x+2*w, y-6*a,0},
    {x+4*w, y-6*a,0},
    {x+6*w, y-6*a,0},
    {x-w, y-3*a,0},
    {x+w, y-3*a,0},
    {x+3*w, y-3*a,0},
    {x+5*w, y-3*a,0},
    {x, y,0},
    {x+2*w, y,0},
    {x+4*w, y,0}
  };
  //fake board
  int[][] res = new int[][]{
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
    {0,0},
  };

  int[] player1 = new int[]{0,0,0,0,0,0,0,0,0,0,0};
  int[] player2 = new int[]{0,0,0,0,0,0,0,0,0,0,0};
  int[] player3 = new int[]{0,0,0,0,0,0,0,0,0,0,0};
  int[] player4 = new int[]{0,0,0,0,0,0,0,0,0,0,0};

  int[] ports = new int[]{0,0,0,0,0,0,0,0,0};


  //sets up screen and size
  public Hexanew(FrontEndInterface f, int[][] board, int numPlayers, boolean mouseListener){
      setTitle("Hexanew");
      setSize(1500, 1000);
      setVisible(true);
      if (mouseListener){
    	  addMouseListener(new Clicks(this, f));
      }
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      interaction = f;
      res = board;
      totalPlayers = numPlayers;
      repaint();
  } 
  public void paint(Graphics graphics){
      g = graphics;
      //allows me to print text!
      g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
      drawBoard(interaction.currentPlayerID);   
      updateBoard();
  }
  public void drawBoard(int currentPlayer){

        //background
        g.setColor(circles);
        int xpoints[]={0,1500,1500,0};
        int ypoints[]={0,0,1000,1000};
        g.fillPolygon(xpoints,ypoints,4);

        //drawing border
        drawBorderHex(a1, x, y+2*a);

        String value ="0";
        //Filling in Board
        for (int i=0; i<19; i++){
          //Draw each small hexagon
          drawHexalex(start[i][0],start[i][1], res[i][0]);
          //Makes sure it's not the dessert
          if(res[i][0] != 0){
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

        drawDevelopement(3*a, x+780+4*a, y-560);
        drawCards(890, 260);
        //Setting players, should be from Julia
        drawPlayers(g2, 900, 300, totalPlayers);
        tradeButton(1300,252);
        for (int i=0; i<9;i++){
          drawPorts(i);
        }
        for (int i=0; i<19; i++){
          if(res[i][1]==0){
            drawRobber(start[i][0],start[i][1]);
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
  }
  public void updateBoard(){
      for (int i=0; i<71;i++){
        if(roadSide[i][2]>0){
          buildRoad(i);
        }
      }
      for (int i=0; i<54; i++){
        //checks for if there is a citty or Settlement
        if (vertex[i][2]==1){
          buildSetty(vertex[i][0],vertex[i][1], vertex[i][3]);
        }
        if (vertex[i][2]==2){
          buildCity(vertex[i][0],vertex[i][1], vertex[i][3]);
        }  
      }
      //checks where the robber is 
      for (int i=0; i<19; i++){
        if(start[i][2]==1){
          drawRobber(start[i][0], start[i][1]); 
        }
      }
      drawResources(g2, 1, player1);
      drawResources(g2, 2, player2);
      drawResources(g2, 3, player3);
      if (totalPlayers==4){
        drawResources(g2, 4, player4);       
      }
      drawStatistics(g2, 1, player1);
      drawStatistics(g2, 2, player2);
      drawStatistics(g2, 3, player3);
      if (totalPlayers==4){
        drawStatistics(g2, 4, player4);       
      }
  }



  //active methods
  public void buildRoad(int v){
      double three=(((3)^(1/2))*a)/2;
      int sqrthree= (int) three;
      double two=a/(4*(2^(1/2)));
      int sqrtwo= (int) two; 
 
      //sets road color to player
      if(roadSide[v][3]==1){
        g.setColor(blue);
      }
      if(roadSide[v][3]==2){
        g.setColor(red);
      }
      if(roadSide[v][3]==3){
        g.setColor(orange);
      }
      if(roadSide[v][3]==4){
        g.setColor(white);
      }

      //forward slant
      int x=0;
      int y=0;
      if(v<24){
        x=vertex[roadSide[v][0]][0];
        y=vertex[roadSide[v][0]][1];
        int xpoints[] = {x+5, x+sqrthree+5, x+sqrthree-sqrtwo+7, x-sqrtwo+7};
        int ypoints[] = {y, y-a+4, y-a-sqrtwo+4, y-sqrtwo};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
      //back slant
      if (v>23 && v<48){
        x=vertex[roadSide[v][1]][0];
        y=vertex[roadSide[v][1]][1];
        int xpoints[] = {x-5, x-sqrthree-5, x-sqrthree+sqrtwo-7, x+sqrtwo-7};
        int ypoints[] = {y+2, y-a+6, y-a-sqrtwo+6, y-sqrtwo+2};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
      //Vertical
      if (v>48 && v<72){
        x=vertex[roadSide[v][1]][0];
        y=vertex[roadSide[v][1]][1];
        int xpoints[] = {x-3, x+a/4-7, x+a/4-7, x-3};
        int ypoints[] = {y-7, y-7, y-2*a+3, y-2*a+3};
        int npoints = 4;
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(circles);
        g.drawPolygon(xpoints, ypoints, npoints);
      }
  }
  public void buildSetty(int x, int y, int currentPlayer){
      x=x-15;
      y=y+15;
      if (g == null){
    	  System.out.println("Can't find g in build setty method");
      }
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
  


  //interaction methods
  public void addRoad(int v1, int v2, int currentPlayer){
      for (int i=0;i<70;i++){
        if(roadSide[i][0]==v1){
          if(roadSide[i][1]==v2){
            roadSide[i][2]=1;
            roadSide[i][3]=currentPlayer; 
          }
        }
        if(roadSide[i][0]==v2){
          if(roadSide[i][1]==v1){
            roadSide[i][2]=1;
            roadSide[i][3]=currentPlayer; 
          }
        }    
      }
      repaint();
  } 
  public void addSettlement(int v){
    vertex[v][2]=1;
    vertex[v][3]=interaction.currentPlayerID;
    repaint();
  }
  public void addCity(int v){
    vertex[v][2]=2;
    vertex[v][3]=interaction.currentPlayerID;
    repaint();
  }
  public void addStatistics(int[] statistics, int currentPlayer){
    for(int i=0;i<statistics.length;i++){
      if (currentPlayer==1) {
        player1[i]=statistics[i];        
      }
      if (currentPlayer==2) {
        player2[i]=statistics[i];        
      }
      if (currentPlayer==3) {
        player3[i]=statistics[i];        
      }
      if (currentPlayer==4) {
        player4[i]=statistics[i];        
      }
    }
    repaint();
  }
  //pass the add robber method the hexagon
  public void addRobber(int h){
    for (int i=0; i<19; i++){
      if(start[i][2]==1){
        start[i][2]=0; 
      }
    }
    start[h][2]=1;
    repaint();
  }
  public void addPorts(int[] port){
    for (int i=0; i<9;i++){
      ports[i]=port[i];
      System.out.println("port="+i+" "+ports[i]);
    }
  }

  //all draw methods
  public void drawHexalex(int x, int y, int resource){
      //array of coordinates, starting at lower left corner of the hexagon
      int xpoints[] = {x, x, x+w, x+2*w, x+2*w, x+w};
      int ypoints[] = {y, y-2*a, y-3*a, y-2*a, y, y+a};
      int npoints = 6;

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

      //draws outline
      g.setColor(circles);
      g.drawPolygon(xpoints, ypoints, npoints);
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
  public void drawRobber(int x, int y){
    g.setColor(robberGray);
    drawCircle(x+w, y-a, 20);
    g.setColor(newGray);
    drawCircle(x+w, y-a, 17);
    g.setColor(robberGray);
    drawCircle(x+w, y-a, 10);
  }
  public void drawDevelopement(int l, int x, int y){

          //Draw card outline
          g.setColor(Color.white);  
          drawCardOutline(3*a/2, x, y);
          //inside circles
          l=l+8;
          x=x+6*l/20-2;
          y=y-l/2+4;
          int radius=35;
          g.setColor(Color.lightGray);
          drawCircle(x, y, radius+1);
          g.setColor(brick);
          drawCircle(x, y, radius-2);
          g.setColor(darkBlue);
          g.fillArc(x+-32, y-21, radius+29, radius+19, 0, -180);
          g.setColor(darkYellow);
          drawCircle(x, y, radius-15);
          g.setColor(Color.lightGray);
          drawCircle(x, y, radius-25);
          g.setColor(stone);
          drawCircle(x, y, radius-27);
  }
  public void drawCards(int x, int y){
      //Drawing cards under PLayers
      for(int j=0; j<5; j++){
        drawResourceCards(x+j*2*a, y, 5-j);
      }
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
  public void drawPlayers(Graphics2D g2,int x, int y, int players){
      Font font = new Font("Gill Sans", Font.PLAIN, 20);
      g2.setFont(font);
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
      //draws cards
      g2.setColor(stone);
      for(int i=0; i<players;i++){
        g2.drawString("Sheep   ", x, y+25+i*150);
        g2.drawString("Wood    ", x, y+40+i*150); 
        g2.drawString("Brick   ", x, y+55+i*150);
        g2.drawString("Wheat   ", x, y+70+i*150);
        g2.drawString("Stone   ", x, y+85+i*150);
      }  
      for(int i=0; i<players;i++){
        g2.drawString("Victory Points", x+250, y+25+i*150);
        g2.drawString("Knights Played", x+250, y+40+i*150);  
        g2.drawString("Unplayed", x+380, y+40+i*150); 
        g2.drawString("Road Builder", x+250, y+55+i*150); 
        g2.drawString("Monopoly", x+250, y+70+i*150); 
        g2.drawString("Year of Plenty", x+250, y+85+i*150); 
      } 
  }
  public void drawResources(Graphics2D g2, int currentPlayer, int[] resourceCount){
      Font font = new Font("Gill Sans", Font.PLAIN, 15);
      g2.setFont(font);
      //draws cards
      g2.setColor(stone);

      String sheep = Integer.toString(resourceCount[0]);
      if(sheep.equals("0")){
        sheep=" ";
      }
      String rock = Integer.toString(resourceCount[1]);
      if(rock.equals("0")){
        rock=" ";
      }
      String wheat = Integer.toString(resourceCount[2]);
      if(wheat.equals("0")){
        wheat=" ";
      }
      String brick = Integer.toString(resourceCount[3]);
      if(brick.equals("0")){
        brick=" ";
      }
      String wood = Integer.toString(resourceCount[4]);
      if(wood.equals("0")){
        wood=" ";
      }

      int x=950;
      int y=300;

      int i=currentPlayer-1;
      g2.drawString(sheep, x, y+25+i*150);
      g2.drawString(wood, x, y+40+i*150); 
      g2.drawString(brick, x, y+55+i*150);
      g2.drawString(wheat, x, y+70+i*150);
      g2.drawString(rock, x, y+85+i*150);

      repaint(); 
  }
  public void drawStatistics(Graphics2D g2, int currentPlayer, int[] statistics){
      Font font = new Font("Gill Sans", Font.PLAIN, 15);
      g2.setFont(font);
      g2.setColor(stone);
      String vp=" ";
      String knight=" ";
      String unplayed=" ";
      String roadBuilder=" ";
      String monopoly=" ";
      String yearOfPlenty=" ";

      if(currentPlayer==1){
        vp = Integer.toString(player1[5]);   
        knight=Integer.toString(player1[6]); 
        unplayed=Integer.toString(player1[7]); 
        roadBuilder=Integer.toString(player1[8]); 
        monopoly=Integer.toString(player1[9]);  
        yearOfPlenty=Integer.toString(player1[10]);     
      }
      if(currentPlayer==2){
        vp = Integer.toString(player2[5]);   
        knight=Integer.toString(player2[6]); 
        unplayed=Integer.toString(player2[7]); 
        roadBuilder=Integer.toString(player2[8]); 
        monopoly=Integer.toString(player2[9]);  
        yearOfPlenty=Integer.toString(player2[10]);          
      }
      if(currentPlayer==3){
        vp = Integer.toString(player3[5]);   
        knight=Integer.toString(player3[6]); 
        unplayed=Integer.toString(player3[7]); 
        roadBuilder=Integer.toString(player3[8]); 
        monopoly=Integer.toString(player3[9]);  
        yearOfPlenty=Integer.toString(player3[10]);         
      }
      if(currentPlayer==4){
        vp = Integer.toString(player4[5]);   
        knight=Integer.toString(player4[6]); 
        unplayed=Integer.toString(player4[7]); 
        roadBuilder=Integer.toString(player4[8]); 
        monopoly=Integer.toString(player4[9]);  
        yearOfPlenty=Integer.toString(player4[10]);         
      }
      int x=1250;
      int y=300;

      int i=currentPlayer-1;
      g2.drawString(vp, x, y+25+i*150);
      g2.drawString(knight, x, y+40+i*150); 
      g2.drawString(unplayed, x+100, y+40+i*150); 
      g2.drawString(roadBuilder, x, y+55+i*150);
      g2.drawString(monopoly, x, y+70+i*150);
      g2.drawString(yearOfPlenty, x, y+85+i*150);
  }
  public void tradeButton(int x, int y){
      g.setColor(Color.white);
      int radius=22;
      drawCircle(x+25,y-radius-10,radius*2);
      radius=20;
      g.setColor(lightPurps);
      drawCircle(x+25,y-radius-12,radius*2);


      Font font = new Font("Gill Sans", Font.PLAIN, 30);
      g2.setFont(font);
      g.setColor(Color.white);
      g2.drawString("Trade", x-10, y-20);
  }
  public void drawPorts(int port){
      double three=(((3)^(1/2))*a)/2;
      int sqrthree= (int) three;
      double two=a/(4*(2^(1/2)));
      int sqrtwo= (int) two; 
      g.setColor(circles);
      //forward slant
      int x1=0;
      int y1=0;
      int x2=0;
      int y2=0;
      if(port==0){
        x1=vertex[7][0];
        y1=vertex[7][1];
        x2=vertex[8][0];
        y2=vertex[8][1];
        if(ports[0]==1){
          g.setColor(stone);
        }    
        if(ports[0]==2){
          g.setColor(wheat);
        } 
        if(ports[0]==3){
          g.setColor(brick);
        } 
        if(ports[0]==4){
          g.setColor(wood);
        }  
        if(ports[0]==5){
          g.setColor(sheep);
        }       
        int xpoints[] = {x1, x2-5, x1};
        int ypoints[] = {y1-7, y2-5, y2-5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==1){
        x1=vertex[2][0];
        y1=vertex[2][1];
        x2=vertex[3][0];
        y2=vertex[3][1];
        if(ports[1]==1){
          g.setColor(stone);
        }    
        if(ports[1]==2){
          g.setColor(wheat);
        } 
        if(ports[1]==3){
          g.setColor(brick);
        } 
        if(ports[1]==4){
          g.setColor(wood);
        }  
        if(ports[1]==5){
          g.setColor(sheep);
        }  
        int xpoints[] = {x1, x2-5, x1};
        int ypoints[] = {y1-7, y2-5, y2-5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==2){
        x1=vertex[5][0];
        y1=vertex[5][1];
        x2=vertex[6][0];
        y2=vertex[6][1];
        if(ports[2]==1){
          g.setColor(stone);
        }    
        if(ports[2]==2){
          g.setColor(wheat);
        } 
        if(ports[2]==3){
          g.setColor(brick);
        } 
        if(ports[2]==4){
          g.setColor(wood);
        }  
        if(ports[2]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1+5, x2, x2};
        int ypoints[] = {y1-5, y1-5, y2-7};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==3){
        x1=vertex[15][0];
        y1=vertex[15][1];
        x2=vertex[25][0];
        y2=vertex[25][1];
        if(ports[3]==1){
          g.setColor(stone);
        }    
        if(ports[3]==2){
          g.setColor(wheat);
        } 
        if(ports[3]==3){
          g.setColor(brick);
        } 
        if(ports[3]==4){
          g.setColor(wood);
        }  
        if(ports[3]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1+5, x1+a, x1+5};
        int ypoints[] = {y1+5, (y1+y2)/2, y2-5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==4){
        x1=vertex[36][0];
        y1=vertex[36][1];
        x2=vertex[46][0];
        y2=vertex[46][1];
        if(ports[4]==1){
          g.setColor(stone);
        }    
        if(ports[4]==2){
          g.setColor(wheat);
        } 
        if(ports[4]==3){
          g.setColor(brick);
        } 
        if(ports[4]==4){
          g.setColor(wood);
        }  
        if(ports[4]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1+5, x1+a, x1+5};
        int ypoints[] = {y1+5, (y1+y2)/2, y2-5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==5){
        x1=vertex[52][0];
        y1=vertex[52][1];
        x2=vertex[53][0];
        y2=vertex[53][1];
        if(ports[5]==1){
          g.setColor(stone);
        }    
        if(ports[5]==2){
          g.setColor(wheat);
        } 
        if(ports[5]==3){
          g.setColor(brick);
        } 
        if(ports[5]==4){
          g.setColor(wood);
        }  
        if(ports[5]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1+5, x2, x2};
        int ypoints[] = {y1+7, y2+7, y1+7};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==6){
        x1=vertex[49][0];
        y1=vertex[49][1];
        x2=vertex[50][0];
        y2=vertex[50][1];
        if(ports[6]==1){
          g.setColor(stone);
        }    
        if(ports[6]==2){
          g.setColor(wheat);
        } 
        if(ports[6]==3){
          g.setColor(brick);
        } 
        if(ports[6]==4){
          g.setColor(wood);
        }  
        if(ports[6]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1, x2-5, x1};
        int ypoints[] = {y1+7, y2+5, y2+5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==7){
        x1=vertex[38][0];
        y1=vertex[38][1];
        x2=vertex[39][0];
        y2=vertex[39][1];
        if(ports[7]==1){
          g.setColor(stone);
        }    
        if(ports[7]==2){
          g.setColor(wheat);
        } 
        if(ports[7]==3){
          g.setColor(brick);
        } 
        if(ports[7]==4){
          g.setColor(wood);
        }  
        if(ports[7]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1, x2-5, x1};
        int ypoints[] = {y1+7, y2+5, y2+5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
      if (port==8){
        x1=vertex[16][0];
        y1=vertex[16][1];
        x2=vertex[27][0];
        y2=vertex[27][1];
        if(ports[8]==1){
          g.setColor(stone);
        }    
        if(ports[8]==2){
          g.setColor(wheat);
        } 
        if(ports[8]==3){
          g.setColor(brick);
        } 
        if(ports[8]==4){
          g.setColor(wood);
        }  
        if(ports[8]==5){
          g.setColor(sheep);
        } 
        int xpoints[] = {x1-5, x1-a, x1-5};
        int ypoints[] = {y1+5, (y1+y2)/2, y2-5};
        int npoints = 3;
        g.fillPolygon(xpoints, ypoints, npoints);
      }
  }
  private void drawCircle(int x, int y, int radius) {
    g.fillOval(x-radius, y-radius, radius*2, radius*2);
  }
}

