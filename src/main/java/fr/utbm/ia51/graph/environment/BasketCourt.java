package fr.utbm.ia51.graph.environment;


import fr.utbm.ia51.activities.ActivityType;

public class BasketCourt extends EnvironmentEntity{	
	public BasketCourt(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.BASKETBALL;
	}
}
