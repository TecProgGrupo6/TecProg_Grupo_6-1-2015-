//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A public class
 */
public class ReturnBooks extends JInternalFrame implements ActionListener{
	
	// Log system from ReturnBooks class
	private final static Logger LOGGER = Logger.getLogger( ReturnBooks.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel
	private JPanel northPanel = new JPanel();

	// For creating the label
	private JLabel title = new JLabel( "BOOK INFORMATION" );

	// For creating the Center Panel
	private JPanel centerPanel = new JPanel();

	// For creating an Internal Panel in the center panel
	private JPanel informationPanel = new JPanel();

	// For creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[2];

	// For creating an array of String
	private String[] informationString = { " Write the Book ID:" , " Write the Member ID:" };

	// For creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[2];

	// For creating an array of string to store the data
	private String[] data;
	private JLabel lblFinePerDay = new JLabel( "Fine/Day" );
	private JTextField txtFinePerDay = new JTextField();
	private JLabel lblTotalFineAmt = new JLabel( "Total fine amount" );
	private JTextField txtTotalFineAmt = new JTextField();

	// For creating an Internal Panel in the center panel
	private JPanel returnButtonPanel = new JPanel();

	// For creating the button
	private JButton returnButton = new JButton( "Return" );

	// For creating the panel
	private JPanel southPanel = new JPanel();

	// For creating the button
	private JButton cancelButton = new JButton( "Cancel" );

	// For creating an object
	private Books book;
	private Members member;
	private Borrow borrow;

	// For checking the information from the text field
	public boolean isCorrect (){

		data = new String[2];

		for ( int i = 0 ; i < informationLabel.length ; i++ ){

			if ( !informationTextField[i].getText().equals( "" ) ){

				data[i] = informationTextField[i].getText();

			}else{

				return false;

			}
		}
		return true;
	}

	// For setting the array of JTextField to null
	public void clearTextField (){

		for ( int i = 0 ; i < informationTextField.length ; i++ ){

			if ( i != 2 ){

				informationTextField[i].setText( null );

			}else{
				// No action
			}
			txtFinePerDay.setText( null );
			txtTotalFineAmt.setText( null );
		}
	}

	void addStringsAndSettingFonts(){
	
		/***********************************************************************
		 * For adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
	
		for ( int i = 0 ; i < informationLabel.length ; i++ ){
	
			informationPanel.add( informationLabel[i] = new JLabel( informationString[i] ) );
			informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
			informationPanel.add( informationTextField[i] = new JTextField() );
			informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
	
		}
	
		informationPanel.add( lblFinePerDay );
		informationPanel.add( txtFinePerDay );
		informationPanel.add( lblTotalFineAmt );
		informationPanel.add( txtTotalFineAmt );
		txtTotalFineAmt.setEditable( false );
		txtFinePerDay.addKeyListener( new keyListener() );
		centerPanel.add( "Center" , informationPanel );
	
	}
	
	// Constructor of returnBooks
	public ReturnBooks (){

		// For setting the title for the internal frame
		super( "Return books" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("ReturnBooks was used.");

		// For setting the icon
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Import16.gif" ) ) );

		// For getting the graphical user interface components display area
		Container cp = getContentPane();

		// For setting the layout
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font
		title.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label
		northPanel.add( title );

		// For adding the north panel to the container
		cp.add( "North" , northPanel );

		// For setting the layout
		centerPanel.setLayout( new BorderLayout() );

		// For setting the layout for the internal panel
		informationPanel.setLayout( new GridLayout( 4 , 2 , 1 , 1 ) );

		addStringsAndSettingFonts();

		// For setting the layout
		returnButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button
		returnButtonPanel.add( returnButton );

		// For setting the font to the button
		returnButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For adding the internal panel to the panel
		centerPanel.add( "South" , returnButtonPanel );

		// For setting the border
		centerPanel.setBorder( BorderFactory.createTitledBorder( "Return a book:" ) );

		// For adding the center panel to the container
		cp.add( "Center" , centerPanel );

		// For setting the layout
		southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button
		southPanel.add( cancelButton );

		// For setting the font to the button
		cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For setting the border
		southPanel.setBorder( BorderFactory.createEtchedBorder() );

		// For adding the south panel to the container
		cp.add( "South" , southPanel );

		/***********************************************************************
		 * For adding the action listener to the button,first the text will be *
		 * taken from the JTextField and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/

		returnButton.addActionListener( this );

		// For adding the action listener for the button to dispose the frame
		cancelButton.addActionListener( this );

		// For setting the visible to true
		setVisible( true );

		// Show the internal frame
		pack();
	}

	public void actionPerformed ( ActionEvent ae ){

		if ( ae.getSource() == returnButton ){

			// For checking if there is a missing information
			if ( isCorrect() ){
				Thread runner = new Thread(){

					public void run (){

						book = new Books();
						member = new Members();
						borrow = new Borrow();
						book.connection( "SELECT * FROM Books WHERE BookID = " + data[0] );
						member.connection( "SELECT * FROM Members WHERE MemberID = " + data[1] );
						int numberOfAvailbleBooks = book.getNumberOfAvailbleBooks();
						int numberOfBorrowedBooks = book.getNumberOfBorrowedBooks() - 1;
						int numberOfBooks = member.getNumberOfBooks();

						// For checking if there is no same information in the
						// database
						if ( numberOfAvailbleBooks == 0 && numberOfBooks > 0 ){

							numberOfAvailbleBooks += 1;
							numberOfBooks -= 1;

							book.update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks + ",NumberOfBorrowedBooks ="
									+ numberOfBorrowedBooks + ",Availble = true WHERE BookID =" + data[0] );
							member.update( "UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1] );
							borrow.update( "DELETE FROM Borrow WHERE BookID =" + data[0] + " AND MemberID =" + data[1] );

							// For setting the array of JTextField to null
							JOptionPane.showMessageDialog( null , "The book is Successfully returned" , "Success" ,
									JOptionPane.INFORMATION_MESSAGE );
							clearTextField();

						}else if ( numberOfAvailbleBooks > 0 && numberOfBooks > 0 ){

							numberOfAvailbleBooks += 1;
							numberOfBooks -= 1;

							book.update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks + ",NumberOfBorrowedBooks ="
									+ numberOfBorrowedBooks + " WHERE BookID =" + data[0] );
							member.update( "UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1] );
							borrow.update( "DELETE FROM Borrow WHERE BookID =" + data[0] + " AND MemberID =" + data[1] );

							// For setting the array of JTextField to null
							JOptionPane.showMessageDialog( null , "The book is Successfully Returned" , "Success" ,
									JOptionPane.INFORMATION_MESSAGE );
							clearTextField();

						}else{
							JOptionPane.showMessageDialog( null , "The book is not borrowed" , "Warning" , JOptionPane.WARNING_MESSAGE );
						}
					}
				};
				runner.start();
			}
			// If there is a missing data, then display Message Dialog
			else{
				JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
			}
		}
		if ( ae.getSource() == cancelButton ){
			dispose();
		}else{
			// No action
		}
	}

	class keyListener extends KeyAdapter{

		public void keyPressed ( KeyEvent k ){

			java.sql.Date da = null;

			if ( k.getKeyCode() == KeyEvent.VK_ENTER ){

				try{

					int fineamt = Integer.parseInt( txtFinePerDay.getText() );
					Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
					Connection con = DriverManager.getConnection( "jdbc:odbc:JLibrary" );
					Statement st = con.createStatement();
					int bookid = Integer.parseInt( informationTextField[0].getText() );
					int memid = Integer.parseInt( informationTextField[1].getText() );

					try{

						String sql = "SELECT DayOfReturn from Borrow where MemberID=" + memid + " and BookID=" + bookid;
						ResultSet rs = st.executeQuery( sql );

						if ( rs.next() ){

							da = rs.getDate( 1 );
							java.util.Date today = new java.util.Date();
							/*
							 * java.util.Date retdate=new
							 * java.util.Date(da.getYear
							 * (),da.getMonth(),da.getDate());
							 * JOptionPane.showMessageDialog(null, "today=" +
							 * today + "\nRet date=" + retdate);
							 */

							System.out.println( today.after( da ) );

							if ( today.after( da ) ){

								long finedays = today.getTime() - da.getTime();
								final int MINUTES = 60;
								final int SECONDS = 60;
								final int HOURS = 24;
								int days = (int) ( finedays / ( 1000 * SECONDS * MINUTES * HOURS ) );
								System.out.println( days );
								txtTotalFineAmt.setText( String.valueOf( fineamt * days ) );

							}else{
								txtTotalFineAmt.setText( "0" );
							}
						}else{
							JOptionPane.showMessageDialog( null , "Member ID entered not found on databse" );
						}

					}catch ( Exception ex1 ){
						JOptionPane.showMessageDialog( null , "Error, Cannot retrieve date value from table" + ex1.toString() );
					}

				}catch ( Exception ex ){
					JOptionPane.showMessageDialog( null , "Error, cannot connect to database" + ex.toString() );
				}
			}
		}
	}// Inner class closed
}// Class closed
