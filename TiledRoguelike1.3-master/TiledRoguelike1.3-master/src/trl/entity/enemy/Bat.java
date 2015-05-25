package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;


import trl.main.Game;
import trl.map.Map;

public class Bat extends Enemy{

	public Bat ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Bat Class
	private final static Logger LOGGER = Logger.getLogger( Bat.class.getName() );

	// Initiliaze Bat
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Bat intialized");

		this.maxHP = 6;
		this.attack = 5;
		this.image = Game.getImageManager().bat;
		this.hp = this.maxHP;
		this.level = 1;
		this.xpReward = 1;
	}
}
