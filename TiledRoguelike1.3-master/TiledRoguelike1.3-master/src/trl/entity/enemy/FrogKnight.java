package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class FrogKnight extends Enemy{

	public FrogKnight ( Map map ){

		super( map );
		init();
	}
	
	// Log system from FrogKnight Class
	private final static Logger LOGGER = Logger.getLogger( FrogKnight.class.getName() );

	// Initialize a frog knight
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Frog Knight intialized");

		this.maxHP = 10;
		this.attack = 8;
		this.image = Game.getImageManager().frogKnight;
		this.hp = this.maxHP;
		this.level = 1;
		this.xpReward = 1;
	}
}
