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
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Ogre intialized");

		maxHP = 28;
		attack = 5;
		image = Game.getImageManager().ogre;
		hp = maxHP;
		xpReward = 14;
		level = 14;
	}
}
