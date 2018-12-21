/**
	File name: protein.java
	Short description: Create the "food"
	IST 242 Assignment: 
	@author Romeo-Migiel, Yusra
	@version 1.01 4/19/2018
*/
public class protein 
{
    private snake snake = new snake();
    
    // Store X and Y of our Protein
    private int proteinX;
    private int proteinY;
    
    // Determining the random position
    private final int randomPos = 25;
    
    public void createProtein()
    {
        // Setting the protein's X and Y position
        int location = (int)(Math.random() * randomPos);
        proteinX = ((location * board.getDotSize()));
        
        location = (int)(Math.random() * randomPos);
        proteinY = ((location * board.getDotSize()));
        
        if((proteinX == snake.getSnakeX(0)) && (proteinY == snake.getSnakeY(0)))
        {
            createProtein();
        }
    }
    
    public int getProteinX()
    {
        return proteinX;
    }
    
    public int getProteinY()
    {
        return proteinY;
    }
}
