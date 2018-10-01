package uk.ac.rhul.cs.dice.vacuumworldgui;

public enum Pieces {
    WHITE_SQUARE("res/imgs/locations/white_square.png"),
    GREEN_NORTH("res/imgs/locations/green_north.png"),
    GREEN_SOUTH("res/imgs/locations/green_south.png"),
    GREEN_EAST("res/imgs/locations/green_east.png"),
    GREEN_WEST("res/imgs/locations/green_west.png"),
    ORANGE_NORTH("res/imgs/locations/orange_north.png"),
    ORANGE_SOUTH("res/imgs/locations/orange_south.png"),
    ORANGE_EAST("res/imgs/locations/orange_east.png"),
    ORANGE_WEST("res/imgs/locations/orange_west.png"),
    WHITE_NORTH("res/imgs/locations/white_north.png"),
    WHITE_SOUTH("res/imgs/locations/white_south.png"),
    WHITE_EAST("res/imgs/locations/white_east.png"),
    WHITE_WEST("res/imgs/locations/white_west.png"),
    USER_NORTH("res/imgs/locations/user_north.png"),
    USER_SOUTH("res/imgs/locations/user_south.png"),
    USER_EAST("res/imgs/locations/user_east.png"),
    USER_WEST("res/imgs/locations/user_west.png"),
    GREEN_DIRT("res/imgs/locations/green_dirt.png"),
    ORANGE_DIRT("res/imgs/locations/orange_dirt.png");
    
    private String imgPath;
    
    private Pieces(String imgPath) {
	this.imgPath = imgPath;
    }
    
    public String getImgPath() {
	return this.imgPath;
    }
    
    public boolean isActor() {
	return this != Pieces.WHITE_SQUARE && this != Pieces.GREEN_DIRT && this != Pieces.ORANGE_DIRT;
    }
    
    public boolean isDirt() {
	return this == Pieces.GREEN_DIRT || this == Pieces.ORANGE_DIRT;
    }
    
    public boolean isEmpty() {
	return this == Pieces.WHITE_SQUARE;
    }
}