
Java implementation of the classic snake game using awt , Swings and java Applets , Created by Nouran Waleed. 

If the user eats a "food", which is the red square that appears on the screen, the snake grows by one block. The user loses if his snake hits itself or if it hits the edge of the screen. 


The majority of the code for this program was done in the SnakeAI.java file, which is the implementation of the snake class.
In class SnakeAl extends from Applet Which implements Runnable and KeyListener. there are ten Methods, paint(Graphics g), init(), resetsnake(), DrawGrid(Graphics g), DrawSnake(Graphics g), DrawFruit(Graphics g), Move(), placefruit(), run(), keyTyped(KeyEvent e), keyPressed(KeyEvent e) and keyReleased(KeyEvent e).   


I checked this program mostly by playing it over and over and trying to do weird things like trying to go in the opposite direction of the current direction to make sure that the program did not allow the user to do this, running into walls to see if the program would end, running into my own snake to see if the program would end, testing the pause and quit buttons mid play, and testing the quit and restart buttons after dying. Overall, most of the testing of this code was just playing the game and giving it weird inputs and seeing what would happen.
