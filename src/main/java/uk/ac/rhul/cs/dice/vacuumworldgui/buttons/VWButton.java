package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JButton;

public interface VWButton {
    public abstract JButton getButton();
    public abstract GridBagConstraints getConstraints();
    public abstract Component getParent();
    public abstract void createButton();
}