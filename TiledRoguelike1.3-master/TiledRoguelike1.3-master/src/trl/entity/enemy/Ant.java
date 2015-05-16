package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class Ant extends Enemy{

	public Ant ( Map map ){

		super( map );
		init();
	}

	// Log system from Ant Class
	private final static Logger LOGGER = Logger.getLogger( Ant.class.getName() );

	// Initiliaze an ant
	public void init (){

		maxHP = 8;
		attack = 5;
		image = Game.getImageManager().ant;
		hp = maxHP;
		xpReward = 4;
		level = 4;

		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Ant Initialized" );
	}
}
