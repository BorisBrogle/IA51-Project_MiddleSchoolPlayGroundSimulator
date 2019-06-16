package fr.utbm.ia51;

import fr.utbm.ia51.tools.PseudoRandomGenerator;
import tools.Chrono;

public class Globals {
	public static PseudoRandomGenerator randomGenerator;
	public static Chrono chrono = new Chrono();
	
	
	public static int NB_AGENTS = 8;
	
	public static final int WIDTH = 1280; // Height of our map, and of our base window
	public static final int HEIGHT = 720; // Width of our map, and of our base window
	
	public static final int START_POS_X = 200;
	public static final int START_POS_Y = 200;
	public static long LOOP_DURATION = 100; // Duration between each steps, in milliseconds

	public static int LOOP_COUNTER = 0;
	
	public static long ACTIVITY_CHANGE_FREQUENCY = 150; // All the agents will change activity every x seconds on average
	public static long IMMOBILITY_TIME = 5; // The agents will stay motionless for 5 seconds max on average
	
	public static long AGENT_RADIUS = 10; // Radius of the agents
	
	public static boolean SHUT_DOWN_SIGNAL = false; // Will be set to true when the GUI is closed, kills all the agents
	
	public static boolean SHOW_FORCE_VECTOR = false; // If we should show the force vector of the agents
	public static boolean SHOW_VIEW_FIELD = false; // If we should show the view field of the agents
	public static boolean SHOW_AGENTS_COORDINATES = false; // If we should show the x;y coordinates of the agents
	public static boolean SHOW_ACTIVITY_TOOLTIP = true; // If we should show the activities of the agents
	

	public static void initGenerator(long seed) {
		Globals.randomGenerator = new PseudoRandomGenerator(seed);
	}
	public static void initGenerator() {
		Globals.randomGenerator = new PseudoRandomGenerator();
	}
}

