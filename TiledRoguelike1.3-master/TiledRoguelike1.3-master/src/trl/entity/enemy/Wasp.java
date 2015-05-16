package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Wasp extends Enemy{

	public Wasp ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Wasp Class
	private final static Logger LOGGER = Logger.getLogger( Wasp.class.getName() );

	// Initiliaze Wasp
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wasp intialized");

		maxHP = 10;
		attack = 5;
		image = Game.getImageManager().wasp;
		hp = maxHP;
		xpReward = 5;
		level = 5;
	}
}
