package trl.entity.item;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Goal extends Item{

	public Goal ( Map map ){

		super( map );
		init();
	}
	
	// Log system from Goal Class
	private final static Logger LOGGER = Logger.getLogger( Goal.class.getName() );

	// Initiliaze Goal
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Goal intialized");

		this.image = Game.getImageManager().goal;
		this.loc = this.map.placeEntity( this , this.map.getRandomNodeInRoom() );
	}
}
