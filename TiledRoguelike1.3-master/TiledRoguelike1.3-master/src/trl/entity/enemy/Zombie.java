package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Zombie extends Enemy{

	public Zombie ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Zombie Class
	private final static Logger LOGGER = Logger.getLogger( Zombie.class.getName() );

	// Initiliaze Zombie
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Zombie intialized");

		this.maxHP = 26;
		this.attack = 5;
		this.image = Game.getImageManager().zombie;
		this.hp = this.maxHP;
		this.xpReward = 13;
		this.level = 13;
	}
}
