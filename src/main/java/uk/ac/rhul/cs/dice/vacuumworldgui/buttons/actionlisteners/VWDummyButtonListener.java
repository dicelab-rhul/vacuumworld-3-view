package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.cloudstrife9999.logutilities.LogUtils;

public class VWDummyButtonListener extends VWAbstractButtonListener {

    public VWDummyButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	LogUtils.log("This feature is not available at the moment.");
    }
}