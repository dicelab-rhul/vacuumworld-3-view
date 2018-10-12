package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VWCreditsDialog extends VWAbstractDialog {
    
    public VWCreditsDialog(Component parent) {
	super("Credits", parent, true, false);
    }
    
    @Override
    public void createDialog() {	
	super.createDialog();
	
	String creditsString = "<html>VacuumWorld 3.0<br/><br/>Powered by:<br/><br/>Emanuele Uliana<br/>Benedict Wilkins<br/>Joel Clarke<br/>Kostas Stathis</html>";
	JLabel creditsLabel = new JLabel(creditsString, SwingConstants.CENTER);
	
	this.dialog.add(creditsLabel);
	this.dialog.setMinimumSize(new Dimension(200, 250));
	
	super.positionAndDisplay();
    }
}