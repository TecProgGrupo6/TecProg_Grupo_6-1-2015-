package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Feature{

	protected boolean passable;
	
	private final static Logger LOGGER = Logger.getLogger( Feature.class.getName() );

	public boolean isPassable (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned Feature");

		return passable;
	}
}
