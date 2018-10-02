package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWToggleLocationListener;

public class VWLocationPanel {
    private JPanel locationPanel;
    private GridBagConstraints constraints;
    private Component parent;
    
    public VWLocationPanel(Component parent, VWState state, String filePath, int x, int y) {
	Coordinates coordinates = new Coordinates(x, y);
	
	this.locationPanel = new JPanel();
	this.locationPanel.setLayout(new GridLayout());
	this.parent = parent;
	
	this.constraints = new GridBagConstraints();
	this.constraints.gridx = x;
	this.constraints.gridy = y;
	this.constraints.insets = new Insets(0, 0, 0, 0);
	
	JLabel label = new JLabel();
	label.setIcon(new ImageIcon(filePath));
	label.addMouseListener(new VWToggleLocationListener(this.parent, state, coordinates));
	
	this.locationPanel.add(label);
    }
    
    public JPanel getLocationPanel() {
	return this.locationPanel;
    }
    
    public GridBagConstraints getConstraints() {
	return this.constraints;
    }
}