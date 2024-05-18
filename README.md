# Planck - Raycasting Engine 1
</br>

## Raycasting engine made entirely in java

</br>


## TODO:
- [x] Create map, able to quickly change map layout
- [x] Make player, able to look around and move in a direction
- [ ] Speed limit
- [ ] Cast single ray
- [ ] Cast a bunch of rays
- [ ] ???
- [ ] profit
  
</br>

## HOW IT WORKS (so far):

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Create an array of values, each one is either 0 or 1. This is done on an 8x8 grid, called gridpanel. In the paintComponent class, there is a for loop, stating that the value of each interger in the array will be parsed through; if the value is one, a black sqaure 64x64px will be created. If 0, it will be white. This creates a map that's easy to build and to modify, as all you have to do to change it is to either add more values, or replace existing ones. 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; After that, the player is created. The rotation is set with AffineTransform; theta is angle, anchor X and Y is the center of the square (player.) The square is then created with the fillRect tool. It's X and Y values are set to the center of itself by taking the "center" and halfing its width and height.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; There's an unimplamented speed limit for the angle speed, this just returns the maximun speed, minimum speed, and angle of the square. Disregard until further notice. The actionPerformed is set immediatley after, tying the square center into the velocity of the object, so the square pivots around itself, and doesn't rotate around the pertimeter of a circle.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Keybinds !!!!!!! On event keyPressed, a switch case begins for e.getKeyCode(). Depending on the key, the velocity in the X and Y is changed + or = Math.sin/cos(angle). Why it works? ¯\\\_(ツ)_/¯. On arrow keys left/right, + or - angle. Soon the raycasting part will be added.