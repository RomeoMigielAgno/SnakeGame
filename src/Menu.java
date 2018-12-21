/**
	File name: Menu.java
	Short description: Creating a menu
	IST 242 Assignment: 
	@author Romeo-Migiel, Yusra
	@version 1.01 4/19/2018
*/
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Menu extends JPanel implements ActionListener      
{
    private int width = 1000;
    private int height = 980;
    
    JButton playButton = new JButton("Play");
    JButton helpButton = new JButton("Controls");
    JButton exitButton = new JButton("Exit");
    
    //CardLayout layout = new CardLayout();
    
    JPanel panel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel menuPanel = new JPanel();
    
    public Menu()
    {   
        //panel.setLayout(layout);
        addButtons();
        setSize(width, height);
        setVisible(true);
        requestFocus();
    }

    private void addButtons() 
    {      
        playButton.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        // Adding Menu Button
        menuPanel.add(playButton);
        menuPanel.add(helpButton);
        menuPanel.add(exitButton);
        
        // Adding Background Color
        menuPanel.setBackground(Color.yellow);
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        Object source = ae.getSource();
        
        // Exit button to exit the program
        if(source == exitButton)
        {
            System.exit(0);
        }
        
        else if (source == playButton)
        {
            //layout.show(panel, "board");
        }
        
        else if(source == helpButton)
        {
            
        }
    }
}
