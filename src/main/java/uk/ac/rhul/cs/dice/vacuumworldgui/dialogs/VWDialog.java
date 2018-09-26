package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;

public interface VWDialog {
    public abstract void createDialog();
    public abstract Component getParent();
}