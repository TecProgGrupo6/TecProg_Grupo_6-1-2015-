package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class GelatinousCube extends Enemy{

	public GelatinousCube ( Map map ){

		super( map );
		init();
	}
	
	// Log system from GelatinousCube Class
	private final static Logger LOGGER = Logger.getLogger( GelatinousCube.class.getName() );

	// Iniitliaze Geletinous Cube
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gelatinous Cube intialized");

		maxHP = 14;
		attack = 5;
		image = Game.getImageManager().gelatinousCube;
		hp = maxHP;
		xpReward = 7;
		level = 7;
	}
}
