package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public abstract class VWAbstractButton implements VWButton {
    private String text;
    private ActionListener actionListener;
    private Component parent;
    protected JButton button;
    protected GridBagConstraints constraints;
    
    public VWAbstractButton(String text, ActionListener actionListener, Component parent) {
	this.text = text;
	this.actionListener = actionListener;
	this.parent = parent;
    }
    
    public ActionListener getActionListener() {
	return this.actionListener;
    }
    
    @Override
    public JButton getButton() {
	return this.button;
    }

    @Override
    public GridBagConstraints getConstraints() {
	return this.constraints;
    }
    
    @Override
    public Component getParent() {
        return this.parent;
    }
    
    @Override
    public void createButton() {
	this.button = new JButton(this.text);
	this.constraints = new GridBagConstraints();
	this.constraints.insets = new Insets(5, 5, 5, 5);
	this.button.setPreferredSize(new Dimension(100, 40));
	this.button.addActionListener(this.actionListener);
    }
}