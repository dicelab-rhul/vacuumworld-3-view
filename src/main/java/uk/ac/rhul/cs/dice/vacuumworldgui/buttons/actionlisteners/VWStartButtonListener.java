package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import uk.ac.rhul.cs.dice.vacuumworldgui.ConstructGameWindow;

public class VWStartButtonListener extends VWAbstractButtonListener {

    public VWStartButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(ActionEvent.ACTION_PERFORMED == e.getID()) {
	    System.out.println("Clicked on Start!!!");
	    createAndShowConstructGameWindow();
	}
    }

    private void createAndShowConstructGameWindow() {
	ConstructGameWindow window = new ConstructGameWindow();
	getParent().setVisible(false);
	getParent().invalidate();
    }
}