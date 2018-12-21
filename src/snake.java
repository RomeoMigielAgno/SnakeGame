/**
	File name: snake.java
	Short description: Create the body and joint of snake
	IST 242 Assignment: 
	@author Romeo-Migiel, Yusra
	@version 1.01 4/19/2018
*/

public class snake 
{
    // Stores the joints and body parts of snake
    private final int[] x = new int[board.getAllDots()];
    private final int [] y = new int[board.getAllDots()];
    
    // Stores the snake's direction
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;
    
    // Stores number of dots (Start with 3)
    private int joint = 0;
    
    public int getSnakeX(int index)
    {
        return x[index];
    }
    
    public int getSnakeY(int index)
    {
        return y[index];
    }
    
    public void setSnakeX(int i)
    {
        x[0] = i;
    }
    
    public void setSnakeY(int i)
    {
        x[0] = i;
    }

    public boolean isMoveLeft() 
    {
        return moveLeft;
    }

    public boolean isMoveRight() 
    {
        return moveRight;
    }

    public boolean isMoveUp() 
    {
        return moveUp;
    }

    public boolean isMoveDown() 
    {
        return moveDown;
    }
    
    public void setMoveLeft(boolean moveLeft) 
    {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) 
    {
        this.moveRight = moveRight;
    }

    public void setMoveUp(boolean moveUp)
    {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) 
    {
        this.moveDown = moveDown;
    }

    public int getJoint() 
    {
        return joint;
    }

    public void setJoint(int j) 
    {
        joint = j;
    }
    
    // Moving snake in joints
    public void move()
    {
        for(int i = joint; i > 0; i--)
        {
            // All the joints move up by one
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }
        
        // Move snake to the left, right , up, down
        if(moveLeft)
        {
            x[0] -= board.getDotSize();
        }
        if(moveRight)
        {
            x[0] += board.getDotSize();
        }
        if(moveUp)
        {
            y[0] -= board.getDotSize();
        }
        if(moveDown)
        {
            y[0] += board.getDotSize();
        }
    }
}
