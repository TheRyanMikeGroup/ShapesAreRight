package application;

import java.util.Set;

import javafx.scene.layout.GridPane;

/**
 * This represents a trial, which contains a mystery shape and what the users guess is
 * @author Ryan
 *
 */
public class Trial extends GridPane{
	
	/**
	 * The mystery shape display, it will hide the shape until it is revealed
	 */
	private MysteryShape shapeDisplay;
	
	/**
	 * User inputs their guess for what the mystery shape is.
	 */
	private ShapeSelector shapeSelector;
	
	/**
	 * This generates a trial with a mystery shape and a selector
	 * @param possibleSides The possible sides that the shape can have
	 * @param possibleColors The possible colors that the shape can be
	 */
	public Trial(Set<Integer> possibleSides, Set<NamedColor> possibleColors) {
		super();
		
		shapeDisplay = new MysteryShape(possibleSides, possibleColors);
		shapeSelector = new ShapeSelector(possibleSides, possibleColors);
		super.addRow(0, shapeDisplay);
		super.addRow(1, shapeSelector);
	}

	/**
	 * Locks in the user's guess, reveals the mystery shape and checks if the user's guess was correct
	 * @return True if the user was correct, false if incorrect
	 */
	public boolean reveal() {
		//Disables the users ability put in their input for the trial
		shapeSelector.disable();
		
		//Reveals the mysteryShape to the user
		shapeDisplay.reveal();
		
		//Checks if the shape the user selected and the mystery shape are the same, and returns the value.
		return ((ColoredShape) shapeDisplay.getColoredShape()).equals(((ColoredShape) shapeSelector.getColoredShape()));
	}


 
	
}
