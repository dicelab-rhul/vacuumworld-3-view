package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VWGameProperties {
    private static VWGameProperties instance;
    private int gridSize;
    private Set<String> minds;
    private String userMind;
    private String defaultMind;
    private Map<String, String> assignedMinds;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    private static final int DEFAULT_SIZE = 5;
    
    private VWGameProperties() {
	this(0);
    }
    
    private VWGameProperties(int gridSize) {
	this.gridSize = gridSize;
	this.minds = new HashSet<>();
	this.assignedMinds = new HashMap<>();
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
    
    public void addMind(String mind) {
	this.minds.add(mind);
    }
    
    public void setDefaultMind(String mind) {
	this.defaultMind = mind;
    }
    
    public void removeMind(String mind) {
	this.minds.remove(mind);
    }
    
    public Set<String> getMinds() {
	return this.minds;
    }
    
    public String[] getMindsAsArray() {
	return this.minds.toArray(new String[this.minds.size()]);
    }
    
    public void setMind(String agentType, String mind) {
	this.assignedMinds.put(agentType, mind);
    }
    
    public String getMind(String agentType) {
	return this.assignedMinds.containsKey(agentType) ? this.assignedMinds.get(agentType) : this.defaultMind;
    }
    
    public void setUserMind(String userMind) {
	this.userMind = userMind;
    }
    
    public String getUserMind() {
	return this.userMind;
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
