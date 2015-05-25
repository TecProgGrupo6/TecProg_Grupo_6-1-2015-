package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DoorClosed extends Feature{

	private final static Logger LOGGER = Logger.getLogger( DoorClosed.class.getName() );
	public DoorClosed (){
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Returned closed door");

		this.passable = false;
	}
}
