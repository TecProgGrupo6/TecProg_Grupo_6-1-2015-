package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Spider extends Enemy{

	public Spider ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Spider Class
	private final static Logger LOGGER = Logger.getLogger( Spider.class.getName() );

	// Initiliaze Spider
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Spider intialized");

		maxHP = 7;
		attack = 5;
		image = Game.getImageManager().spider;
		hp = maxHP;
		level = 2;
		xpReward = 2;
	}
}
