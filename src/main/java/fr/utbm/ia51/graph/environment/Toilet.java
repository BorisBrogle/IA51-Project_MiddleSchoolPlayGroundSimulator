package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;


public class Toilet extends EnvironmentEntity{
	public Toilet(String imagePath) {
		super(imagePath);
		this.isCovering = true;
		this.activityType = ActivityType.TOILET;
	}
}

