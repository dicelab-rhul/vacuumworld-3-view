package uk.ac.rhul.cs.dice.vacuumworldgui.communication;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWHandshakeWhitelister;
import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VWMessageCodes;
import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldMessage;
import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldRuntimeException;

public class VWControllerManager {
    private Socket socketWithController;
    private InputStream from;
    private OutputStream to;
    private ValidatingObjectInputStream fromController;
    private ObjectOutputStream toController;
    private VacuumWorldMessage latest;

    public VWControllerManager(String controllerIp, int controllerPort) {
	LogUtils.log("View here: attempting to connect to the controller at " + controllerIp + ":" + controllerPort + ".");
	
	try {
	    this.socketWithController = new Socket(controllerIp, controllerPort);
	    this.to = this.socketWithController.getOutputStream();
	    this.from = this.socketWithController.getInputStream();
	    this.toController = new ObjectOutputStream(this.to);
	    this.fromController = new ValidatingObjectInputStream(this.from);
	    
	    LogUtils.log("View here: connected to the controller at " + controllerIp + ":" + controllerPort + ".");
	}
	catch (Exception e) {
	    throw new VacuumWorldRuntimeException(e);
	}
    } 

    public void sendAcknowledgementToModel() {
	VacuumWorldMessage message = new VacuumWorldMessage(VWMessageCodes.ACK_FROM_VIEW, null);
	
	sendMessage(message);
    }
    
    public void sendStopToModel() {
	VacuumWorldMessage message = new VacuumWorldMessage(VWMessageCodes.QUIT_FROM_VIEW, null);
	
	sendMessage(message);
    }
    
    public void sendStateToModel(JSONObject state) {
	VacuumWorldMessage message = new VacuumWorldMessage(VWMessageCodes.INIT_GAME_FROM_VIEW, state);
	
	sendMessage(message);
    }
    
    public JSONObject fetchUpdateFromModel() {
	try {
	    VWHandshakeWhitelister.whitelistHandshakeClasses(this.fromController);
	    VacuumWorldMessage message = (VacuumWorldMessage) this.fromController.readObject();
	    
	    return message.getContent();
	}
	catch(Exception e) {
	    throw new IllegalArgumentException(e);
	}
    }
    
    public VacuumWorldMessage getLatestReceivedMessage() {
	return this.latest;
    }
    
    public void setupNetwork() {
	LogUtils.log("View here: initiating handshake with the controller...");
	
	sendHVC();
	parseHCV();
	sendHVM();
	parseHMV();
	
	LogUtils.log("View here: handshake completed!");
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
	else {
	    LogUtils.log("View here: received " + receivedCode + " from the controller.");
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

    private void sendMessage(VacuumWorldMessage message) {
	LogUtils.log("View here: sending " + message.getCode() + " to the controller...");
	
	try {
	    this.toController.reset();
	    this.toController.writeObject(message);
	    this.toController.flush();
	}
	catch (Exception e) {
	    throw new VacuumWorldRuntimeException(e);
	}
    }

    private VacuumWorldMessage receiveMessage() {
	try {
	    VWHandshakeWhitelister.whitelistHandshakeClasses(this.fromController);
	    
	    return (VacuumWorldMessage) this.fromController.readObject();
	}
	catch (Exception e) {
	    throw new VacuumWorldRuntimeException(e);
	}
    }
}