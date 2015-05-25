package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Gremlin extends Enemy{

	public Gremlin ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Gremlin Class
	private final static Logger LOGGER = Logger.getLogger( Gremlin.class.getName() );

	// Initiliaze Gremlin
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gremlin intialized");

		this.maxHP = 24;
		this.attack = 5;
		this.image = Game.getImageManager().gremlin;
		this.hp = this.maxHP;
		this.xpReward = 12;
		this.level = 12;
	}
}
