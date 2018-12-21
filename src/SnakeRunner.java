/**
	File name: SnakeRunner.java
	Short description: Main class to run snake game
	IST 242 Assignment: 
	@author Romeo-Migiel, Yusra
	@version 1.01 4/19/2018
*/

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SnakeRunner extends JFrame 
{      
    // Constructor
    public SnakeRunner()
    {       
//        add(new Menu());
        add(new board());       
        pack();    
    }
   
    public static void main(String[] args) throws Exception
    {
        // Causing a run method to be executed asynchronously
        EventQueue.invokeLater(() -> 
        {
            JFrame frame = new SnakeRunner();
            frame.setVisible(true);
        }); 

    }  
}
