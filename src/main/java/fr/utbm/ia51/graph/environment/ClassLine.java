package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;

public class ClassLine extends EnvironmentEntity{
	public ClassLine(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.CLASSLINE;
	}
}

