package uk.ac.rhul.cs.dice.vacuumworldgui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	primaryStage.setTitle("Test JavaFX");
	
	Button top = new Button("Top");
	Button bottom = new Button("Bottom");
	Button left = new Button("Left");
	Button right = new Button("Right");
	Button center = new Button("Center");
	
	BorderPane layout = new BorderPane();
	
	layout.setTop(top);
	layout.setLeft(left);
	layout.setRight(right);
	layout.setBottom(bottom);
	layout.setCenter(center);
	
	BorderPane.setAlignment(layout.getTop(), Pos.TOP_CENTER);
	BorderPane.setAlignment(layout.getBottom(), Pos.BOTTOM_CENTER);
	BorderPane.setAlignment(layout.getLeft(), Pos.CENTER_LEFT);
	BorderPane.setAlignment(layout.getRight(), Pos.CENTER_RIGHT);
	BorderPane.setAlignment(layout.getCenter(), Pos.CENTER);
	
	Scene scene = new Scene(layout, 300, 200);
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}