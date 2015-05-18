//import the packages for using the classes in them into the program

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;

import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * A public class
 */

public class StatusBar extends JPanel{
	
	// Log system from Toolbar class
	private final static Logger LOGGER = Logger.getLogger( StatusBar.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	private JLabel statusBar = new JLabel( "  " );

	// Constructor of StatusBar
	public StatusBar (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("StatusBar created.");
		
		statusBar.setFont( new Font( "Tahoma" , Font.BOLD , 9 ) );
		this.add( statusBar );
		this.setBorder( new SoftBevelBorder( javax.swing.border.BevelBorder.LOWERED ) );

	}
}
