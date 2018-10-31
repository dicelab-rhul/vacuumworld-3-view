package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseEvent;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWSelectionDialog;

public class VWToggleLocationListener extends VWAbstractToggleLocationListener {
    private Coordinates coordinates;
    
    public VWToggleLocationListener(Component parent, Coordinates coordinates) {
	super(parent);
	
	this.coordinates = coordinates;
    }

    private void createAndShowSelectionDialog() {
	VWSelectionDialog dialog = new VWSelectionDialog(getParent(), this.coordinates);
	dialog.createDialog();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	LogUtils.log("Clicked on a location!!!");
	createAndShowSelectionDialog();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// VWToggleLocationListerer does not need to override this.
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// VWToggleLocationListerer does not need to override this.
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// VWToggleLocationListerer does not need to override this.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// VWToggleLocationListerer does not need to override this.
    }
}