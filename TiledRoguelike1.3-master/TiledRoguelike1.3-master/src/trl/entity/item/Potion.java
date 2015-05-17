package trl.entity.item;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Potion extends Item{

	public Potion ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Potion Class
	private final static Logger LOGGER = Logger.getLogger( Potion.class.getName() );

	// Initializes Potion
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Potion intialized");

		this.image = Game.getImageManager().potion;
		this.loc = map.placeEntity( this , map.getRandomNodeInRoom() );
	}
}
