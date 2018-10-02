package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWOptionsOKButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWMindSelectedListener;

public class VWOptionsDialog extends VWAbstractDialog {

    public VWOptionsDialog(Component parent) {
	super("VacuumWorld 3.0 options", parent, true, false);
    }
    
    @Override
    public void createDialog() {
        super.createDialog();
        
        this.dialog.setLayout(new GridLayout(9, 1));
        
        String sizeString = "<html>Grid size</html>";
	JLabel sizeLabel = new JLabel(sizeString, SwingConstants.CENTER);
        
	int current = VWGameProperties.getInstance().getGridSize();
	int currentValue = current == 0 ? VWGameProperties.getDefaultSize() : current;
	
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, VWGameProperties.getMinSize(), VWGameProperties.getMaxSize(), currentValue);
        sizeSlider.setMajorTickSpacing(1);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.addChangeListener(new GridSizeListener());
        
        String greenMind = "<html>Green agent minds</html>";
	JLabel greenMindLabel = new JLabel(greenMind, SwingConstants.CENTER);
	
	String orangeMind = "<html>Orange agent minds</html>";
	JLabel orangeMindLabel = new JLabel(orangeMind, SwingConstants.CENTER);
	
	String whiteMind = "<html>White agent minds</html>";
	JLabel whiteMindLabel = new JLabel(whiteMind, SwingConstants.CENTER);
        
	JComboBox<String> greenAgentMindBox = createBox("green");
	JComboBox<String> orangeAgentMindBox = createBox("orange");
	JComboBox<String> whiteAgentMindBox = createBox("white");
	
        VWOptionsOKButton button = new VWOptionsOKButton(getDialog());
        button.createButton();
        
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(button.getButton());
        
        this.dialog.add(sizeLabel, 0);
        this.dialog.add(sizeSlider, 1);
        this.dialog.add(greenMindLabel, 2);
        this.dialog.add(greenAgentMindBox, 3);
        this.dialog.add(orangeMindLabel, 4);
        this.dialog.add(orangeAgentMindBox, 5);
        this.dialog.add(whiteMindLabel, 6);
        this.dialog.add(whiteAgentMindBox, 7);
        this.dialog.add(okButtonPanel, 8);
	this.dialog.setMinimumSize(new Dimension(220, 275));
	
	super.positionAndDisplay();
    }
    
    private JComboBox<String> createBox(String type) {
	JComboBox<String> box = new JComboBox<>(VWGameProperties.getInstance().getMindsAsArray());
	box.setSelectedItem(VWGameProperties.getInstance().getMind(type));
	box.addActionListener(new VWMindSelectedListener(type));
	
	return box;
    }

    private class GridSizeListener implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider) e.getSource();
	    
	    VWGameProperties.getInstance(source.getValue());
	    
	    System.out.println("Grid size changed: now " + VWGameProperties.getInstance().getGridSize() + ".");
	}
    }
 }