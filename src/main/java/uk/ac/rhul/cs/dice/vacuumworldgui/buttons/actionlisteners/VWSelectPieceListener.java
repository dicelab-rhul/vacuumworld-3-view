package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWSelectPieceListener implements MouseListener {
    private Component parent;
    private volatile VWState state;
    private Coordinates coordinates;
    private String imgPath; 
    
    public VWSelectPieceListener(Component parent, VWState state, Coordinates coordinates, String imgPath) {
	this.parent = parent;
	this.state = state;
	this.coordinates = coordinates;
	this.imgPath = imgPath;
    }

    private void updateState() {	
	if(tryingToAddAnAgent()) {
	    LogUtils.log("Trying to an agent on location " + this.coordinates + "...");
	    attemptToAddAnAgent();
	}
	else if(tryingToAddAUser()) {
	    LogUtils.log("Trying to an agent on location " + this.coordinates + "...");
	    attemptToAddAUser();
	}
	else if(tryingToAddADirt()) {
	    LogUtils.log("Trying to add a piece of dirt on location " + this.coordinates + "...");
	    attemptToAddADirt();
	}
	else {
	    LogUtils.log("Resetting location " + this.coordinates + "...");
	    this.state.resetLocation(this.coordinates);
	}
	
	this.state.getCallback().update();
    }

    private boolean tryingToAddAUser() {
	String[] tokens = this.imgPath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	return name.contains("user");
    }

    private boolean tryingToAddAnAgent() {
	String[] tokens = this.imgPath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	return !name.startsWith("white_square") && !name.contains("dirt") && !name.contains("user");
    }

    private boolean tryingToAddADirt() {
	String[] tokens = this.imgPath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	return name.contains("dirt");
    }

    private void attemptToAddADirt() {
	if(this.state.getLocations().get(this.coordinates).doesADirtExist()) {
	    LogUtils.log("Nope! A piece of dirt already exists on location "  + this.coordinates + ".");
	    
	    return;
	}
	
	LogUtils.log("Adding a piece of dirt on location "  + this.coordinates + ".");
	
	if(this.state.getLocations().get(this.coordinates).doesAnActorExist()) {
	    this.state.addDirtToLocationWithActorFromView(this.coordinates, this.imgPath);
	}
	else {
	    this.state.addDirtToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }

    private void attemptToAddAnAgent() {
	if(this.state.getLocations().get(this.coordinates).doesAnActorExist()) {
	    LogUtils.log("Nope! An actor already exists on location "  + this.coordinates + ".");
	    
	    return;
	}
	
	LogUtils.log("Adding an agent on location "  + this.coordinates + ".");
	
	if(this.state.getLocations().get(this.coordinates).doesADirtExist()) {
	    this.state.addActorToLocationWithDirtFromView(this.coordinates, this.imgPath);
	}
	else {
	    this.state.addActorToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }
    
    private void attemptToAddAUser() {
	if(this.state.getLocations().get(this.coordinates).doesAnActorExist()) {
	    LogUtils.log("Nope! An actor already exists on location "  + this.coordinates + ".");
	    
	    return;
	}
	
	LogUtils.log("Adding a user on location "  + this.coordinates + ".");
	
	if(this.state.getLocations().get(this.coordinates).doesADirtExist()) {
	    this.state.addActorToLocationWithDirtFromView(this.coordinates, this.imgPath);
	}
	else {
	    this.state.addActorToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	updateState();
	
	((JDialog) this.parent).dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// useless
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// useless
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// useless
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// useless
    }
}