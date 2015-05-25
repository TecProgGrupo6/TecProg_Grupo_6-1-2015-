//import the packages for using the classes in them into the program

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Members{
	
	// Log system from Members class
	private final static Logger LOGGER = Logger.getLogger( Members.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	// The ID of the member
	private int memberID;

	// Member ID
	private int ID;

	// The member's password
	private String password;

	// The member's name
	private String name;

	// The member's email
	private String email;

	// The member's major
	private String major;

	// Number of books
	private int numberOfBooks;

	private int mony;
	private Date expired;
	private String URL = "jdbc:odbc:JLibrary";

	public Members (){

	}

	/*
	 * public Members(int memberID, int ID, String password, String name, String
	 * email, String major, int numberOfBooks, int mony, Date expired) {
	 * this.memberID = memberID; this.ID = ID; this.password = password;
	 * this.name = name; this.email = email; this.major = major;
	 * this.numberOfBooks = numberOfBooks; this.mony = mony; this.expired =
	 * expired; }
	 */

	public int getMemberID (){

		return this.memberID;
	}

	public int getID (){

		return this.ID;
	}

	public String getPassword (){

		return this.password;
	}

	public String getName (){

		return this.name;
	}

	public String getEmail (){

		return this.email;
	}

	public String getMajor (){

		return this.major;
	}

	public int getNumberOfBooks (){

		return this.numberOfBooks;
	}

	public int getMony (){

		return this.mony;
	}

	public Date getExpired (){

		return this.expired;
	}

	// Establishing an connection
	public void connection ( String Query ){
		
		try{

			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );

		}catch ( ClassNotFoundException cnfe ){

			System.out.println( "Members.java\n" + cnfe.toString() );

		}catch ( Exception e ){

			System.out.println( "Members.java\n" + e.toString() );

		}

		/***************************************************************
		 * For making the connection,creating the statement and update * the
		 * table in the database. After that,closing the statmenet * and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/
		try{

			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.resultSet = this.statement.executeQuery( Query );
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Connection stablished");

			while ( this.resultSet.next() ){

				this.memberID = this.resultSet.getInt( 1 );
				this.ID = this.resultSet.getInt( 2 );
				this.password = this.resultSet.getString( 3 );
				this.name = this.resultSet.getString( 4 );
				this.email = this.resultSet.getString( 5 );
				this.major = this.resultSet.getString( 6 );
				this.numberOfBooks = this.resultSet.getInt( 7 );
				this.mony = this.resultSet.getInt( 8 );
				this.expired = this.resultSet.getDate( 9 );

			}

			this.resultSet.close();
			this.statement.close();
			this.connection.close();
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Connection closed");

		}catch ( SQLException SQLe ){

			System.out.println( "Members.java\n" + SQLe.toString() );
		}
	}

	// Updating member
	public void update ( String Query ){

		try{

			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );

		}catch ( ClassNotFoundException cnfe ){

			System.out.println( "Members.java\n" + cnfe.toString() );

		}catch ( Exception e ){

			System.out.println( "Members.java\n" + e.toString() );

		}
		/***************************************************************
		 * For making the connection,creating the statement and update * the
		 * table in the database. After that,closing the statmenet * and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/
		try{

			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate( Query );
			this.statement.close();
			this.connection.close();

		}catch ( SQLException SQLe ){

			System.out.println( "Members.java\n" + SQLe.toString() );
		}
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Updated member");

	}
}
