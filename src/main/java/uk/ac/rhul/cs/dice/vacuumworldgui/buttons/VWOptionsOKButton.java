package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWOptionOKButtonListener;

public class VWOptionsOKButton extends VWAbstractButton {

    public VWOptionsOKButton(Component parent) {
	super("OK", new VWOptionOKButtonListener(parent), parent);
    }
}