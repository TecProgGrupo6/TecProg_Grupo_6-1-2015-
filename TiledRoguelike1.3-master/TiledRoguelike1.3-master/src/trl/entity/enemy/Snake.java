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
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Snake intialized");

		maxHP = 5;
		attack = 5;
		image = Game.getImageManager().snake;
		hp = maxHP;
		level = 1;
		xpReward = 1;
	}
}
