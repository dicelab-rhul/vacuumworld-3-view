package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

public class VWWIP {
    private static VWWIP instance;
    private int size;
    private Map<Coordinates, IncrementalLocation> locationsWIP;
    
    private VWWIP(int size) {
	this.locationsWIP = new HashMap<>();
	this.size = size;
    }

    public static VWWIP getInstance(int size) {
	if(VWWIP.instance == null) {
	    VWWIP.instance = new VWWIP(size);
	}
	
	return VWWIP.instance;
    }
    
    public JPanel render() {
	//TODO
	return null;
    }
    
    public JSONObject createInitialState() {
	JSONObject initial = new JSONObject();
	
	initial.put("size", this.size);
	
	JSONArray notableLocations = createNotableLocations();
	
	initial.put("notable_locations", notableLocations);
	
	return initial;
    }

    private JSONArray createNotableLocations() {
	JSONArray notableLocations = new JSONArray();
	
	this.locationsWIP.values().stream().filter(loc -> !loc.isEmpty()).forEach(loc -> notableLocations.put(createLocation(loc)));
	
	return notableLocations;
    }

    /*
     * 
     * Note: in this case the location cannot be blank (otherwise this private method would not be called). Hence, the check is skipped.
     * 
     */
    private JSONObject createLocation(IncrementalLocation loc) {
	JSONObject location = new JSONObject();
	
	location.append("x", loc.getCoordinates().getX());
	location.append("y", loc.getCoordinates().getY());
	
	//by construction, either an actor or a dirt are there.
	if(loc.doesAnActorExist()) {
	    location.append("actor", loc.getP1().serialize());
	}
	else {
	    location.append("dirt", loc.getP1().serialize());
	}
	
	if(loc.isActorAndDirt()) {
	    location.append("dirt", loc.getP2().serialize());
	}
	
	return location;
    }
}
