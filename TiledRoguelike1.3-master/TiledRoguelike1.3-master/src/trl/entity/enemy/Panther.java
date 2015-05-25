package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Panther extends Enemy{

	public Panther ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Panther Class
	private final static Logger LOGGER = Logger.getLogger( Panther.class.getName() );

	// Initiliaze Panther
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Panther intialized");

		this.maxHP = 18;
		this.attack = 5;
		this.image = Game.getImageManager().panther;
		this.hp = this.maxHP;
		this.xpReward = 9;
		this.level = 9;
	}
}
