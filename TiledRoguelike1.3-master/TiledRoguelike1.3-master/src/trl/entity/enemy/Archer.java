package trl.entity.enemy;

import trl.entity.actor.Actor;
import trl.main.Game;
import trl.map.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Archer extends Enemy{

	public Archer ( Map map ){

		super( map );
		init();
	}
	
	private final static Logger LOGGER = Logger.getLogger( Archer.class.getName() );

	// Initiliaze archer
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Archer");

		maxHP = 5;
		attack = 5;
		image = Game.getImageManager().archer;
		hp = maxHP;
		xpReward = 1;
		level = 1;
	}
}
