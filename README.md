# UnderTale-Multicolor-Tile-Puzzle

UnderTale Multicolor Tile Puzzle in java with extra tabs for analysis

![eclipse_JGJ8nt2yNj](https://github.com/Creepler13/UnderTale-Multicolor-Tile-Puzzle/assets/29956950/1d89565a-54d5-44fb-8915-95a2469a50cf)


Color Rules 

 - Pink tiles have no effect and can be walked on freely.
 - Red tiles act as solid walls.
 - Yellow tiles force the player back to the last tile they stepped on (the player's controls are locked in the meantime).
 - Orange tiles change the player's flavor to "Orange".
 - Purple tiles force the player to the next tile in the direction they are facing (the player's controls are locked in the meantime). Additionally, purple tiles change the player's flavor to "Lemon".
 - Blue tiles vary in function depending on the following factors:
     - If the blue tile is adjacent to a yellow tile, the blue tile acts identically to a yellow tile, simulating electrified water.
     - If the player's flavor is "Orange", the blue tile also acts identically to a yellow tile, luring the piranhas out and forcing the player back to the previous tile.
     - If neither of the above conditions is met, the blue tile acts identically to a pink tile.
