package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;

import javax.swing.JButton;

public interface VWDialog {
    public abstract void createDialog();
    public abstract Component getParent();
    public abstract JButton createOkButton();
}