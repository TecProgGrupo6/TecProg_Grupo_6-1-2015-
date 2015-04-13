

//import the packages for using the classes in them into the program

import java.sql.*;

public class Borrow {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	//Inform the book ID
	private int bookID;
	
	//Represent the member ID
	private int memberID;
	
	//Represent the day of the borrowing
	private Date dayOfBorrowed;
	
	//Represent the day of the return
	private Date dayOfReturn;
	
	//URL connection
	private String URL = "jdbc:odbc:JLibrary";

	public Borrow() {
	}

	public int getBookID() {
		return bookID;
	}

	public int getMemberID() {
		return memberID;
	}

	public Date getDayOfBorrowed() {
		return dayOfBorrowed;
	}

	public Date getDayOfReturn() {
		return dayOfReturn;
	}

	//Establishing the connection
	public void connection() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch ( ClassNotFoundException cnfe ) {
			System.out.println("Borrow.java\n" + cnfe.toString());
		}
		catch ( Exception e ) {
			System.out.println("Borrow.java\n" + e.toString());
		}
		
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Borrow");
			
			while ( resultSet.next() ) {
				
				bookID = resultSet.getInt(1);
				memberID = resultSet.getInt(2);
				dayOfBorrowed = resultSet.getDate(3);
				dayOfReturn = resultSet.getDate(4);
				
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		}
		catch ( SQLException SQLe ) {
			System.out.println("Borrow.java\n" + SQLe.toString());
		}
	}

	//Updating the borrowing
	public void update( String Query ) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch ( ClassNotFoundException cnfe ) {
			System.out.println("Borrow.java\n" + cnfe.toString());
		}
		catch ( Exception e ) {
			System.out.println("Borrow.java\n" + e.toString());
		}
		
		/***************************************************************
		 * for making the connection,creating the statement and update *
		 * the table in the database. After that,closing the statmenet *
		 * and connection. There is catch block SQLException for error *
		 ***************************************************************/
		try {
			
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			statement.executeUpdate(Query);
			statement.close();
			connection.close();
			
		}
		catch ( SQLException SQLe ) {
			System.out.println("Borrow.java\n" + SQLe.toString());
		}
	}
}