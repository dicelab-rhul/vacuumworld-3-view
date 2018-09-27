package uk.ac.rhul.cs.dice.vacuumworldgui.locationstatus;

public enum ActorEnum {
    GREEN, ORANGE, WHITE, USER, NONE;
    
    public String getColor() {
	switch(this) {
	case GREEN:
	    return "green";
	case ORANGE:
	    return "orange";
	case WHITE:
	    return "white";
	case USER:
	    return null;
	case NONE:
	default:
	    throw new IllegalArgumentException();
	}
    }
    
    public String getType() {
	switch(this) {
	case GREEN:
	case ORANGE:
	case WHITE:
	    return "cleaning_agent";
	case USER:
	    return "user";
	case NONE:
	default:
	    throw new IllegalArgumentException();    
	}
    }
}