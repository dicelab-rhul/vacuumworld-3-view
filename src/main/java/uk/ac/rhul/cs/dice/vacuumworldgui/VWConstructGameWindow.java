package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWButtonsPanel;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWConstructGridPanel;

public class VWConstructGameWindow {
    
    public VWConstructGameWindow() {
	buildWindow();
    }

    private void buildWindow() {
	JFrame window = new JFrame();
	window.setTitle("Please, position the actors and the pieces of dirt.");
	window.setLayout(new BorderLayout());
	
	VWConstructGridPanel grid = new VWConstructGridPanel(window, VWGameProperties.getInstance().getGridSize());
	
	VWButtonsPanel buttonsPanel = new VWButtonsPanel(window);
	buttonsPanel.generatePanelForConstructGameWindow(grid.getState());
	
	window.add(buttonsPanel.getPanel(), BorderLayout.SOUTH);
	window.add(grid.getGrid(), BorderLayout.CENTER);
	
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	window.pack();
	window.repaint();
	window.revalidate();
	window.setLocationRelativeTo(null);
	window.setVisible(true);
    }
}