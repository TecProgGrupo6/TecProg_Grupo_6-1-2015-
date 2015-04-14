

//import the packages for using the classes in them into the program

import java.sql.*;


public class Books {
	
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	//Id of the book
	private int bookID;
	
	//The subject of the book
	private String subject; 
	
	//The title of the book
	private String title; 
	
	// The author of the book
	private String author; 
	
	//The publisher of the book
	private String publisher; 
	
	// The copyright of the book
	private int copyright; 
	
	//The edition of the book
	private int edition; 
	
	//Number of pages
	private int pages; 
	
	private String ISBN;
	
	//The number of books
	private int numberOfBooks; 
	
	//The number of books that are available
	private int numberOfAvailbleBooks; 
	
	//The number of books that are borrowed
	private int numberOfBorrowedBooks; 
	
	//The name of the library
	private String library; 
	
	//If its available
	private boolean availble; 
	
	private String URL = "jdbc:odbc:JLibrary";

	public Books() {
	}

	public int getBookID() {
		return bookID;
	}

	public String getSubject() {
		return subject;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getCopyright() {
		return copyright;
	}

	public int getEdition() {
		return edition;
	}

	public int getPages() {
		return pages;
	}

	public String getISBN() {
		return ISBN;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public int getNumberOfAvailbleBooks() {
		return numberOfAvailbleBooks;
	}

	public int getNumberOfBorrowedBooks() {
		return numberOfBorrowedBooks;
	}

	public String getLibrary() {
		return library;
	}

	public boolean getAvailble() {
		return availble;
	}

	// Establishing connection
	public void connection( String Query ) {
		
		try {
			
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
			
		}
		catch ( ClassNotFoundException cnfe ) {
			
			System.out.println("Books.java\n" + cnfe.toString());
			
		}
		catch ( Exception e ) {
			
			System.out.println("Books.java\n" + e.toString());
			
		}
		
		/***************************************************************
		 * For making the connection,creating the statement and update *
		 * the table in the database. After that, closing the statement*
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		
		try {
			
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			
			while ( resultSet.next() ) {
				
				// Getting the book specification
				bookID = resultSet.getInt(1);
				subject = resultSet.getString(2);
				title = resultSet.getString(3);
				author = resultSet.getString(4);
				publisher = resultSet.getString(5);
				copyright = resultSet.getInt(6);
				edition = resultSet.getInt(7);
				pages = resultSet.getInt(8);
				ISBN = resultSet.getString(9);
				numberOfBooks = resultSet.getInt(10);
				numberOfAvailbleBooks = resultSet.getInt(11);
				numberOfBorrowedBooks = resultSet.getInt(12);
				library = resultSet.getString(13);
				availble = resultSet.getBoolean(14);
				
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		}
		catch ( SQLException SQLe ) {
			
			System.out.println("Books.java\n" + SQLe.toString());
			
		}
	}

	//Updating book
	public void update( String Query ) {
		
		try {
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
		}
		
		catch ( ClassNotFoundException cnfe ) {
			
			System.out.println("Books.java\n" + cnfe.toString());
			
		}
		catch ( Exception e ) {
			
			System.out.println("Books.java\n" + e.toString());
			
		}
		
		/***************************************************************
		 * For making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statement *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		
		try {
			
			//connection = DriverManager.getConnection("jdbc:odbc:JLibrary2");
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			statement.executeUpdate(Query);
			statement.close();
			connection.close();
			
		}
		catch ( SQLException SQLe ) {
			
			System.out.println( "Books.java\nError:" + SQLe.toString() );
			
		}
	}
}