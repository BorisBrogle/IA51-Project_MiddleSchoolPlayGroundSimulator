package fr.utbm.ia51;

import fr.utbm.ia51.tools.PseudoRandomGenerator;
import tools.Chrono;

public class Globals {
	public static PseudoRandomGenerator randomGenerator = new PseudoRandomGenerator();
	public static Chrono chrono = new Chrono();
	
	public static long LOOP_DURATION = 100; // Duration between each steps, in milliseconds
	public static boolean SHOW_FORCE_VECTOR = true; // If we should print the force vector of the agents
}
