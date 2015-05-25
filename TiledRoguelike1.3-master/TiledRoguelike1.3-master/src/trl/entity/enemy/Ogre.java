package trl.entity.enemy;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Ogre extends Enemy{

	public Ogre ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Ogre Class
	private final static Logger LOGGER = Logger.getLogger( Ogre.class.getName() );

	// Initialize Ogre
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Ogre intialized");

		this.maxHP = 28;
		this.attack = 5;
		this.image = Game.getImageManager().ogre;
		this.hp = this.maxHP;
		this.xpReward = 14;
		this.level = 14;
	}
}
