//import the packages for using the classes in them into the program

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * A public class
 */
public class ListSearchMembers extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from ListSearchMembers class
	private final static Logger LOGGER = Logger.getLogger( ListSearchMembers.class.getName() );
	
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel
	private JPanel northPanel = new JPanel();

	// For creating the Center Panel
	private JPanel centerPanel = new JPanel();

	// For creating the label
	private JLabel label = new JLabel( "THE LIST FOR THE SEARCHED MEMBERS" );

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

	// Constructor of listSearchMembers
	public ListSearchMembers ( String query ){

		// For setting the title for the internal frame
		super( "Searched Members" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("ListSearchMembers created.");

		// For setting the icon
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/List16.gif" ) ) );

		// For getting the graphical user interface components display area
		Container cp = getContentPane();

		/***********************************************************************
		 * for setting the required information for the ResultSetTableModel
		 * class*
		 ************************************************************************/
		final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
		final String DATABASE_URL = "jdbc:odbc:JLibrary";
		final String DEFAULT_QUERY = query;

		// For bassing the required information to the ResultSetTableModel
		// object
		ResultSetTableModel tableModel2 = this.tableModel;
		try{

			this.tableModel = new ResultSetTableModel( JDBC_DRIVER , DATABASE_URL , DEFAULT_QUERY );

			// for setting the Query
			try{
				tableModel2.setQuery( DEFAULT_QUERY );
			}catch ( @SuppressWarnings("unused") SQLException sqlException ){

				// Nothing to do
			}
		}catch ( ClassNotFoundException classNotFound ){

			System.out.println( "ListSearchMembers.java\n" + classNotFound.toString() );

		}catch ( @SuppressWarnings("unused") SQLException sqlException ){

			// Nothing to do
		}

		// For setting the table with the information
		this.table = new JTable( tableModel2 );

		// For setting the size for the table
		JTable table2 = this.table;
		table2.setPreferredScrollableViewportSize( new Dimension( 700 , 200 ) );

		// For setting the font
		table2.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		this.scrollPane = new JScrollPane( table2 );

		// For setting the size for the table columns
		for ( int i = 0 ; i < 6 ; i++ ){

			this.column = this.table.getColumnModel().getColumn( i );

			if ( i == 0 ){// MemberID

				this.column.setPreferredWidth( 30 );

			}else if ( i == 1 ){// ID

				this.column.setPreferredWidth( 20 );

			}else if ( i == 2 ){// Name

				this.column.setPreferredWidth( 150 );

			}else if ( i == 3 ){// E-MAIL

				this.column.setPreferredWidth( 120 );

			}else if ( i == 4 ){// Major

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

			// for adding the actionListener to the button
			this.printButton.addActionListener( new ActionListener(){

				@Override
				public void actionPerformed ( ActionEvent ae ){

					Thread runner = new Thread(){

						@Override
						public void run (){

							try{

								PrinterJob prnJob = PrinterJob.getPrinterJob();
								prnJob.setPrintable( new PrintingMembers( DEFAULT_QUERY ) );

								if ( !prnJob.printDialog() ){

									return;
								}else{

									// No action
								}

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

			// for setting the visible to true
			setVisible( true );
			// to show the frame
			pack();
		}
	}
}
