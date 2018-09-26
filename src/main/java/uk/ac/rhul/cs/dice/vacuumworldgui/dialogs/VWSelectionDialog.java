package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;

import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWSelectionPanel;

public class VWSelectionDialog extends VWAbstractDialog {

    public VWSelectionDialog(Component parent) {
	super("Please select a component to place.", parent, true, false);
    }
    
    @Override
    public void createDialog() {	
	super.createDialog();
	
	VWSelectionPanel panel = new VWSelectionPanel(getParent());
	
	this.dialog.add(panel.getGrid());
	this.dialog.setMinimumSize(new Dimension(200, 250));
	
	super.positionAndDisplay();
    }
}