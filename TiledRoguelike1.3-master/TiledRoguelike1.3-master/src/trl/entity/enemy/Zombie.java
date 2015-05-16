package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Zombie extends Enemy{

	public Zombie ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Zombie Class
	private final static Logger LOGGER = Logger.getLogger( Zombie.class.getName() );

	// Initiliaze Zombie
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Zombie intialized");

		maxHP = 26;
		attack = 5;
		image = Game.getImageManager().zombie;
		hp = maxHP;
		xpReward = 13;
		level = 13;
	}
}
