# Catan

This is our special topics COSC-390 course!

Julia, Julia, and CJ 

Plan:

GamePlayer.java
-the controller class
-Tasks:
1.initialize the board
2. create GUI
3. control user input
4. update tables/board when user gives input 


Board.java
-constrcutor: randomly assign tile location and probabilities
-getBoard(): returns randomly constructed board
-getTestBoard(): returns known board

Visualize.java
-DrawMap(): will take the verticies from PlayGame.java and create a visual interface

Vertex.java
-one object for each corner on the map

Edge.java
-one object for each edge

Player.java
- a class for each participant that has their ID (used by vertex and edge to determine who owns the spot)

