package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

public class Gargoyle extends Enemy{

	public Gargoyle ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Gargoyle Class
	private final static Logger LOGGER = Logger.getLogger( Gargoyle.class.getName() );

	// Initiliaze a gargoyle
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gargoyle intialized");

		maxHP = 30;
		attack = 5;
		image = Game.getImageManager().gargoyle;
		hp = maxHP;
		xpReward = 15;
		level = 15;
	}
}
