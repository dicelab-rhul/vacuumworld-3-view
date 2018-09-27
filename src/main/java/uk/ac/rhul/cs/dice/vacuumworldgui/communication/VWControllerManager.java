package uk.ac.rhul.cs.dice.vacuumworldgui.communication;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWMessageCodes;
import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldMessage;

public class VWControllerManager {
    private Socket socketWithController;
    private InputStream from;
    private OutputStream to;
    private ObjectInputStream fromController;
    private ObjectOutputStream toController;
    private VacuumWorldMessage latest;

    public VWControllerManager(String controllerIp, int controllerPort) {
	try {
	    this.socketWithController = new Socket(controllerIp, controllerPort);
	    this.to = this.socketWithController.getOutputStream();
	    this.from = this.socketWithController.getInputStream();
	    this.toController = new ObjectOutputStream(this.to);
	    this.fromController = new ObjectInputStream(this.from);
	}
	catch (Exception e) {
	    throw new RuntimeException(e);
	}
    } 

    public VacuumWorldMessage getLatestReceivedMessage() {
	return this.latest;
    }
    
    public void setupNetwork() {
	sendHVC();
	parseHCV();
	sendHVM();
	parseHMV();
    }
    
    private void parseHCV() {
	parseMessageType(VWMessageCodes.HELLO_VIEW_FROM_CONTROLLER);
    }

    private void parseHMV() {
	parseMessageType(VWMessageCodes.HELLO_VIEW_FROM_MODEL);
    }
    
    private void parseMessageType(VWMessageCodes expected) {
	VWMessageCodes receivedCode = this.latest.getCode();
	
	if(!expected.equals(receivedCode)) {
	    throw new IllegalArgumentException("Expected" + expected + ", got " + receivedCode + " instead.");
	}
    }

    public void sendHVC() {
	VacuumWorldMessage hvc = new VacuumWorldMessage(VWMessageCodes.HELLO_CONTROLLER_FROM_VIEW, null);
	sendMessage(hvc);
	
	this.latest = receiveMessage();
    }
    
    public void sendHVM() {
	VacuumWorldMessage hvm = new VacuumWorldMessage(VWMessageCodes.HELLO_MODEL_FROM_VIEW, null);
	sendMessage(hvm);
	
	this.latest = receiveMessage();
    }
    
    public void sendInit(JSONObject content) {
	VacuumWorldMessage message = new VacuumWorldMessage(VWMessageCodes.INIT_GAME_FROM_VIEW, content);
	sendMessage(message);
	
	this.latest = receiveMessage();
    }

    private void sendMessage(VacuumWorldMessage hvc) {
	try {
	    this.toController.reset();
	    this.toController.writeObject(hvc);
	    this.toController.flush();
	}
	catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    private VacuumWorldMessage receiveMessage() {
	try {
	    return (VacuumWorldMessage) this.fromController.readObject();
	}
	catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}