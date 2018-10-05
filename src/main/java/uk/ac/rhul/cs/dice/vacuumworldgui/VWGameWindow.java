package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWButtonsPanel;
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
	
	VWButtonsPanel buttonsPanel = new VWButtonsPanel(this.window);
	buttonsPanel.generatePanelForGameWindow(state);
	
	this.window.add(buttonsPanel.getPanel(), BorderLayout.SOUTH);
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	this.window.setLocationRelativeTo(null);
	this.window.setVisible(true);
	
	System.out.println(state.serializeState().toString());
    }
    
    public void reset(VWState state, int gridSize) {
	//this.window.invalidate();
	VWGameGridPanel grid = new VWGameGridPanel(this.window, gridSize, state);
	
	//this.layout = new BorderLayout();
	//this.window.setLayout(this.layout);
	this.window.remove(this.layout.getLayoutComponent(BorderLayout.CENTER));
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	
	//this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	//this.window.setVisible(true);
    }
    
    public JFrame getWindow() {
	return this.window;
    }
}