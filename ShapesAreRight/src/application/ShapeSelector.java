package application;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

/**
 * Handles the selection for the users guess
 * @author Ryan
 *
 */
public class ShapeSelector extends FlowPane{
	/**
	 * Set of all the possible colors the shape can be
	 */
	private Set<Color> possibleColors;
	
	/**
	 * The set of all the possible number of sides the shape can have
	 */
	private Set<Integer> possibleSides;
	
	/**
	 * The number sides selector
	 */
	private ComboBox<Integer> side;
	
	/**
	 * The color selector.
	 */
	private ComboBox<NamedColor> color;
	
	/**
	 * Initializes the GUI for the selection by setting up the comboboxs and the respective listeners.
	 * @param possibleSides
	 * @param possibleColors
	 */
	public ShapeSelector(Set<Integer> possibleSides,
			Set<NamedColor> possibleColors) {
		super();
		
		//Creates the combo boxes for the side guess and the color from the sets of possible sides and colors
		side = new ComboBox<Integer>((ObservableList<Integer>) FXCollections.observableArrayList(possibleSides));
		color = new ComboBox<NamedColor>((ObservableList<NamedColor>) FXCollections.observableArrayList(possibleColors));
		
		//Initializes combo boxes to the first value in both sets
		side.setValue(possibleSides.iterator().next());
		color.setValue(possibleColors.iterator().next());
		
		//Adds the two combo boxes to itself
		super.getChildren().add(side);
		super.getChildren().add(color);
	}

	/**
	 * Disables the users ability to enter in thier guess
	 */
	public void disable() {
		//Disables the number of sides selector
		side.setDisable(true);
		
		//Disables the color selector
		color.setDisable(true);
		
	}

	/**
	 * Returns the shape that the user selected
	 * @return The colored shape the user selected
	 */
	public ColoredShape getColoredShape() {
		return new ColoredShape(((Integer) side.getValue()).intValue() , ((NamedColor) color.getValue()));
	}


	

	
	

}
