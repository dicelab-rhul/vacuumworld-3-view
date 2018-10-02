package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWSelectPieceListener implements MouseListener {
    private Component parent;
    private VWState state;
    private Coordinates coordinates;
    private String imgPath; 
    
    public VWSelectPieceListener(Component parent, VWState state, Coordinates coordinates, String imgPath) {
	this.parent = parent;
	this.state = state;
	this.coordinates = coordinates;
	this.imgPath = imgPath;
    }

    private void updateState() {	
	if(tryingToAddAnActor()) {
	    System.out.println("Trying to an actor on location " + this.coordinates + "...");
	    attemptToAddAnActor();
	}
	else if(tryingToAddADirt()) {
	    System.out.println("Trying to add a piece of dirt on location " + this.coordinates + "...");
	    attemptToAddADirt();
	}
	else {
	    System.out.println("Resetting location " + this.coordinates + "...");
	    this.state.resetLocation(this.coordinates);
	}
	
	this.state.getCallback().update();
    }

    private boolean tryingToAddAnActor() {
	String[] tokens = this.imgPath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	return !name.startsWith("white_square") && !name.contains("dirt");
    }

    private boolean tryingToAddADirt() {
	String[] tokens = this.imgPath.split("/");
	String name = tokens[tokens.length - 1].split("\\.")[0];
	
	return name.contains("dirt");
    }

    private void attemptToAddADirt() {
	if(this.state.getLocations().get(this.coordinates).doesADirtExist()) {
	    System.out.println("Nope! A piece of dirt already exists on location "  + this.coordinates + ".");
	    
	    return;
	}
	
	System.out.println("Adding a piece of dirt on location "  + this.coordinates + ".");
	
	if(this.state.getLocations().get(this.coordinates).doesAnActorExist()) {
	    this.state.addDirtToLocationWithActorFromView(this.coordinates, this.imgPath);
	}
	else {
	    this.state.addDirtToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }

    private void attemptToAddAnActor() {
	if(this.state.getLocations().get(this.coordinates).doesAnActorExist()) {
	    System.out.println("Nope! An actor already exists on location "  + this.coordinates + ".");
	    
	    return;
	}
	
	System.out.println("Adding an actor on location "  + this.coordinates + ".");
	
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
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }
}