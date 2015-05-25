package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Gargoyle extends Enemy{

	public Gargoyle ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Gargoyle Class
	private final static Logger LOGGER = Logger.getLogger( Gargoyle.class.getName() );

	// Initiliaze a gargoyle
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Gargoyle intialized");

		this.maxHP = 30;
		this.attack = 5;
		this.image = Game.getImageManager().gargoyle;
		this.hp = this.maxHP;
		this.xpReward = 15;
		this.level = 15;
	}
}
