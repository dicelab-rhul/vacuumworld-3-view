package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWJSON;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWConstructGridPanel;

public class VWState {
    private static VWState instance;
    private int size;
    private Map<Coordinates, VWIncrementalLocation> locations;
    private VWConstructGridPanel callback;
    private int numberOfActors;
    private boolean userPresent;
    private Coordinates userCoordinates;
    
    //constructor "from view for model through controller.".
    private VWState(int size) {
	this.locations = new HashMap<>();
	this.size = size;
	this.numberOfActors = 0;
	this.userPresent = false;
    }
    
    //constructor "from model through controller for view".
    private VWState(JSONObject state) {
	this.locations = new HashMap<>();
	this.numberOfActors = 0;
	
	extractSize(state);
	deserialize(state);
	refreshUserFlag();
    }

    /**
     * 
     * Call this to construct an empty grid. Do not call this if you just want to access the existing instance. Call {@link VWState#getExistingInstance()} instead. 
     * 
     * @param size the grid size.
     * 
     * @return the state.
     * 
     */
    public static VWState getInstance(int size) {
	if(VWState.instance == null) {
	    VWState.instance = new VWState(size);
	}
	
	return VWState.instance;
    }
    
    /**
     * 
     * Call this to reconstruct the state from a {@link JSONObject} representation. Do not call this if you just want to access the existing instance. Call {@link VWState#getExistingInstance()} instead. 
     * 
     * @param state a {@link JSONObject} representation of the state.
     * 
     * @return the state.
     * 
     */
    public static VWState getInstance(JSONObject state) {
	if(VWState.instance == null) {
	    VWState.instance = new VWState(state);
	}
	
	return VWState.instance;
    }
    
    /**
     * 
     * Call this to reconstruct the state from the path of a save-state file. Do not call this if you just want to access the existing instance. Call {@link VWState#getExistingInstance()} instead. 
     * 
     * @param savestatePath the path to a save-state file.
     * 
     * @throws IllegalArgumentException if any error happens while loading the save-state, or if the save-state does not exist.
     * 
     * @return the state.
     * 
     */
    public static VWState getInstance(String savestatePath) {
	try {
	    if(VWState.instance == null) {
		//The triple '/' after 'file' (rather than 2), and the .replace() are needed on Windows.
		String qualPath = "file:///" + savestatePath.replace("\\", "/");
		URI uri = new URI(qualPath);
		JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
		JSONObject root = new JSONObject(tokener);
		VWState.instance = new VWState(root);
		
		LogUtils.log("Loaded " + qualPath + ".");
	    }
	    
	    return VWState.instance;
	}
	catch(Exception e) {
	    throw new IllegalArgumentException(e);
	}
    }
    
    /**
     * 
     * Returns the existing instance of the state, or <code>null</code>, if such instance does not exist.
     * 
     * @return the existing instance of the state, or <code>null</code>, if such instance does not exist.
     * 
     */
    public static VWState getExistingInstance() {
	return VWState.instance;
    }
    
    public void refreshUserFlag() {
	if(this.locations == null || this.locations.isEmpty()) {
	    unsetUserFlagAndCoordinates();
	}
	else {
	    VWIncrementalLocation candidate = this.locations.entrySet().stream().filter(e -> e.getValue().doesAUserExist()).map(Map.Entry::getValue).findFirst().orElse(null);
	    
	    this.userPresent = candidate != null && candidate.doesAUserExist();
	    this.userCoordinates = this.userPresent ? candidate.getCoordinates() : null;
	}
    }
    
    public void setUserFlagAndCoordinates(Coordinates userCoordinates) {
	this.userPresent = true;
	this.userCoordinates = userCoordinates;
    }
    
    public void unsetUserFlagAndCoordinates() {
	this.userPresent = false;
	this.userCoordinates = null;
    }
    
    public boolean isAUserPresent() {
	return this.userPresent;
    }
    
    public Coordinates getUserCoordinates() {
	return this.userCoordinates;
    }
    
    public boolean isAValidInitialState() {
	return checkUserLimit() && isAnAgentThere();
    }
    
    private boolean isAnAgentThere() {
	return this.locations.entrySet().stream().filter(e -> e.getValue().doesAnAgentExist()).count() > 0;
    }

    private boolean checkUserLimit() {
	return this.locations.entrySet().stream().filter(e -> e.getValue().doesAUserExist()).count() < 2;
    }

    public int getGridSize() {
	return this.size;
    }
    
    public int getNumberOfActors() {
	return this.numberOfActors;
    }
    
    public boolean areThereAnyActors() {
	return this.numberOfActors > 0;
    }
    
    public Map<Coordinates, VWIncrementalLocation> getLocations() {
	return this.locations;
    }
    
    public VWConstructGridPanel getCallback() {
	return this.callback;
    }
    
    public void setCallback(VWConstructGridPanel callback) {
	this.callback = callback;
    }
    
    public static void reset(int size) {
	VWState.instance = new VWState(size);
    }
    
    public static void reset(JSONObject state) {
	VWState.instance = new VWState(state);
    }
    
    public static void reset() {
	VWState.instance = null;
    }
    
    public void saveState(String path) {
	try(FileWriter fw = new FileWriter(path)) {
	    String state = serializeState().toString(4);
	    fw.write(state);
	    fw.flush();
	}
	catch(Exception e) {
	    LogUtils.log("It was impossible to save the state to " + path + ".");
	    LogUtils.log(e);
	}
    }
    
    public void addEmptyLocationFromView(Coordinates coordinates) {
	this.locations.put(coordinates, new VWIncrementalLocation(coordinates, new VWIncrementalPiece(), new VWIncrementalPiece()));
    }
    
    public void resetLocation(Coordinates coordinates) {
	if(this.locations.get(coordinates).doesAnAgentExist() && this.numberOfActors > 0) {
	    this.numberOfActors--;
	}
	
	if(this.locations.get(coordinates).doesAUserExist()) {
	    unsetUserFlagAndCoordinates();
	}
	
	addEmptyLocationFromView(coordinates);
    }
    
    public void addActorToEmptyLocationFromView(Coordinates coordinates, String imagePath) {
	VWIncrementalLocation location = this.locations.get(coordinates);
	
	VWIncrementalPiece actor = new VWIncrementalPiece();
	
	String[] tokens = imagePath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	actor.setType(getType(name));
	actor.setId(generateActorId(name));
	actor.setColor(getActorColor(name));
	actor.setOrientation(getOrientation(name));
	actor.setMind(getMind(name));
	actor.setImgPath(imagePath);
	
	generateNewSensors().forEach(actor::addSensor);
	generateNewActuators().forEach(actor::addActuator);
	
	location.setP1(actor);
	
	this.locations.put(coordinates, location);
	
	if(!imagePath.contains(VWJSON.USER)) {
	    this.numberOfActors++;
	}
	else {
	    setUserFlagAndCoordinates(coordinates);
	}
    }

    private String getMind(String name) {
	switch(getType(name)) {
	case VWJSON.USER:
	    return VWGameProperties.getInstance().getUserMind();
	case VWJSON.AVATAR:
	    return "";
	default:
	    return getAgentMind(getActorColor(name));
	}
    }

    private String getAgentMind(String color) {
	switch(color) {
	case VWJSON.GREEN_AGENT:
	    return VWGameProperties.getInstance().getMind(VWJSON.GREEN_AGENT);
	case VWJSON.ORANGE_AGENT:
	    return VWGameProperties.getInstance().getMind(VWJSON.ORANGE_AGENT);
	case VWJSON.WHITE_AGENT:
	    return VWGameProperties.getInstance().getMind(VWJSON.WHITE_AGENT);
	default:
	    return "";
	}
    }

    public void addDirtToEmptyLocationFromView(Coordinates coordinates, String imagePath) {
	VWIncrementalLocation location = this.locations.get(coordinates);
	
	VWIncrementalPiece dirt = new VWIncrementalPiece();
	
	dirt.setType(VWJSON.DIRT);
	dirt.setColor(getDirtColor(imagePath));
	dirt.setImgPath(imagePath);
	location.setP1(dirt);

	this.locations.put(coordinates, location);
    }
    
    public void addDirtToLocationWithActorFromView(Coordinates coordinates, String imagePath) {
	VWIncrementalLocation location = this.locations.get(coordinates);
	VWIncrementalPiece dirt = new VWIncrementalPiece();
	
	dirt.setType(VWJSON.DIRT);
	dirt.setColor(getDirtColor(imagePath));
	dirt.setImgPath(imagePath);
	location.setP2(dirt);

	this.locations.put(coordinates, location);
    }
    
    private String getDirtColor(String imagePath) {
	String[] tokens = imagePath.split("/");
	
	return tokens[tokens.length - 1].split("\\.")[0].split("_")[0];
    }

    public void addActorToLocationWithDirtFromView(Coordinates coordinates, String imagePath) {
	VWIncrementalLocation location = this.locations.get(coordinates);
	VWIncrementalPiece dirt = location.getP2();
	VWIncrementalPiece actor = new VWIncrementalPiece();
	
	String[] tokens = imagePath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	actor.setType(getType(name));
	actor.setId(generateActorId(name));
	actor.setColor(getActorColor(name));
	actor.setOrientation(getOrientation(name));
	actor.setMind(getMind(name));
	actor.setImgPath(imagePath);
	
	generateNewSensors().forEach(actor::addSensor);
	generateNewActuators().forEach(actor::addActuator);
	
	location.setP1(actor);
	location.setP2(dirt);
	
	this.locations.put(coordinates, location);
	
	if(!imagePath.contains(VWJSON.USER)) {
	    this.numberOfActors++;
	}
	else {
	    setUserFlagAndCoordinates(coordinates);
	}
    }
    
    private String getType(String name) {
	if(name.startsWith(VWJSON.USER)) {
	    return VWJSON.USER;
	}
	else if(name.startsWith(VWJSON.AVATAR)) {
	    return VWJSON.AVATAR;
	}
	else {
	    return VWJSON.CLEANING_AGENT;
	}
    }

    private String generateActorId(String name) {
	switch(getType(name)) {
	case VWJSON.USER:
	    return "User-" + UUID.randomUUID();
	case VWJSON.AVATAR:
	    return "Avatar-" + UUID.randomUUID();
	default:
	    return "Agent-" + UUID.randomUUID();
	}
    }

    private String getActorColor(String name) {
	String type = getType(name);
	
	if(!VWJSON.CLEANING_AGENT.equals(type)) {
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
	List<String> purposes = Arrays.asList(VWJSON.SENSOR_SEE, VWJSON.SENSOR_LISTEN, VWJSON.SENSOR_ACTUATOR_OTHER);
	
	purposes.forEach(purpose -> sensors.add(Arrays.asList("Sensor-" + UUID.randomUUID(), purpose)));
	
	return sensors;
    }

    private List<List<String>> generateNewActuators() {
	List<List<String>> actuators = new ArrayList<>();
	List<String> purposes = Arrays.asList(VWJSON.ACTUATOR_ACT_PHYSICALLY, VWJSON.ACTUATOR_SPEAK, VWJSON.SENSOR_ACTUATOR_OTHER);
	
	purposes.forEach(purpose -> actuators.add(Arrays.asList("Actuator-" + UUID.randomUUID(), purpose)));
	
	return actuators;
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
	
	initial.put(VWJSON.SIZE, this.size);
	
	JSONArray notableLocations = createNotableLocations();
	
	initial.put(VWJSON.NOTABLE_LOCATIONS, notableLocations);
	
	return initial;
    }

    private void extractSize(JSONObject state) {
	if(!state.has(VWJSON.SIZE)) {
	    throw new IllegalArgumentException();
	}
	else {
	    this.size = state.getInt(VWJSON.SIZE);
	}
    }

    private void deserialize(JSONObject state) {
	if(!state.has(VWJSON.NOTABLE_LOCATIONS)) {
	    throw new IllegalArgumentException();
	}
	else {
	    deserializeLocations(state.getJSONArray(VWJSON.NOTABLE_LOCATIONS));
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
	    Coordinates coordinates = new Coordinates(location.getInt(VWJSON.X), location.getInt(VWJSON.Y));
	    
	    this.locations.put(coordinates, deserializeLocationHelper(location, coordinates));
	}
    }

    private VWIncrementalLocation deserializeLocationHelper(JSONObject location, Coordinates coordinates) {
	if(location.has(VWJSON.ACTOR) && location.has(VWJSON.DIRT)) {
	    return createLocationWithActorAndDirt(location, coordinates);
	}
	else if(location.has(VWJSON.ACTOR)) {
	    return createLocationWithLoneActor(location, coordinates);
	}
	else if(location.has(VWJSON.DIRT)) {
	    return createLocationWithLoneDirt(location, coordinates);
	}
	else {
	    throw new IllegalArgumentException();
	}
    }

    private VWIncrementalLocation createLocationWithActorAndDirt(JSONObject location, Coordinates coordinates) {
	VWIncrementalPiece actor = createActor(location.getJSONObject(VWJSON.ACTOR));
	VWIncrementalPiece dirt = createDirt(location.getJSONObject(VWJSON.DIRT));
	
	return new VWIncrementalLocation(coordinates, actor, dirt);
    }

    private VWIncrementalLocation createLocationWithLoneActor(JSONObject location, Coordinates coordinates) {
	VWIncrementalPiece actor = createActor(location.getJSONObject(VWJSON.ACTOR));
	
	return new VWIncrementalLocation(coordinates, actor, new VWIncrementalPiece());
    }

    private VWIncrementalPiece createActor(JSONObject a) {	
	String type = a.getString(VWJSON.TYPE);
	String id = a.getString(VWJSON.ACTOR_ID);
	String orientation = a.getString(VWJSON.ORIENTATION);
	String mind = a.getString(VWJSON.MIND);
	
	Object color = a.get(VWJSON.ACTOR_COLOR);
	String col = JSONObject.NULL.equals(color) ? null : a.getString(VWJSON.ACTOR_COLOR);
	
	JSONArray sensors = a.getJSONArray(VWJSON.SENSORS);
	JSONArray actuators = a.getJSONArray(VWJSON.ACTUATORS);
	
	return createActor(type, id, orientation, mind, col, sensors, actuators);
    }

    private VWIncrementalPiece createActor(String type, String id, String orientation, String mind, String color, JSONArray sensors, JSONArray actuators) {
	VWIncrementalPiece actor = new VWIncrementalPiece();
	
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
	
	characteristics.add(appendix.getString(VWJSON.SENSOR_ACTUATOR_ID));
	characteristics.add(appendix.getString(VWJSON.SENSOR_ACTUATOR_PURPOSE));
	
	return characteristics;
    }

    private String getImgPath(String type, String color, String orientation) {
	StringBuilder builder = new StringBuilder("/imgs/locations/");
	
	switch(type) {
	case VWJSON.CLEANING_AGENT:
	    builder.append(color);
	    break;
	case VWJSON.USER:
	    builder.append(VWJSON.USER);
	    break;
	case VWJSON.AVATAR:
	    builder.append(VWJSON.AVATAR);
	    break;
	default:
	    throw new IllegalArgumentException();
	}
	
	builder.append("_");
	builder.append(orientation);
	builder.append(".png");
	
	return builder.toString();
    }

    private VWIncrementalLocation createLocationWithLoneDirt(JSONObject location, Coordinates coordinates) {
	VWIncrementalPiece dirt = createDirt(location.getJSONObject(VWJSON.DIRT));
	
	return new VWIncrementalLocation(coordinates, dirt, new VWIncrementalPiece());
    }

    private VWIncrementalPiece createDirt(JSONObject d) {
	VWIncrementalPiece dirt = new VWIncrementalPiece();
	
	dirt.setType(VWJSON.DIRT);
	String color = d.getString(VWJSON.DIRT_COLOR); //guaranteed to be a String for dirts.
	dirt.setColor(color);
	dirt.setImgPath("/imgs/locations/" + color + "_dirt.png");
	
	return dirt;
    }

    private void padMissingLocations() {
	for(int i = 0; i < this.size; i++) {
	    for(int j = 0; j < this.size; j++) {
		Coordinates coordinates = new Coordinates(i, j);
		
		if(!this.locations.containsKey(coordinates)) {
		    this.locations.put(coordinates, new VWIncrementalLocation(coordinates, new VWIncrementalPiece(), new VWIncrementalPiece()));
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
    private JSONObject createLocation(VWIncrementalLocation loc) {
	JSONObject location = new JSONObject();
	
	location.put(VWJSON.X, loc.getCoordinates().getX());
	location.put(VWJSON.Y, loc.getCoordinates().getY());
	
	//by construction, either an actor or a dirt are there.
	if(loc.doesAnActorExist()) {
	    location.put(VWJSON.ACTOR, loc.getP1().serialize());
	}
	else {
	    location.put(VWJSON.DIRT, loc.getP1().serialize());
	}
	
	if(loc.isActorAndDirt()) {
	    location.put(VWJSON.DIRT, loc.getP2().serialize());
	}
	
	return location;
    }
}