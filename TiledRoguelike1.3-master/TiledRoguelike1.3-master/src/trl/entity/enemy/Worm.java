package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Worm extends Enemy{

	public Worm ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Worm Class
	private final static Logger LOGGER = Logger.getLogger( Worm.class.getName() );

	// Initiliaze Worm
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Worm intialized");

		this.maxHP = 5;
		this.attack = 5;
		this.image = Game.getImageManager().worm;
		this.hp = this.maxHP;
		this.xpReward = 1;
		this.level = 1;
	}
}
