package fr.utbm.ia51;

import fr.utbm.ia51.tools.PseudoRandomGenerator;
import tools.Chrono;

public class Globals {
	public static PseudoRandomGenerator randomGenerator = new PseudoRandomGenerator();
	public static Chrono chrono = new Chrono();
	
	
	public static final int NB_AGENTS = 3;
	
	public static final int WIDTH = 1280; // Height of our map, and of our base window
	public static final int HEIGHT = 720; // Width of our map, and of our base window
	
	public static final int START_POS_X = 200;
	public static final int START_POS_Y = 200;
	public static long LOOP_DURATION = 100; // Duration between each steps, in milliseconds
	
	public static long ACTIVITY_CHANGE_FREQUENCY = 3600; // All the agents will change activity every x seconds on average
	public static long IMMOBILITY_TIME = 5; // The agents will stay motionless for 5 seconds max on average
	
	public static long AGENT_RADIUS = 10; // Radius of the agents
	
	public static boolean SHUT_DOWN_SIGNAL = false; // Will be set to true when the GUI is closed, kills all the agents
	
	public static boolean SHOW_FORCE_VECTOR = true; // If we should show the force vector of the agents
	public static boolean SHOW_VIEW_FIELD = false; // If we should show the view field of the agents
	public static boolean SHOW_AGENTS_COORDINATES = false; // If we should show the x;y coordinates of the agents
	public static boolean SHOW_ACTIVITY_TOOLTIP = false; // If we should show the activities of the agents
	
	public static boolean SHOW_STAT = false; // If we should show the statistics
	public static int TOT_SET_TARGET = 0;
	public static int TOT_REACHED_TARGET = 0;
	
	//STAT FOR EACH ACTIVITIES
	public static int NB_SET_BASKETBALL = 0;
	public static int NB_REACHED_BASKETBALL = 0;
	public static int NB_SET_TENNISTABLE = 0;
	public static int NB_REACHED_TENNISTABLE = 0;
	public static int NB_SET_SOCCER = 0;
	public static int NB_REACHED_SOCCER = 0;
	public static int NB_SET_BENCH = 0;
	public static int NB_REACHED_BENCH = 0;
	public static int NB_SET_LIBRARY = 0;
	public static int NB_REACHED_LIBRARY = 0;
	public static int NB_SET_CLASSLINE = 0;
	public static int NB_REACHED_CLASSLINE = 0;
	public static int NB_SET_FOREST = 0;
	public static int NB_REACHED_FOREST = 0;
	public static int NB_SET_TOILET = 0;
	public static int NB_REACHED_TOILET = 0;
	public static int NB_SET_FIGHT = 0;
	public static int NB_REACHED_FIGHT = 0;
	
	public static float TOT_WALKED_DISTANCE = 0;
}
