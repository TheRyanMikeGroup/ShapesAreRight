package application;


/**
 * This object is a n-sided shape with a javafx color also.
 * @author Ryan
 *
 */
public class ColoredShape extends Shape{

	/**
	 * The javafx color that the shape will be filled in with.
	 */
	private final NamedColor color;

	
	/**
	 * Creates a colored shape
	 * @param num_sides The number of sides the shape has
	 * @param color The color of the shape
	 */
	public ColoredShape(int num_sides, NamedColor color) {
		super(num_sides);
		this.color = color;
		polygon.getStyleClass().setAll("coloredPolygon");
		polygon.setFill(color.getColor());
		getChildren().clear();
		getChildren().setAll(polygon, text);
		
		System.out.println("Generated " + num_sides + " " + color);
	}
	
	/**
	 * Creates the string version of the object
	 * @return The Shape string and the the colored to string
	 */
	public String toString()
	{
		return super.toString() + " Color is " + color.toString();
	}
	
	
	/**
	 * Checks if two colored shapes are equal
	 * @param other The colored shape that the object will be compared with
	 * @return true if and only if the two shapes are equal and the colors are equal.
	 */
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof ColoredShape))
			return false;
		
		ColoredShape coloredShapeOther = (ColoredShape)other;
		return super.equals(coloredShapeOther) && color.toString().equals(coloredShapeOther.color.toString());
	}
	
	/**
	 * 
	 * @return The color of the shape.
	 */
	public NamedColor getColor()
	{
		return color;
	}
}
