package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWButtonsPanel;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWGameGridPanel;

public class VWGameWindow {
    private JFrame window;
    private BorderLayout layout;

    public VWGameWindow() {
	buildWindow();
    }
    
    private void buildWindow() {
	int gridSize = VWState.getExistingInstance().getGridSize();
	
	this.window = new JFrame();
	this.window.setTitle("Welcome to the grid!");
	
	this.layout = new BorderLayout();
	
	this.window.setLayout(this.layout);
	
	VWGameGridPanel grid = new VWGameGridPanel(this.window, gridSize);
	
	VWButtonsPanel buttonsPanel = new VWButtonsPanel(this.window);
	buttonsPanel.generatePanelForGameWindow();
	
	this.window.add(buttonsPanel.getPanel(), BorderLayout.SOUTH);
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.window.pack();
	this.window.repaint();
	this.window.revalidate();
	this.window.setLocationRelativeTo(null);
	this.window.setVisible(true);
	
	LogUtils.log(VWState.getExistingInstance().serializeState().toString());
    }
    
    public void reset(int gridSize) {
	VWGameGridPanel grid = new VWGameGridPanel(this.window, gridSize);
	
	this.window.remove(this.layout.getLayoutComponent(BorderLayout.CENTER));
	this.window.add(grid.getGrid(), BorderLayout.CENTER);
	this.window.repaint();
	this.window.revalidate();
    }
    
    public JFrame getWindow() {
	return this.window;
    }
}