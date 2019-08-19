package snakeproject;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class SnakeAl extends Applet implements Runnable, KeyListener {

    private final int boxheight = 15;
    private final int boxwidth = 15;
    private final int gridwidth = 40;
    private final int gridheight = 40;
    private int direction = Direction.NODirection;
    private Point fruit;

    private LinkedList<Point> snake; // same as arraylist and more efficient
    private Thread runthread = null;
    private Graphics globalG;

    @Override
    public void paint(Graphics g) {
        DrawGrid(g);
        DrawSnake(g);
        DrawFruit(g);
        this.addKeyListener(this);
        if (runthread == null) {
            runthread = new Thread(this);
            runthread.start();
        }
    }

    @Override
    public void init() {
        setSize(700, 700);
        snake = new LinkedList<Point>();
        fruit = new Point(10, 10);
        snake.add(new Point(3, 1));
        snake.add(new Point(3, 2));
        snake.add(new Point(3, 3));
    }

    public void resetsnake() {
        snake = new LinkedList<Point>();
        fruit = new Point(10, 10);
        snake.add(new Point(3, 1));
        snake.add(new Point(3, 2));
        snake.add(new Point(3, 3));
    }

    public void DrawGrid(Graphics g) {
        // draw the grid
        g.drawRect(0, 0, boxwidth * gridwidth, boxheight * gridheight);

        // draw the vertical lines 
        for (int x = boxwidth; x <= boxwidth * gridwidth; x += boxwidth) {
            g.drawLine(x, 0, x, boxheight * gridwidth);
        }
        // draw the Horizontal lines
        for (int y = boxheight; y <= boxheight * gridheight; y += boxheight) {
            g.drawLine(0, y, boxheight * gridheight, y);
        }
    }

    public void DrawSnake(Graphics g) {
        g.setColor(Color.green);
        for (Point P : snake) {
            g.fillRect(P.x * boxwidth, P.y * boxheight, boxwidth, boxheight);
        }
        g.setColor(Color.black);
    }

    public void DrawFruit(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(fruit.x * boxwidth, fruit.y * boxheight, boxwidth, boxheight);
    }

    public void Move() {
        // System.out.println("I enertete here");
        System.out.println(direction);
        Point Head = snake.peekFirst();
        Point newpoint = Head;
        switch (direction) {
            case Direction.North:
                newpoint = new Point(Head.x, Head.y - 1);
                break;
            case Direction.South:
                newpoint = new Point(Head.x, Head.y + 1);
                break;
            case Direction.West:
                newpoint = new Point(Head.x - 1, Head.y);
                break;
            case Direction.East:
                newpoint = new Point(Head.x + 1, Head.y);
                break;
        }
      
        if (newpoint.equals(fruit)) {
            Point addpoint = (Point) newpoint.clone();

            switch (direction) {
                case Direction.North:
                    newpoint = new Point(Head.x, Head.y - 1);
                    break;
                case Direction.South:
                    newpoint = new Point(Head.x, Head.y + 1);
                    break;
                case Direction.West:
                    newpoint = new Point(Head.x - 1, Head.y);
                    break;
                case Direction.East:
                    newpoint = new Point(Head.x + 1, Head.y);
                    break;
            }

            snake.push(addpoint);
            placefruit();
        } else if (newpoint.x < 0 || newpoint.x > gridwidth) {
            resetsnake();
        } else if (newpoint.y < 0 || newpoint.y > gridheight) {
            // out of boundaries y
            resetsnake();
        }
        else if (snake.contains(newpoint)) {
            // out of boundaries y
            resetsnake();
        }
        snake.remove(snake.peekLast());
        snake.push(newpoint);

    }

    public void placefruit() {
        Random Rand = new Random();
        int randomX = Rand.nextInt(gridwidth);
        int randomY = Rand.nextInt(gridheight);
        Point Randompoint = new Point(randomX, randomY);
        fruit = Randompoint;
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            Move();

            try {
                Thread.currentThread();
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.North;
                System.out.println("I entefffffffffff");
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.South;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.West;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.East;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
