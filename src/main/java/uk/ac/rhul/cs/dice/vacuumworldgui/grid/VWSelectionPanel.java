package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;

public class VWSelectionPanel implements VWGridPanel {
    private JPanel grid;
    private Component parent;
    private static final int NUMBER_OF_DIFFERENT_PIECES = 14; //4 white + 4 green + 4 user + 1 green dirt + 1 orenge dirt.
    private static final int SQUARE_SIZE = (int) Math.ceil(Math.sqrt((NUMBER_OF_DIFFERENT_PIECES + 1))); // +1 for the empty location sprite. 

    public VWSelectionPanel(Component parent, Coordinates coordinates) {
	this.parent = parent;
	
	GridBagLayout layout = new GridBagLayout();

	this.grid = new JPanel(layout);

	for(int i = 0; i < SQUARE_SIZE; i++) {
	    for(int j = 0; j < SQUARE_SIZE; j++) {
		VWSelectionDialogLocationPanel panel = constructPanel(coordinates, i, j);
		
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
    
    private VWSelectionDialogLocationPanel constructPanel(Coordinates coordinates, int i, int j) {
	switch(i) {
	case 0:
	    return getGreenAgentPanel(coordinates, j);
	case 1:
	    return getOrangeAgentPanel(coordinates, j);
	case 2:
	    return getWhiteAgentPanel(coordinates, j);
	case 3:
	    return getOtherPanel(coordinates, j);
	default:
	    throw new IllegalArgumentException();
	}
    }

    private VWSelectionDialogLocationPanel getGreenAgentPanel(Coordinates coordinates, int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/green_north.png", 0, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/green_south.png", 0, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/green_east.png", 0, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/green_west.png", 0, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getOrangeAgentPanel(Coordinates coordinates, int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/orange_north.png", 1, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/orange_south.png", 1, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/orange_east.png", 1, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/orange_west.png", 1, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getWhiteAgentPanel(Coordinates coordinates, int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/white_north.png", 2, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/white_south.png", 2, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/white_east.png", 2, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/white_west.png", 2, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    private VWSelectionDialogLocationPanel getOtherPanel(Coordinates coordinates, int j) {
	switch(j) {
	case 0:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/green_dirt.png", 3, j);
	case 1:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/orange_dirt.png", 3, j);
	case 2:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/user_north.png", 3, j);
	case 3:
	    return new VWSelectionDialogLocationPanel(this.parent, coordinates, "/imgs/locations/white_square.png", 3, j);
	default:
	    throw new IllegalArgumentException();
	}
    }
}