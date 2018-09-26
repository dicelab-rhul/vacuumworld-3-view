package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWConstructGridPanel;

public class ConstructGameWindow {
    private JFrame window;
    private BorderLayout layout;
    
    public ConstructGameWindow() {
	buildWindow();
    }

    private void buildWindow() {
	this.window = new JFrame();
	this.window.setTitle("Please, position the actors and the pieces of dirt.");
	
	this.layout = new BorderLayout();
	
	this.window.setLayout(this.layout);
	
	VWConstructGridPanel grid = new VWConstructGridPanel(this.window);
	
	this.window.add(new JLabel("Test1"), BorderLayout.NORTH);
	this.window.add(new JLabel("Test2"), BorderLayout.SOUTH);
	this.window.add(new JLabel("Test3"), BorderLayout.EAST);
	this.window.add(grid.getGrid(), BorderLayout.WEST);
	this.window.add(new JLabel("Test5"), BorderLayout.CENTER);
	
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	this.window.setVisible(true);
    }
}