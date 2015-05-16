package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class Demon extends Enemy{

	public Demon ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Demon Class
	private final static Logger LOGGER = Logger.getLogger( Demon.class.getName() );

	// Initiliaze demon
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Demon intialized");

		maxHP = 15;
		attack = 10;
		image = Game.getImageManager().demon;
		hp = maxHP;
		level = 1;
		xpReward = 1;
	}
}
