package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.Main;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWGameLocationPanel {
    private JPanel locationPanel;
    private GridBagConstraints constraints;
    private Component parent;

    public VWGameLocationPanel(Component parent, VWState state, String filePath, int x, int y) {
	this.locationPanel = new JPanel();
	this.locationPanel.setLayout(new GridLayout());
	this.parent = parent;
	
	this.constraints = new GridBagConstraints();
	this.constraints.gridx = x;
	this.constraints.gridy = y;
	this.constraints.insets = new Insets(0, 0, 0, 0);
	
	JLabel label = new JLabel();
	
	try {
	    label.setIcon(new ImageIcon(ImageIO.read(Main.class.getResourceAsStream(filePath))));
	}
	catch(Exception e) {
	    LogUtils.log(e);
	}
	
	this.locationPanel.add(label);
    }
    
    public Component getParent() {
	return this.parent;
    }
    
    public JPanel getLocationPanel() {
	return this.locationPanel;
    }
    
    public GridBagConstraints getConstraints() {
	return this.constraints;
    }
}