//import the packages for using the classes in them into the program

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Toolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from Toolbar class
	private final static Logger LOGGER = Logger.getLogger( Toolbar.class.getName() );
	
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the buttons to use them in ToolBar
	public JButton[] button;

	// For creating the name of the image file 24*24
	public String[] imageName24 = { "images/Add24.gif" , //$NON-NLS-1$
									"images/List24.gif" , //$NON-NLS-1$
									"images/Add24.gif" , //$NON-NLS-1$
									"images/List24.gif" , //$NON-NLS-1$
									"images/Find24.gif" ,  //$NON-NLS-1$
									"images/Export24.gif" ,  //$NON-NLS-1$
									"images/Import24.gif" , //$NON-NLS-1$
									"images/Exit24.gif" }; //$NON-NLS-1$

	// For creating the tipText for the toolbar
	public String[] tipText = { "Add Books" ,  //$NON-NLS-1$
								"List All Books" ,  //$NON-NLS-1$
								"Add Members" ,  //$NON-NLS-1$
								"List Members" ,  //$NON-NLS-1$
								"Search" ,  //$NON-NLS-1$
								"Borrow Books" , //$NON-NLS-1$
								"Return Books" ,  //$NON-NLS-1$
								"Exit" }; //$NON-NLS-1$

	// Creating a toolbar
	public Toolbar (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Toolbar created."); //$NON-NLS-1$
		
		this.button = new JButton[19];

		for ( int i = 0 ; i < this.imageName24.length ; i++ ){

			if ( i == 2 || i == 4 || i == 5 || i == 7 ){

				// For adding separator to the toolBar
				addSeparator();

			}else{
				// No action
			}

			// For adding the buttons to toolBar
			add( this.button[i] = new JButton( new ImageIcon( ClassLoader.getSystemResource( this.imageName24[i] ) ) ) );

			// For setting the ToolTipText to the button
			this.button[i].setToolTipText( this.tipText[i] );
		}
	}
}
