package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWGameGridPanel extends VWAbstractGridPanel {

    public VWGameGridPanel(Component parent, int gridSize) {
	super(parent, gridSize);
	
	for(int i = 0; i < getGridSize(); i++) {
	    for(int j = 0; j < getGridSize(); j++) {
		Coordinates coordinates = new Coordinates(i, j);
		String img = VWState.getExistingInstance().getLocations().get(coordinates).getP1().getImgPath();
		
		VWGameLocationPanel panel = new VWGameLocationPanel(getParent(), img, i, j);
		
		getGrid().add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	getGrid().repaint();
	getGrid().revalidate();
	getGrid().setVisible(true);
    }
}