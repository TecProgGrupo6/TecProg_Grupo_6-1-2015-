package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DoorOpen extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( DoorOpen.class.getName() );


	public DoorOpen (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Returned open door");
		this.passable = true;
	}
}
