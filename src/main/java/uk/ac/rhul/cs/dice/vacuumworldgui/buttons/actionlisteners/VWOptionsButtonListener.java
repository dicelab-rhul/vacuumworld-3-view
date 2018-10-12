package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWOptionsDialog;

public class VWOptionsButtonListener extends VWAbstractButtonListener {

    public VWOptionsButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(ActionEvent.ACTION_PERFORMED == e.getID()) {
	    LogUtils.log("Clicked on Options!!!");
	    createAndDisplayOptions();
	}
    }

    private void createAndDisplayOptions() {
	VWOptionsDialog optionsDialog = new VWOptionsDialog(getParent());
	optionsDialog.createDialog();
    }
}