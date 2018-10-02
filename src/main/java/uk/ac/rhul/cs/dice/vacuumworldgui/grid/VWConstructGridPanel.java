package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;

public class VWConstructGridPanel extends VWAbstractGridPanel {
    
    public VWConstructGridPanel(Component parent, int gridSize) {
	super(parent, gridSize);
	
	getState().setCallback(this);
	
	for(int i = 0; i < getGridSize(); i++) {
	    for(int j = 0; j < getGridSize(); j++) {
		Coordinates coordinates = new Coordinates(i, j);
		getState().addEmptyLocationFromView(coordinates);
		VWLocationPanel panel = new VWLocationPanel(getParent(), getState(), "/res/imgs/locations/white_square.png", i, j);
		
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
		String img = getState().getLocations().get(coordinates).getP1().getImgPath();
		VWLocationPanel panel = new VWLocationPanel(getParent(), getState(), img, i, j);
		
		getGrid().add(panel.getLocationPanel(), panel.getConstraints());
	    }
	}
	
	displayGrid();
	refreshParent();
    }
}