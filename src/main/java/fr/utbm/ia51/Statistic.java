package fr.utbm.ia51;

import fr.utbm.ia51.Globals;
import java.util.LinkedList;
import fr.utbm.ia51.graph.human.GraphHuman;

public class Statistic {
	
	private float averageGlobalDistance;
	private float averageGlobalTime;
	private float averageGlobalSpeed;
	
	private int numberActivitySet;
	private int numberActivityReached;
	
	
	private float proportionActivity;

	
	public Statistic() {
		averageGlobalDistance = 0;
		averageGlobalTime = 0;
		averageGlobalSpeed = 0;
		
		//numberActivityReached=0;
		//numberActivitySet = 0;
	}
	
	public static void setAverageGlobalDistance(/*liste des distances, student number*/) {
		//averageGlobalDistance = 0;
		/*for
		
		*/
	}
	
	public static void setAverageGlobalTime(/*liste des temps, student number*/) {
		//averageGlobalTime = 0;
		/*for
		
		*/
	}
	
	public static void setAverageGlobalSpeed(/*liste des vitesse, student number*/) {
		//averageGlobalSpeed = 0;
		/*for
		
		*/
	}
	
	//percentage of reached activities
	public static float statActivityReached() {
		return ((float) Globals.TOT_REACHED_TARGET / Globals.TOT_SET_TARGET)*100;
	}
	
	public static void statActivityReachedTotal() {
	
	}

}
