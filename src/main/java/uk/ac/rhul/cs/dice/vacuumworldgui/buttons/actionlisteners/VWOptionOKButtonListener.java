package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;

public class VWOptionOKButtonListener extends VWAbstractButtonListener {

    public VWOptionOKButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	((JDialog) getParent()).invalidate();
	((JDialog) getParent()).dispose();
    }
}