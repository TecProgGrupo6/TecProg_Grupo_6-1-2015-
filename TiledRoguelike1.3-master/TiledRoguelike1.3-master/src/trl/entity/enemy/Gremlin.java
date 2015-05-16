package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Gremlin extends Enemy{

	public Gremlin ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Gremlin Class
	private final static Logger LOGGER = Logger.getLogger( Gremlin.class.getName() );

	// Initiliaze Gremlin
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gremlin intialized");

		maxHP = 24;
		attack = 5;
		image = Game.getImageManager().gremlin;
		hp = maxHP;
		xpReward = 12;
		level = 12;
	}
}
