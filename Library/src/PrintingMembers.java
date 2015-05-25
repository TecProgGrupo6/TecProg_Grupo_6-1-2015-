// Import the packages for using the classes in them into the program.

import javax.swing.*;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintingMembers extends JInternalFrame implements Printable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from PrintingMembers class
	private final static Logger LOGGER = Logger.getLogger( PrintingMembers.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For setting the connection and statement.

	// Connection status
	private Connection connection = null;

	// Creating the statement
	private Statement statement = null;

	// Resultset from the statement which comes from the data base
	private ResultSet resultset = null;

	// Constant of the URL from the database
	private String URL = "jdbc:odbc:JLibrary";

	// For creating the text area.
	private JTextArea textArea = new JTextArea();

	// For creating the vector to use it in the print.
	private Vector<String> lines;

	// Constant to check number os spaces in the Vector method
	public static final int TAB_SIZE = 10;

	// Constructor of JLibrary.
	public PrintingMembers ( String query ){

		super( "Printing Members" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Members were printed");

		// For getting the graphical user interface components display area.
		Container container = getContentPane();

		// For setting the font.
		this.textArea.setFont( new Font( "Tahoma" , Font.PLAIN , 9 ) );

		// For adding the text area to the container.
		container.add( this.textArea );
		try{

			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		}catch ( ClassNotFoundException ea ){

			System.out.println( ea.toString() );
		}catch ( Exception e ){

			System.out.println( e.toString() );
		}

		/***************************************************************
		 * For making the connection,creating the statement and update * the
		 * table in the database. After that,closing the statmenet * and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/
		try{
			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.resultset = this.statement.executeQuery( query );
			this.textArea.append( "=============== Members Information ===============\n\n" );

			while ( this.resultset.next() ){
				this.textArea.append( "Member ID: " + this.resultset.getString( "ID" ) + "\n" + "Name: " + this.resultset.getString( "Name" ) + "\n"
						+ "Major: " + this.resultset.getString( "Major" ) + "\n" + "Expired: " + this.resultset.getString( "Expired" ) + "\n\n" );
			}

			this.textArea.append( "=============== Members Information ===============" );
			this.resultset.close();
			this.statement.close();
			this.connection.close();

		}catch ( SQLException SQLe ){

			System.out.println( SQLe.toString() );
		}
		// For setting the visible to true.
		setVisible( true );

		// To show the frame.
		pack();
	}

	@Override
	public int print ( Graphics graphics , PageFormat pageFormat , int pageIndex ) throws PrinterException{

		graphics.translate( (int) pageFormat.getImageableX() , (int) pageFormat.getImageableY() );
		int wPage = (int) pageFormat.getImageableWidth();
		int hPage = (int) pageFormat.getImageableHeight();
		graphics.setClip( 0 , 0 , wPage , hPage );

		graphics.setColor( this.textArea.getBackground() );
		graphics.fillRect( 0 , 0 , wPage , hPage );
		graphics.setColor( this.textArea.getForeground() );

		Font font = this.textArea.getFont();
		graphics.setFont( font );
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int hLine = fontMetrics.getHeight();

		if ( this.lines == null ){

			this.lines = getLines( fontMetrics , wPage );

		}else{

			// Nothing to do

		}

		int numLines = this.lines.size();
		int linesPerPage = Math.max( hPage / hLine , 1 );
		int numPages = (int) Math.ceil( (double) numLines / (double) linesPerPage );

		if ( pageIndex >= numPages ){
			this.lines = null;
			return NO_SUCH_PAGE;
		}

		int xAxis = 0;
		int yAxis = fontMetrics.getAscent();
		int lineIndex = linesPerPage * pageIndex;

		while ( lineIndex < this.lines.size() && yAxis < hPage ){

			String string = this.lines.get( lineIndex );
			graphics.drawString( string , xAxis , yAxis );
			yAxis += hLine;
			lineIndex++;

		}
		return PAGE_EXISTS;
	}

	protected Vector<String> getLines ( FontMetrics fontMetrics , int wPage ){

		Vector<String> vector = new Vector<>();

		String text = this.textArea.getText();
		String prevToken = "";
		StringTokenizer stringTokenizer = new StringTokenizer( text , "\n\r" , true );

		while ( stringTokenizer.hasMoreTokens() ){
			String line = stringTokenizer.nextToken();

			if ( line.equals( "\r" ) ){
				continue;
			}else{
				// Nothing to do
			}

			/*
			 * StringTokenizer will ignore empty lines, so it's a bit tricky to
			 * get them...
			 */
			if ( line.equals( "\n" ) && prevToken.equals( "\n" ) ){
				vector.add( "" );
			}else{
				// Nothing to do
			}
			prevToken = line;
			if ( line.equals( "\n" ) ){
				continue;
			}else{
				// Nothing to do
			}

			StringTokenizer stringTokenizer2 = new StringTokenizer( line , " \t" , true );
			String line2 = "";

			while ( stringTokenizer2.hasMoreTokens() ){
				String token = stringTokenizer2.nextToken();

				if ( token.equals( "\t" ) ){

					int numSpaces = TAB_SIZE - line2.length() % TAB_SIZE;
					token = "";

					for ( int k = 0 ; k < numSpaces ; k++ )
						token += " ";
				}else{
					// Nothing to do
				}

				int lineLength = fontMetrics.stringWidth( line2 + token );

				if ( lineLength > wPage && line2.length() > 0 ){

					vector.add( line2 );
					line2 = token.trim();
					continue;
				}else{
					// Nothing to do
				}
				line2 += token;
			}
			vector.add( line2 );
		}
		return vector;
	}
}
