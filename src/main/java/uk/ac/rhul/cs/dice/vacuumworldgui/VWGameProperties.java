package uk.ac.rhul.cs.dice.vacuumworldgui;

public class VWGameProperties {
    private static VWGameProperties instance;
    private int gridSize;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    private static final int DEFAULT_SIZE = 5;
    
    private VWGameProperties() {}
    
    private VWGameProperties(int gridSize) {
	this.gridSize = gridSize;
    }

    public static VWGameProperties getInstance() {
	if(VWGameProperties.instance == null) {
	    VWGameProperties.instance = new VWGameProperties();
	}
	
	return VWGameProperties.instance;
    }
    
    public static VWGameProperties getInstance(int gridSize) {
	if(VWGameProperties.instance == null) {
	    VWGameProperties.instance = new VWGameProperties(gridSize);
	}
	else {
	    VWGameProperties.instance.setGridSize(gridSize);
	}
	
	return VWGameProperties.instance;
    }
    
    private void setGridSize(int gridSize) {
	this.gridSize = gridSize;
    }
    
    public int getGridSize() {
	return this.gridSize == 0 ? VWGameProperties.DEFAULT_SIZE : this.gridSize;
    }
    
    public static int getMaxSize() {
	return VWGameProperties.MAX_SIZE;
    }
    
    public static int getMinSize() {
	return VWGameProperties.MIN_SIZE;
    }
    
    public static int getDefaultSize() {
	return VWGameProperties.DEFAULT_SIZE;
    }
}
