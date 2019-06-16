package fr.utbm.ia51.activities;

import java.util.HashMap;

import fr.utbm.ia51.Globals;

public class ActivityManager {
	HashMap<ActivityType, Double> activityChances = new HashMap<>();
	int nbActivities;
	double maxWeight = 0.9;
	double growthFactor = 5.0;
	
	public ActivityManager() {
		nbActivities = ActivityType.values().length;
		
		// Will associate a weight to all the activities
		for (ActivityType a : ActivityType.values()) { 
			double chance = 1.0/nbActivities;
		    activityChances.put(a, chance);
		}
	}
	
	
	/*
	 * Returns an activity chosen randomly between all the activities using their weights, and raise its weight
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

		raiseActivity(activities[activity]);
//		printActivitiesAndWeights(); //I let it print for the moment, you can comment it if you want
		return activities[activity];
	}
	
	
	/*
	 * Just used to print the different activities and their chances of appearing
	 */
	public void printActivitiesAndWeights() {
		System.out.println("\n---- Printing the activity chances:");
		for (HashMap.Entry<ActivityType, Double> entry : activityChances.entrySet()) {
			ActivityType key = entry.getKey();
			Double value = entry.getValue();
		    System.out.format("Activity %s, weight %.5f\n", key, value);
		}
		System.out.println("----\n");
	}
	
	
	/*
	 * Function used to raise an activity chance of appearing. 
	 * It also lowers the chances for the other activities to appear.
	 * @param a: activity that we want to have a higher chance of appearing
	 */
	private void raiseActivity(ActivityType a) {
		double activityWeight = this.activityChances.get(a);
		double x = raiseFunctionInvert(activityWeight);
		double newWeight = raiseFunction(x+1);
		double diff = newWeight - activityWeight;
		
		double totalWeights = 0;
		for (ActivityType activity : ActivityType.values()) { 
			if(activity != a) {
				double weight = activityChances.get(activity);
				double newDivisedWeight = weight-diff/nbActivities;
				this.activityChances.put(activity, newDivisedWeight);
				if(newDivisedWeight < 0.0) {
					this.activityChances.put(activity, 0.0);
				}
				totalWeights += newDivisedWeight;
			}
		}
		this.activityChances.put(a, 1.0-totalWeights);
	}
	
	
	/*
	 * Mathematical function that returns y, such as f(x) = y, with f(x)=-growthFactor/(x+growthFactor)+maxWeight;
	 * @param x
	 */
	private double raiseFunction(double x) {
		return -growthFactor/(x+growthFactor)+maxWeight;
	}
	
	
	/*
	 * Mathematical function that returns x, such as f(x) = y, with f(x)=-growthFactor/(x+growthFactor)+maxWeight;
	 * @param y
	 */
	private double raiseFunctionInvert(double y) {
		return -growthFactor/(y-maxWeight)-growthFactor;
	}
	
}
