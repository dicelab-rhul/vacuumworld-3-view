package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWButtonsPanel;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWConstructGridPanel;

public class VWConstructGameWindow {
    private JFrame window;
    private BorderLayout layout;
    
    public VWConstructGameWindow() {
	buildWindow();
    }

    private void buildWindow() {
	this.window = new JFrame();
	this.window.setTitle("Please, position the actors and the pieces of dirt.");
	
	this.layout = new BorderLayout();
	
	this.window.setLayout(this.layout);
	
	VWConstructGridPanel grid = new VWConstructGridPanel(this.window, VWGameProperties.getInstance().getGridSize());
	
	VWButtonsPanel buttonsPanel = new VWButtonsPanel(this.window);
	buttonsPanel.generatePanelForConstructGameWindow(grid.getState());
	
	this.window.add(buttonsPanel.getPanel(), BorderLayout.SOUTH);
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	this.window.setLocationRelativeTo(null);
	this.window.setVisible(true);
    }
}