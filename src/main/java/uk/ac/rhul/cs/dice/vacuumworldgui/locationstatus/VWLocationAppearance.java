package uk.ac.rhul.cs.dice.vacuumworldgui.locationstatus;

import org.json.JSONArray;
import org.json.JSONObject;

public class VWLocationAppearance {
    private int x;
    private int y;
    private String actorId;
    private ActorEnum actor;
    private String actorMind;
    private OrientationEnum actorOrientation;
    private DirtEnum dirt;
    
    public VWLocationAppearance(int x, int y) {
	this.x = x;
	this.y = y;
	
	clear();
    }
    
    public VWLocationAppearance(int x, int y, String actorId, ActorEnum actor, String actorMind, OrientationEnum orientation) {
	this(x, y);
	
	setActor(actorId, actor, actorMind, orientation);
    }
    
    public VWLocationAppearance(int x, int y, DirtEnum dirt) {
	this(x, y);
	
	setDirt(dirt);
    }
    
    public VWLocationAppearance(int x, int y, String actorId, ActorEnum actor, String actorMind, OrientationEnum orientation, DirtEnum dirt) {
	this(x, y, actorId, actor, actorMind, orientation);
	
	setDirt(dirt);
    }
    
    private boolean isActorFree() {
	return this.actor == ActorEnum.NONE && 
	this.actorOrientation == OrientationEnum.UNDEFINED &&
	"".equals(this.actorMind) &&
	"".equals(this.actorId);
    }
    
    private boolean isDirtFree() {
	return this.dirt == DirtEnum.NONE;
    }
    
    private boolean isEmpty() {
	return isActorFree() && isDirtFree();
    }
    
    public void clear() {
	this.actor = ActorEnum.NONE;
	this.actorOrientation = OrientationEnum.UNDEFINED;
	this.actorMind = "";
	this.actorId = "";
	this.dirt = DirtEnum.NONE;
    }
    
    public void setActor(String actorId, ActorEnum actor, String actorMind, OrientationEnum orientation) {
	this.actorId = actorId;
	this.actor = actor;
	this.actorMind = actorMind;
	this.actorOrientation = orientation;
    }
    
    public void setDirt(DirtEnum dirt) {
	this.dirt = dirt;
    }
    
    public JSONObject serialize() {
	if(isEmpty()) {
	    return null;
	}
	
	JSONObject location = new JSONObject();
	
	location.append("x", this.x);
	location.append("y", this.y);
	
	if(!isActorFree()) {
	    location.append("actor", serializeActor());
	}
	
	if(!isDirtFree()) {
	    location.append("dirt", serializeDirt());
	}
	
	return location;
    }

    private JSONObject serializeDirt() {
	JSONObject dirt = new JSONObject();
	
	dirt.append("color", this.dirt.toString());
	
	return dirt;
    }

    private JSONObject serializeActor() {
	JSONObject actor = new JSONObject();
	
	actor.append("type", this.actor.getType());
	actor.append("id", this.actorId);
	actor.append("color", this.actor.getColor());
	actor.append("orientation", this.actorOrientation.toString());
	actor.append("mind", this.actorMind);
	actor.append("sensors", serializeSensors());
	actor.append("actuators", serializeActuators());
	
	return actor;
    }

    private JSONArray serializeSensors() {
	JSONArray sensors = new JSONArray();
	
	sensors.put(createAppendix("Sensor1", "see"));
	sensors.put(createAppendix("Sensor2", "listen"));
	sensors.put(createAppendix("Sensor3", "other"));
	
	return sensors;
    }

    private JSONObject createAppendix(String id, String purpose) {
	JSONObject appendix = new JSONObject();
	
	appendix.append("id", id);
	appendix.append("purpose", purpose);
	
	return appendix;
    }

    private JSONArray serializeActuators() {
	JSONArray actuators = new JSONArray();
	
	actuators.put(createAppendix("Actuator1", "act_physically"));
	actuators.put(createAppendix("Actuator2", "speak"));
	actuators.put(createAppendix("Actuator3", "other"));
	
	return actuators;
    }
}