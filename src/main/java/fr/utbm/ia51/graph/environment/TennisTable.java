package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;

public class TennisTable extends EnvironmentEntity{
	public TennisTable(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.TENNISTABLE;
	}
}

