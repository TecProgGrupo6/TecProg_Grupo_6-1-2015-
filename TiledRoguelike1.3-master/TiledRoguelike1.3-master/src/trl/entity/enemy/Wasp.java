package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Wasp extends Enemy{

	public Wasp ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Wasp Class
	private final static Logger LOGGER = Logger.getLogger( Wasp.class.getName() );

	// Initiliaze Wasp
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wasp intialized");

		this.maxHP = 10;
		this.attack = 5;
		this.image = Game.getImageManager().wasp;
		this.hp = this.maxHP;
		this.xpReward = 5;
		this.level = 5;
	}
}
