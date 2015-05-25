package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Goal extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( Goal.class.getName() );

	public Goal (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned Goal");

		this.passable = true;
	}
}
