package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VWSaveResultDialog extends VWAbstractDialog {
    private boolean saved;
    private String path;

    public VWSaveResultDialog(Component parent, boolean saved, String path) {
	super("Info", parent, true, false);

	this.saved = saved;
	this.path = path;
    }

    @Override
    public void createDialog() {
	super.createDialog();

	String result = createResultString();
	JLabel resultLabel = new JLabel(result, SwingConstants.CENTER);
	JButton okButton = createOkButton();
	
	this.dialog.setLayout(new GridLayout(2, 1));
	this.dialog.add(resultLabel, 0);
	this.dialog.add(okButton, 1);
	this.dialog.setMinimumSize(new Dimension(400, 100));
	
	super.positionAndDisplay();
    }

    private String createResultString() {
	if (!this.saved) {
	    return "The state has not been saved, as it is an illegal state.";
	}
	else if (this.path == null) {
	    return "There was an unknown error while saving the state.";
	}
	else {
	    return "<html>The state has been saved to " + this.path + "</html>";
	}
    }
}