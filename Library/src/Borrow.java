//import the packages for using the classes in them into the program

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Borrow{

	// Log system from AddBooks Class
	private final static Logger LOGGER = Logger.getLogger( Borrow.class.getName() );
		
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	private Connection connection = null;

	private Statement statement = null;

	private ResultSet resultSet = null;

	// Inform the book ID
	private int bookID;

	// Represent the member ID
	private int memberID;

	// Represent the day of the borrowing
	private Date dayOfBorrowed;

	// Represent the day of the return
	private Date dayOfReturn;

	// URL connection
	private String URL = "jdbc:odbc:JLibrary";

	public Borrow (){

	}

	public int getBookID (){

		return this.bookID;
	}

	public int getMemberID (){

		return this.memberID;
	}

	public Date getDayOfBorrowed (){

		return this.dayOfBorrowed;
	}

	public Date getDayOfReturn (){

		return this.dayOfReturn;
	}

	// Establishing the connection
	public void connection (){

		try{
			
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		}catch ( ClassNotFoundException cnfe ){
			
			System.out.println( "Borrow.java\n" + cnfe.toString() );
		}catch ( Exception e ){
			
			System.out.println( "Borrow.java\n" + e.toString() );
		}

		/***************************************************************
		 * for making the connection,creating the statement and update * the
		 * table in the database. After that,closing the statmenet * and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/
		try{

			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.resultSet = this.statement.executeQuery( "SELECT * FROM Borrow" );

			while ( this.resultSet.next() ){

				this.bookID = this.resultSet.getInt( 1 );
				this.memberID = this.resultSet.getInt( 2 );
				this.dayOfBorrowed = this.resultSet.getDate( 3 );
				this.dayOfReturn = this.resultSet.getDate( 4 );

			}
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Connection established");

			this.resultSet.close();
			this.statement.close();
			this.connection.close();

		}catch ( SQLException SQLe ){
			
			System.out.println( "Borrow.java\n" + SQLe.toString() );
		}
	}

	// Executing the class
	@SuppressWarnings ( "static-method" )
	public void executeClass (){

		try{
			
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		}catch ( ClassNotFoundException cnfe ){
			
			System.out.println( "Borrow.java\n" + cnfe.toString() );
		}catch ( Exception e ){
			
			System.out.println( "Borrow.java\n" + e.toString() );
		}

	}

	/***************************************************************
	 * for making the connection,creating the statement and update * the table
	 * in the database. After that,closing the statmenet * and connection. There
	 * is catch block SQLException for error *
	 ***************************************************************/
	// Updating the borrowing
	public void update ( String Query ){

		executeClass();

		try{

			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate( Query );
			this.statement.close();
			this.connection.close();
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Updated");

		}catch ( SQLException SQLe ){
			
			System.out.println( "Borrow.java\n" + SQLe.toString() );
		}
	}
}
