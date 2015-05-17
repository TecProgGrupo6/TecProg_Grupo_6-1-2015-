package trl.entity.item;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Lock extends Item{
	
	// Log system from Lock Class
	private final static Logger LOGGER = Logger.getLogger( Lock.class.getName() );

	public Lock ( Map map ){

		super( map );
		this.image = Game.getImageManager().lock;
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Lock intialized");
		
	}
}
