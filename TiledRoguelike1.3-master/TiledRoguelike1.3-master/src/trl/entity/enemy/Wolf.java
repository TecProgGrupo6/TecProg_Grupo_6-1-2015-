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
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wolf intialized");

		this.maxHP = 20;
		this.attack = 5;
		this.image = Game.getImageManager().wolf;
		this.hp = this.maxHP;
		this.xpReward = 10;
		this.level = 10;
	}
}
