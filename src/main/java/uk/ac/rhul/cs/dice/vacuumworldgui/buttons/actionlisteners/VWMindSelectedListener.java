package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;

public class VWMindSelectedListener implements ActionListener {
    private String type;
    
    public VWMindSelectedListener(String type) {
	this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JComboBox<?> source = (JComboBox<?>) e.getSource();
	String selected = (String) source.getSelectedItem();
	
	System.out.println("Mind changed: " + this.type + " now has " + selected + ".");
	
	VWGameProperties.getInstance().setMind(this.type, selected);
    }
}