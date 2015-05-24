package trl.map.feature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StairDown extends Feature{
	
	private final static Logger LOGGER = Logger.getLogger( StairDown.class.getName() );


	public StairDown (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Returned StairDown");


		passable = true;
	}
}
