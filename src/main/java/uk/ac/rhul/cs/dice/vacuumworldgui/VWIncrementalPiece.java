package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWJSON;

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
	this.type = VWJSON.BLANK;
	this.color = null;
	this.orientation = null;
	this.mind = null;
	this.sensors = new ArrayList<>();
	this.actuators = new ArrayList<>();
	this.imgPath = VWGameProperties.getInstance().getWhiteLocationPath();
    }
    
    public boolean isBlank() {
	return VWJSON.BLANK.equals(this.type);
    }
    
    public boolean isAgent() {
	return VWJSON.CLEANING_AGENT.equals(this.type);
    }
    
    public boolean isUser() {
	return VWJSON.USER.equals(this.type);
    }
    
    public boolean isAvatar() {
	return VWJSON.AVATAR.equals(this.type);
    }
    
    public boolean isActor() {
	return isAgent() || isUser() || isAvatar();
    }
    
    public boolean isDirt() {
	return VWJSON.DIRT.equals(this.type);
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
	agent.put(VWJSON.TYPE, this.type);
	agent.put(VWJSON.ACTOR_ID, prefix + UUID.randomUUID());
	agent.put(VWJSON.ACTOR_COLOR, this.color == null ? JSONObject.NULL : this.color);
	agent.put(VWJSON.ORIENTATION, this.orientation);
	agent.put(VWJSON.MIND, this.mind);
	agent.put(VWJSON.SENSORS, serializeSensors());
	agent.put(VWJSON.ACTUATORS, serializeActuators());
	
	return agent;
    }

    private JSONArray serializeSensors() {
	JSONArray sensorsArray = new JSONArray();
	
	this.sensors.forEach(sensor -> sensorsArray.put(serializeAppendix(sensor)));
	
	return sensorsArray;
    }

    private JSONArray serializeActuators() {
	JSONArray actuatorsArray = new JSONArray();
	
	this.actuators.forEach(actuator -> actuatorsArray.put(serializeAppendix(actuator)));
	
	return actuatorsArray;
    }
    
    private JSONObject serializeAppendix(List<String> appendix) {
	JSONObject a = new JSONObject();
	a.put(VWJSON.SENSOR_ACTUATOR_ID, appendix.get(0));
	a.put(VWJSON.SENSOR_ACTUATOR_PURPOSE, appendix.get(1));
	
	return a;
    }

    private JSONObject serializeDirt() {
	JSONObject dirt = new JSONObject();
	dirt.put(VWJSON.DIRT_COLOR, this.color);
	
	return dirt;
    }
}