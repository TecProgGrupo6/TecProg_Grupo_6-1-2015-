package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.map.Map;

public class Ghost extends Enemy{

	public Ghost ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Ghost Class
	private final static Logger LOGGER = Logger.getLogger( Ghost.class.getName() );

	// Initiliaze Ghost
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Ghost intialized");

		this.maxHP = 5;
		this.attack = 5;
		// image = Game.getImageManager().ghost;
		this.hp = this.maxHP;
		this.xpReward = 1;
		this.level = 1;
	}
}
