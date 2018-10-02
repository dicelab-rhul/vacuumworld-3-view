package uk.ac.rhul.cs.dice.vacuumworldgui;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.communication.VWControllerManager;

public class Main {

    private Main() {}

    public static void main(String[] args) {
	LogUtils.log("The GUI is running.");
	VWWelcomeWindow w1 = new VWWelcomeWindow();
	//VWControllerManager manager = new VWControllerManager("127.0.0.1", 13337);
	//manager.setupNetwork();
    }
}