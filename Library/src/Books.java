//import the packages for using the classes in them into the program

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Books{

	// Log system from AddBooks Class
	private final static Logger LOGGER = Logger.getLogger( Books.class.getName() );
		
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	// Id of the book
	private int bookID;

	// The subject of the book
	private String subject;

	// The title of the book
	private String title;

	// The author of the book
	private String author;

	// The publisher of the book
	private String publisher;

	// The copyright of the book
	private int copyright;

	// The edition of the book
	private int edition;

	// Number of pages
	private int pages;

	private String ISBN;

	// The number of books
	private int numberOfBooks;

	// The number of books that are available
	private int numberOfAvailbleBooks;

	// The number of books that are borrowed
	private int numberOfBorrowedBooks;

	// The name of the library
	private String library;

	// If its available
	private boolean availble;

	private String URL = "jdbc:odbc:JLibrary";

	public Books (){

	}

	public int getBookID (){

		return this.bookID;
	}

	public String getSubject (){

		return this.subject;
	}

	public String getTitle (){

		return this.title;
	}

	public String getAuthor (){

		return this.author;
	}

	public String getPublisher (){

		return this.publisher;
	}

	public int getCopyright (){

		return this.copyright;
	}

	public int getEdition (){

		return this.edition;
	}

	public int getPages (){

		return this.pages;
	}

	public String getISBN (){

		return this.ISBN;
	}

	public int getNumberOfBooks (){

		return this.numberOfBooks;
	}

	public int getNumberOfAvailbleBooks (){

		return this.numberOfAvailbleBooks;
	}

	public int getNumberOfBorrowedBooks (){

		return this.numberOfBorrowedBooks;
	}

	public String getLibrary (){

		return this.library;
	}

	public boolean getAvailble (){

		return this.availble;
	}

	// Establishing connection
	public void connection ( String Query ){

		try{

			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Connected");

		}catch ( ClassNotFoundException cnfe ){

			System.out.println( "Books.java\n" + cnfe.toString() );

		}catch ( Exception e ){

			System.out.println( "Books.java\n" + e.toString() );

		}

		/***************************************************************
		 * For making the connection,creating the statement and update * the
		 * table in the database. After that, closing the statement* and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/

		try{

			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.resultSet = this.statement.executeQuery( Query );

			while ( this.resultSet.next() ){

				// Getting the book specification
				this.bookID = this.resultSet.getInt( 1 );
				this.subject = this.resultSet.getString( 2 );
				this.title = this.resultSet.getString( 3 );
				this.author = this.resultSet.getString( 4 );
				this.publisher = this.resultSet.getString( 5 );
				this.copyright = this.resultSet.getInt( 6 );
				this.edition = this.resultSet.getInt( 7 );
				this.pages = this.resultSet.getInt( 8 );
				this.ISBN = this.resultSet.getString( 9 );
				this.numberOfBooks = this.resultSet.getInt( 10 );
				this.numberOfAvailbleBooks = this.resultSet.getInt( 11 );
				this.numberOfBorrowedBooks = this.resultSet.getInt( 12 );
				this.library = this.resultSet.getString( 13 );
				this.availble = this.resultSet.getBoolean( 14 );

			}
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Connection established");

			this.resultSet.close();
			this.statement.close();
			this.connection.close();

		}catch ( SQLException SQLe ){

			System.out.println( "Books.java\n" + SQLe.toString() );

		}
	}

	// Updating book
	public void update ( String Query ){

		try{

			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );

		}

		catch ( ClassNotFoundException cnfe ){

			System.out.println( "Books.java\n" + cnfe.toString() );

		}catch ( Exception e ){

			System.out.println( "Books.java\n" + e.toString() );

		}

		/***************************************************************
		 * For making the connection,creating the statement and update * the
		 * table in the database. After that,closing the statement * and
		 * connection. There is catch block SQLException for error *
		 ***************************************************************/

		try{

			// connection = DriverManager.getConnection("jdbc:odbc:JLibrary2");
			this.connection = DriverManager.getConnection( this.URL );
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate( Query );
			this.statement.close();
			this.connection.close();
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Updated");

		}catch ( SQLException SQLe ){

			System.out.println( "Books.java\nError:" + SQLe.toString() );

		}
	}
}
