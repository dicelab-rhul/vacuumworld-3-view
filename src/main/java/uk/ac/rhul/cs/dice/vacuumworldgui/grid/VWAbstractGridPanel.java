package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public abstract class VWAbstractGridPanel implements VWGridPanel {
    private Component parent;
    private int gridSize;
    private volatile VWState state;
    private GridBagLayout layout;
    private JPanel grid;
    
    public VWAbstractGridPanel(Component parent, int gridSize, VWState state) {
	this.parent = parent;
	this.gridSize = gridSize;
	this.state = state;
	
	this.layout = new GridBagLayout();
	this.grid = new JPanel(this.layout);
    }
    
    public VWAbstractGridPanel(Component parent, int gridSize) {
	this(parent, gridSize, VWState.getInstance(gridSize));
    }
    
    public void displayGrid() {
	this.grid.repaint();
	this.grid.revalidate();
	this.grid.setVisible(true);
    }
    
    public void refreshParent() {
	((JFrame) this.parent).pack();
	this.parent.repaint();
	this.parent.revalidate();
    }

    public Component getParent() {
        return this.parent;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public VWState getState() {
        return this.state;
    }

    public GridBagLayout getLayout() {
        return this.layout;
    }

    public void setLayout(GridBagLayout layout) {
        this.layout = layout;
    }

    public JPanel getGrid() {
        return grid;
    }

    public void setGrid(JPanel grid) {
        this.grid = grid;
    }
}