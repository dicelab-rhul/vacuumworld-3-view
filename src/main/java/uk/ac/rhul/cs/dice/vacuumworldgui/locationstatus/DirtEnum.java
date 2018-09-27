package uk.ac.rhul.cs.dice.vacuumworldgui.locationstatus;

public enum DirtEnum {
    GREEN, ORANGE, NONE;
    
    @Override
    public String toString() {
        if(this == NONE) {
            throw new IllegalArgumentException();
        }
	
        return super.toString().toLowerCase();
    }
}