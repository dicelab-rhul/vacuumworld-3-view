package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseEvent;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWSelectionDialog;

public class VWToggleLocationListener extends VWAbstractToggleLocationListener {
    private VWState state;
    private Coordinates coordinates;
    
    public VWToggleLocationListener(Component parent, VWState state, Coordinates coordinates) {
	super(parent);
	
	this.state = state;
	this.coordinates = coordinates;
    }

    private void createAndShowSelectionDialog() {
	VWSelectionDialog dialog = new VWSelectionDialog(getParent(), this.state, this.coordinates);
	dialog.createDialog();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	System.out.println("Clicked on a location!!!");
	createAndShowSelectionDialog();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }
}