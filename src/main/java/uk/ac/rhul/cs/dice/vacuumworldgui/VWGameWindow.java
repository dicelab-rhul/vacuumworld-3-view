package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWStopAndSaveButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWGameGridPanel;

public class VWGameWindow {
    private JFrame window;
    private BorderLayout layout;

    public VWGameWindow(VWState state, int gridSize) {
	buildWindow(state, gridSize);
    }
    
    private void buildWindow(VWState state, int gridSize) {
	this.window = new JFrame();
	this.window.setTitle("Welcome to the grid!");
	
	this.layout = new BorderLayout();
	
	this.window.setLayout(this.layout);
	
	VWGameGridPanel grid = new VWGameGridPanel(this.window, gridSize, state);
	
	VWStopAndSaveButton b = new VWStopAndSaveButton(this.window, state);
	b.createButton();
	
	this.window.add(b.getButton(), BorderLayout.SOUTH);
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	this.window.setLocationRelativeTo(null);
	this.window.setVisible(true);
    }
}