package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.map.Map;

public class Ghost extends Enemy{

	public Ghost ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Ghost Class
	private final static Logger LOGGER = Logger.getLogger( Ghost.class.getName() );

	// Initiliaze Ghost
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Ghost intialized");

		maxHP = 5;
		attack = 5;
		// image = Game.getImageManager().ghost;
		hp = maxHP;
		xpReward = 1;
		level = 1;
	}
}
