package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWGameGridPanel extends VWAbstractGridPanel {

    public VWGameGridPanel(Component parent, int gridSize, VWState state) {
	super(parent, gridSize, state);
	
	for(int i = 0; i < getGridSize(); i++) {
	    for(int j = 0; j < getGridSize(); j++) {
		Coordinates coordinates = new Coordinates(i, j);
		String img = getState().getLocations().get(coordinates).getP1().getImgPath();
		
		VWGameLocationPanel panel = new VWGameLocationPanel(getParent(), getState(), img, i, j);
		
		getGrid().add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	getGrid().repaint();
	getGrid().revalidate();
	getGrid().setVisible(true);
    }
}