package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWCreditsDialog;

public class VWCreditsButtonListener extends VWAbstractButtonListener {
    
    public VWCreditsButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(ActionEvent.ACTION_PERFORMED == e.getID()) {
	    LogUtils.log("Clicked on Credits!!!");
	    createAndDisplayCredits();
	}
    }

    private void createAndDisplayCredits() {
	VWCreditsDialog creditsDialog = new VWCreditsDialog(getParent());
	creditsDialog.createDialog();
    }
}