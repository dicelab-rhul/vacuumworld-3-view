package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWConstructGridPanel extends VWAbstractGridPanel {
    
    public VWConstructGridPanel(Component parent, int gridSize) {
	super(parent, gridSize);
	
	VWState.getExistingInstance().setCallback(this);
	
	for(int i = 0; i < getGridSize(); i++) {
	    for(int j = 0; j < getGridSize(); j++) {
		Coordinates coordinates = new Coordinates(i, j);
		VWState.getExistingInstance().addEmptyLocationFromView(coordinates);
		VWLocationPanel panel = new VWLocationPanel(getParent(), VWGameProperties.getInstance().getWhiteLocationPath(), i, j);
		
		getGrid().add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	displayGrid();
    }
    
    public void update() {
	getGrid().removeAll();
	
	for(int i = 0; i < getGridSize(); i++) {
	    for(int j = 0; j < getGridSize(); j++) {
		Coordinates coordinates = new Coordinates(i, j);
		String img = VWState.getExistingInstance().getLocations().get(coordinates).getP1().getImgPath();
		VWLocationPanel panel = new VWLocationPanel(getParent(), img, i, j);
		
		getGrid().add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	displayGrid();
	refreshParent();
    }
}