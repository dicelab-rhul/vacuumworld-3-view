package uk.ac.rhul.cs.dice.vacuumworldgui;

public class Coordinates {
    private int x;
    private int y;
    
    public Coordinates(int x, int y) {
	this.x = x;
	this.y = y;
    }
    
    public int getX() {
	return this.x;
    }
    
    public int getY() {
	return this.y;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + this.x;
	result = prime * result + this.y;
	
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	
	if (obj == null) {
	    return false;
	}
	
	if (getClass() != obj.getClass()) {
	    return false;
	}
	
	return castAndCompare(obj);
    }

    private boolean castAndCompare(Object obj) {
	Coordinates other = (Coordinates) obj;
	
	if (other == null) {
	    return false;
	}
	
	if (this.x != other.x) {
	    return false;
	}
	
	if (y != other.y) {
	    return false;
	}
	
	return true;
    }
}