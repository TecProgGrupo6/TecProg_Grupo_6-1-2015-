package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Wolf extends Enemy{

	public Wolf ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Wolf Class
	private final static Logger LOGGER = Logger.getLogger( Wolf.class.getName() );

	// Initiliaze Wolf
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wolf intialized");

		maxHP = 20;
		attack = 5;
		image = Game.getImageManager().wolf;
		hp = maxHP;
		xpReward = 10;
		level = 10;
	}
}
