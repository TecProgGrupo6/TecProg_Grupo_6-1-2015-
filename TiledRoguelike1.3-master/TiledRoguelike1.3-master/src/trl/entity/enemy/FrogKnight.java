package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class FrogKnight extends Enemy{

	public FrogKnight ( Map map ){

		super( map );
		init();
	}
	
	// Log system from FrogKnight Class
	private final static Logger LOGGER = Logger.getLogger( FrogKnight.class.getName() );

	// Initialize a frog knight
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Frog Knight intialized");

		maxHP = 10;
		attack = 8;
		image = Game.getImageManager().frogKnight;
		hp = maxHP;
		level = 1;
		xpReward = 1;
	}
}
