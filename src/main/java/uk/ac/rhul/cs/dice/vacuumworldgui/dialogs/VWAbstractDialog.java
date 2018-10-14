package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;

public abstract class VWAbstractDialog implements VWDialog {
    private String title;
    private boolean modal;
    private boolean resizable;
    private Component parent;
    protected JDialog dialog;
    
    public VWAbstractDialog(String title, Component parent, boolean modal, boolean resizable) {
	this.parent = parent;
	this.title = title;
	this.modal = modal;
	this.resizable = resizable;
    }
    
    @Override
    public void createDialog() {
	this.dialog = new JDialog((Frame) this.parent);
	this.dialog.setTitle(this.title);
	this.dialog.setModal(this.modal);
	this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.dialog.setResizable(this.resizable);
    }
    
    protected void positionAndDisplay() {
	this.dialog.pack();
	this.dialog.setLocationRelativeTo(this.parent);
	this.dialog.validate();
	this.dialog.setVisible(true);
    }
    
    public JDialog getDialog() {
	return this.dialog;
    }
    
    @Override
    public Component getParent() {
        return this.parent;
    }
    
    @Override
    public JButton createOkButton() {
	JButton button = new JButton("OK");
	
	button.addActionListener(e -> getDialog().dispose());
	
	return button;
    }
}