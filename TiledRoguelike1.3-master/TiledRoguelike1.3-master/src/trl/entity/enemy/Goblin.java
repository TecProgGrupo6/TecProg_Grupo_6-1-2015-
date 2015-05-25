package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Goblin extends Enemy{

	public Goblin ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Goblin Class
	private final static Logger LOGGER = Logger.getLogger( Goblin.class.getName() );

	// Initiliaze Goblin
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Goblin intialized");

		this.maxHP = 16;
		this.attack = 5;
		this.image = Game.getImageManager().goblin;
		this.hp = this.maxHP;
		this.xpReward = 8;
		this.level = 8;
	}
}
