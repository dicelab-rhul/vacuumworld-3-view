package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;

public class VWStopButtonListener extends VWAbstractButtonListener {
    
    public VWStopButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	LogUtils.log("Stopping the simulation...");
	
	VWGameProperties.getInstance().getManager().sendStopToModel();
	
	LogUtils.log("Done.");
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
    }
}