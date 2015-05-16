package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Panther extends Enemy{

	public Panther ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Panther Class
	private final static Logger LOGGER = Logger.getLogger( Panther.class.getName() );

	// Initiliaze Panther
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Panther intialized");

		maxHP = 18;
		attack = 5;
		image = Game.getImageManager().panther;
		hp = maxHP;
		xpReward = 9;
		level = 9;
	}
}
