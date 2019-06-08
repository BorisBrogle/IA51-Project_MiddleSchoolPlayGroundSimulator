package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;

public class Forest extends EnvironmentEntity {	
	public Forest(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.FOREST;
	}
}

