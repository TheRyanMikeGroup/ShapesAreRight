package application;

import javafx.scene.paint.Color;

/**
 * This class represents a color that has a string name attached to it
 * @author Ryan
 *
 */
public class NamedColor{
	
	//The color value of the named color
	private Color color;
	
	//The name of the color
	private String name;
	
	/**
	 * Initializes a named colored with the respective color and name value
	 * @param color The color value of the color
	 * @param name The name of the color
	 */
	public NamedColor(Color color, String name)
	{
		//Initializes the color and the name
		this.color = color;
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}

	/**
	 * Returns the color value of the named color
	 * @return The color value of the color
	 */
	public Color getColor() {
		return color;
	}
}
