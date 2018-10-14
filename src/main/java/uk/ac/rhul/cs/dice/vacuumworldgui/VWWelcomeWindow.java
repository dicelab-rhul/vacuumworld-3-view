package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWCreditsButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWLoadButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWOptionsButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWNewGameButton;

public class VWWelcomeWindow {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 545;
    
    public VWWelcomeWindow() throws IOException {
	LogUtils.log("View here: building UI...");
	buildWelcomeWindow();
    }

    private void buildWelcomeWindow() throws IOException {
	JFrame welcomeWindowFrame = new JFrame();
	welcomeWindowFrame.setTitle("Welcome to VacuumWorld 3.0!");
	welcomeWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel welcomePanel = new JPanel();
	JLabel welcomeLabel = new JLabel();
	
	LogUtils.log("View here: before...");
	welcomeLabel.setIcon(new ImageIcon(ImageIO.read(Main.class.getResourceAsStream("/res/imgs/start_menu.png"))));
	LogUtils.log("View here: after...");
	
	VWNewGameButton startButton = new VWNewGameButton(welcomeWindowFrame);
	startButton.createButton();
	
	VWLoadButton loadButton = new VWLoadButton(welcomeWindowFrame);
	loadButton.createButton();
	
	VWOptionsButton optionsButton = new VWOptionsButton(welcomeWindowFrame);
	optionsButton.createButton();
	
	VWCreditsButton creditsButton = new VWCreditsButton(welcomeWindowFrame);
	creditsButton.createButton();
	
	welcomeLabel.setLayout(new GridBagLayout());
	welcomeLabel.add(startButton.getButton(), startButton.getConstraints());
	welcomeLabel.add(loadButton.getButton(), loadButton.getConstraints());
	welcomeLabel.add(optionsButton.getButton(), optionsButton.getConstraints());
	welcomeLabel.add(creditsButton.getButton(), creditsButton.getConstraints());
	
	welcomePanel.add(welcomeLabel);
	
	welcomeWindowFrame.add(welcomePanel);
	welcomeWindowFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	welcomeWindowFrame.setLocationRelativeTo(null);
	welcomeWindowFrame.setVisible(true);
    }
}