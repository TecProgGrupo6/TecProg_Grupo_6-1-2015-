package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Wyvern extends Enemy{

	public Wyvern ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Wyvern Class
	private final static Logger LOGGER = Logger.getLogger( Wyvern.class.getName() );

	// Initiliaze Wyvern
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wyvern intialized");

		this.maxHP = 22;
		this.attack = 5;
		this.image = Game.getImageManager().wyvern;
		this.hp = this.maxHP;
		this.xpReward = 11;
		this.level = 11;
	}
}
