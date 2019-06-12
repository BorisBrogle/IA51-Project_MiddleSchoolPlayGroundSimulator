package fr.utbm.ia51;

import fr.utbm.ia51.tools.PseudoRandomGenerator;
import tools.Chrono;

public class Globals {
	public static PseudoRandomGenerator randomGenerator = new PseudoRandomGenerator();
	public static Chrono chrono = new Chrono();
	
	public static final int WIDTH = 1280; // Height of our map, and of our base window
	public static final int HEIGHT = 720; // Width of our map, and of our base window
	
	public static long LOOP_DURATION = 100; // Duration between each steps, in milliseconds
	
	public static long ACTIVITY_CHANGE_FREQUENCY = 3600; // All the agents will change activity every x seconds on average
	public static long IMMOBILITY_TIME = 5; // The agents will stay motionless for 5 seconds max on average
	
	public static boolean SHOW_FORCE_VECTOR = true; // If we should print the force vector of the agents
	
	public static boolean SHUT_DOWN_SIGNAL = false; // Will be set to true when the GUI is closed, kills all the agents
	
	public static boolean NIUT_NIUT_LES_CHECKBOX = true;
}
