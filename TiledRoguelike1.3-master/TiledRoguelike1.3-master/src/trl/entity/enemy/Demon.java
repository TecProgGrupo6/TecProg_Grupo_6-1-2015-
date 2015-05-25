package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Demon extends Enemy{

	public Demon ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Demon Class
	private final static Logger LOGGER = Logger.getLogger( Demon.class.getName() );

	// Initiliaze demon
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Demon intialized");

		this.maxHP = 15;
		this.attack = 10;
		this.image = Game.getImageManager().demon;
		this.hp = this.maxHP;
		this.level = 1;
		this.xpReward = 1;
	}
}
