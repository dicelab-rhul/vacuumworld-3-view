package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.util.Optional;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class WelcomeScene extends Scene {
    private String title;
    
    public WelcomeScene(Parent root, String title) {
	super(root);
	
	this.title = title;
    }

    public WelcomeScene(Parent root, Paint fill) {
	super(root, fill);
    }

    public WelcomeScene(Parent root, String title, double width, double height) {
	super(root, width, height);
	
	this.title = title;
    }

    public WelcomeScene(Parent root, double width, double height, Paint fill) {
	super(root, width, height, fill);
    }

    public WelcomeScene(Parent root, double width, double height, boolean depthBuffer) {
	super(root, width, height, depthBuffer);
    }

    public WelcomeScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
	super(root, width, height, depthBuffer, antiAliasing);
    }
    
    public Optional<Pane> getLayoutIfAny() {
	try {
	    return Optional.of((Pane) getRoot());
	}
	catch(ClassCastException e) {
	    return Optional.empty();
	}
    }
    
    public String getTitle() {
	return this.title;
    }
}