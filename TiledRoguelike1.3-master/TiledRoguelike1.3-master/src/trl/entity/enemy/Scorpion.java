package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Scorpion extends Enemy{

	public Scorpion ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Scorpion Class
	private final static Logger LOGGER = Logger.getLogger( Scorpion.class.getName() );

	// Initiliaze Scorpion
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Scorpion intialized");

		this.maxHP = 12;
		this.attack = 5;
		this.image = Game.getImageManager().scorpion;
		this.hp = this.maxHP;
		this.xpReward = 6;
		this.level = 6;
	}
}
