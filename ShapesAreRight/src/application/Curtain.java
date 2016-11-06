package application;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Curtain extends AnchorPane{

	/**
	 * Is the curtain revealed;
	 */
	private boolean revealed;
	
	
	public Curtain()
	{
		super();
		
		//Colors the curtain a beautiful aqua color
		setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
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
		
		//Makes the curtain transparent
		setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		
	}
}
