package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.Coordinates;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWIllegalStateDialog;

public class VWSelectPieceListener implements MouseListener {
    private Component parent;
    private Coordinates coordinates;
    private String imgPath;
    private static final String ERROR_TAG_ACTOR = "Nope! An actor already exists on location ";
    private static final String ERROR_TAG_DIRT = "Nope! A piece of dirt already exists on location ";
    private static final String ERROR_TAG_USER = "Nope! A user already exists on location ";
    
    public VWSelectPieceListener(Component parent, Coordinates coordinates, String imgPath) {
	this.parent = parent;
	this.coordinates = coordinates;
	this.imgPath = imgPath;
    }

    private void updateState() {	
	if(tryingToAddAnAgent()) {
	    LogUtils.log("Trying to an agent on location " + this.coordinates + "...");
	    attemptToAddAnAgent();
	}
	else if(tryingToAddAUser()) {
	    LogUtils.log("Trying to an user on location " + this.coordinates + "...");
	    attemptToAddAUser();
	}
	else if(tryingToAddADirt()) {
	    LogUtils.log("Trying to add a piece of dirt on location " + this.coordinates + "...");
	    attemptToAddADirt();
	}
	else {
	    LogUtils.log("Resetting location " + this.coordinates + "...");
	    VWState.getExistingInstance().resetLocation(this.coordinates);
	}
	
	VWState.getExistingInstance().getCallback().update();
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
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesADirtExist()) {
	    LogUtils.log(ERROR_TAG_DIRT  + this.coordinates + ".");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(this.parent.getParent(), ERROR_TAG_DIRT  + this.coordinates + ".");
	    dialog.createDialog();
	    
	    return;
	}
	
	LogUtils.log("Adding a piece of dirt on location "  + this.coordinates + ".");
	
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesAnActorExist()) {
	    VWState.getExistingInstance().addDirtToLocationWithActorFromView(this.coordinates, this.imgPath);
	}
	else {
	    VWState.getExistingInstance().addDirtToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }

    private void attemptToAddAnAgent() {
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesAnActorExist()) {
	    LogUtils.log(ERROR_TAG_ACTOR  + this.coordinates + ".");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(this.parent.getParent(), ERROR_TAG_ACTOR  + this.coordinates + ".");
	    dialog.createDialog();
	    
	    return;
	}
	
	LogUtils.log("Adding an agent on location "  + this.coordinates + ".");
	
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesADirtExist()) {
	    VWState.getExistingInstance().addActorToLocationWithDirtFromView(this.coordinates, this.imgPath);
	}
	else {
	    VWState.getExistingInstance().addActorToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }
    
    private void attemptToAddAUser() {
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesAnActorExist()) {
	    LogUtils.log(ERROR_TAG_ACTOR  + this.coordinates + ".");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(this.parent.getParent(), ERROR_TAG_ACTOR  + this.coordinates + ".");
	    dialog.createDialog();
	}
	else if(VWState.getExistingInstance().isAUserPresent()) {
	    LogUtils.log(ERROR_TAG_USER  + VWState.getExistingInstance().getUserCoordinates() + ". Only one user per grid is allowed.");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(this.parent.getParent(), ERROR_TAG_USER  + this.coordinates + ". Only one user per grid is allowed.");
	    dialog.createDialog();
	}
	else {
	    addUser();
	}
    }

    private void addUser() {
	LogUtils.log("Adding a user on location "  + this.coordinates + ".");
	
	if(VWState.getExistingInstance().getLocations().get(this.coordinates).doesADirtExist()) {
	    VWState.getExistingInstance().addActorToLocationWithDirtFromView(this.coordinates, this.imgPath);
	}
	else {
	    VWState.getExistingInstance().addActorToEmptyLocationFromView(this.coordinates, this.imgPath);
	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	updateState();
	
	((JDialog) this.parent).dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// VWSelectPieceListerer does not need to override this.
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// VWSelectPieceListerer does not need to override this.
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// VWSelectPieceListerer does not need to override this.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// VWSelectPieceListerer does not need to override this.
    }
}