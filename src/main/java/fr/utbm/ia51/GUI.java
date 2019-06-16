package fr.utbm.ia51;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import fr.utbm.ia51.boot.Boot;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.graph.human.GraphHuman;
import io.sarl.bootstrap.SRE;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static GUI gui = null;
	public LinkedList<GraphHuman> graphHumans = new LinkedList<>();
	
	final int WIDTH = Globals.WIDTH;
	final int HEIGHT = Globals.HEIGHT;

	private CheckBox coordCheckBox = new CheckBox();
	private CheckBox vectorCheckBox = new CheckBox();
	private CheckBox viewfieldCheckBox = new CheckBox();
	private CheckBox tooltipCheckBox = new CheckBox();
	private Scene mainMenu;
	
    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }
    
    public static GUI waitForGUIStart() {
    	try {
    		latch.await();
    	}catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return gui;
    }
    
    public static void setGUI(GUI gui0) {
    	gui=gui0;
    	latch.countDown();
    }
    
    public GUI() {
    	setGUI(this);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        primaryStage.setTitle("High School Playground Simulator");
        
        Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, Color.TRANSPARENT);
        
        BackgroundImage bg = new BackgroundImage(
        						new Image("file:resources/graphism/image/background.png", this.WIDTH, this.HEIGHT, false, true),
                				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));
        
        
        GraphEnvironment environment = new GraphEnvironment(this.WIDTH, this.HEIGHT);
        root.getChildren().add(environment);
 
        
        //*******Part Dedicated to the launch configuration*******//
        
        Label nbAgentLabel = new Label("Choose number of Students to spawn : ");
        Spinner<Integer> nbAgentSpinner = new Spinner<>(1,100, 5);
        nbAgentSpinner.setEditable(true);
        nbAgentSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
        	  if (!newValue) {
        		  nbAgentSpinner.increment(0); // won't change value, but will commit editor
        	  }
        	});
        Tooltip nbAgentTooltip = new Tooltip("According to your computer performances, crashes may occur if you put too much agents in the simulation. Try at your own risks");
        Tooltip.install(nbAgentSpinner, nbAgentTooltip);
        HBox nbAgentChooseHBox = new HBox(nbAgentLabel,nbAgentSpinner);
        nbAgentChooseHBox.setSpacing(2);
        
        
        Label seedLabel = new Label("Add a seed to the simulation");
        TextField seedTextField = new TextField();
        Tooltip seedTooltip = new Tooltip("Seeds will make you able to reproduce a scenario that already occured in a past simulation.\nNot mandatory to launch the simulation");
        Tooltip.install(seedTextField, seedTooltip);
        HBox seedHBox = new HBox(seedLabel,seedTextField);
        
        Button launchButton = new Button("Launch Simulation");
      
        VBox mainMenuVBox = new VBox(nbAgentChooseHBox,seedHBox,launchButton);
        mainMenuVBox.setStyle("-fx-background-color : #bdb5b5cc");
        mainMenuVBox.setTranslateX(450);
        mainMenuVBox.setTranslateY(300);
        root.getChildren().add(mainMenuVBox);
        
        launchButton.setOnAction(e->{
        	Globals.NB_AGENTS = nbAgentSpinner.getValue();
            for(int i=0;i<Globals.NB_AGENTS;i++) {
            	GraphHuman g = new GraphHuman(Globals.START_POS_X, Globals.START_POS_Y, Globals.AGENT_RADIUS, null, environment);
            	root.getChildren().add(g);
            	environment.getArtifacts().add(g);
            	this.graphHumans.add(g);
            }
            
            String seedString = seedTextField.getText();
            if(seedString.length() != 0) {
                long seed = 0;
                for(int i = 0; i < seedString.length(); ++i) {
                	seed += (int)seedString.charAt(i)*(10*(i+1));
                }
                Globals.initGenerator(seed);
                primaryStage.setTitle("High School Playground Simulator - seed "+seedString);
            } else {
                Globals.initGenerator();
                primaryStage.setTitle("High School Playground Simulator - seed "+Globals.randomGenerator.getSeed());
            }

            
       //************************************************//


            
      //*******Part Dedicated to the control panel*******//
  
            VBox controlVBox = new VBox();
            controlVBox.setSpacing(5);
            controlVBox.setAlignment(Pos.CENTER);
            controlVBox.getChildren().add(environment.getRingButton());
            
            
            VBox coordVBox = new VBox();
            coordVBox.setAlignment(Pos.CENTER);
            coordCheckBox.selectedProperty().addListener((obs,old,val)->{
            	for(GraphHuman g : graphHumans) {
            		if(val)
            			g.getCoordinatesLabel().setVisible(true);
            		else
            			g.getCoordinatesLabel().setVisible(false);
            	}
            });
            coordCheckBox.setSelected(Globals.SHOW_AGENTS_COORDINATES);
            Label coordLabel = new Label("Coordinates");
            coordLabel.setTextFill(Color.WHITE);
            coordVBox.getChildren().addAll(coordLabel,coordCheckBox);
            
            
     
            VBox vectVBox = new VBox();
            vectVBox.setAlignment(Pos.CENTER);
            vectorCheckBox.selectedProperty().addListener((obs,old,val)->{
            	for(GraphHuman g : this.graphHumans) {
            		if(val)
            			g.getForceArrow().setVisible(true);
            		else
            			g.getForceArrow().setVisible(false);
            	}
            	
            });
            vectorCheckBox.setSelected(Globals.SHOW_FORCE_VECTOR);
            Label vectorLabel = new Label("Vector direction");
            vectorLabel.setTextFill(Color.WHITE);
            vectVBox.getChildren().addAll(vectorLabel,vectorCheckBox);
            
            
            VBox tooltipVBox = new VBox();
            tooltipVBox.setAlignment(Pos.CENTER);
            tooltipCheckBox.selectedProperty().addListener((obs,old,val)->{
            	for(GraphHuman g : this.graphHumans) {
            		if(val)
            			g.getActivityDesired().setVisible(true);
            		else
            			g.getActivityDesired().setVisible(false);
            	}
            	
            });
            tooltipCheckBox.setSelected(Globals.SHOW_ACTIVITY_TOOLTIP);
            Label tooltipLabel = new Label("Activity tooltip");
            tooltipLabel.setTextFill(Color.WHITE);
            tooltipVBox.getChildren().addAll(tooltipLabel,tooltipCheckBox);

            
            VBox viewfieldVBox = new VBox();
            viewfieldVBox.setAlignment(Pos.CENTER);
            viewfieldCheckBox.selectedProperty().addListener((obs,old,val)->{
            	for(GraphHuman g : this.graphHumans) {
            		if(val)
            			g.getViewField().setVisible(true);
            		else
            			g.getViewField().setVisible(false);
            	}
            });
            viewfieldCheckBox.setSelected(Globals.SHOW_VIEW_FIELD);
            Label viewfieldLabel = new Label("Viewfield");
            viewfieldLabel.setTextFill(Color.WHITE);
            viewfieldVBox.getChildren().addAll(viewfieldLabel,viewfieldCheckBox);
            
            
            Button statButton = new Button();
            statButton.setAlignment(Pos.CENTER);
            statButton.setText("Statistics");
            statButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
     
                    Label statTarget = new Label("Number of target set : " + Statistic.TOT_SET_TARGET + 
                    							"\nNumber of target reached : " + Statistic.TOT_REACHED_TARGET + 
                    							"\nPercentage reached : " + Statistic.statActivityReached() + "%" + 
                    							"\nTotal walked distance : " + Statistic.TOT_WALKED_DISTANCE/20 + "m ( " + Statistic.averageDistance() + "m per student in average)" +
                    							"\nTotal walking time : " + (float)Statistic.WALKING_TIME/1000 + "sec (" + Statistic.averageWalkingTimePerAct() + "sec per activity )" +
                    							"\nTotal activity time : " + (float)Statistic.ACTIVITY_TIME/1000 + "sec (" + Statistic.averageTimePerAct() + "sec per activity )" +
                    							"\nAverage speed : " + Statistic.averageSpeed() + "m/sec" +
                    							"\n\nPercentage Activity :\n" +
                    							"Tennis Table : " + Statistic.statTennisTableReached() + "% reached (" + Statistic.statTennisTablePart() + "%)\n" +
                    							"Soccer : " + Statistic.statSoccerReached() + "% reached (" + Statistic.statSoccerPart() + "%)\n" +
                    							"BasketBall : " + Statistic.statBasketReached() + "% reached (" + Statistic.statBasketPart() + "%)\n" +
                    							"Bench : " + Statistic.statBenchReached() + "% reached (" + Statistic.statBenchPart() + "%)\n" +
                    							"Library : " + Statistic.statLibraryReached() + "% reached (" + Statistic.statLibraryPart() + "%)\n" +
                    							"ClassLine : " + Statistic.statClassLineReached() + "% reached (" + Statistic.statClassLinePart() + "%)\n" +
                    							"Forest : " + Statistic.statForestReached() + "% reached (" + Statistic.statForestPart() + "%)\n" +
                    							"Toilet : " + Statistic.statToiletReached() + "% reached (" + Statistic.statToiletPart() + "%)\n"
                    							);
                    
                    StackPane statLayout = new StackPane();
                    statLayout.getChildren().addAll(statTarget);
     
                    Scene statScene = new Scene(statLayout, 400, 300);
     
                    // New window (Stage)
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Statistics");
                    newWindow.setScene(statScene);
     
                    // Set position of second window, related to primary window.
                    newWindow.setX(primaryStage.getX() + 200);
                    newWindow.setY(primaryStage.getY() + 100);
     
                    newWindow.show();
                }
            });       
            //************************************************//

            
            controlVBox.getChildren().addAll(coordVBox, vectVBox, viewfieldVBox, tooltipVBox, statButton);
            
            
            
            root.getChildren().add(controlVBox);
            try {
            	
            	// Launch the Environment Agent 
    			SRE.getBootstrap().startAgent(Boot.class, this.graphHumans);
    		} catch (Exception exception) {
    			// TODO Auto-generated catch block
    			exception.printStackTrace();
    		}
        	mainMenuVBox.setVisible(false);

            
        });
         
               

        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        

    }
    
    
    /*
     * If called, this function allows the user to click anywhere in the scene to know its position
     */
    protected void addMouseClickCoords(Scene scene) {
    	//Creating the mouse event handler 
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) { 
              System.out.println("Clicked at: x="+e.getX()+" y="+e.getY()); 
           } 
        };   
        //Adding event Filter 
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
    
    
    /**
     *Override the close method the application's window
     */
    @Override
    public void stop() throws Exception {
    	System.out.println("--> Shutting down the application");
    	Globals.SHUT_DOWN_SIGNAL = true;
    }
}
