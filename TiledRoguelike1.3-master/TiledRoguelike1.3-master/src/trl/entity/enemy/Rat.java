package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Rat extends Enemy{

	public Rat ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Rat Class
	private final static Logger LOGGER = Logger.getLogger( Rat.class.getName() );

	// Initiliaze Rat
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Rat intialized");

		maxHP = 8;
		attack = 5;
		image = Game.getImageManager().rat;
		hp = maxHP;
		level = 3;
		xpReward = 3;
	}
}
