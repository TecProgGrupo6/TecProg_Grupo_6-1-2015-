import javax.swing.table.AbstractTableModel;
import java.sql.*;

/**
 * ResultSet rows and columns are counted from 1 and JTable rows and columns are
 * counted from 0. When processing ResultSet rows or columns for use in a
 * JTable, it is necessary to add 1 to the row or column number to manipulate
 * the appropriate ResultSet column (i.e., JTable column 0 is ResultSet column 1
 * and JTable row 0 is ResultSet row 1)
 */
public class ResultSetTableModel extends AbstractTableModel{
	
	// Connection status
	private Connection connection;
	
	// Creating the statement
	private Statement statement;
	
	// Resultset from the statement which comes from the data base
	private ResultSet resultSet;
	
	// Getting the metaData from the ResultSet Class
	private ResultSetMetaData metaData;
	
	// Number of rows from the ResultSet
	private int numberOfRows;
	
	// Keep track of database connection status.
	private boolean connectedToDatabase = false;

	// Initialize resultSet and obtain its meta data object.
	// Determine number of rows.
	public ResultSetTableModel(String driver, String url, String query) throws SQLException, ClassNotFoundException {
		
		// load database driver class
		Class.forName(driver); 
		
		// Connect to database
		connection = DriverManager.getConnection(url); 
		
		// Create Statement to query database.
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		// Update database connection status.
		connectedToDatabase = true;
		
		// Set query and execute it.
		setQuery(query); 
	}

	// Get class that represents column type.
	public Class getColumnClass( int column ) throws IllegalStateException{
		
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		
		// Determine Java class of column.
		try{
			String className = metaData.getColumnClassName(column + 1);
			
			// Return Class object that represents className.
			return Class.forName(className); 
												
		}
		
		// Catch SQLExceptions and ClassNotFoundExceptions.
		catch (Exception exception){
			exception.printStackTrace();
		}
		
		// If problems occur above, assume type Object.
		return Object.class;
	}

	// Get number of columns in ResultSet.
	public int getColumnCount() throws IllegalStateException{
		
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		
		// Determine number of columns.
		try{
			return metaData.getColumnCount();
		}
		
		// Catch SQLExceptions and print error message.
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		
		// If problems occur above, return 0 for number of columns.
		return 0;
	}

	// Get name of a particular column in ResultSet.
	public String getColumnName( int column ) throws IllegalStateException{
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		
		// Determine column name.
		try{
			return metaData.getColumnName(column + 1);
		}
		
		// Catch SQLExceptions and print error message.
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		
		// If problems, return empty string for column name.
		return "";
	}

	// Return number of rows in ResultSet.
	public int getRowCount() throws IllegalStateException{
		
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		return numberOfRows;
	}

	// Obtain value in particular row and column.
	public Object getValueAt( int row, int column ) throws IllegalStateException{
		
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		
		// Obtain a value at specified ResultSet row and column.
		try{
			resultSet.absolute(row + 1);
			return resultSet.getObject(column + 1);
		}
		
		// Catch SQLExceptions and print error message.
		catch ( SQLException sqlException ){
			sqlException.printStackTrace();
		}
		
		// If problems, return empty string object.
		return "";
	}

	// Set new database query string.
	public void setQuery( String query ) throws SQLException, IllegalStateException{
		
		// Ensure database connection is available.
		if ( !connectedToDatabase ){
			throw new IllegalStateException("Not Connected to Database");
		}else{
			
		}
		// Specify query and execute it.
		resultSet = statement.executeQuery(query);
		
		// Obtain meta data for ResultSet.
		metaData = resultSet.getMetaData();
		
		// Determine number of rows in ResultSet.
		resultSet.last(); // move to last row
		
		// Get row number.
		numberOfRows = resultSet.getRow();
		
		// Notify JTable that model has changed.
		fireTableStructureChanged(); 
	}

	// Close Statement and Connection.
	public void disconnectFromDatabase(){
		
		// Close Statement and Connection.
		try{
			statement.close();
			connection.close();
		}
		
		// Catch SQLExceptions and print error message.
		catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		
		// Update database connection status.
		finally{
			connectedToDatabase = false;
		}
	}
} // End class ResultSetTableModel.