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
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Spider intialized");

		this.maxHP = 7;
		this.attack = 5;
		this.image = Game.getImageManager().spider;
		this.hp = this.maxHP;
		this.level = 2;
		this.xpReward = 2;
	}
}
