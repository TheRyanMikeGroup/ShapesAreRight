package application;
	
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Main extends Application {
	
	// Global Variables
	private int numberOfCards = -1;		// Number of cards to be dealt
	
	ObservableList<Integer> selectedShapes = FXCollections.observableArrayList(); // list of user-selected shapes to play the game with
	ObservableList<NamedColor> selectedColors = FXCollections.observableArrayList(); // list of user-selected colors to play the game with
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {

			// AnchorPane for all buttons, labels, and list on initial screen, root is the center of the borderpane page
			AnchorPane root = new AnchorPane();
			
			
			// BorderPane as the base of the scene
			BorderPane page = new BorderPane();
			
			
			// Top of page, intro AnchorPane to be displayed before game() is called
			AnchorPane intro = new AnchorPane(); 
			
			
			// Create new scene 
			Scene scene = new Scene(page,700,500);
	
			
			
			// This label is displayed at the top of the screen and acts as an into before the user starts playing the game
			Label introLabel = new Label("  The Shapes Are Right!");
			introLabel.setStyle( "-fx-background-color: #000000; -fx-text-fill: #eeeeee;  -fx-font-size: 60");
			introLabel.setPrefWidth(700);
			introLabel.setPrefHeight(250);
			intro.getChildren().add(introLabel);
			
			
			// Set the top of the GUI to the intro graphic; center to all buttons, labels, and lists to collect user input
			page.setTop(intro);
			page.setCenter(root);
	
			
			// List of numbers that are selectable as the number of cards to be dealt			
			ObservableList<Integer> numOptions = 
				    FXCollections.observableArrayList(
				    	3,
				        5,
				        7
				    );
			
			
			// List of colors selectable in colorShapesLV
			ObservableList<NamedColor> colorOptions = 
				    FXCollections.observableArrayList();
			
			
			// Adding all the colors to the list of selectable options
			colorOptions.add(new NamedColor(Color.RED, "Red"));
			colorOptions.add(new NamedColor(Color.ORANGE, "Orange"));
			colorOptions.add(new NamedColor(Color.YELLOW, "Yellow"));
			colorOptions.add(new NamedColor(Color.GREEN, "Green"));
			colorOptions.add(new NamedColor(Color.BLUE, "Blue"));
			colorOptions.add(new NamedColor(Color.PURPLE, "Purple"));
			colorOptions.add(new NamedColor(Color.PINK, "Pink"));
			colorOptions.add(new NamedColor(Color.WHITE, "White"));
			colorOptions.add(new NamedColor(Color.GREY, "Grey"));
			
			

			// List of shapes selectable in typeShapesLV
			ObservableList<Integer> shapeOptions = 
				    FXCollections.observableArrayList();			
			for(int i = 3; i < 11; i++)
				shapeOptions.add(i);
			

			
			// Initialize all objects to be used in AnchorPane "root"
			ComboBox<Integer> numShapesCB = new ComboBox<Integer>(numOptions);
			ListView<Integer> typeShapesLV = new ListView<Integer>(shapeOptions);
			ListView<NamedColor> colorShapesLV = new ListView<NamedColor>(colorOptions);
			
			Label typeShapesLB = new Label(" Choose Shapes:\n(number of sides)");
			Label colorShapesLB = new Label(" Choose Colors:");
			Label numShapesLB = new Label("Number of shapes:");
			Label testLabel = new Label();
			Button dealButton = new Button("Deal!");
			Button quitButton = new Button("Quit");
			Label selectedShapesLabel = new Label();
			Label selectedColorsLabel = new Label();
			Button resetButton = new Button("Reset");			
			
			
			// Group typeShapes objects together for easy moving
			Group typeShapesG = new Group();
			typeShapesG.getChildren().add(typeShapesLV);
			typeShapesG.getChildren().add(typeShapesLB);
			typeShapesLV.setPrefSize(100, 150);
			typeShapesLV.setTranslateY(36);
			typeShapesG.setTranslateX(30);
			typeShapesG.setTranslateY(20);
			
			
			// Group colorShapes objects together for easy moving
			Group colorShapesG = new Group();
			colorShapesG.getChildren().add(colorShapesLV);
			colorShapesG.getChildren().add(colorShapesLB);
			colorShapesLV.setPrefSize(100, 150);
			colorShapesLV.setTranslateY(36);
			colorShapesG.setTranslateX(180);
			colorShapesG.setTranslateY(20);
			
			
			// Group numShapes objects together for easy moving
			Group numShapesG = new Group();
			numShapesG.getChildren().add(numShapesLB);
			numShapesG.getChildren().add(numShapesCB);
			numShapesCB.setTranslateX(130);
			numShapesLB.setTranslateY(5);
			numShapesG.setTranslateX(310);
			numShapesG.setTranslateY(15);

			
			// Position and set properties of buttons and labels used on initial screen
			dealButton.setTranslateX(500);
			dealButton.setTranslateY(180);
			
			quitButton.setTranslateX(600);
			quitButton.setTranslateY(180);
			
			resetButton.setTranslateX(550);
			resetButton.setTranslateY(180);

			testLabel.setTranslateX(180);
			testLabel.setTranslateY(220);
			testLabel.setStyle( "-fx-background-color: #eeeeee; -fx-text-fill: red;  -fx-font-size: 20");
			
			selectedShapesLabel.setTranslateX(380);
			selectedShapesLabel.setTranslateY(40);
			selectedShapesLabel.setPrefSize(100, 200);
			selectedShapesLabel.setWrapText(true);
			
			selectedColorsLabel.setTranslateX(300);
			selectedColorsLabel.setTranslateY(40);
			selectedColorsLabel.setPrefSize(100, 200);
			selectedColorsLabel.setWrapText(true);
	
			colorShapesLV.setStyle("-fx-background-color: blue");
			typeShapesLV.setStyle("-fx-background-color: blue");
			
			
			
			// Allow the ListView for type of shape to handle multiple selections
			typeShapesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			typeShapesLV.getSelectionModel().getSelectedItems().addListener(
					new ListChangeListener<Integer>() {
						public void onChanged
						(ListChangeListener.Change< ? extends Integer> c) {
							
							selectedShapes = typeShapesLV.getSelectionModel().getSelectedItems(); // Set selectedShapes list to the selected shapes
							selectedShapesLabel.setText("SELECTED\nSHAPES:\n");
							for(Integer i:selectedShapes){
								selectedShapesLabel.setText(selectedShapesLabel.getText() + i + " sided shape.\n");  // Print selected shapes to a label
							
							}
						}
					});
			
			
			
			// Allow the ListView for color of shape to handle multiple selections
			colorShapesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			colorShapesLV.getSelectionModel().getSelectedItems().addListener(
					new ListChangeListener<NamedColor>() {
						public void onChanged
						(ListChangeListener.Change< ? extends NamedColor> c) {
							
							selectedColors = colorShapesLV.getSelectionModel().getSelectedItems(); // Set selectedColors list to the selected colors
							selectedColorsLabel.setText("SELECTED\nCOLORS:\n");
							for(NamedColor i:selectedColors){
								selectedColorsLabel.setText(selectedColorsLabel.getText() + i + "\n"); // Print selected shapes to a label
							
							}
						}
					});	
			
			
			
			// Handle the dropdown menu to select number of cards to deal
		       numShapesCB.setOnAction( new EventHandler<ActionEvent>() {
		        	public void handle( ActionEvent ae ) {
		        		
		        		numberOfCards = numShapesCB.getValue();
		        		
		        	}
		        });
			
			
			
	        // Click button to deal cards
	       dealButton.setOnAction( new EventHandler<ActionEvent>() {
	        	public void handle( ActionEvent ae ) {
	        		
	        		Set<NamedColor> possibleColors = new HashSet<NamedColor>();
	        		Set<Integer> possibleSides = new HashSet<Integer>();
	        		
	        		// addAll the user-selected colors and shapes from observable list to sets to be passed to the game
	        		possibleColors.addAll(selectedColors);
	        		possibleSides.addAll(selectedShapes);
	        		
	        		// Error checking, tests whether or not user has input enough information to start a game
	        		if(possibleColors.isEmpty()) testLabel.setText("Error: please select at least one color");
	        		else if(possibleSides.isEmpty()) testLabel.setText("Error: please select at least one shape");
	        		else if(numberOfCards == -1) testLabel.setText("Error: please select a number of cards");
	        		else{
	        				        			
	        			testLabel.setText("");
	        			
	        			// Initialization of game
	        			Game testGame = new Game(numberOfCards, possibleSides, possibleColors);
	    				page.getChildren().add(testGame);
	    				
	    				// Set the center of the page to root
	    				page.setCenter(root);
	    				
	    				// Set introLabel to not visible in order to show game board
	    				introLabel.setVisible(false);
	    				
	    				
	    				testGame.setTranslateY(50);
	    				
	    				// After user starts the game, they are no longer allowed to re-deal or change selected shapes/colors
	    				typeShapesG.setDisable(true);
	    				colorShapesG.setDisable(true);
	    				dealButton.setDisable(true);
	    					
	        		}
	        	}
	        });
	       

	       
	        // Click button to quit. This will close the window
	        quitButton.setOnAction( new EventHandler<ActionEvent>() {
	        	public void handle( ActionEvent ae ) {
	        		primaryStage.close();
	        	}
	        });
		
	        
	        // Click button to reset. This will bring user back to initial intro screen and reset all necessary values
	        resetButton.setOnAction( new EventHandler<ActionEvent>() {
	        	public void handle( ActionEvent ae ) {
	        		
	        		// Clear all children of the page (this includes the game, the root pane, and the intro pane)
    				page.getChildren().clear();
	        		
    				// Re-add children to the page, less the game
    				introLabel.setVisible(true);
	        		page.setTop(intro);
	    			page.setCenter(root);
	    			
	    			// Clear all listview objects and combobox
	    			typeShapesLV.getSelectionModel().clearSelection();
	    			colorShapesLV.getSelectionModel().clearSelection();
	    			numShapesCB.getSelectionModel().clearSelection();
	    			
	    			// Re-inable buttons
	        		typeShapesG.setDisable(false);
    				colorShapesG.setDisable(false);
    				dealButton.setDisable(false);
    				
    				// Clear label text
    				selectedColorsLabel.setText("");
    				selectedShapesLabel.setText("");
    				
    				// Reset variables
    				numberOfCards = -1;
    				selectedShapes.clear();
    				selectedColors.clear();
    				
	                
	        	}
	        });
	        
	        
	       
	        // Add all objects to the BorderPane 'root'
			root.getChildren().add(typeShapesG);
			root.getChildren().add(colorShapesG);
			root.getChildren().add(numShapesG);
			root.getChildren().add(testLabel);
			root.getChildren().add(dealButton);
			root.getChildren().add(quitButton);
			root.getChildren().add(selectedColorsLabel);
			root.getChildren().add(selectedShapesLabel);
			root.getChildren().add(resetButton);
			

			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
