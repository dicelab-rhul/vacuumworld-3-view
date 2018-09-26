package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class VWSelectionPanel extends VWAbstractGridPanel {
    private JPanel grid;
    private Component parent;

    public VWSelectionPanel(Component parent) {
	this.parent = parent;
	
	GridBagLayout layout = new GridBagLayout();

	this.grid = new JPanel(layout);

	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 4; j++) {
		VWSelectionDialogLocationPanel panel = constructPanel(i, j);
		
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
    
    private VWSelectionDialogLocationPanel constructPanel(int i, int j) {
	switch(i) {
	case 0:
	    return getGreenAgentPanel(j);
	case 1:
	    return getOrangeAgentPanel(j);
	case 2:
	    return getWhiteAgentPanel(j);
	case 3:
	    return getOtherPanel(j);
	default:
	    throw new IllegalArgumentException();
	}
    }

    private VWSelectionDialogLocationPanel getGreenAgentPanel(int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/green_north.png", 0, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/green_south.png", 0, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/green_east.png", 0, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/green_west.png", 0, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getOrangeAgentPanel(int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/orange_north.png", 1, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/orange_south.png", 1, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/orange_east.png", 1, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/orange_west.png", 1, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getWhiteAgentPanel(int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_north.png", 2, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_south.png", 2, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_east.png", 2, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_west.png", 2, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getOtherPanel(int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/green_dirt.png", 3, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/orange_dirt.png", 3, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_square.png", 3, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, "res/imgs/locations/white_square.png", 3, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
}