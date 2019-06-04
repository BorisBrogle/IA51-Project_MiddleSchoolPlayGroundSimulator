package fr.utbm.ia51.activities;

import fr.utbm.ia51.Globals;

public class ActivityManager {
	
	public ActivityManager() {
		
	}
	
	public ActivityType chooseRandomActivity() {
		ActivityType[] enumValues = ActivityType.values();
		int activity = Globals.randomGenerator.getRandom(0, enumValues.length);
		return enumValues[activity];
	}
}
