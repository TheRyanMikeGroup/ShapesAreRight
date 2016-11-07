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
	private int numberOfCards = -1;		// number of cards to be dealt
	
	ObservableList<Integer> selectedShapes = FXCollections.observableArrayList(); // list of user-selected shapes to play the game with
	ObservableList<NamedColor> selectedColors = FXCollections.observableArrayList(); // list of user-selected colors to play the game with
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {

			AnchorPane root = new AnchorPane();
			BorderPane page = new BorderPane();
			AnchorPane intro = new AnchorPane();
			
			Scene scene = new Scene(page,700,500);
			
			//final ImageView imv1 = new ImageView();
			//imv1.setImage(new Image("thepriceisright.png"));
			//intro.getChildren().add(imv1);
			
			Label introLabel = new Label(" The Shapes Are Right!");
			introLabel.setStyle( "-fx-background-color: #000000; -fx-text-fill: #eeeeee;  -fx-font-size: 60");
			introLabel.setPrefWidth(700);
			introLabel.setPrefHeight(250);
			
			intro.setPrefHeight(247);
			intro.getChildren().add(introLabel);
			
			
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
			colorOptions.add(new NamedColor(Color.BLACK, "Black"));
			
			

			// List of shapes selectable in typeShapesLV
			ObservableList<Integer> shapeOptions = 
				    FXCollections.observableArrayList();
			for(int i = 3; i < 11; i++)
				shapeOptions.add(i);
			

			
			// Initialize all objects to be used in FlowPane "controlPane"
			ComboBox<Integer> numShapesCB = new ComboBox<Integer>(numOptions);
			ListView<Integer> typeShapesLV = new ListView<Integer>(shapeOptions);
			ListView<NamedColor> colorShapesLV = new ListView<NamedColor>(colorOptions);
			
			Label typeShapesLB = new Label(" Choose Shapes:\n(number of sides)");
			Label colorShapesLB = new Label(" Choose Colors:");
			Label numShapesLB = new Label("Number of shapes:");
			Label testLabel = new Label("Test Label");
			Button dealButton = new Button("Deal!");
			Button quitButton = new Button("Quit");

			
			
			// Group typeShapes objects together for easy moving
			Group typeShapesG = new Group();
			typeShapesG.getChildren().add(typeShapesLV);
			typeShapesG.getChildren().add(typeShapesLB);
			typeShapesLV.setPrefSize(100, 200);
			typeShapesLV.setTranslateY(36);
			typeShapesG.setTranslateX(50);
			typeShapesG.setTranslateY(40);
			
			// Group colorShapes objects together for easy moving
			Group colorShapesG = new Group();
			colorShapesG.getChildren().add(colorShapesLV);
			colorShapesG.getChildren().add(colorShapesLB);
			colorShapesLV.setPrefSize(100, 200);
			colorShapesLV.setTranslateY(36);
			colorShapesG.setTranslateX(300);
			colorShapesG.setTranslateY(40);
			
			// Group numShapes objects together for easy moving
			Group numShapesG = new Group();
			numShapesG.getChildren().add(numShapesLB);
			numShapesG.getChildren().add(numShapesCB);
			numShapesCB.setTranslateX(130);
			numShapesLB.setTranslateY(5);
			numShapesG.setTranslateX(500);
			numShapesG.setTranslateY(40);

			/*
			 threeCardView.setTranslateX(100);
			 
			threeCardView.setPrefSize(300, 100);
			threeCardView.setStyle( "-fx-background-color: #F11111");
			
			fiveCardView.setTranslateX(100);
			fiveCardView.setPrefSize(500, 100);
			fiveCardView.setStyle( "-fx-background-color: #600000");
			
			sevenCardView.setTranslateX(100);
			sevenCardView.setPrefSize(700, 100);
			sevenCardView.setStyle( "-fx-background-color: #100000");
			*/
			
			dealButton.setTranslateX(500);
			dealButton.setTranslateY(100);
			
			quitButton.setTranslateX(600);
			quitButton.setTranslateY(100);

			
			
			
			
			// Allow the ListView for type of shape to handle multiple selections
			typeShapesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			typeShapesLV.getSelectionModel().getSelectedItems().addListener(
					new ListChangeListener<Integer>() {
						public void onChanged
						(ListChangeListener.Change< ? extends Integer> c) {
							testLabel.setText(typeShapesLV.getSelectionModel().getSelectedItems().toString());
							selectedShapes = typeShapesLV.getSelectionModel().getSelectedItems();
						}
					});
			
			// Allow the ListView for color of shape to handle multiple selections
			colorShapesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			colorShapesLV.getSelectionModel().getSelectedItems().addListener(
					new ListChangeListener<NamedColor>() {
						public void onChanged
						(ListChangeListener.Change< ? extends NamedColor> c) {
							testLabel.setText(colorShapesLV.getSelectionModel().getSelectedItems().toString());
							selectedColors = colorShapesLV.getSelectionModel().getSelectedItems();
							System.out.println(selectedColors);
						}
					});	
			
			
			// Handle the dropdown menu to select number of cards to deal
		       numShapesCB.setOnAction( new EventHandler<ActionEvent>() {
		        	public void handle( ActionEvent ae ) {
		        		
		        		numberOfCards = numShapesCB.getValue();
		        		testLabel.setText("" + numberOfCards);
		        		
		        	//	if(numShapesCB.getValue() == " ")
		        	//	{ 
		        	//		numShapesCB.disarm();
		        	//		numShapesCB.setPromptText("Number of Shape Cards:");
		        	//	}
		        	//	else{numShapesCB.setPromptText("Number of Shape Cards: " + numShapesCB.getValue());}
		        	}
		        });
			
			
			
	        // Click to button to deal cards
	       dealButton.setOnAction( new EventHandler<ActionEvent>() {
	        	public void handle( ActionEvent ae ) {
	        		Set<NamedColor> possibleColors = new HashSet<NamedColor>();
	        		Set<Integer> possibleSides = new HashSet<Integer>();
	        		
	        		possibleColors.addAll(selectedColors);
	        		possibleSides.addAll(selectedShapes);
	        		
	        		if(possibleColors != null && possibleSides != null && numberOfCards != -1){
	        			Game testGame = new Game(numberOfCards, possibleSides, possibleColors);
	    				//root.getChildren().add(testGame);
	    				page.setTop(testGame);
	    				page.setCenter(root);
	    				typeShapesG.setDisable(true);
	    				colorShapesG.setDisable(true);
	    				dealButton.setDisable(true);
	    				
	    				
	    				}
	        		else testLabel.setText("Error: please enter correct info");
	        	

	        	}
	        });
	       

	       
	        // Click to button to quit. This will close the window
	        quitButton.setOnAction( new EventHandler<ActionEvent>() {
	        	public void handle( ActionEvent ae ) {
	        		primaryStage.close();
	        	}
	        });
		

	        
	        
	       
	        // Add all objects to the BorderPane 'root'
			root.getChildren().add(typeShapesG);
			root.getChildren().add(colorShapesG);
			root.getChildren().add(numShapesG);
			root.getChildren().add(testLabel);
			root.getChildren().add(dealButton);
			root.getChildren().add(quitButton);
			//root.getChildren().add(threeCardView);
			//root.getChildren().add(fiveCardView);
			//root.getChildren().add(sevenCardView);
			
			
			//root.setTop(controlPane);
			//root.setLeft(controlPane);
			
			//root.getChildren().add(controlPane);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
