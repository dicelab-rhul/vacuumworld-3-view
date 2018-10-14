package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWJSON;
import uk.ac.rhul.cs.dice.vacuumworldgui.communication.VWControllerManager;

public class Main {
    private static final String CONFIG_FILE_PATH = "config.json";
    
    private Main() {}

    public static void main(String[] args) throws IOException {
	LogUtils.enableVerbose();
	LogUtils.log("The GUI is running.");
	String[] fromConfig = getDetailsFromConfigFile();
	
	if (!checkPort(fromConfig[1])) {
	    LogUtils.log("Malformed or illegal details have been provided. Please edit " + CONFIG_FILE_PATH + " and retry.");
	}
	else {
	    init(fromConfig[0], fromConfig[1]);
	}
    }

    private static void init(String hostname, String port) throws IOException {
	VWControllerManager manager = new VWControllerManager(hostname, Integer.valueOf(port));
	manager.setupNetwork();
	
	VWGameProperties.getInstance().setManager(manager);
	VWGameProperties.getInstance().setWhiteLocationPath("/imgs/locations/white_square.png");
	
	List<List<String>> minds = parseMindsFile();
	List<String> agentMinds = minds == null ? Collections.emptyList() : minds.get(0);
	List<String> userMinds = minds == null ? Collections.emptyList() : minds.get(1);
	List<String> defaultMinds = minds == null ? Collections.emptyList() : minds.get(2);
	
	VWGameProperties.getInstance().setUserMind(userMinds.get(0));
	VWGameProperties.getInstance().setDefaultMind(defaultMinds.get(0));
	
	agentMinds.forEach(VWGameProperties.getInstance()::addMind);
	
	new VWWelcomeWindow();
    }

    private static List<List<String>> parseMindsFile() throws IOException {	
	try {
	    JSONTokener tokener = new JSONTokener(new FileInputStream(VWGameProperties.MINDS_FILE));
	    JSONObject root = new JSONObject(tokener);
	    
	    return parseMinds(root);
	}
	catch(FileNotFoundException e) {
	    LogUtils.fakeLog(e);
	    LogUtils.log(VWGameProperties.MINDS_FILE + " was not found.");
	    
	    return Collections.emptyList();
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	    
	    return Collections.emptyList();
	}
    }

    private static List<List<String>> parseMinds(JSONObject root) {
	List<String> agentMinds = root.getJSONArray(VWJSON.AGENTS).toList().stream().map(mind -> (String) mind).collect(Collectors.toList());
	List<String> userMinds = Arrays.asList(root.getString(VWJSON.USERS));
	List<String> defaultMind = Arrays.asList(root.getString(VWJSON.AGENTS_DEFAULT));
	
	return Arrays.asList(agentMinds, userMinds, defaultMind);
    }
    
    private static String[] getDetailsFromConfigFile() {
	try {
	    JSONTokener tokener = new JSONTokener(new FileInputStream(CONFIG_FILE_PATH));
	    JSONObject root = new JSONObject(tokener);
	    
	    return new String[] {root.getString(VWJSON.CONTROLLER_HOSTNAME), root.getString(VWJSON.CONTROLLER_PORT)};
	}
	catch(FileNotFoundException e) {
	    LogUtils.fakeLog(e);
	    LogUtils.log(CONFIG_FILE_PATH + " was not found.");
	    
	    return new String[] {null, null};
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	    
	    return new String[] {null, null};
	}
    }
    
    private static boolean checkPort(String port) {
	if (port == null) {
	    return false;
	}

	return testPort(port);
    }

    private static boolean testPort(String portRepresentation) {
	try {
	    int port = Integer.parseInt(portRepresentation);

	    return port > 0 && port < 65536;
	}
	catch (NumberFormatException e) {
	    LogUtils.fakeLog(e);

	    return false;
	}
    }
}