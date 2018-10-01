package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.JPanel;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class State {
    private static State instance;
    private int size;
    private Map<Coordinates, IncrementalLocation> locations;
    
    //constructor "from view for model through controller.".
    private State(int size) {
	this.locations = new HashMap<>();
	this.size = size;
    }
    
    //constructor "from model through controller for view".
    private State(JSONObject state) {
	this.locations = new HashMap<>();
	
	extractSize(state);
	deserialize(state);
    }
    
    public static State getInstance(int size) {
	if(State.instance == null) {
	    State.instance = new State(size);
	}
	
	return State.instance;
    }
    
    public static State getInstance(JSONObject state) {
	if(State.instance == null) {
	    State.instance = new State(state);
	}
	
	return State.instance;
    }
    
    public static State getInstance(String savestatePath) {
	try {
	    if(State.instance == null) {
		URI uri = new URI("file:./" + savestatePath);
		JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
		JSONObject root = new JSONObject(tokener);
		State.instance = new State(root);
	    }
	    
	    return State.instance;
	}
	catch(Exception e) {
	    throw new IllegalArgumentException(e);
	}
    }
    
    public static void reset(int size) {
	State.instance = new State(size);
    }
    
    public static void reset(JSONObject state) {
	State.instance = new State(state);
    }
    
    public static void reset() {
	State.instance = null;
    }
    
    public void saveState(String path) {
	try(FileWriter fw = new FileWriter(path)) {
	    String state = serializeState().toString(4);
	    fw.write(state);
	    fw.flush();
	}
	catch(Exception e) {
	    e.printStackTrace(System.out);
	    LogUtils.log("It was impossible to save the state to " + path + ".");
	    LogUtils.log(e);
	}
    }
    
    public void addEmptyLocationFromView(Coordinates coordinates) {
	this.locations.put(coordinates, new IncrementalLocation(coordinates, new IncrementalPiece(), new IncrementalPiece()));
    }
    
    public void resetLocation(Coordinates coordinates) {
	addEmptyLocationFromView(coordinates);
    }
    
    public void addActorToEmptyLocationFromView(Coordinates coordinates, String imagePath, String mind) {
	IncrementalLocation location = this.locations.get(coordinates);
	
	IncrementalPiece actor = new IncrementalPiece();
	
	String[] tokens = imagePath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	actor.setType(getType(name));
	actor.setId(generateActorId(name));
	actor.setColor(getActorColor(name));
	actor.setOrientation(getOrientation(name));
	actor.setMind(mind);
	actor.setImgPath(imagePath);
	
	generateNewSensors().forEach(actor::addSensor);
	generateNewActuators().forEach(actor::addActuator);
	
	location.setP1(actor);
	
	this.locations.put(coordinates, location);
    }
    
    public void addDirtToEmptyLocationFromView(Coordinates coordinates, String imagePath) {
	IncrementalLocation location = this.locations.get(coordinates);
	
	IncrementalPiece dirt = new IncrementalPiece();
	
	dirt.setType("dirt");
	dirt.setColor(getDirtColor(imagePath));
	dirt.setImgPath(imagePath);
	location.setP1(dirt);

	this.locations.put(coordinates, location);
    }
    
    public void addDirtToLocationWithActorFromView(Coordinates coordinates, String imagePath) {
	IncrementalLocation location = this.locations.get(coordinates);
	IncrementalPiece dirt = new IncrementalPiece();
	
	dirt.setType("dirt");
	dirt.setColor(getDirtColor(imagePath));
	dirt.setImgPath(imagePath);
	location.setP2(dirt);

	this.locations.put(coordinates, location);
    }
    
    private String getDirtColor(String imagePath) {
	String[] tokens = imagePath.split("/");
	
	return tokens[tokens.length - 1].split("\\.")[0].split("_")[0];
    }

    public void addActorToLocationWithDirtFromView(Coordinates coordinates, String imagePath, String mind) {
	IncrementalLocation location = this.locations.get(coordinates);
	IncrementalPiece dirt = location.getP2();
	IncrementalPiece actor = new IncrementalPiece();
	
	String[] tokens = imagePath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	actor.setType(getType(name));
	actor.setId(generateActorId(name));
	actor.setColor(getActorColor(name));
	actor.setOrientation(getOrientation(name));
	actor.setMind(mind);
	actor.setImgPath(imagePath);
	
	generateNewSensors().forEach(actor::addSensor);
	generateNewActuators().forEach(actor::addActuator);
	
	location.setP1(actor);
	location.setP2(dirt);
	
	this.locations.put(coordinates, location);
    }
    
    private String getType(String name) {
	if(name.startsWith("user")) {
	    return "user";
	}
	else if(name.startsWith("avatar")) {
	    return "avatar";
	}
	else {
	    return "cleaning_agent";
	}
    }

    private String generateActorId(String name) {
	switch(getType(name)) {
	case "user":
	    return "User-" + UUID.randomUUID();
	case "avatar":
	    return "Avatar-" + UUID.randomUUID();
	default:
	    return "Agent-" + UUID.randomUUID();
	}
    }

    private String getActorColor(String name) {
	String type = getType(name);
	
	if(!"cleaning_agent".equals(type)) {
	    return null;
	}
	else {
	    return name.split("_")[0];
	}
    }

    private String getOrientation(String name) {
	return name.split("_")[1];
    }

    private List<List<String>> generateNewSensors() {
	List<List<String>> sensors = new ArrayList<>();
	List<String> purposes = Arrays.asList("see", "listen", "other"); //TODO extract to an enum in vwcommon.
	
	purposes.forEach(purpose -> sensors.add(Arrays.asList("Sensor-" + UUID.randomUUID(), purpose)));
	
	return sensors;
    }

    private List<List<String>> generateNewActuators() {
	List<List<String>> actuators = new ArrayList<>();
	List<String> purposes = Arrays.asList("act_physically", "speak", "other"); //TODO extract to an enum in vwcommon.
	
	purposes.forEach(purpose -> actuators.add(Arrays.asList("Actuator-" + UUID.randomUUID(), purpose)));
	
	return actuators;
    }

    /**
     * 
     * Renders the state into a {@link JPanel} for the view.
     * 
     * @return a {@link JPanel} representation of the state.
     * 
     */
    public JPanel renderIntoJPanel() {
	//TODO
	return null;
    }
    
    /**
     * 
     * Serializes the state into a {@link JSONObject} for the controller.
     * 
     * @return a {@link JSONObject} representation of the state.
     * 
     */
    public JSONObject serializeState() {
	JSONObject initial = new JSONObject();
	
	initial.put("size", this.size);
	
	JSONArray notableLocations = createNotableLocations();
	
	initial.put("notable_locations", notableLocations);
	
	return initial;
    }

    private void extractSize(JSONObject state) {
	if(!state.has("size")) {
	    throw new IllegalArgumentException();
	}
	else {
	    this.size = state.getInt("size");
	}
    }

    private void deserialize(JSONObject state) {
	if(!state.has("notable_locations")) {
	    throw new IllegalArgumentException();
	}
	else {
	    deserializeLocations(state.getJSONArray("notable_locations"));
	    padMissingLocations();
	}
    }

    private void deserializeLocations(JSONArray notableLocations) {
	notableLocations.forEach(this::deserializeLocation);
    }
    
    private void deserializeLocation(Object loc) {
	JSONObject location = (JSONObject) loc;
	
	if(location == null) {
	    throw new IllegalArgumentException();
	}
	else {
	    Coordinates coordinates = new Coordinates(location.getInt("x"), location.getInt("y"));
	    
	    this.locations.put(coordinates, deserializeLocationHelper(location, coordinates));
	}
    }

    private IncrementalLocation deserializeLocationHelper(JSONObject location, Coordinates coordinates) {
	if(location.has("actor") && location.has("dirt")) {
	    return createLocationWithActorAndDirt(location, coordinates);
	}
	else if(location.has("actor")) {
	    return createLocationWithLoneActor(location, coordinates);
	}
	else if(location.has("dirt")) {
	    return createLocationWithLoneDirt(location, coordinates);
	}
	else {
	    throw new IllegalArgumentException();
	}
    }

    private IncrementalLocation createLocationWithActorAndDirt(JSONObject location, Coordinates coordinates) {
	IncrementalPiece actor = createActor(location.getJSONObject("actor"));
	IncrementalPiece dirt = createDirt(location.getJSONObject("dirt"));
	
	return new IncrementalLocation(coordinates, actor, dirt);
    }

    private IncrementalLocation createLocationWithLoneActor(JSONObject location, Coordinates coordinates) {
	IncrementalPiece actor = createActor(location.getJSONObject("actor"));
	
	return new IncrementalLocation(coordinates, actor, new IncrementalPiece());
    }

    private IncrementalPiece createActor(JSONObject a) {	
	String type = a.getString("type");
	String id = a.getString("id");
	String orientation = a.getString("orientation");
	String mind = a.getString("mind");
	
	Object color = a.get("color");
	String col = JSONObject.NULL.equals(color) ? null : a.getString("color");
	
	JSONArray sensors = a.getJSONArray("sensors");
	JSONArray actuators = a.getJSONArray("actuators");
	
	return createActor(type, id, orientation, mind, col, sensors, actuators);
    }

    private IncrementalPiece createActor(String type, String id, String orientation, String mind, String color, JSONArray sensors, JSONArray actuators) {
	IncrementalPiece actor = new IncrementalPiece();
	
	actor.setId(id);
	actor.setColor(color);
	actor.setMind(mind);
	actor.setType(type);
	actor.setOrientation(orientation);
	
	getAppendices(sensors).forEach(actor::addSensor);
	getAppendices(actuators).forEach(actor::addActuator);
	
	actor.setImgPath(getImgPath(type, color, orientation));
	
	return actor;
    }

    private List<List<String>> getAppendices(JSONArray a) {
	List<List<String>> appendices = new ArrayList<>();
	
	a.forEach(appendix -> appendices.add(getAppendix(appendix)));
	
	return appendices;
    }

    private List<String> getAppendix(Object obj) {
	JSONObject appendix = (JSONObject) obj;
	
	if(appendix == null) {
	    throw new IllegalArgumentException();
	}
	else {
	    return getAppendixHelper(appendix);
	}
    }

    private List<String> getAppendixHelper(JSONObject appendix) {
	List<String> characteristics = new ArrayList<>();
	
	characteristics.add(appendix.getString("id"));
	characteristics.add(appendix.getString("purpose"));
	
	return characteristics;
    }

    private String getImgPath(String type, String color, String orientation) {
	StringBuilder builder = new StringBuilder("res/imgs/locations/");
	
	switch(type) {
	case "cleaning_agent":
	    builder.append(color);
	    break;
	case "user":
	    builder.append("user");
	    break;
	case "avatar":
	    builder.append("avatar");
	    break;
	default:
	    throw new IllegalArgumentException();
	}
	
	builder.append("_");
	builder.append(orientation);
	builder.append(".png");
	
	return builder.toString();
    }

    private IncrementalLocation createLocationWithLoneDirt(JSONObject location, Coordinates coordinates) {
	IncrementalPiece dirt = createDirt(location.getJSONObject("dirt"));
	
	return new IncrementalLocation(coordinates, dirt, new IncrementalPiece());
    }

    private IncrementalPiece createDirt(JSONObject d) {
	IncrementalPiece dirt = new IncrementalPiece();
	
	dirt.setType("dirt");
	String color = d.getString("color"); //guaranteed to be a String for dirts.
	dirt.setColor(color);
	dirt.setImgPath("res/imgs/locations/" + color + "_dirt.png");
	
	return dirt;
    }

    private void padMissingLocations() {
	for(int i = 0; i < this.size; i++) {
	    for(int j = 0; j < this.size; j++) {
		Coordinates coordinates = new Coordinates(i, j); //TODO check.
		
		if(!this.locations.containsKey(coordinates)) {
		    this.locations.put(coordinates, new IncrementalLocation(coordinates, new IncrementalPiece(), new IncrementalPiece()));
		}
	    }
	}
    }

    private JSONArray createNotableLocations() {
	JSONArray notableLocations = new JSONArray();
	
	this.locations.values().stream().filter(loc -> !loc.isEmpty()).forEach(loc -> notableLocations.put(createLocation(loc)));
	
	return notableLocations;
    }

    /*
     * 
     * Note: in this case the location cannot be blank (otherwise this private method would not be called). Hence, the check is skipped.
     * 
     */
    private JSONObject createLocation(IncrementalLocation loc) {
	JSONObject location = new JSONObject();
	
	location.put("x", loc.getCoordinates().getX());
	location.put("y", loc.getCoordinates().getY());
	
	//by construction, either an actor or a dirt are there.
	if(loc.doesAnActorExist()) {
	    location.put("actor", loc.getP1().serialize());
	}
	else {
	    location.put("dirt", loc.getP1().serialize());
	}
	
	if(loc.isActorAndDirt()) {
	    location.put("dirt", loc.getP2().serialize());
	}
	
	return location;
    }
}
