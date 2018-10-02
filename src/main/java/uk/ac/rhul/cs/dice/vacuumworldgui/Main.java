package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.io.IOException;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.communication.VWControllerManager;

public class Main {

    private Main() {}

    public static void main(String[] args) throws IOException {
	LogUtils.log("The GUI is running.");
	VWControllerManager manager = new VWControllerManager("127.0.0.1", 13337);
	manager.setupNetwork();
	
	VWGameProperties.getInstance().setManager(manager);
	VWGameProperties.getInstance().setUserMind("VacuumWorldUserMind");
	VWGameProperties.getInstance().setDefaultMind("VacuumWorldDefaultMind");
	VWGameProperties.getInstance().addMind("VacuumWorldDefaultMind");
	VWGameProperties.getInstance().addMind("VacuumWorldGoalOrientedMind");
	VWGameProperties.getInstance().addMind("VacuumWorldGreenMind");
	VWGameProperties.getInstance().addMind("VacuumWorldOrangeMind");
	VWGameProperties.getInstance().addMind("VacuumWorldWhiteMind");
	
	new VWWelcomeWindow();
    }
}