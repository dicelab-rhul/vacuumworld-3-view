package uk.ac.rhul.cs.dice.vacuumworldgui;

import org.json.JSONObject;
import org.junit.Test;

public class ParserAndSerializerTest {

    @Test
    public void test(String path) {
	int gridSize = 6;
	
	String agent32 = "res/imgs/locations/green_west.png";
	String mindAgent32 = "com.DummyMind1.java";
	
	String agent14= "res/imgs/locations/white_south.png";
	String mindAgent14 = "com.DummyMind2.java";
	
	String agent33 = "res/imgs/locations/orange_north.png";
	String mindAgent33 = "com.DummyMind3.java";
	
	String user55 = "res/imgs/locations/user_east.png";
	String mindUser55 = "com.DummyMind4.java";
	
	String avatar02 = "res/imgs/locations/avatar_west.png";
	String mindAvatar02 = "com.DummyMind5.java";
	
	String dirt11 = "res/imgs/locations/orange_dirt.png";
	
	String dirt14 = "res/imgs/locations/green_dirt.png";
	
	VWState state = VWState.getInstance(gridSize);
	
	for(int i = 0; i < gridSize; i++) {
	    for(int j = 0; j < gridSize; j++) {
		Coordinates coordinates = new Coordinates(i, j); //TODO check.
		state.addEmptyLocationFromView(coordinates);
	    }
	}
	
	state.addActorToEmptyLocationFromView(new Coordinates(3, 2), agent32, mindAgent32);
	state.addActorToEmptyLocationFromView(new Coordinates(1, 4), agent14, mindAgent14);
	state.addActorToEmptyLocationFromView(new Coordinates(3, 3), agent33, mindAgent33);
	state.addActorToEmptyLocationFromView(new Coordinates(5, 5), user55, mindUser55);
	state.addActorToEmptyLocationFromView(new Coordinates(0, 2), avatar02, mindAvatar02);
	state.addDirtToEmptyLocationFromView(new Coordinates(1, 1), dirt11);
	state.addDirtToLocationWithActorFromView(new Coordinates(1, 4), dirt14);
	
	
	JSONObject json = state.serializeState();
	
	print(json);
	
	VWState.reset(json);
	json = state.serializeState();
	
	print(json);
	
	state.saveState(path);
	
	VWState.reset();
	state = VWState.getInstance(path);
	json = state.serializeState();
	
	print(json);
    }

    private void print(JSONObject json) {
	System.out.println(json.toString(4));
    }
}