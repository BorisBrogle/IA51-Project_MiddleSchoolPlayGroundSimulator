package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;

public class Bench extends EnvironmentEntity{
	public Bench(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.BENCH;
	}
}

