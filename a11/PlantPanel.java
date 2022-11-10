package a11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * This class contains one button for each possible plant, and when a button is
 * clicked then it switches the currently selected plant as appropriate.
 */
@SuppressWarnings("serial")
public class PlantPanel extends JPanel implements ActionListener {

	private JRadioButton plant1 = new JRadioButton("plant");
	private JRadioButton plant2 = new JRadioButton("bomb");
	private static String selectedPlant;

	public PlantPanel() {
		// Add this as a listener, so it can receive events
		// when either button is clicked.
		plant1.addActionListener(this);
		plant2.addActionListener(this);

		add(plant1);
		add(plant2);
		setLayout(new BoxLayout(this, 2));

		// A ButtonGroup ensures that only one button is selected
		// at a time.
		ButtonGroup group = new ButtonGroup();
		group.add(plant1);
		group.add(plant2);
		plant1.setSelected(true);
		selectedPlant = "PLANT";
	}

	/**
	 * Returns the currently selected plant.
	 */
	public static String getPlant() {
		return selectedPlant;
	}

	/**
	 * Handles events from the JRadioButtons in order to switch the currently
	 * selected plant as appropriate.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == plant1) {
			selectedPlant = "PLANT";
		} else if (e.getSource() == plant2) {
			selectedPlant = "BOMB";
		} else {
			System.out.println("UNKNOWN BUTTON");
		}
	}

}
