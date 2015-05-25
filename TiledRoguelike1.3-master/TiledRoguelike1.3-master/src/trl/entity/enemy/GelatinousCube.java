package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class GelatinousCube extends Enemy{

	public GelatinousCube ( Map map ){

		super( map );
		init();
	}
	
	// Log system from GelatinousCube Class
	private final static Logger LOGGER = Logger.getLogger( GelatinousCube.class.getName() );

	// Iniitliaze Geletinous Cube
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gelatinous Cube intialized");

		this.maxHP = 14;
		this.attack = 5;
		this.image = Game.getImageManager().gelatinousCube;
		this.hp = this.maxHP;
		this.xpReward = 7;
		this.level = 7;
	}
}
