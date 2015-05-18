// Import the packages for using the classes in them into the program.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A public class
 */
public class BorrowBooks extends JInternalFrame{

	// Log system from AddBooks Class
	private final static Logger LOGGER = Logger.getLogger( AddBooks.class.getName() );
			
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the label.
	private JLabel title = new JLabel( "BOOK INFORMATION" );

	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel informationPanel = new JPanel();

	// For creating an array of JLabel.
	private JLabel[] informationLabel = new JLabel[4];

	// For creating an array of String.
	private String[] informationString = { " Write the Book ID:" , " Write the Member ID:" , " The Current Data:" , " The Return Date:" };

	// For creating an array of JTextField.
	private JTextField[] informationTextField = new JTextField[4];

	// For creating the date in the String.
	private String date = new SimpleDateFormat( "dd-MM-yy" , Locale.getDefault() ).format( new java.util.Date() );

	// For creating an array of string to store the data.
	private String[] data;

	// For creating an Internal Panel in the center panel.
	private JPanel borrowButtonPanel = new JPanel();

	// For creating the button.
	private JButton borrowButton = new JButton( "Borrow" );

	// For creating South Panel.
	private JPanel southPanel = new JPanel();

	// For creating the button.
	private JButton cancelButton = new JButton( "Cancel" );

	// For creating an object.
	private Books book;

	private Members member;

	private Borrow borrow;

	// To check the information from the text field.
	public boolean isCorrect (){

		data = new String[4];
		for ( int i = 0 ; i < informationLabel.length ; i++ ){
			if ( !informationTextField[i].getText().equals( "" ) ){

				data[i] = informationTextField[i].getText();
				
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Is correct");

			}else{
				
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Is not correct");
				
				return false;
			}
		}
		return true;
	}

	// For setting the array of JTextField to null.
	public void clearTextField (){

		for ( int i = 0 ; i < informationTextField.length ; i++ )
			if ( i != 2 ){

				informationTextField[i].setText( null );

			}else{
				// No action
			}
	}
	
	void addingTheStringsToTheLabels(){
		
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		for ( int i = 0 ; i < informationLabel.length ; i++ ){
	
			informationPanel.add( informationLabel[i] = new JLabel( informationString[i] ) );
			informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
	
			if ( i == 2 ){
	
				informationPanel.add( informationTextField[i] = new JTextField( date ) );
				informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
				informationTextField[i].setEnabled( false );
	
			}else{
	
				informationPanel.add( informationTextField[i] = new JTextField() );
				informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
			}
		}
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("String added");
		
	}

	// Constructor of borrowBooks.
	public BorrowBooks (){

		// For setting the title for the internal frame.
		super( "Borrow Books" , false , true , false , true );

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Export16.gif" ) ) );

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For setting the layout.
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font.
		title.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label to the panel.
		northPanel.add( title );

		// For adding the panel to the container.
		cp.add( "North" , northPanel );

		// For setting the layout.
		centerPanel.setLayout( new BorderLayout() );

		// For setting the layout for the internal panel.
		informationPanel.setLayout( new GridLayout( 4 , 2 , 1 , 1 ) );

		addingTheStringsToTheLabels();
		
		centerPanel.add( "Center" , informationPanel );

		// For setting the layout.
		borrowButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For setting the font to the button.
		borrowButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For adding the button to the panel.
		borrowButtonPanel.add( borrowButton );

		// For adding the panel to the center panel.
		centerPanel.add( "South" , borrowButtonPanel );

		// For setting the border to the panel.
		centerPanel.setBorder( BorderFactory.createTitledBorder( "Borrow a book:" ) );

		// For adding the panel to the container.
		cp.add( "Center" , centerPanel );

		// For adding the layout.
		southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For setting the font to the button.
		cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For adding the button to the panel.
		southPanel.add( cancelButton );

		// For setting the border to the panel.
		southPanel.setBorder( BorderFactory.createEtchedBorder() );

		// For adding the panel to the container.
		cp.add( "South" , southPanel );

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/
		borrowButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isCorrect() ){
					Thread runner = new Thread(){

						public void run (){

							book = new Books();
							member = new Members();
							borrow = new Borrow();
							book.connection( "SELECT * FROM Books WHERE BookID = " + data[0] );
							member.connection( "SELECT * FROM Members WHERE MemberID = " + data[1] );

							int numberOfAvailbleBooks = book.getNumberOfAvailbleBooks();
							int numberOfBorrowedBooks = 1 + book.getNumberOfBorrowedBooks();
							int numberOfBooks = 1 + member.getNumberOfBooks();

							// For checking if there is no same information in.
							// The database.
							if ( numberOfAvailbleBooks == 1 ){

								numberOfAvailbleBooks -= 1;
								book.update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks
										+ ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + ",Availble = false WHERE BookID =" + data[0] );

								member.update( "UPDATE Members SET NumberOfBooks = " + numberOfBooks + " WHERE MemberID = " + data[1] );

								borrow.update( "INSERT INTO Borrow (BookID, MemberID, DayOfBorrowed, DayOfReturn) VALUES (" + data[0] + ","
										+ data[1] + ",'" + data[2] + "','" + data[3] + "')" );
								
								LOGGER.setLevel( Level.INFO );
								LOGGER.info("Informations updated");

								// For setting the array of JTextField to null
								clearTextField();

							}else if ( numberOfAvailbleBooks > 1 ){

								numberOfAvailbleBooks -= 1;
								book.update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks
										+ ",NumberOfBorrowedBooks =" + numberOfBorrowedBooks + " WHERE BookID =" + data[0] );

								member.update( "UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + data[1] );

								borrow.update( "INSERT INTO Borrow (BookID, MemberID, DayOfBorrowed, DayOfReturn) VALUES (" + data[0] + ","
										+ data[1] + ",'" + data[2] + "','" + data[3] + "')" );

								// For setting the array of JTextField to null.
								JOptionPane.showMessageDialog( null , "The book is Successfully borrowed" , "Success" ,
										JOptionPane.INFORMATION_MESSAGE );
								clearTextField();
								
								LOGGER.setLevel( Level.INFO );
								LOGGER.info("Informations updated");

							}else{
								JOptionPane
										.showMessageDialog( null , "The book is Not Available" , "Warning" , JOptionPane.WARNING_MESSAGE );
							}
						}
					};
					runner.start();
				}
				// If there is a missing data, then display Message Dialog.
				else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
				}
			}
		} );

		// For adding the action listener for the button to dispose the frame.
		cancelButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				dispose();
			}
		} );
		// For setting the visible to true.
		setVisible( true );
		// Show the internal frame.
		pack();
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Book Borrowed");
		
	}
}
