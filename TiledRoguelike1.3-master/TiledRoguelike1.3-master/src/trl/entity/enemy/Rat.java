package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Rat extends Enemy{

	public Rat ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Rat Class
	private final static Logger LOGGER = Logger.getLogger( Rat.class.getName() );

	// Initiliaze Rat
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Rat intialized");

		this.maxHP = 8;
		this.attack = 5;
		this.image = Game.getImageManager().rat;
		this.hp = this.maxHP;
		this.level = 3;
		this.xpReward = 3;
	}
}
