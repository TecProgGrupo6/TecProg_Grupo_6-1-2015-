// Import the packages for using the classes in them into the program.

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
public class ListSearchBooks extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from ListSearchBooks class
	private final static Logger LOGGER = Logger.getLogger( ListSearchBooks.class.getName() );
	
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();

	// For creating the label.
	private JLabel label = new JLabel( "THE LIST FOR THE SEARCHED BOOKS" );

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

	// Constructor of listSearchBooks.
	public ListSearchBooks ( String query ){

		// For setting the title for the internal frame.
		super( "Searched Books" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("ListSearchBooks was called.");

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/List16.gif" ) ) );

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		/***********************************************************************
		 * for setting the required information for the ResultSetTableModel
		 * class*
		 ************************************************************************/
		final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
		final String DATABASE_URL = "jdbc:odbc:JLibrary";
		final String DEFAULT_QUERY = query;

		// For bassing the required information to the ResultSetTableModel.
		// Object.
		try{
			this.tableModel = new ResultSetTableModel( JDBC_DRIVER , DATABASE_URL , DEFAULT_QUERY );
			// For setting the Query.
			try{
				this.tableModel.setQuery( DEFAULT_QUERY );
			}catch ( SQLException sqlException ){

				// Do nothing

			}
		}catch ( ClassNotFoundException classNotFound ){

			// Do nothing

		}catch ( SQLException sqlException ){

			// Do nothing

		}

		// For setting the table with the information.
		this.table = new JTable( this.tableModel );

		// For setting the size for the table.
		this.table.setPreferredScrollableViewportSize( new Dimension( 990 , 200 ) );

		// For setting the font.
		this.table.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		// For setting the scrollpane to the table.
		this.scrollPane = new JScrollPane( this.table );

		// For setting the size for the table columns.
		for ( int i = 0 ; i < 13 ; i++ ){
			this.column = this.table.getColumnModel().getColumn( i );
			if ( i == 0 ){ // BookID.
				this.column.setPreferredWidth( 20 );
			}else{

				// Do nothing
			}

			if ( i == 1 ){ // Subject.
				this.column.setPreferredWidth( 100 );
			}else{

				// Do nothing
			}

			if ( i == 2 ){ // Title.
				this.column.setPreferredWidth( 150 );
			}else{

				// Do nothing
			}

			if ( i == 3 ){ // Auther.
				this.column.setPreferredWidth( 50 );
			}else{

				// Do nothing
			}

			if ( i == 4 ){ // Publisher.
				this.column.setPreferredWidth( 70 );
			}
			if ( i == 5 ){ // Copyright.
				this.column.setPreferredWidth( 40 );
			}else{

				// Do nothing
			}

			if ( i == 6 ){ // Edition.
				this.column.setPreferredWidth( 40 );
			}else{

				// Do nothing
			}

			if ( i == 7 ){ // Pages.
				this.column.setPreferredWidth( 40 );
			}else{

				// Do nothing
			}

			if ( i == 8 ){ // NumberOfBooks.
				this.column.setPreferredWidth( 80 );
			}else{

				// Do nothing
			}

			if ( i == 9 ){ // ISBN.
				this.column.setPreferredWidth( 70 );
			}else{

				// Do nothing
			}

			if ( i == 10 ){ // Library.
				this.column.setPreferredWidth( 30 );
			}else{

				// Do nothing
			}

			if ( i == 11 ){ // Availble.
				this.column.setPreferredWidth( 30 );
			}else{

				// Do nothing
			}

			if ( i == 12 ){ // ShelfNo.
				this.column.setPreferredWidth( 30 );
			}else{

				// Do nothing
			}

		}
		// For setting the font to the label.
		this.label.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For setting the layout to the panel.
		this.northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For adding the label to the panel.
		this.northPanel.add( this.label );

		// For adding the panel to the container.
		cp.add( "North" , this.northPanel );

		// For setting the layout to the panel.
		this.centerPanel.setLayout( new BorderLayout() );

		// For creating an image for the button.
		ImageIcon printIcon = new ImageIcon( ClassLoader.getSystemResource( "images/Print16.gif" ) );

		// For adding the button to the panel.
		this.printButton = new JButton( "print the books" , printIcon );

		// For setting the tip text.
		this.printButton.setToolTipText( "Print" );

		// For setting the font to the button.
		this.printButton.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		// For adding the button to the panel.
		this.centerPanel.add( this.printButton , BorderLayout.NORTH );

		// For adding the scrollpane to the panel.
		this.centerPanel.add( this.scrollPane , BorderLayout.CENTER );

		// For setting the border to the panel.
		this.centerPanel.setBorder( BorderFactory.createTitledBorder( "Books:" ) );

		// For adding the panel to the container.
		cp.add( "Center" , this.centerPanel );

		// For adding the actionListener to the button.
		this.printButton.addActionListener( new ActionListener(){

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
