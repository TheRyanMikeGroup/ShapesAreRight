package application;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * This object represents a shape with n-sides.
 * @author Ryan
 *
 */
public class Shape extends AnchorPane{
	
	/**
	 * The number of sides the shape has
	 */
	private final int num_sides;
	private Scale scale;
	private Translate translate;
	protected Polygon polygon;
	protected Text text;
	
	private static final double INITIAL_DIMENSION = 100.0;
	
	/**
	 * Constructs a shape of n-sides
	 * @param num_sides The number of sides the shape has
	 */
	public Shape(int num_sides)
	{
		//Check that the number of sides is valid
		if(num_sides < 3)
			throw new IllegalArgumentException("The number of sides on a regular polygon must be greater than or equal to 3.");
		
		//Sets the number of sides
		this.num_sides = num_sides;
		
		//Initializes the scale and translate transformations for the recentering of the objects
		scale = new Scale(1, 1);
		translate = new Translate(0, 0);
		
		//Sets the center of scaling
		scale.setPivotX(INITIAL_DIMENSION / 2);
		scale.setPivotY(INITIAL_DIMENSION /2);
		
		//Sets the window size to the initial size
		super.setPrefHeight(2*INITIAL_DIMENSION);
		super.setPrefWidth(INITIAL_DIMENSION);
		
		//Adds the application style sheet
		getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//Initializes the polygon
		polygon = new Polygon();
		
		//Sets the style of the polygon
		polygon.getStyleClass().add("defaultNoncoloredPolygon");
		
		
		//Generates the regular polygon
		for(int i = 0; i < num_sides; i++)
		{
			polygon.getPoints().add((1 + .9*Math.sin((i * 1.0) / num_sides * 2 * Math.PI)) * INITIAL_DIMENSION / 2.0 );
			polygon.getPoints().add((1 - .9*Math.cos((i * 1.0) / num_sides * 2 * Math.PI)) * INITIAL_DIMENSION / 2.0 );
		}
		
		//Adds the Regular Polygon to the pane
		super.getChildren().add(polygon);
		
				
		//Creates the label
		text = new Text("" + num_sides);
		
		//Centers the number in the polygon
		text.setY(INITIAL_DIMENSION / 2 + text.getBoundsInLocal().getHeight() / 4);
		text.setX(INITIAL_DIMENSION / 2 - text.getBoundsInLocal().getWidth() / 2.0);
		
		//Sets the style of text
		text.getStyleClass().add("defaultNoncoloredPolygon");
		
		
		super.getChildren().add(text);
		
		
		
		//Add a listener so that if the pane changes its height then the graphics will change 
		super.heightProperty().addListener(new ChangeListener<Number>() {
			public void changed( ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue)
			{
				scale.setY(newValue.doubleValue()/ INITIAL_DIMENSION); //Sets the amount the shape needs to be scaled on y-axis
				translate.setY((newValue.doubleValue() - INITIAL_DIMENSION) / 2.0); //Sets the amount the shape needs to be translated to stay in the center
				
				getTransforms().setAll(translate, scale); //Adds the transformations to transformation queue
				
				
			}
		});
		
		//Add a listener so that if the pane changes its height then the graphics will change
		super.widthProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue)
			{	
				scale.setX(newValue.doubleValue()/ INITIAL_DIMENSION); //Sets the amount the shape needs to be scaled on x-axis
				translate.setX((newValue.doubleValue() - INITIAL_DIMENSION) / 2.0); //Sets the amount the shape needs to be translated on x-axis
				getTransforms().setAll(translate, scale); //Adds the transformations to transformation queue
			}
		});
	}
	
	/**
	 * Compares two shapes to see if they are equal
	 * @param other The shape that will be compared too
	 * @return true if and only if the number of sides of each shape are equivalent.
	 */
	public boolean equals(Object other)
	{
		if(!(other instanceof Shape))
			return false;
		
		Shape otherShape = (Shape) other;
		
		return this.num_sides == otherShape.num_sides;
	}
	
	/**
	 * Creates a string to describe the number of sides on the object.
	 */
	public String toString()
	{
		return num_sides + "";
	}
	
	/**
	 * @return The number of sides the polygon has
	 */
	public int get_num_sides()
	{
		return num_sides;
	}

	/**
	 * Generates the graphics in the pane when the panes has the specified width and height
	 * @param width The current or new value of width of the pane
	 * @param height The current or new value of the height of the pane
	 */
	private void drawGraphics(double width, double height)
	{
		//Creates the polygon for center of graphic
		Polygon polygon = new Polygon();
		polygon.setStrokeWidth(5);
		
		//Creates all the nodes
		for(int i = 0; i < num_sides; i ++)
		{
			double sinValue = Math.sin(i / num_sides * 2*Math.PI); //Calculates the sin of the point
			polygon.getPoints().add((1 + sinValue)*width/2); //Adds the total width using the sin
			polygon.getPoints().add((1 - sinValue + Math.PI / 2) * height /  2); //Adds the height by phase shifting the sin value and then scaling for the height.
		}
		
		//Line line = new Line(0, 0 , width, height);
		super.getChildren().add(polygon);
	}
	

}
