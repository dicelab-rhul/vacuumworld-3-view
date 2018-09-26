package uk.ac.rhul.cs.dice.vacuumworldgui;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWCreditsButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWLoadButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWStartButton;

public class WelcomeWindow {
    private JFrame welcomeWindowFrame;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 545;
    
    public WelcomeWindow() {
	buildWelcomeWindow();
    }

    private void buildWelcomeWindow() {
	this.welcomeWindowFrame = new JFrame();
	this.welcomeWindowFrame.setTitle("Welcome to VacuumWorld 3.0!");
	this.welcomeWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel welcomePanel = new JPanel();
	JLabel welcomeLabel = new JLabel();
	welcomeLabel.setIcon(new ImageIcon("res/imgs/start_menu.png"));
	
	VWStartButton startButton = new VWStartButton(this.welcomeWindowFrame);
	startButton.createButton();
	
	VWLoadButton loadButton = new VWLoadButton(this.welcomeWindowFrame);
	loadButton.createButton();
	
	VWCreditsButton creditsButton = new VWCreditsButton(this.welcomeWindowFrame);
	creditsButton.createButton();
	
	welcomeLabel.setLayout(new GridBagLayout());
	welcomeLabel.add(startButton.getButton(), startButton.getConstraints());
	welcomeLabel.add(loadButton.getButton(), loadButton.getConstraints());
	welcomeLabel.add(creditsButton.getButton(), creditsButton.getConstraints());
	
	welcomePanel.add(welcomeLabel);
	
	this.welcomeWindowFrame.add(welcomePanel);
	this.welcomeWindowFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	this.welcomeWindowFrame.setVisible(true);
    }
}