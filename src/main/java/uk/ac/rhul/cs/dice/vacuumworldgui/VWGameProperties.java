package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.ac.rhul.cs.dice.vacuumworldgui.communication.VWControllerManager;

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
    public static final String MINDS_FILE = "minds.json";
    private String whiteLocationPath;
    private VWControllerManager manager;
    private volatile boolean paused;
    private volatile boolean started;
    
    private VWGameProperties() {
	this(0);
    }
    
    private VWGameProperties(int gridSize) {
	this.gridSize = gridSize;
	this.minds = new HashSet<>();
	this.assignedMinds = new HashMap<>();
	this.whiteLocationPath = "";
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
    
    public void setWhiteLocationPath(String whiteLocationPath) {
	this.whiteLocationPath = whiteLocationPath;
    }
    
    public String getWhiteLocationPath() {
	return this.whiteLocationPath;
    }
    
    public void pause() {
	this.paused = true;
    }
    
    public void resume() {
	this.paused = false;
    }
    
    public void setStarted() {
	this.started = true;
    }
    
    public boolean hasTheSimulationBeenStarted() {
	return this.started;
    }
    
    public boolean isPaused() {
	return this.paused;
    }
    
    public void setManager(VWControllerManager manager) {
	this.manager = manager;
    }
    
    public VWControllerManager getManager() {
	return this.manager;
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
    
    public String getDefaultMind() {
	return this.defaultMind;
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