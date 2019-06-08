package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;

public class SoccerField extends EnvironmentEntity {	
	public SoccerField(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.SOCCER;
	}
}
