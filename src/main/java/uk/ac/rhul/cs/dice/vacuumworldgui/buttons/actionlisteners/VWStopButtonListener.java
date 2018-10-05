package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;

public class VWStopButtonListener extends VWAbstractButtonListener {
    
    public VWStopButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("Stopping the simulation...");
	
	VWGameProperties.getInstance().getManager().sendStopToModel();
	
	System.out.println("Done.");
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
    }
}