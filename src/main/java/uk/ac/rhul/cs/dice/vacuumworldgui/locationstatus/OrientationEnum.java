package uk.ac.rhul.cs.dice.vacuumworldgui.locationstatus;

public enum OrientationEnum {
    NORTH, SOUTH, EAST, WEST, UNDEFINED;
    
    @Override
    public String toString() {
	if(this == UNDEFINED) {
	    throw new IllegalArgumentException();
	}
	
        return super.toString().toLowerCase();
    }
}