package application;

import java.util.Set;

import javafx.scene.layout.GridPane;

/**
 * This class represents the current rounds board
 * @author Ryan
 *
 */
public class Board extends GridPane{

	/**
	 * This is the array of trials.
	 */
	private Trial[] trials;
	
	/**
	 * The current trial that we are on, starting with 0
	 */
	private int currentTrial = 0;
	
	/**
	 * Initializes the array of trials
	 * @param number_of_trials The number of trials there should be during the game
	 * @param possibleSides The possible number of sides the shapes can have
	 * @param possibleColors The possible colors the shapes can have
	 */
	public Board(int number_of_trials, Set<Integer> possibleSides,
			Set<NamedColor> possibleColors) {
		
		super();
		super.setPrefHeight(300);
		super.setPrefWidth(800);
		
		trials = new Trial[number_of_trials];
		
		for(int i = 0; i < number_of_trials; i++)
		{
			trials[i] = new Trial(possibleSides, possibleColors);
			super.addColumn(i, trials[i]);
		}
	}

	/**
	 * Reveals the current trial and moves to the next trail
	 * @return The number of points the user gets for that trial
	 */
	public int revealNext() {
		//Gets the current trial
		Trial trial = trials[currentTrial]; 
		
		//Reveals the current trial and saves if the users guess was correct
		boolean correct = trial.reveal();
		
		//Moves to the next trial
		currentTrial++;
		
		//If the user was correct, they get one point if not they get 0 points
		if(correct)
			return 1;
		else
			return 0;
	}

	/**
	 * Returns if the current board has gone through all of its trial
	 * @return True if there are no more trials in the board else false
	 */
	public boolean roundOver() {
		
		return currentTrial >= trials.length;
	}




}