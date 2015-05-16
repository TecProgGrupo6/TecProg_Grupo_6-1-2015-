package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Scorpion extends Enemy{

	public Scorpion ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Scorpion Class
	private final static Logger LOGGER = Logger.getLogger( Scorpion.class.getName() );

	// Initiliaze Scorpion
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Scorpion intialized");

		maxHP = 12;
		attack = 5;
		image = Game.getImageManager().scorpion;
		hp = maxHP;
		xpReward = 6;
		level = 6;
	}
}
