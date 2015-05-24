package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Wall extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( Wall.class.getName() );


	public Wall (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned Wall");

		passable = false;
	}
}
