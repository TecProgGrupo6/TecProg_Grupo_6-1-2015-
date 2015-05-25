package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Void extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( Void.class.getName() );


	public Void (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned Void");

		this.passable = false;
	}

}
