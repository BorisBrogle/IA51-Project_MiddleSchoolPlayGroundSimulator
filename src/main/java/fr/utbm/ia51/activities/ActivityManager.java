package fr.utbm.ia51.activities;

import java.util.HashMap;

import fr.utbm.ia51.Globals;

public class ActivityManager {
	HashMap<ActivityType, Double> activityChances = new HashMap<>();
	int nbActivities;
	
	public ActivityManager() {
		nbActivities = ActivityType.values().length;
		
		// Will associate a weight to all the activities
		for (ActivityType a : ActivityType.values()) { 
			double chance = 1.0/nbActivities;
		    activityChances.put(a, chance);
		}
	}
	
	
	/*
	 * Returns an activity chosen randomly between all the activities using their weights
	 * @return: activityType that has been chosen
	 */
	public ActivityType chooseActivity() {
		ActivityType[] activities = ActivityType.values();
		double rnd = Globals.randomGenerator.getRandom(0.0, 1.0);
		
		int activity = -1;
		double totalWeights = 0;
		do {
			activity++;
			totalWeights += activityChances.get(activities[activity]);
		} while(totalWeights <= rnd && activity < nbActivities-1);
		
		return activities[activity];
	}
	
	
}
