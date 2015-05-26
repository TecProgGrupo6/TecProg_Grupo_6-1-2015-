//import the packages for using the classes in them into the program

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A public class
 */
public class ListMembers extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from ListMembers class
	private final static Logger LOGGER = Logger.getLogger( ListMembers.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel
	private JPanel northPanel = new JPanel();

	// For creating the Center Panel
	private JPanel centerPanel = new JPanel();

	// For creating the label
	private JLabel label = new JLabel( "THE LIST FOR THE MEMBER" );

	// For creating the button
	private JButton printButton;

	// For creating the table
	private JTable table;

	// For creating the TableColumn
	private TableColumn column = null;

	// For creating the JScrollPane
	private JScrollPane scrollPane;

	// For creating an object for the ResultSetTableModel class
	private ResultSetTableModel tableModel;

	/***************************************************************************
	 * for setting the required information for the ResultSetTableModel class. *
	 ***************************************************************************/
	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String DATABASE_URL = "jdbc:odbc:JLibrary";
	private static final String DEFAULT_QUERY = "SELECT MemberID, ID, Name, EMail," + "Major, Expired FROM Members";

	// Constructor of listMembers
	public ListMembers (){

		// For setting the title for the internal frame
		super( "Members" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("ListMembers was created.");

		// For setting the icon
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/List16.gif" ) ) );

		// For getting the graphical user interface components display area
		Container cp = getContentPane();

		/*
		 * For bassing the required information to the ResultSetTableModel
		 * object
		 */
		try{

			this.tableModel = new ResultSetTableModel( JDBC_DRIVER , DATABASE_URL , DEFAULT_QUERY );

			// For setting the Query
			try{

				this.tableModel.setQuery( DEFAULT_QUERY );

			}

			catch ( SQLException sqlException ){
				//no action
			}
		}catch ( ClassNotFoundException classNotFound ){
			//no action
		}catch ( SQLException sqlException ){
			//no action
		}

		// For setting the table with the information
		this.table = new JTable( this.tableModel );

		// For setting the size for the table
		this.table.setPreferredScrollableViewportSize( new Dimension( 700 , 200 ) );

		// For setting the font
		this.table.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		// For setting the scrollpane to the table
		this.scrollPane = new JScrollPane( this.table );

		// For setting the size for the table columns
		for ( int i = 0 ; i < 6 ; i++ ){

			this.column = this.table.getColumnModel().getColumn( i );
			if ( i == 0 ){// MemberID

				this.column.setPreferredWidth( 30 );

			}else if ( i == 1 ){ // ID

				this.column.setPreferredWidth( 20 );

			}else if ( i == 2 ){ // Name

				this.column.setPreferredWidth( 150 );

			}else if ( i == 3 ){ // E-MAIL

				this.column.setPreferredWidth( 120 );

			}else if ( i == 4 ){ // Major

				this.column.setPreferredWidth( 20 );

			}else if ( i == 5 ){// Expired

				this.column.setPreferredWidth( 40 );

			}else{
				// No action
			}

			// For setting the font to the label
			this.label.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

			// For setting the layout to the panel
			this.northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

			// For adding the label to the panel
			this.northPanel.add( this.label );

			// For adding the panel to the container
			cp.add( "North" , this.northPanel );

			// For setting the layout to the panel
			this.centerPanel.setLayout( new BorderLayout() );

			// For creating an image for the button
			ImageIcon printIcon = new ImageIcon( ClassLoader.getSystemResource( "images/Print16.gif" ) );

			// For adding the button to the panel
			this.printButton = new JButton( "print the members" , printIcon );

			// For setting the tip text
			this.printButton.setToolTipText( "Print" );

			// For setting the font to the button
			this.printButton.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

			// For adding the button to the panel
			this.centerPanel.add( this.printButton , BorderLayout.NORTH );

			// For adding the scrollpane to the panel
			this.centerPanel.add( this.scrollPane , BorderLayout.CENTER );

			// For setting the border to the panel
			this.centerPanel.setBorder( BorderFactory.createTitledBorder( "Members:" ) );

			// For adding the panel to the container
			cp.add( "Center" , this.centerPanel );

			// For adding the actionListener to the button
			this.printButton.addActionListener( new ActionListener(){

				public void actionPerformed ( ActionEvent ae ){

					// Making a new thread
					Thread runner = new Thread(){

						public void run (){

							try{

								PrinterJob prnJob = PrinterJob.getPrinterJob();
								prnJob.setPrintable( new PrintingMembers( DEFAULT_QUERY ) );

								if ( !prnJob.printDialog() )

									return;
								setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
								prnJob.print();
								setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

							}catch ( PrinterException ex ){

								System.out.println( "Printing error: " + ex.toString() );

							}
						}
					};
					runner.start();
				}
			} );

			// For setting the visible to true
			setVisible( true );

			// To show the frame
			pack();
		}
	}
}
