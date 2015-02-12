PHASE 1 (2/5): Build the board
	A) Create a 4x19 array that holds placement info for resources, probabilities, ports, and robber
		- CJ
		- Board.java
		- Reference Board Interpretation Key file
		- 2/19 complete
	B) Create a GUI interface that takes the array from part A as input and creates the visualization of the board
		- Julia P
		- Visualize.java
	C) Create a graph that take the array from part A as input and creates a graph of the board for the game/AI to run on
		- Julia E
		- Vertex.java, Edge.java, GamePlayer.java

PHASE 2 (2/12): Achieve human-to-human usability
	A) Create structures to hold data that changes throughout the game (player settlements, cities, roads, cards)
		- Player.java
	B) GUI interpretation of Player Data
	C) Graph interpretation of Player Data
	D) Starting interface (ie how many players)
	E) Rules handling
		- Do all the rules handling in the graph. Any click in the GUI gets sent to the graph and is verified and added there 	before added graphically
		- ie: 	Do you have a legal combination of cards to build this city/settlement/road/dcard (consider ports and 4 for 1)?
				Is this a legal spot to build (two road segments away, no one else is built there)?
				Is this a legal spot to put the robber (the robber wasn't there before)?
				Do you have any settlements/roads/cities left to build (ie max 5 settlements)?



**the position of the robber needs to change
**development cards
14 Knights 
5 Victory Points
2 Road Building Cards
2 Monopoly Cards
2 Year of Plenty Cards