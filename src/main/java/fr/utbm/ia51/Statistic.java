package fr.utbm.ia51;

import fr.utbm.ia51.Statistic;
import fr.utbm.ia51.activities.ActivityType;


public class Statistic {
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
	
	public static float TOT_WALKED_DISTANCE = 0;
	
	public static long WALKING_TIME = 0;
	public static long ACTIVITY_TIME = 0;
	
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
	
	public static float TOT_WALKED_DISTANCE = 0;
	
	public static long WALKING_TIME = 0;
	public static long ACTIVITY_TIME = 0;
	
	public Statistic() {

	}

	
	//percentage of reached activities
	public static float statActivityReached() {
		return ((float) Statistic.TOT_REACHED_TARGET / Statistic.TOT_SET_TARGET)*100;
	}
	
	//TennisTable
	public static float statTennisTableReached() {
		return ((float) Statistic.NB_REACHED_TENNISTABLE / Statistic.NB_SET_TENNISTABLE)*100;
	}
	public static float statTennisTablePart() {
		return ((float) Statistic.NB_REACHED_TENNISTABLE / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Soccer
	public static float statSoccerReached() {
		return ((float) Statistic.NB_REACHED_SOCCER / Statistic.NB_SET_SOCCER)*100;
	}
	public static float statSoccerPart() {
		return ((float) Statistic.NB_REACHED_SOCCER / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Basket
	public static float statBasketReached() {
		return ((float) Statistic.NB_REACHED_BASKETBALL / Statistic.NB_SET_BASKETBALL)*100;
	}
	public static float statBasketPart() {
		return ((float) Statistic.NB_REACHED_BASKETBALL / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Bench
	public static float statBenchReached() {
		return ((float) Statistic.NB_REACHED_BENCH / Statistic.NB_SET_BENCH)*100;
	}
	public static float statBenchPart() {
		return ((float) Statistic.NB_REACHED_BENCH / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Library
	public static float statLibraryReached() {
		return ((float) Statistic.NB_REACHED_LIBRARY / Statistic.NB_SET_LIBRARY)*100;
	}
	public static float statLibraryPart() {
		return ((float) Statistic.NB_REACHED_LIBRARY / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//ClassLine
	public static float statClassLineReached() {
		return ((float) Statistic.NB_REACHED_CLASSLINE / Statistic.NB_SET_CLASSLINE)*100;
	}
	public static float statClassLinePart() {
		return ((float) Statistic.NB_REACHED_CLASSLINE / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Forest
	public static float statForestReached() {
		return ((float) Statistic.NB_REACHED_FOREST / Statistic.NB_SET_FOREST)*100;
	}
	public static float statForestPart() {
		return ((float) Statistic.NB_REACHED_FOREST / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	//Toilet
	public static float statToiletReached() {
		return ((float) Statistic.NB_REACHED_TOILET / Statistic.NB_SET_TOILET)*100;
	}
	public static float statToiletPart() {
		return ((float) Statistic.NB_REACHED_TOILET / Statistic.TOT_REACHED_TARGET)*100;
	}
	
	public static void countActivityTypeSet(ActivityType type) {
		switch (type) {
		case TENNISTABLE:
			Statistic.NB_SET_TENNISTABLE++;
			break;
		case SOCCER:
			Statistic.NB_SET_SOCCER++;
			break;
		case BASKETBALL:
			Statistic.NB_SET_BASKETBALL++;
			break;
		case BENCH:
			Statistic.NB_SET_BENCH++;
			break;
		case LIBRARY:
			Statistic.NB_SET_LIBRARY++;
			break;
		case CLASSLINE:
			Statistic.NB_SET_CLASSLINE++;
			break;
		case FOREST:
			Statistic.NB_SET_FOREST++;
			break;
		case TOILET:
			Statistic.NB_SET_TOILET++;
			break;
		default:
			break;
		}
	}
	
	public static void countActivityTypeReached(ActivityType type) {
		switch (type) {
		case TENNISTABLE:
			Statistic.NB_REACHED_TENNISTABLE++;
			break;
		case SOCCER:
			Statistic.NB_REACHED_SOCCER++;
			break;
		case BASKETBALL:
			Statistic.NB_REACHED_BASKETBALL++;
			break;
		case BENCH:
			Statistic.NB_REACHED_BENCH++;
			break;
		case LIBRARY:
			Statistic.NB_REACHED_LIBRARY++;
			break;
		case CLASSLINE:
			Statistic.NB_REACHED_CLASSLINE++;
			break;
		case FOREST:
			Statistic.NB_REACHED_FOREST++;
			break;
		case TOILET:
			Statistic.NB_REACHED_TOILET++;
			break;
		default:
			break;
		}
	}
	
	public static float averageDistance() {
		return ((float)Statistic.TOT_WALKED_DISTANCE / Globals.NB_AGENTS) / 20 ;
	}
	
	public static float averageWalkingTimePerAct() {
		return ((float)Statistic.WALKING_TIME / Statistic.TOT_REACHED_TARGET)/ 1000;
	}
	
	public static float averageTimePerAct() {
		return ((float)Statistic.ACTIVITY_TIME / Statistic.TOT_REACHED_TARGET)/ 1000;
	}
	
	public static float averageSpeed() {
		return ((((float)Statistic.TOT_WALKED_DISTANCE / Globals.NB_AGENTS) /20) / ((float)Statistic.WALKING_TIME/1000));
	}

}
