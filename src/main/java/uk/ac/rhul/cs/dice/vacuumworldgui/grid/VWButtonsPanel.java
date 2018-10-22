package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWDummyButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWPauseButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWSaveButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWStartSimulationButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWStopButton;

public class VWButtonsPanel {
    private Component parent;
    private JPanel panel;
    private int size;
    
    public VWButtonsPanel(Component parent) {
	this.parent = parent;
	this.panel = new JPanel();
	this.size = 55 * Math.max(VWGameProperties.getInstance().getGridSize(), 5);
	this.panel.setLayout(new GridLayout(1, 3));
	this.panel.setMaximumSize(new Dimension(size, 40));
	this.panel.setSize(new Dimension(size, 40));
	this.panel.setPreferredSize(new Dimension(size, 40));
    }
    
    public void generatePanelForConstructGameWindow() {
	VWStartSimulationButton b = new VWStartSimulationButton(this.parent);
	b.createButton();
	
	VWDummyButton b2 = new VWDummyButton("Stop", this.parent);
	b2.createButton();
	
	VWSaveButton b3 = new VWSaveButton(this.parent);
	b3.createButton();
	b3.setEnabled();
	
	this.panel.add(b.getButton());
	this.panel.add(b2.getButton());
	this.panel.add(b3.getButton());
    }
    
    public void generatePanelForGameWindow() {
	VWStopButton b2 = new VWStopButton(this.parent);
	b2.createButton();
	
	VWSaveButton b3 = new VWSaveButton(this.parent);
	b3.createButton();
	
	VWPauseButton b = new VWPauseButton(this.parent);
	b.createButton();
	b.setStopButtonRefecence(b2);
	b.setSaveButtonListener(b3);
	
	this.panel.add(b.getButton());
	this.panel.add(b2.getButton());
	this.panel.add(b3.getButton());
    }
    
    public JPanel getPanel() {
	return this.panel;
    }
}