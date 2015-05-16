package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Worm extends Enemy{

	public Worm ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Worm Class
	private final static Logger LOGGER = Logger.getLogger( Worm.class.getName() );

	// Initiliaze Worm
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Worm intialized");

		maxHP = 5;
		attack = 5;
		image = Game.getImageManager().worm;
		hp = maxHP;
		xpReward = 1;
		level = 1;
	}
}
