package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;


public class Library extends EnvironmentEntity{	
	public Library(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.LIBRARY;
	}
}

