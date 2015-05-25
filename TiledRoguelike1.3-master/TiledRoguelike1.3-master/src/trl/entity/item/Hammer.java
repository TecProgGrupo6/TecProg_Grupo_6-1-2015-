package trl.entity.item;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Hammer extends Item{

	public Hammer ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Hammer Class
	private final static Logger LOGGER = Logger.getLogger( Hammer.class.getName() );

	// Initiliaze Hammer
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Hammer intialized");

		this.image = Game.getImageManager().hammer;
		this.loc = this.map.placeEntity( this , this.map.getRandomNodeInRoom() );
	}
}
