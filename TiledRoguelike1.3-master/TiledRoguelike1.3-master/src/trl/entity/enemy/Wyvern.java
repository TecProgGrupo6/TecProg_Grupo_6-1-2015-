package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Wyvern extends Enemy{

	public Wyvern ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Wyvern Class
	private final static Logger LOGGER = Logger.getLogger( Wyvern.class.getName() );

	// Initiliaze Wyvern
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wyvern intialized");

		maxHP = 22;
		attack = 5;
		image = Game.getImageManager().wyvern;
		hp = maxHP;
		xpReward = 11;
		level = 11;
	}
}
