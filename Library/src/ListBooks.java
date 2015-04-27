// Import the packages for using the classes in them into the program.

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;

/**
 * A public class
 */
public class ListBooks extends JInternalFrame{

	/***************************************************************************
	 *** Declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();

	// For creating the label.
	private JLabel northLabel = new JLabel( "THE LIST FOR THE BOOKS" );

	// For creating the button.
	private JButton printButton;

	// For creating the table.
	private JTable table;

	// For creating the TableColumn.
	private TableColumn column = null;

	// For creating the JScrollPane.
	private JScrollPane scrollPane;

	// For creating an object for the ResultSetTableModel class.
	private ResultSetTableModel tableModel;

	/***************************************************************************
	 * for setting the required information for the ResultSetTableModel class. *
	 ***************************************************************************/
	// Constant of the database url
	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";

	// Database related
	private static final String DATABASE_URL = "jdbc:odbc:JLibrary";

	// Database related
	private static final String DEFAULT_QUERY = "SELECT BookID, Subject, Title, Author,"
			+ "Publisher, Copyright, Edition, Pages, NumberOfBooks, ISBN, Library, Availble,ShelfNo FROM Books";

	// Constructor of listBooks.
	public ListBooks (){

		// For setting the title for the internal frame.
		super( "Books" , false , true , false , true );

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/List16.gif" ) ) );

		// SetLocale(new java.util.Locale("ar", "SA", ""));

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For bassing the required information to the ResultSetTableModel.
		// Object.
		try{
			tableModel = new ResultSetTableModel( JDBC_DRIVER , DATABASE_URL , DEFAULT_QUERY );
			// For setting the Query.
			try{
				tableModel.setQuery( DEFAULT_QUERY );
			}catch ( SQLException sqlException ){

				// Do nothing.

			}
		}catch ( ClassNotFoundException classNotFound ){

			System.out.println( classNotFound.toString() );
		}catch ( SQLException sqlException ){

			System.out.println( sqlException.toString() );
		}
		// For setting the table with the information.
		table = new JTable( tableModel );

		// For setting the size for the table.
		table.setPreferredScrollableViewportSize( new Dimension( 990 , 200 ) );

		// For setting the font.
		table.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		// For setting the scrollpane to the table.
		scrollPane = new JScrollPane( table );

		// For setting the size for the table columns.
		for ( int i = 0 ; i < 13 ; i++ ){
			column = table.getColumnModel().getColumn( i );
			// BookID.
			if ( i == 0 ){

				column.setPreferredWidth( 20 );
			}
			// Subject.
			if ( i == 1 ){

				column.setPreferredWidth( 100 );
			}
			// Title.
			if ( i == 2 ){

				column.setPreferredWidth( 150 );
			}
			// Auther.
			if ( i == 3 ){

				column.setPreferredWidth( 50 );
			}
			// Publisher.
			if ( i == 4 ){

				column.setPreferredWidth( 70 );
			}
			// Copyright.
			if ( i == 5 ){

				column.setPreferredWidth( 40 );
			}
			// Edition.
			if ( i == 6 ){

				column.setPreferredWidth( 40 );
			}
			// Pages.
			if ( i == 7 ){

				column.setPreferredWidth( 40 );
			}
			// NumberOfBooks.
			if ( i == 8 ){

				column.setPreferredWidth( 80 );
			}
			// ISBN.
			if ( i == 9 ){

				column.setPreferredWidth( 70 );
			}
			// Library.
			if ( i == 10 ){

				column.setPreferredWidth( 30 );
			}
			// Availble.
			if ( i == 11 ){

				column.setPreferredWidth( 30 );
			}
			// ShelfNo.
			if ( i == 12 ){

				column.setPreferredWidth( 30 );
			}
		}
		// For setting the font to the label.
		northLabel.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For setting the layout to the panel.
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For adding the label to the panel.
		northPanel.add( northLabel );

		// For adding the panel to the container.
		cp.add( "North" , northPanel );

		// For setting the layout to the panel.
		centerPanel.setLayout( new BorderLayout() );

		// For creating an image for the button.
		ImageIcon printIcon = new ImageIcon( ClassLoader.getSystemResource( "images/Print16.gif" ) );

		// For adding the button to the panel.
		printButton = new JButton( "print the books" , printIcon );

		// For setting the tip text.
		printButton.setToolTipText( "Print" );

		// For setting the font to the button.
		printButton.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		// For adding the button to the panel.
		centerPanel.add( printButton , BorderLayout.NORTH );

		// For adding the scrollpane to the panel.
		centerPanel.add( scrollPane , BorderLayout.CENTER );

		// For setting the border to the panel.
		centerPanel.setBorder( BorderFactory.createTitledBorder( "Books:" ) );

		// For adding the panel to the container.
		cp.add( "Center" , centerPanel );

		// For adding the actionListener to the button.
		printButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				Thread runner = new Thread(){

					public void run (){

						try{
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable( new PrintingBooks( DEFAULT_QUERY ) );
							if ( !prnJob.printDialog() ){

								return;
							}else{

								// Do nothing

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

		// For setting the visible to true.
		setVisible( true );

		// To show the frame.
		pack();
	}
}
