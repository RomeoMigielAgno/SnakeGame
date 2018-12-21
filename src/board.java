/**
	File name: board.java
	Short description: Creating the board and its interface
	IST 242 Assignment: 
	@author Romeo-Migiel, Yusra
	@version 1.01 4/19/2018
*/
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class board extends JPanel implements ActionListener
{
    // Create the height and width of the window
    private final static int boardWidth = 1000;
    private final static int boardHeight = 980;
    
    // Store the pixel size of food and snake joints
    private final static int pixelSize = 25;
    
    // Total amount of pixels in order for the game to not end prematurely
    private final static int totalPixels = (boardWidth * boardHeight) / (pixelSize * pixelSize);
    
    // Checking to see if the game is running
    private boolean inGame = true;
    
    // Record tick times using a timer
    private Timer timer;
    
    // Set game speed
    private static int speed = 100;
    
    // Instances of snake and food
    private snake snake = new snake();
    private protein protein = new protein();
    
    // Constructor
    public board()
    {
        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent ke) 
            {
               
            }

            @Override
            public void keyPressed(KeyEvent ke) {
               int key = ke.getKeyCode();
                if((key == KeyEvent.VK_A) && (!snake.isMoveRight()))
                {
                    snake.setMoveLeft(true);
                    snake.setMoveUp(false);
                    snake.setMoveDown(false);
                }
                if((key == KeyEvent.VK_D) && (!snake.isMoveLeft()))
                {
                    snake.setMoveRight(true);
                    snake.setMoveUp(false);
                    snake.setMoveDown(false);
                }
                if((key == KeyEvent.VK_W) && (!snake.isMoveDown()))
                {
                    snake.setMoveUp(true);
                    snake.setMoveLeft(false);
                    snake.setMoveRight(false);
                }
                if((key == KeyEvent.VK_S) && (!snake.isMoveUp()))
                {
                    snake.setMoveDown(true);
                    snake.setMoveLeft(false);
                    snake.setMoveRight(false);
                }
                if((key == KeyEvent.VK_R) && (inGame = false))
                {
                     initGame();
                    inGame = true;
                    snake.setMoveLeft(false);
                    snake.setMoveRight(false);
                    snake.setMoveUp(false);
                    snake.setMoveDown(false);                 
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) 
            {
                
            }
        });
        
        setBackground(Color.PINK);
        setFocusable(true);
        
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        
        initGame();
    }
    // Menu ActionListener
   
    // Paint Components
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    
    // Draw the snake and food (repaint())
    void draw(Graphics g)
    {
        // Paint snake when its alive
        if(inGame == true)
        {
            g.setColor(Color.GRAY);
            g.fillRect(protein.getProteinX(), protein.getProteinY(), pixelSize, pixelSize);
            
            // Draw the snake
            for(int i = 0; i < snake.getJoint(); i++)
            {
                // head of Snake
                if(i == 0)
                {
                    g.setColor(Color.YELLOW);
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), pixelSize, pixelSize);
                }
                // Body of Snake
                else
                {
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), pixelSize, pixelSize);
                }
            }
            // Syncing the graphics together
            Toolkit.getDefaultToolkit().sync();
         
        }
        else
        {
            end(g);
        }
    }
    void initGame() 
    {
        
        //set the snake's size
        snake.setJoint(3); 
        
        // Create the snake's body
        for(int i = 0; i < snake.getJoint(); i++)
        {
            snake.setSnakeX(boardWidth / 2);
            snake.setSnakeY(boardHeight / 2);
        }
       
        // Start moving the snake to the right
        snake.setMoveRight(true);
        
        // First Protein appears
        protein.createProtein();
        
        // Set timer to make the game move
        timer = new Timer(speed, this);
        timer.start();
    }
     void end(Graphics g)
    {
        // Game over message
        String text = "Game over";
        String text2 = "Press R to restart";
        String text3 = "Thanks for playing";
        
        // Set color for text
        g.setColor(Color.BLUE);
        
        // New font instance
        Font font = new Font("Arial", Font.BOLD, 50);
        FontMetrics metrics = getFontMetrics(font);
        
        // Drawing the message on board
        g.drawString(text, (boardWidth - metrics.stringWidth(text)) / 4, 
                boardHeight / 4);
        g.drawString(text2, (boardWidth - metrics.stringWidth(text)) / 2, 
                boardHeight / 2);
         g.drawString(text3, (boardWidth - metrics.stringWidth(text)) / 3, 
                boardHeight / 3);
        
    }
    // Making sure that the game runs constantly
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        
        if(inGame == true)
        {
            snake.move();
            checkCollision();
            checkProteinCollision();
        }
        // Render the screen
        repaint();
    }
 
    private boolean proximity(int a, int b, int closer)
    {
        return Math.abs((long)a - b) <= closer;
    }
    public static int getAllDots()
    {
        return totalPixels;
    }
    
    public static int getDotSize()
    {
        return pixelSize;
    }

    // Checking the collision with snake and edges
    private void checkCollision() 
    {
       // when the snake hits his own joints
        for(int i = snake.getJoint(); i > 0; i--)
        {
            // Snake cannot intersect itself
            if((i > 5) && (snake.getSnakeX(0) == snake.getSnakeX(i) && snake.getSnakeY(0) == snake.getSnakeY(i)))
            {
                // Game ends
                inGame = false;
            }
        }
        // if the snake intersects the edges
        if(snake.getSnakeY(0) >= boardHeight)
        {
            inGame = false;
        }
        if(snake.getSnakeY(0) < 0)
        {
            inGame = false;
        }
        if(snake.getSnakeX(0) >= boardWidth)
        {
            inGame = false;
        }
        if(snake.getSnakeX(0) < 0)
        {
            inGame = false;
        }
        if(!inGame)
        {
            timer.stop();
        }
    }
    private void checkProteinCollision() 
    {
        if((proximity(snake.getSnakeX(0), protein.getProteinX(), 20) 
                && (proximity(snake.getSnakeY(0), protein.getProteinY(), 20))))
        {       
            // Adds the joint
            snake.setJoint((snake.getJoint() + 1));
            
            // Create a new protein
            protein.createProtein();
        }
    }
    
}
