package application;

import java.util.Random;
import java.util.Set;

import javafx.scene.layout.StackPane;

public class MysteryShape extends StackPane{

	/**
	 * The random shape for the trial
	 */
	private ColoredShape shape;
	
	/**
	 * The curtain that hides the shape
	 */
	private Curtain curtain = new Curtain();
	
	/**
	 * Creates a mystery shape with a random number of sides and color from the two sets
	 * @param possibleSides The possible number of sides the shape can have
	 * @param possibleColors The possible colors the shape can be
	 */
	public MysteryShape(Set<Integer> possibleSides, Set<NamedColor> possibleColors) {
		super();
		this.setPrefSize(100, 100);
		shape = new ColoredShape(getRandomFromSet(possibleSides),
									getRandomFromSet(possibleColors));
		
		super.getChildren().add(shape);
		super.getChildren().add(curtain);
	}
	
	/**
	 * Returns the identity of the shape behind the curtain and if it is revealed
	 */
	public String toString()
	{
		return shape.toString() + " Is revealed: " + curtain.toString();
	}
	
	/**
	 * Returns a random element in the set
	 * @param set The set to select randomly from
	 * @return A random variable in the set
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRandomFromSet(Set<T> set)
	{
		return (T) (set.toArray()[(new Random()).nextInt(set.size())]);
	}
	
	/**
	 * Reveals the shape behind the curtain
	 */
	public void reveal() {
		
		//Reveals the object behind the curtain
		curtain.reveal();
		
	}

	/**
	 * Gets the colored shape that is the mystery shape
	 * @return The colored Shape the is the mystery shape
	 */
	public ColoredShape getColoredShape() {
		return shape;
	}
	
}
