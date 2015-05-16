package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;


import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class Bat extends Enemy{

	public Bat ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Bat Class
	private final static Logger LOGGER = Logger.getLogger( Bat.class.getName() );

	// Initiliaze Bat
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Bat intialized");

		maxHP = 6;
		attack = 5;
		image = Game.getImageManager().bat;
		hp = maxHP;
		level = 1;
		xpReward = 1;
	}
}
