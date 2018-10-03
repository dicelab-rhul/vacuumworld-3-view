package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import uk.ac.rhul.cs.dice.vacuumworldgui.communication.VWControllerManager;

public class Main {

    private Main() {}

    public static void main(String[] args) throws IOException {
	LogUtils.log("The GUI is running.");
	VWControllerManager manager = new VWControllerManager("127.0.0.1", 13337);
	manager.setupNetwork();
	
	VWGameProperties.getInstance().setManager(manager);
	
	List<List<String>> minds = parseMindsFile();
	List<String> agentMinds = minds.get(0);
	List<String> userMinds = minds.get(1);
	
	VWGameProperties.getInstance().setUserMind(userMinds.get(0)); //TODO change this
	VWGameProperties.getInstance().setDefaultMind(minds.get(2).get(0)); //TODO change this
	
	agentMinds.forEach(VWGameProperties.getInstance()::addMind);
	
	new VWWelcomeWindow();
    }

    private static List<List<String>> parseMindsFile() throws IOException {
	try {
	    JSONTokener tokener = new JSONTokener(Main.class.getResource(VWGameProperties.MINDS_FILE).openStream());
	    JSONObject root = new JSONObject(tokener);
	    
	    return parseMinds(root);
	}
	catch(Exception e) {
	    e.printStackTrace(System.out);
	    throw new IOException(e);
	}
    }

    private static List<List<String>> parseMinds(JSONObject root) {
	List<String> agentMinds = root.getJSONArray("agents").toList().stream().map(mind -> (String) mind).collect(Collectors.toList());
	List<String> userMinds = Arrays.asList(root.getString("users"));
	List<String> defaultMind = Arrays.asList(root.getString("agents_default"));
	
	return Arrays.asList(agentMinds, userMinds, defaultMind);
    }
}