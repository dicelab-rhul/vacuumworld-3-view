package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VWFatalErrorDialog extends VWAbstractDialog {

    public VWFatalErrorDialog(Component parent) {
	super("Fatal error!", parent, true, false);
    }
    
    @Override
    public void createDialog() {
        super.createDialog();
        
	JLabel errorLabel = new JLabel("<html>Fatal error from the server!<br/>Close this dialog to stop the system.</html>", SwingConstants.CENTER);
	
	this.dialog.setLayout(new GridLayout(1, 1));
	this.dialog.add(errorLabel, 0);
	this.dialog.setMinimumSize(new Dimension(400, 100));
        
        getDialog().setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        
        super.positionAndDisplay();
    }
}