package fr.utbm.ia51;

import fr.utbm.ia51.Globals;
import fr.utbm.ia51.activities.ActivityType;

import java.util.LinkedList;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;

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
	
	//TennisTable
	public static float statTennisTableReached() {
		return ((float) Globals.NB_REACHED_TENNISTABLE / Globals.NB_SET_TENNISTABLE)*100;
	}
	public static float statTennisTablePart() {
		return ((float) Globals.NB_REACHED_TENNISTABLE / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Soccer
	public static float statSoccerReached() {
		return ((float) Globals.NB_REACHED_SOCCER / Globals.NB_SET_SOCCER)*100;
	}
	public static float statSoccerPart() {
		return ((float) Globals.NB_REACHED_SOCCER / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Basket
	public static float statBasketReached() {
		return ((float) Globals.NB_REACHED_BASKETBALL / Globals.NB_SET_BASKETBALL)*100;
	}
	public static float statBasketPart() {
		return ((float) Globals.NB_REACHED_BASKETBALL / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Bench
	public static float statBenchReached() {
		return ((float) Globals.NB_REACHED_BENCH / Globals.NB_SET_BENCH)*100;
	}
	public static float statBenchPart() {
		return ((float) Globals.NB_REACHED_BENCH / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Library
	public static float statLibraryReached() {
		return ((float) Globals.NB_REACHED_LIBRARY / Globals.NB_SET_LIBRARY)*100;
	}
	public static float statLibraryPart() {
		return ((float) Globals.NB_REACHED_LIBRARY / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//ClassLine
	public static float statClassLineReached() {
		return ((float) Globals.NB_REACHED_CLASSLINE / Globals.NB_SET_CLASSLINE)*100;
	}
	public static float statClassLinePart() {
		return ((float) Globals.NB_REACHED_CLASSLINE / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Forest
	public static float statForestReached() {
		return ((float) Globals.NB_REACHED_FOREST / Globals.NB_SET_FOREST)*100;
	}
	public static float statForestPart() {
		return ((float) Globals.NB_REACHED_FOREST / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Toilet
	public static float statToiletReached() {
		return ((float) Globals.NB_REACHED_TOILET / Globals.NB_SET_TOILET)*100;
	}
	public static float statToiletPart() {
		return ((float) Globals.NB_REACHED_TOILET / Globals.TOT_REACHED_TARGET)*100;
	}
	
	//Fight
	public static float statFightReached() {
		return ((float) Globals.NB_REACHED_FIGHT / Globals.NB_SET_FIGHT)*100;
	}
	public static float statFightPart() {
		return ((float) Globals.NB_REACHED_FIGHT / Globals.TOT_REACHED_TARGET)*100;
	}
	
	
	public static void countActivityTypeSet(ActivityType type) {
		switch (type) {
		case TENNISTABLE:
			Globals.NB_SET_TENNISTABLE++;
			break;
		case SOCCER:
			Globals.NB_SET_SOCCER++;
			break;
		case BASKETBALL:
			Globals.NB_SET_BASKETBALL++;
			break;
		case BENCH:
			Globals.NB_SET_BENCH++;
			break;
		case LIBRARY:
			Globals.NB_SET_LIBRARY++;
			break;
		case CLASSLINE:
			Globals.NB_SET_CLASSLINE++;
			break;
		case FOREST:
			Globals.NB_SET_FOREST++;
			break;
		case TOILET:
			Globals.NB_SET_TOILET++;
			break;
		case FIGHT:
			Globals.NB_SET_FIGHT++;
			break;
		default:
			break;
		}
	}
	
	public static void countActivityTypeReached(ActivityType type) {
		switch (type) {
		case TENNISTABLE:
			Globals.NB_REACHED_TENNISTABLE++;
			break;
		case SOCCER:
			Globals.NB_REACHED_SOCCER++;
			break;
		case BASKETBALL:
			Globals.NB_REACHED_BASKETBALL++;
			break;
		case BENCH:
			Globals.NB_REACHED_BENCH++;
			break;
		case LIBRARY:
			Globals.NB_REACHED_LIBRARY++;
			break;
		case CLASSLINE:
			Globals.NB_REACHED_CLASSLINE++;
			break;
		case FOREST:
			Globals.NB_REACHED_FOREST++;
			break;
		case TOILET:
			Globals.NB_REACHED_TOILET++;
			break;
		case FIGHT:
			Globals.NB_REACHED_FIGHT++;
			break;
		default:
			break;
		}
	}
	
	public static float averageDistance() {
		return ((float)Globals.TOT_WALKED_DISTANCE / Globals.NB_AGENTS) / 20 ;
	}

}
