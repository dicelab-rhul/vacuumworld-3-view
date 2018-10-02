package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public class VWIncrementalPiece {
    private String id;
    private String type;
    private String color;
    private String orientation;
    private String mind;
    private List<List<String>> sensors;
    private List<List<String>> actuators;
    private String imgPath;
    
    
    public VWIncrementalPiece() {
	this.id = null;
	this.type = "blank";
	this.color = null;
	this.orientation = null;
	this.mind = null;
	this.sensors = new ArrayList<>();
	this.actuators = new ArrayList<>();
	this.imgPath = "res/imgs/locations/white_square.png";
    }
    
    public boolean isBlank() {
	return "blank".equals(this.type);
    }
    
    public boolean isAgent() {
	return "cleaning_agent".equals(this.type);
    }
    
    public boolean isUser() {
	return "user".equals(this.type);
    }
    
    public boolean isAvatar() {
	return "avatar".equals(this.type);
    }
    
    public boolean isActor() {
	return isAgent() || isUser() || isAvatar();
    }
    
    public boolean isDirt() {
	return "dirt".equals(this.type);
    }
    
    public String getId() {
	return this.id;
    }
    
    public String getType() {
	return this.type;
    }
    
    public String getColor() {
	return this.color;
    }
    
    public String getOrientation() {
	return this.orientation;
    }
    
    public String getMind() {
	return this.mind;
    }
    
    public List<List<String>> getSensors() {
	return this.sensors;
    }
    
    public List<List<String>> getActuators() {
	return this.actuators;
    }
    
    public String getImgPath() {
	return this.imgPath;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public void setType(String type) {
	this.type = type;
    }
    
    public void setColor(String color) {
	this.color = color;
    }
    
    public void setOrientation(String orientation) {
	this.orientation = orientation;
    }
    
    public void setMind(String mind) {
	this.mind = mind;
    }
    
    public void addSensor(String purpose) {
	this.sensors.add(Arrays.asList("Sensor-" + UUID.randomUUID(), purpose));
    }
    
    public void addSensor(String id, String purpose) {
	this.sensors.add(Arrays.asList(id, purpose));
    }
    
    public void addSensor(List<String> characteristics) {
	this.sensors.add(characteristics);
    }
    
    public void addActuator(String purpose) {
	this.actuators.add(Arrays.asList("Actuator-" + UUID.randomUUID(), purpose));
    }
    
    public void addActuator(String id, String purpose) {
	this.actuators.add(Arrays.asList(id, purpose));
    }
    
    public void addActuator(List<String> characteristics) {
	this.actuators.add(characteristics);
    }
    
    public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
    }
    
    public JSONObject serialize() {
	if(isActor()) {
	    return serializeActor();
	}
	else if(isDirt()) {
	    return serializeDirt();
	}
	else if(isBlank()) {
	    return null;
	}
	else {
	    throw new IllegalArgumentException();
	}
    }

    private JSONObject serializeActor() {
	if(isAgent()) {
	    return serializeActor("Agent-");
	}
	else if(isUser()) {
	    return serializeActor("User-");
	}
	else if(isAvatar()) {
	    return serializeActor("Avatar-");    
	}
	else {
	    throw new IllegalArgumentException();
	}
    }

    private JSONObject serializeActor(String prefix) {
	JSONObject agent = new JSONObject();
	agent.put("type", this.type);
	agent.put("id", prefix + UUID.randomUUID());
	agent.put("color", this.color == null ? JSONObject.NULL : this.color);
	agent.put("orientation", this.orientation);
	agent.put("mind", this.mind);
	agent.put("sensors", serializeSensors());
	agent.put("actuators", serializeActuators());
	
	return agent;
    }

    private JSONArray serializeSensors() {
	JSONArray sensors = new JSONArray();
	
	this.sensors.forEach(sensor -> sensors.put(serializeAppendix(sensor)));
	
	return sensors;
    }

    private JSONArray serializeActuators() {
	JSONArray actuators = new JSONArray();
	
	this.actuators.forEach(actuator -> actuators.put(serializeAppendix(actuator)));
	
	return actuators;
    }
    
    private JSONObject serializeAppendix(List<String> appendix) {
	JSONObject a = new JSONObject();
	a.put("id", appendix.get(0));
	a.put("purpose", appendix.get(1));
	
	return a;
    }

    private JSONObject serializeDirt() {
	JSONObject dirt = new JSONObject();
	dirt.put("color", this.color);
	
	return dirt;
    }
}