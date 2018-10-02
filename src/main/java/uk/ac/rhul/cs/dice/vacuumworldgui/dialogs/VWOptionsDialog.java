package uk.ac.rhul.cs.dice.vacuumworldgui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWOptionsOKButton;

public class VWOptionsDialog extends VWAbstractDialog {

    public VWOptionsDialog(Component parent) {
	super("VacuumWorld 3.0 options", parent, true, false);
    }
    
    @Override
    public void createDialog() {
        super.createDialog();
        
        this.dialog.setLayout(new GridLayout(3, 1));
        
        String sizeString = "<html>Grid size</html>";
	JLabel sizeLabel = new JLabel(sizeString, SwingConstants.CENTER);
        
	int current = VWGameProperties.getInstance().getGridSize();
	int currentValue = current == 0 ? VWGameProperties.getDefaultSize() : current;
	
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, VWGameProperties.getMinSize(), VWGameProperties.getMaxSize(), currentValue);
        sizeSlider.setMajorTickSpacing(1);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.addChangeListener(new GridSizeListener());
        
        VWOptionsOKButton button = new VWOptionsOKButton(getDialog());
        button.createButton();
        
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(button.getButton());
        
        this.dialog.add(sizeLabel, 0);
        this.dialog.add(sizeSlider, 1);
        this.dialog.add(okButtonPanel, 2);
	this.dialog.setMinimumSize(new Dimension(220, 275));
	
	super.positionAndDisplay();
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