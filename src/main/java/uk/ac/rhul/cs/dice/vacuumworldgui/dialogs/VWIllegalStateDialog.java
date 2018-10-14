package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VWIllegalStateDialog extends VWAbstractDialog {
    private String message;
    
    public VWIllegalStateDialog(Component parent, String message) {
	super("Illegal initial state!", parent, false, false);
	
	this.message = message;
    }
    
    @Override
    public void createDialog() {
	super.createDialog();
	
	JLabel errorLabel = new JLabel(this.message, SwingConstants.CENTER);
	JButton okButton = createOkButton();
	
	this.dialog.setLayout(new GridLayout(2, 1));
	this.dialog.add(errorLabel, 0);
	this.dialog.add(okButton, 1);
	this.dialog.setMinimumSize(new Dimension(400, 100));
	
	super.positionAndDisplay();
    }
}