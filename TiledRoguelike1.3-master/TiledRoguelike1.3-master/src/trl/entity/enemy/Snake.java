package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Snake extends Enemy{

	public Snake ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Snake Class
	private final static Logger LOGGER = Logger.getLogger( Snake.class.getName() );

	// Initiliaze Snake
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Snake intialized");

		this.maxHP = 5;
		this.attack = 5;
		this.image = Game.getImageManager().snake;
		this.hp = this.maxHP;
		this.level = 1;
		this.xpReward = 1;
	}
}
