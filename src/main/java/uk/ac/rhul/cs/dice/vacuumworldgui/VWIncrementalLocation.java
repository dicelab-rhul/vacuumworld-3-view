package uk.ac.rhul.cs.dice.vacuumworldgui;

public class VWIncrementalLocation {
    private Coordinates c;
    private VWIncrementalPiece p1;
    private VWIncrementalPiece p2;
    
    public VWIncrementalLocation(Coordinates c, VWIncrementalPiece p1, VWIncrementalPiece p2) {
	this.c = c;
	this.p1 = p1;
	this.p2 = p2;
	
	check();
    }

    public Coordinates getCoordinates () {
	return this.c;
    }
    
    public void setP1(VWIncrementalPiece p1) {
	this.p1 = p1;
    }
    
    public void setP2(VWIncrementalPiece p2) {
	this.p2 = p2;
    }
    
    public VWIncrementalPiece getP1() {
	return this.p1;
    }
    
    public VWIncrementalPiece getP2() {
	return this.p2;
    }
    
    public boolean isEmpty() {
	return this.p1.isBlank();
    }
    
    public boolean doesAnActorExist() {
	return this.p1.isActor();
    }
    
    public boolean doesADirtExist() {
	return this.p1.isDirt() || this.p2.isDirt();
    }
    
    public boolean isLoneActor() {
	return this.p1.isActor() && this.p2 == null;
    }
    
    public boolean isLoneDirt() {
	return this.p1.isDirt();
    }
    
    public boolean isActorAndDirt() {
	return this.p1.isActor() && this.p2.isDirt();
    }
    
    private void check() {
	if(this.p1.isBlank() || this.p1.isDirt()) {
	    checkEmpty(this.p2);
	}
	else if(this.p1.isActor()) {
	    checkNotActor(this.p2);
	}
	else {
	    throw new IllegalArgumentException();
	}
    }

    private void checkEmpty(VWIncrementalPiece p) {
	if(!p.isBlank()) {
	    throw new IllegalArgumentException();
	}
    }

    private void checkNotActor(VWIncrementalPiece p) {
	if(p.isActor()) {
	    throw new IllegalArgumentException();
	}
    }
}