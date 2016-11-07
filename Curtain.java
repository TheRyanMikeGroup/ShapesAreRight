package application;

import java.sql.Time;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Curtain extends AnchorPane{

	/**
	 * Is the curtain revealed;
	 */
	private boolean revealed;
	
	/**
	 * Curtain image that hides the shape
	 */
	private ImageView curtain;
	
	
	public Curtain()
	{
		super();
		//Creates the curtain object that hides the shape
		curtain = new ImageView("curtain.jpg");
		curtain.setFitHeight(100);
		curtain.setFitWidth(100);
		super.getChildren().add(curtain);
		
	}
	
	/**
	 * Returns the string value if the curtain is revealed.
	 */
	@Override
	public String toString()
	{
		return "" + revealed;
	}

	/**
	 * Reveals what is behind the curtain
	 */
	public void reveal() {
		Timeline animation = new Timeline();
		
		DoubleProperty scaleProperty = curtain.scaleYProperty();
		DoubleProperty translateProperty = curtain.translateYProperty();
		animation.getKeyFrames().add(new KeyFrame(
				new Duration(0),
				new KeyValue(scaleProperty, 1),
				new KeyValue(translateProperty, 0)));
		animation.getKeyFrames().add(new KeyFrame(
				new Duration(1000),
				new KeyValue(scaleProperty, 0),
				new KeyValue(translateProperty, -50)));
		animation.play();
		
	}
}
