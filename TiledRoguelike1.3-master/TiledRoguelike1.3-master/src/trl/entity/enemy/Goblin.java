package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class Goblin extends Enemy{

	public Goblin ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Goblin Class
	private final static Logger LOGGER = Logger.getLogger( Goblin.class.getName() );

	// Initiliaze Goblin
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Goblin intialized");

		maxHP = 16;
		attack = 5;
		image = Game.getImageManager().goblin;
		hp = maxHP;
		xpReward = 8;
		level = 8;
	}
}
