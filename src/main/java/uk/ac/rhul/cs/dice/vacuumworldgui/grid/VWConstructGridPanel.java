package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class VWConstructGridPanel extends VWAbstractGridPanel {
    private JPanel grid;
    private Component parent;
    
    public VWConstructGridPanel(Component parent) {
	this.parent = parent;
	
	GridBagLayout layout = new GridBagLayout();
	
	this.grid = new JPanel(layout);
	
	for(int i = 0; i < 5; i++) {
	    for(int j = 0; j < 5; j++) {
		VWLocationPanel panel = new VWLocationPanel(this.parent, "res/imgs/locations/white_square.png", i, j);
		
		this.grid.add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	this.grid.repaint();
	this.grid.revalidate();
	this.grid.setVisible(true);
    }
    
    public JPanel getGrid() {
	return this.grid;
    }
}