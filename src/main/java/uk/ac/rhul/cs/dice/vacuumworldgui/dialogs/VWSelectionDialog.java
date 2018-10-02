package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWSelectionPanel;

public class VWSelectionDialog extends VWAbstractDialog {
    private VWState state;
    private Coordinates coordinates;
    
    public VWSelectionDialog(Component parent, VWState state, Coordinates coordinates) {
	super("Please select a component to place.", parent, true, false);
	
	this.state = state;
	this.coordinates = coordinates;
    }
    
    @Override
    public void createDialog() {	
	super.createDialog();
	
	VWSelectionPanel panel = new VWSelectionPanel(getDialog(), this.state, this.coordinates);
	
	this.dialog.add(panel.getGrid());
	this.dialog.setMinimumSize(new Dimension(200, 250));
	
	super.positionAndDisplay();
    }
}