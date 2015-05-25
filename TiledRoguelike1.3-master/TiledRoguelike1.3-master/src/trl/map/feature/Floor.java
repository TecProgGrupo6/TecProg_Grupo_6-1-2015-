package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Floor extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( Floor.class.getName() );

	public Floor (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned Floor");

		this.passable = true;
	}
}
