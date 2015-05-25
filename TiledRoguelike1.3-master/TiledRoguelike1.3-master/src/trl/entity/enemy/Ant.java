package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	@Override
	public void init (){

		this.maxHP = 8;
		this.attack = 5;
		this.image = Game.getImageManager().ant;
		this.hp = this.maxHP;
		this.xpReward = 4;
		this.level = 4;

		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Ant Initialized" );
	}
}
