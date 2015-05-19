//import the packages for using the classes in them into the program

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Toolbar extends JToolBar{

	// Log system from Toolbar class
	private final static Logger LOGGER = Logger.getLogger( Toolbar.class.getName() );
	
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the buttons to use them in ToolBar
	public JButton[] button;

	// For creating the name of the image file 24*24
	public String[] imageName24 = { "images/Add24.gif" , "images/List24.gif" , "images/Add24.gif" , "images/List24.gif" ,

	"images/Find24.gif" , "images/Export24.gif" , "images/Import24.gif" ,

	"images/Exit24.gif" };

	// For creating the tipText for the toolbar
	public String[] tipText = { "Add Books" , "List All Books" , "Add Members" , "List Members" , "Search" , "Borrow Books" ,
			"Return Books" , "Exit" };

	// Creating a toolbar
	public Toolbar (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Toolbar created.");
		
		button = new JButton[19];

		for ( int i = 0 ; i < imageName24.length ; i++ ){

			if ( i == 2 || i == 4 || i == 5 || i == 7 ){

				// For adding separator to the toolBar
				addSeparator();

			}else{
				// No action
			}

			// For adding the buttons to toolBar
			add( button[i] = new JButton( new ImageIcon( ClassLoader.getSystemResource( imageName24[i] ) ) ) );

			// For setting the ToolTipText to the button
			button[i].setToolTipText( tipText[i] );
		}
	}
}
